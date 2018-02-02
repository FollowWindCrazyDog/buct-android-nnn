package cn.edu.buct.areatour.features.mapguide;
/**
 * <pre>
 *     author : iSharpener 李炳
 *     e-mail : 1437138419@qq.com
 *     time   : 2017/11/1
 *     desc   : 地图绘制类
 *     version: 1.0
 * </pre>
 */

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import java.util.ArrayList;
import java.util.List;

import cn.edu.buct.areatour.MainActivity;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.edu.buct.areatour.features.exhibition.arealist.SpotItemActivity;


public class MapGuideFragment extends Fragment {

    public class MyPoiOverlay extends PoiOverlay {
        public PoiSearch x = PoiSearch.newInstance();
        public MyPoiOverlay(BaiduMap arg0,PoiSearch x) {
            super(arg0);
            this.x = x;
        }
        @Override
        public boolean onPoiClick (int arg0) {
            super.onPoiClick(arg0);
            PoiInfo poiInfo = getPoiResult().getAllPoi().get(arg0);
            // 检索poi详细信息
            x.searchPoiDetail(new PoiDetailSearchOption()
                    .poiUid(poiInfo.uid));
            return true;
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbar();
    }
    private float mLastX;        //获取传感器监听器的角度
    private List<MarkerInfoUtil> infos; //存储所有Marker的信息
    private MapStatus mMapStatus;//地图当前状态
    public BDLocation currentLocation;  //地图当前位置信息
    private TextureMapView mMapView = null;
    private BaiduMap mBaiduMap;
    private PoiResult poiresult = null;
    private MapStatusUpdate mMapStatusUpdate;
    private MyOrientationListener myOrientationListener;    //定义方向传感器
    private MyLocationConfiguration configuration;
    private LocationClient mLocationClient = null;       //初始化LocationClient类
    private BDLocationListener myListener = new MyLocationListener();        //定位请求回调接口
    private InfoWindow infoWindow;     //创建info窗口
    private InfoWindow.OnInfoWindowClickListener listener;       //设置infoWindow的监听器
    private View view3;
    private MyPoiOverlay poiOverlay;
    private int totalPage;
    private boolean ClickSearch = false;
    private String currentQuery ="";
    private String mLastQuery;
    private boolean HasMark = false;
   // public PoiSearch poiSearch = PoiSearch.newInstance();
    public MarkerInfoUtil mark;
    public int UPDATE;
    @SuppressLint("HandlerLeak")
    private Handler handler= new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                    mBaiduMap.showInfoWindow(infoWindow);//显示此infoWindow
                    break;
                default:
                    break;
            }
        }
    };
    public MapGuideFragment() {
    }
    /**
     * 绘制所有的Marker
     */
    private void setMarkerInfo() {
        infos = new ArrayList<>();
        MarkerInfoUtil marker = new MarkerInfoUtil(40.258099,116.152254,"北京化工大学昌平校区", R.drawable.ic_gg,"北京化工大学，一所211大学");
        MarkerInfoUtil marker1 = new MarkerInfoUtil(39.9201090000,116.4037050000,"故宫午门",R.drawable.ic_poi,"午门（Meridian Gate）是紫禁城的正门，位于紫禁城南北轴线，通高37.95米，始建于明朝永乐十八年（1420年），清朝顺治四年（1647年）重修，清朝嘉庆六年（1801年）再修。");
        infos.add(marker);
        infos.add(marker1);
        draw_marker(marker1);
        draw_marker(marker);
    }
    /**
     * 绘制图层
     */
    public void draw_marker(final MarkerInfoUtil marker){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MarkerOptions markerOptions = new MarkerOptions();//参数设置类
                BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(marker.getImgId());
                LatLng latLng = new LatLng(marker.getLatitude(),marker.getLongitude());
                markerOptions.position(latLng);//marker坐标位置
                markerOptions.icon(bitmap);//marker图标，可以自定义
                markerOptions.draggable(false);//是否可拖拽，默认不可拖拽
                markerOptions.anchor(0.5f, 1.0f);//设置 marker覆盖物与位置点的位置关系，默认（0.5f, 1.0f）水平居中，垂直下对齐
                markerOptions.alpha(0.8f);//marker图标透明度，0~1.0，默认为1.0
                markerOptions.animateType(MarkerOptions.MarkerAnimateType.jump);//marker出现的方式，从天上掉下
                markerOptions.flat(false);//marker突变是否平贴地面
                markerOptions.zIndex(1);//index
                OverlayOptions option =  markerOptions
                        .position(latLng)
                        .icon(bitmap);
                mBaiduMap.addOverlay(option);
            }
        }).start();

    }
    /**
     * 设置方向传感器监听，返回数据mLastX
     */
    private void initMyLoc() {
        //方向传感器监听
        myOrientationListener = new MyOrientationListener(getContext());
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                //将获取的x轴方向赋值给全局变量
                mLastX = x;
            }
        });
    }
    /**
     * 城市内搜索
     */
