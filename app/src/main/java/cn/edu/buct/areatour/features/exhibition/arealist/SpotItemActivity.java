package cn.edu.buct.areatour.features.exhibition.arealist;
/**
 * <pre>
 *     author : hewro 王红雁
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/25
 *     desc   : 景区单项详情页面
 *     version: 1.0
 * </pre>
 */

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaeger.library.StatusBarUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.Entities.Expobject;
import cn.edu.buct.areatour.Entities.Expobjimage;
import cn.edu.buct.areatour.Entities.Museum;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.Service.MuseumInterface;
import cn.edu.buct.areatour.common.activity.BaseActivity;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.edu.buct.areatour.features.exhibition.arealist.areardetail.SpotItemCommentFragment;
import cn.edu.buct.areatour.features.exhibition.arealist.areardetail.SpotItemIntroFragment;
import cn.edu.buct.areatour.features.exhibition.arealist.areardetail.SpotItemSpeechFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class SpotItemActivity extends BaseActivity {


    public static final String SPOT_NAME = "spot_name";
    public static final String SPOT_IMAGE_ID = "spot_image_id";
    public static final String SPOT_CONTENT_TEXT = "spot_content_text";

    @BindView(R.id.spotItem_image_view)
    ImageView spotItemImageView;

    @BindView(R.id.tv_spot_item_title)
    TextView tvSpotItemTitle;
    @BindView(R.id.tv_spot_item_english_title)
    TextView tvSpotItemEnglishTitle;
    @BindView(R.id.tv_spot_item_visitors_num)
    TextView tvSpotItemVisitorsNum;
    @BindView(R.id.tv_spot_item_distance)
    TextView tvSpotItemDistance;
    @BindView(R.id.tl_spot_item_tab)
    TabLayout tlSpotItemTab;
    @BindView(R.id.spot_item_tab_viewpager)
    ViewPager spotItemTabViewpager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar_spot_item)
    AppBarLayout appbarSpotItem;
    @BindView(R.id.view_need_offset)
    View viewNeedOffset;
    @BindView(R.id.bt_heart)
    FloatingActionButton btHeart;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    Expobject expobject;
    private String expobjInfo;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_item);
        setSmallImageSize();
        ButterKnife.bind(this);

        StatusBarUtil.setTranslucentForImageView(this, toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle("景点详情");


        /**
         * 从上一个活动{景区列表}中获取传递过来的数据
         */



        List<Expobjimage> expobjimages = new ArrayList<>();
        Intent intent = getIntent();
        String expobjid = intent.getStringExtra("ExpobjId");
        expobjInfo = intent.getStringExtra("ExpobjInfo");
        Log.i("SpoItemActivity-->", expobjid+expobjInfo);
        try {
            ObjectMapper mapper = new ObjectMapper();
            expobject = mapper.readValue(expobjInfo, Expobject.class);
            String spotItemName = expobject.getExpobjname();
            expobjimages = expobject.getExpobjimage();
            String spotItemContent = intent.getStringExtra(expobject.getExpobjintroduction());
            tvSpotItemTitle.setText(expobject.getExpobjname());
            tvSpotItemVisitorsNum.setText(expobject.getExpobjvisitcount()+"人去过");
            tvSpotItemDistance.setText("地址："+expobject.getExpobjaddress());
        }catch (IOException e){
            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.106.122.103:8080/TourServer/")
                .addConverterFactory(JacksonConverterFactory.create())//retrofit已经把Json解析封装在内部了 你需要传入你想要的解析工具就行了 默认支持Gson解析
                .build();

        // 创建 网络请求接口 的实例
        MuseumInterface featurerequest = retrofit.create(MuseumInterface.class);
//                Map<String, String> paramsMap = new HashMap<>();
//                paramsMap.put("expobjid", expobjid);

        // 对 发送请求 进行封装(设置内容)
//            Call<String> call = loginrequest.login( userName.getText().toString(),  password.getText().toString());
        Call<Museum> call = featurerequest.showMuseumRelateInfo(expobjid);
        call.enqueue(new Callback<Museum>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Museum> call, Response<Museum> response) {
                // 请求处理,输出结果
                // 输出翻译的内容
                // System.out.println("翻译是："+ response.body().getTranslateResult().get(0).get(0).getTgt());
                Log.i("response---->", "有响应");
                if(response.isSuccessful()) {

                    //初始化景区ArrayList
//                            Expobject exp=response.body();

                    Museum museum = response.body();
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        String mum = objectMapper.writeValueAsString(museum);
                        Log.i("museum--->", mum);
                    }catch (JsonProcessingException e){
                        e.printStackTrace();
                    }

                    expobject.setMuseum(museum);

                    try{
                                /*ObjectMapper expmapper = new ObjectMapper();
                                Expobject exp = expmapper.readValue(str, Expobject.class);
                                exp.setMuseum(museum);*/
                        ObjectMapper mapper = new ObjectMapper();
                        expobjInfo = mapper.writeValueAsString(expobject);
                    }catch(IOException e2){
                        e2.printStackTrace();
                    }
                    handler.sendEmptyMessage(1);

                    /*Intent intent = new Intent(UIUtils.getContext(),SpotItemActivity.class);
                    intent.putExtra("ExpobjInfo", exp);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    UIUtils.getContext().startActivity(intent);*/
                }
            }

            //请求失败时回调，可能网络异常，也可能Gson解析数据转换出错
            @Override
            public void onFailure(Call<Museum> call, Throwable throwable) {
                throwable.printStackTrace();
                // Toast.makeText(SpotListContentFragment.this, "网络异常，请检查网络", Toast.LENGTH_SHORT ).show();
            }
        });

        ImageView SpotItemImageView = (ImageView) findViewById(R.id.spotItem_image_view);
        String url="http://39.106.122.103:8080/documents/image/";
        Glide.with(this).load(url+expobjimages.get(0).getExpobjimagename()).into(SpotItemImageView);
       /* int spotItemImageId = intent.getIntExtra(SPOT_IMAGE_ID, 0);
        String spotItemContent = intent.getStringExtra(SPOT_CONTENT_TEXT);
        ImageView SpotItemImageView = (ImageView) findViewById(R.id.spotItem_image_view);
        Glide.with(this).load(spotItemImageId).into(SpotItemImageView);*/

        btHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "您已点赞", Snackbar.LENGTH_SHORT).setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(UIUtils.getContext(), "取消点赞成功", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        //设置过渡动画
        ViewCompat.setTransitionName(spotItemImageView, "transitionPic");

//        initSpotItemLayout(expobjInfo);

    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //执行逻辑
            if(msg.what == 1){
                initSpotItemLayout(expobjInfo);
            }
        }
    };

    /**
     * 修复TextView 左侧放置图片，图片大小不可控问题
     */
    private void setSmallImageSize() {

        TextView editText1 = (TextView)findViewById(R.id.tv_spot_item_visitors_num);
        Drawable drawable1 = getResources().getDrawable(R.drawable.ic_directions_walk_black_24dp);
        drawable1.setBounds(0, 0, 50, 50);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        editText1.setCompoundDrawables(drawable1, null, null, null);//只放左边

        Log.d("testtt", "width = " + drawable1.getMinimumWidth());

        TextView editText2 = (TextView)findViewById(R.id.tv_spot_item_distance);
        Drawable drawable2 = getResources().getDrawable(R.drawable.ic_pin_drop_black_24dp);
        drawable2.setBounds(0, 0, 50, 50);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        editText2.setCompoundDrawables(drawable2, null, null, null);//只放左边


    }

    /**
     * 初始化景区详情页面的TabLayout
     */
    private void initSpotItemLayout(String expobjInfo) {

        setupViewPager(spotItemTabViewpager, expobjInfo);

        //绑定ViewPager 与 TabLayout
        tlSpotItemTab.setupWithViewPager(spotItemTabViewpager);
        //设置TabLayout模式
        tlSpotItemTab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    /**
     * 初始化ViewPager
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager, String expobjInfo) {


        //碎片列表
        List<Fragment> fragmentList = new ArrayList<>();
        SpotItemIntroFragment spotItemIntroFragment =new SpotItemIntroFragment();
        SpotItemSpeechFragment spotItemSpeechFragment = new SpotItemSpeechFragment();
        SpotItemCommentFragment spotItemCommentFragment = new SpotItemCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ExpobjInfo", expobjInfo);
        spotItemIntroFragment.setArguments(bundle);
        spotItemSpeechFragment.setArguments(bundle);
        spotItemCommentFragment.setArguments(bundle);
        fragmentList.add(spotItemIntroFragment);
        fragmentList.add(spotItemSpeechFragment);
        fragmentList.add(spotItemCommentFragment);



        //标题列表
        ArrayList<String> tabTitles = new ArrayList<>();
        tabTitles.add("简介");
        tabTitles.add("解说");
        tabTitles.add("评论");

        //新建适配器
        SpotListViewPagerAdapter adapter = new SpotListViewPagerAdapter(getSupportFragmentManager(), fragmentList, tabTitles);

        //设置ViewPager
        viewPager.setAdapter(adapter);
    }

    /**
     * 点击toolbar上的按钮事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
