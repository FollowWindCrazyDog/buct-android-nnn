package cn.edu.buct.areatour.features.exhibition.arealist;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.buct.areatour.Entities.Expobject;
import cn.edu.buct.areatour.MainActivity;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.Service.ExpobjectInterface;
import cn.edu.buct.areatour.features.exhibition.LocationModule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

//TODO：将会增加选项卡功能

public class SpotListFragment extends Fragment {


    @BindView(R.id.tl_spot_list_tab)
    TabLayout tlSpotListTab;
    Unbinder unbinder;
    @BindView(R.id.spot_list_toolbar)
    Toolbar spotListToolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<Expobject> expobjects;
    //景区测试arrayList3
    private List<LocationModule> spotList = new ArrayList<>();


    /**
     * 碎片的绘制布局时期
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spots_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        ((MainActivity) getActivity()).setSupportActionBar(spotListToolbar);
        setHasOptionsMenu(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.106.122.103:8080/TourServer/")
                .addConverterFactory(JacksonConverterFactory.create())//retrofit已经把Json解析封装在内部了 你需要传入你想要的解析工具就行了 默认支持Gson解析
                .build();

        // 创建 网络请求接口 的实例
        ExpobjectInterface featurerequest = retrofit.create(ExpobjectInterface.class);
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("expobjtype", "热门景区");

        // 对 发送请求 进行封装(设置内容)
//            Call<String> call = loginrequest.login( userName.getText().toString(),  password.getText().toString());
        Call<List<Expobject>> call = featurerequest.listExpobjects(paramsMap);
        call.enqueue(new Callback<List<Expobject>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<Expobject>> call, Response<List<Expobject>> response) {
                // 请求处理,输出结果
                // 输出翻译的内容
                // System.out.println("翻译是："+ response.body().getTranslateResult().get(0).get(0).getTgt());
                Log.i("response---->", "有响应");
                if(response.isSuccessful()) {
                    Log.i("response---->", "Success!!!");
                    //初始化景区ArrayList
                    expobjects=response.body();
                    if(!expobjects.isEmpty()){
                        handler.sendEmptyMessage(1);
                    }
                }
            }

            //请求失败时回调，可能网络异常，也可能Gson解析数据转换出错
            @Override
            public void onFailure(Call<List<Expobject>> call, Throwable throwable) {
                throwable.printStackTrace();
                // Toast.makeText(SpotListContentFragment.this, "网络异常，请检查网络", Toast.LENGTH_SHORT ).show();
            }
        });
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //执行逻辑
            if(msg.what == 1){
                String exp="";
                try{
                    ObjectMapper mapper = new ObjectMapper();
                    exp=mapper.writeValueAsString(expobjects);
                    System.out.println(exp);
                }catch(JsonProcessingException e){
                    e.printStackTrace();
                }
                initSpotTab(exp);
            }
        }
    };

    /**
     * 初始化TabLayout
     */
    private void initSpotTab(String exp) {

        setUpViewPager(viewPager, exp);
        tlSpotListTab.setupWithViewPager(viewPager);
        tlSpotListTab.setTabMode(TabLayout.MODE_FIXED);
    }

    /**
     * 设置ViewPager
     *
     * @param viewPager
     */
    private void setUpViewPager(ViewPager viewPager, String exp){
//        System.out.println(exp);
        List<Fragment> fragmentList=new ArrayList<Fragment>();
        SpotListContentFragment spotListContentFragment=new SpotListContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("exp", exp);
        spotListContentFragment.setArguments(bundle);
        fragmentList.add(spotListContentFragment);
        //标题列表
        List<String> pageTitleList = new ArrayList<>();
        pageTitleList.add("热门景区");
                        /*pageTitleList.add("离我最近");
                        pageTitleList.add("展览ing...");*/

        //新建适配器
        SpotListViewPagerAdapter adapter = new SpotListViewPagerAdapter(getChildFragmentManager(), fragmentList,pageTitleList);

        //设置ViewPager
        viewPager.setAdapter(adapter);

       /*fragmentList.add(new SpotListContentFragment());
       fragmentList.add(new SpotListContentFragment());*/


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 导入菜单栏布局
     *
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.spot_list_menu, menu);
    }
}