//    private void citySearch(int page) {
//        // 设置检索参数
//        PoiCitySearchOption citySearchOption = new PoiCitySearchOption();
//        citySearchOption.city("北京");// 城市
//        citySearchOption.keyword("学校");// 关键字
//        citySearchOption.pageCapacity(15);// 默认每页10条
//        citySearchOption.pageNum(page);// 分页编号
//        // 发起检索请求
//        poiSearch.searchInCity(citySearchOption);
//    }
//
//    /**
//     * 范围检索
//     */
//    private void boundSearch(int page) {
//        PoiBoundSearchOption boundSearchOption = new PoiBoundSearchOption();
//        LatLng southwest = new LatLng(currentLocation.getLatitude() - 0.01, currentLocation.getLongitude() - 0.012);// 西南
//        LatLng northeast = new LatLng(currentLocation.getLatitude() + 0.01, currentLocation.getLongitude() + 0.012);// 东北
//        LatLngBounds bounds = new LatLngBounds.Builder().include(southwest)
//                .include(northeast).build();// 得到一个地理范围对象
//        boundSearchOption.bound(bounds);// 设置poi检索范围
//        boundSearchOption.keyword("学校");// 检索关键字
//        boundSearchOption.pageNum(page);
//        poiSearch.searchInBound(boundSearchOption);// 发起poi范围检索请求
//    }
//
    /**
     * 附近检索
     */
//    private void nearbySearch(int page,PoiSearch m) {
//        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption();
//        nearbySearchOption.location(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));
//        nearbySearchOption.keyword("学校");
//        nearbySearchOption.radius(1000);// 检索半径，单位是米
//        nearbySearchOption.pageNum(page);
//        m.searchNearby(nearbySearchOption);// 发起附近检索请求
//    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final List<String> permissionList = new ArrayList<>();
        SDKInitializer.initialize(getActivity().getApplicationContext());
        final PoiSearch poiSearch = PoiSearch.newInstance();

        View view = inflater.inflate(R.layout.fragment_map_guide, container, false);

        final FloatingSearchView mSearchView = view.findViewById(R.id.floating_search_view);
        final FloatingActionButton locationmode = view.findViewById(R.id.dw_bt2);
        final FloatingActionButton loc = view.findViewById(R.id.dw_bt1);

        View view2 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.marker_click,null);


        TextView todetail = view2.findViewById(R.id.tv_navigation_LL);
        todetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UIUtils.getContext(), SpotItemActivity.class);
                UIUtils.getContext().startActivity(intent);
            }
        });

        listener = new InfoWindow.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick() {
                Intent intent =new Intent(getActivity(), SpotItemActivity.class);//启动

                intent.putExtra(SpotItemActivity.SPOT_NAME,mark.getName());
                intent.putExtra(SpotItemActivity.SPOT_IMAGE_ID,R.drawable.gugong);
                intent.putExtra(SpotItemActivity.SPOT_CONTENT_TEXT,mark.getDescription());
                UIUtils.getContext().startActivity(intent);
            }
        };

      //  View to
        new Thread(new Runnable() {
            @Override
            public void run() {
                //搜索栏获得和取消焦点
                mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
                    @Override
                    public void onFocus() {
                        if(!currentQuery.equals("")) {
                            mSearchView.setSearchBarTitle(mLastQuery);
                            PoiCitySearchOption citySearchOption = new PoiCitySearchOption();
                            citySearchOption.city("北京");// 城市
                            citySearchOption.keyword(currentQuery);// 关键字
                            citySearchOption.pageCapacity(10);// 默认每页10条
                            citySearchOption.pageNum(1);// 分页编号
                            // 发起检索请求
                            poiSearch.searchInCity(citySearchOption);
                        }
                    }
                    @Override
                    public void onFocusCleared() {
                        mSearchView.setSearchBarTitle("");
                        mLastQuery = currentQuery;
                        mSearchView.clearSuggestions();
                        //你也可以将已经打上的搜索字符保存，以致在下一次点击的时候，搜索栏内还保存着之前输入的字符
                        //mSearchView.setSearchText(searchSuggestion.getBody());

                    }
                });

                //搜索栏内容改变监听器
                mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
                    @Override
                    public void onSearchTextChanged(String oldQuery, final String newQuery) {
                        currentQuery = newQuery;
                        if (!oldQuery.equals("")&&newQuery.equals("")) {
                            mSearchView.clearSuggestions();
                        } else {
                            if(!newQuery.equals("")) {
                                ClickSearch = false;
                                PoiCitySearchOption citySearchOption = new PoiCitySearchOption();
                                citySearchOption.city("北京");// 城市
                                citySearchOption.keyword(newQuery);// 关键字
                                citySearchOption.pageCapacity(10);// 默认每页10条
                                citySearchOption.pageNum(1);// 分页编号
                                // 发起检索请求
                                poiSearch.searchInCity(citySearchOption);
                            }
                        }
                    }
                });
                //floatingSearchView 搜索键点击事件
                mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
                    @Override
                    public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                        // Log.d(TAG,searchSuggestion.getBody());
                    }
                    @Override
                    public void onSearchAction(String query) {
                        if(!query.equals("")) {
                            ClickSearch = true;
                            PoiCitySearchOption citySearchOption = new PoiCitySearchOption();
                            citySearchOption.city("北京");// 城市
                            citySearchOption.keyword(query);// 关键字
                            citySearchOption.pageCapacity(10);// 默认每页10条
                            citySearchOption.pageNum(1);// 分页编号
                            // 发起检索请求
                            poiSearch.searchInCity(citySearchOption);
                        }
                    }
                });
                //POI检索监听器
                poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
                    @Override
                    public void onGetPoiResult(PoiResult poiResult) {
                        if(poiResult == null||poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND){
                            poiresult = poiResult;
                            //没有找到检索结果
                            Toast.makeText(getActivity(),"未找到结果",Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(poiResult.error == SearchResult.ERRORNO.NO_ERROR){
                            poiresult = poiResult;
                            List<PoiInfo> addrlist = poiresult.getAllPoi();
                            List<poiMessage> meslist = new ArrayList<>();
                            for(PoiInfo item : addrlist) {
                                meslist.add(new poiMessage(item.name));
                            }
                            if(ClickSearch) {
                                mBaiduMap.clear();
                                setMarkerInfo();
                                poiOverlay = new MyPoiOverlay(mBaiduMap, poiSearch);
                                poiOverlay.setData(poiResult);// 设置POI数据
                                mBaiduMap.setOnMarkerClickListener(poiOverlay);
                                poiOverlay.addToMap();// 将所有的overlay添加到地图上
                                poiOverlay.zoomToSpan();
                                totalPage = poiResult.getTotalPageNum();// 获取总分页数
                                Toast.makeText(
                                        getActivity(),
                                        "总共查到" + poiResult.getTotalPoiNum() + "个兴趣点, 分为"
                                                + totalPage + "页", Toast.LENGTH_SHORT).show();
                                ClickSearch = false;
                                HasMark = true;
                            }
                            if(ClickSearch != true&&!HasMark)
                                mSearchView.swapSuggestions(meslist);
                        }
                    }
                    @Override
                    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                        if (poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR) {
                            Toast.makeText(getActivity(), "抱歉，未找到结果",
                                    Toast.LENGTH_SHORT).show();
                        } else {// 正常返回结果的时候，此处可以获得很多相关信息
                            Toast.makeText(
                                    getActivity(),
                                    poiDetailResult.getName() + ": "
                                            + poiDetailResult.getAddress(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

                    }
                });
                //点击定位按钮重新定位
                loc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        //获取经纬度
                        mSearchView.clearSuggestions();
                        LatLng ll = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                        MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                        //设置动画到中间
                        mBaiduMap.setMapStatus(status);
                        status = MapStatusUpdateFactory.zoomTo(16f);
                        mBaiduMap.animateMapStatus(status);
                        mBaiduMap.hideInfoWindow();
                        String addr = currentLocation.getLocationDescribe();
                        Toast.makeText(getActivity(),addr,Toast.LENGTH_SHORT).show();

                    }
                });
                locationmode.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        mSearchView.clearSuggestions();
                        if(mBaiduMap.getLocationConfiguration().locationMode.toString()=="NORMAL"){
                            FloatingActionButton button_mode = getActivity().findViewById(R.id.dw_bt2);
                            button_mode.setImageResource(R.drawable.ic_mode);
                            button_mode.setColorFilter(Color.BLACK);
                            configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS,true, null);
                            mBaiduMap.setMyLocationConfigeration(configuration);
                            LatLng ll = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                            //设置动画到中间
                            mBaiduMap.setMapStatus(status);
                            status = MapStatusUpdateFactory.zoomTo(16f);
                            mBaiduMap.animateMapStatus(status);
                            mBaiduMap.setCompassEnable(false);
                            mBaiduMap.hideInfoWindow();
                            Toast.makeText(getActivity(),"定位锁定",Toast.LENGTH_SHORT).show();
                        }else if(mBaiduMap.getLocationConfiguration().locationMode.toString()=="COMPASS"){
                            FloatingActionButton button_mode = getActivity().findViewById(R.id.dw_bt2);
                            button_mode.setImageResource(R.drawable.ic_compass);
                            button_mode.setColorFilter(Color.BLUE);
                            configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,true, null);
                            mBaiduMap.setMyLocationConfigeration(configuration);
                            LatLng ll = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                            //设置动画到中间
                            mBaiduMap.setMapStatus(status);
                            status = MapStatusUpdateFactory.zoomTo(16f);
                            mBaiduMap.animateMapStatus(status);
                            mMapStatus=new MapStatus.Builder().build();
                            mMapStatus=new MapStatus.Builder().overlook(0).build();
                            mMapStatusUpdate=MapStatusUpdateFactory.newMapStatus(mMapStatus);
                            mBaiduMap.setMapStatus(mMapStatusUpdate);
                            mBaiduMap.hideInfoWindow();
                            Toast.makeText(getActivity(),"自由拖动",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //地图点击事件
                mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                    @Override
                    public boolean onMapPoiClick(MapPoi arg0) {
                        return false;
                    }
                    @Override
                    public void onMapClick(LatLng arg0) {
                        mBaiduMap.hideInfoWindow();
                    }
                });
                mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        view3 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.marker_click,null);
                     //   ImageView iv_img = view3.findViewById(R.id.iv_img);
                        TextView tv_name = view3.findViewById(R.id.agent_name);
                        TextView tv_description = view3.findViewById(R.id.agent_addr);
                        //得到点击的覆盖物的经纬度
                        LatLng ll = marker.getPosition();
                        Point p = mBaiduMap.getProjection().toScreenLocation(ll);
                        p.y -= 90;
                        LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
                        //初始化infoWindow，最后那个参数表示显示的位置相对于覆盖物的竖直偏移量，这里也可以传入一个监听器
                        for(MarkerInfoUtil m : infos)
                        {
                            if(m.getLatitude() == ll.latitude&&m.getLongitude()==ll.longitude) {
                                mark = m;
                           //     iv_img.setBackgroundResource(m.getImgId());
                                tv_name.setText(m.getName());
                                tv_description.setText(m.getDescription());
                                BitmapDescriptor bitmapDescriptor1 = BitmapDescriptorFactory.fromView(view3);
                                mBaiduMap.hideInfoWindow();
                                infoWindow = new InfoWindow(bitmapDescriptor1, llInfo, -30,listener);
                                Message message = new Message();
                                message.what = 1;
                                handler.sendMessage(message);
                                //让地图以备点击的覆盖物为中心
                                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                                mBaiduMap.setMapStatus(status);
                            }
                        }
                        return true;
                    }
                });
            }
        }).start();
        //获取地图控件引用
        mMapView = view.findViewById(R.id.bmapView);
        //获取地图
        mBaiduMap = mMapView.getMap();
        //设置地图类型
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //定位初始化
        mLocationClient = new LocationClient(getActivity());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //开启定位
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(getActivity(), permissions, 1);
        } else {
            initMyLoc();
            initLocation();
            mLocationClient.start();
        }

        return view;
    }
    /**
     * 设置定位SDK的定位方式
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    /**
     * BDLocationListener为结果监听接口，异步获取定位结果
     */
    private class MyLocationListener implements BDLocationListener {
        public boolean isFirstLocation = true;
        @Override
        public void onReceiveLocation(BDLocation location) {
            currentLocation = location;
            //将获取的location信息给百度map
            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360，mLastX就是获取到的方向传感器传来的x轴数值
                    .direction(mLastX)
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(data);
            //LocationMode定位模式有三种：普通模式，跟随模式，罗盘模式，在这使用罗盘模式
            if(mBaiduMap.getLocationConfiguration().locationMode.toString()=="NORMAL") {
                configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
                mBaiduMap.setMyLocationConfigeration(configuration);
            }
           else if(mBaiduMap.getLocationConfiguration().locationMode.toString()=="COMPASS") {
                configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS, true, null);
                mBaiduMap.setMyLocationConfigeration(configuration);
            }
//            Log.i("定位模式",mBaiduMap.getLocationConfiguration().locationMode.toString());
            if (isFirstLocation) {
                //获取经纬度
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
              //设置动画到中间
                mBaiduMap.setMapStatus(status);
                status = MapStatusUpdateFactory.zoomTo(16f);
                mBaiduMap.animateMapStatus(status);
                String addr = location.getLocationDescribe();
                //hewro修改注释：将UIUtils.getContext()代替之前的getActivity()，避免因为活动还没有加载完全，执行这行代码会带来空指针的问题
                Toast.makeText(UIUtils.getContext(),addr,Toast.LENGTH_SHORT).show();
                setMarkerInfo();
                isFirstLocation = false;
            }
//            StringBuffer sb = new StringBuffer(256);
//            sb.append("time : ");
//            sb.append(location.getTime());
//            sb.append("\nerror code : ");
//            sb.append(location.getLocType());
//            sb.append("\nlatitude : ");
//            sb.append(location.getLatitude());
//            sb.append("\nlontitude : ");
//            sb.append(location.getLongitude());
//            sb.append("\nradius : ");
//            sb.append(location.getRadius());
//            if(location.getLocType()==BDLocation.TypeGpsLocation)
//
//            {// GPS定位结果
//                sb.append("\nspeed : ");
//                sb.append(location.getSpeed());// 单位：公里每小时
//                sb.append("\nsatellite : ");
//                sb.append(location.getSatelliteNumber());
//                sb.append("\nheight : ");
//                sb.append(location.getAltitude());// 单位：米
//                sb.append("\ndirection : ");
//                sb.append(location.getDirection());// 单位度
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                sb.append("\ndescribe : ");
//                sb.append("gps定位成功");
//
//            }
//
//            else if(location.getLocType()==BDLocation.TypeNetWorkLocation)
//
//            {// 网络定位结果
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                //运营商信息
//                sb.append("\noperationers : ");
//                sb.append(location.getOperators());
//                sb.append("\ndescribe : ");
//                sb.append("网络定位成功");
//            }
//
//            else if(location.getLocType()==BDLocation.TypeOffLineLocation)
//
//            {// 离线定位结果
//                sb.append("\ndescribe : ");
//                sb.append("离线定位成功，离线定位结果也是有效的");
//            }
//
//            else if(location.getLocType()==BDLocation.TypeServerError)
//
//            {
//                sb.append("\ndescribe : ");
//                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//            }
//
//            else if(location.getLocType()==BDLocation.TypeNetWorkException)
//
//            {
//                sb.append("\ndescribe : ");
//                sb.append("网络不同导致定位失败，请检查网络是否通畅");
//            }
//
//            else if(location.getLocType()==BDLocation.TypeCriteriaException)
//
//            {
//                sb.append("\ndescribe : ");
//                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//            }
//            sb.append("\nlocationdescribe : ");
//            sb.append(location.getLocationDescribe());// 位置语义化信息
//            List<Poi> list = location.getPoiList();// POI数据
//            if(list!=null)
//
//            {
//                sb.append("\npoilist size = : ");
//                sb.append(list.size());
//                for (Poi p : list) {
//                    sb.append("\npoi= : ");
//                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
//                }
//            }
//
//            Log.i("BaiduLocationApiDem",sb.toString());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mLocationClient.isStarted()) {
            //mLocationClient.start();
            //开启方向传感器
            //myOrientationListener.start();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        //mBaiduMap.setMyLocationEnabled(false);
        //myOrientationListener.stop();
        //mMapView.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();

    }
    @Override
    public void onPause(){
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();

    }
}

