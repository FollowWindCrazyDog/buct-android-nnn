package cn.edu.buct.areatour;
/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/25
 *     desc   : 项目的入口，主活动
 *     version: 1.0
 * </pre>
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.Entities.Explanation;
import cn.edu.buct.areatour.Service.AudioPlayInterface;
import cn.edu.buct.areatour.common.activity.BaseActivity;
import cn.edu.buct.areatour.common.utils.BottomNavigationViewHelper;
import cn.edu.buct.areatour.common.utils.CheckPermissionUtils;
import cn.edu.buct.areatour.features.audioplay.AudioListActivity;
import cn.edu.buct.areatour.features.exhibition.arealist.SpotListFragment;
import cn.edu.buct.areatour.features.exhibition.search.SearchMainFragment;
import cn.edu.buct.areatour.features.mapguide.MapGuideFragment;
import cn.edu.buct.areatour.features.usermodule.UserModuleFragment;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.ivMoney)
    CircleImageView ivMoney;
    private BottomNavigationView bottomNavigationView;
    @BindView(R.id.baseToolbar)
    Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean lastClick = false;
    /**
     * 主活动创建的生命时期 函数onCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //TODO :头部顶栏对于每个碎片，可能需要重新定制。即每个碎片布局都要有自己的顶栏

        setSupportActionBar(toolbar);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        //底部导航栏按钮点击事件
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        initPermission();
        switchFragment(new MapGuideFragment());
        lastClick = true;
    }

    /**
     * 初始化权限事件
     */
    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(this);
        if (permissions.length == 0) {
            //权限都申请了
            //是否登录
        } else {
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }

    /**
     * 创建顶部菜单项
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    /**
     * 菜单按钮选择事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.top_search:
                SearchMainFragment searchMainFragment = new SearchMainFragment();
                //点击搜索按钮
                switchFragment(searchMainFragment);
                return true;
            case R.id.top_filter:
                //点击分类查看按钮
                Toast.makeText(MainActivity.this, "点击了分类查看按钮", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }


    /**
     * 启动碎片，这个是公用方法，点击底部导航栏切换到你的碎片，只需要传递你的碎片对象即可
     *
             * @param fragment
     */
    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //将碎片的布局替换到fl_main_body布局中
        transaction.replace(R.id.fl_main_body, fragment);
        transaction.commit();
    }

    /**
     * 底部导航按钮点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_maps:
                if(!lastClick) {
                    switchFragment(new MapGuideFragment());
                    lastClick = true;
                }
                //点击地图导览按钮
                break;
            case R.id.item_search:
                lastClick = false;
                //TODO:点击搜索按钮，不是跳转到碎片中，而是打开顶部搜索框
                SearchMainFragment searchMainFragment = new SearchMainFragment();
                switchFragment(searchMainFragment);
                break;
            case R.id.item_play:
                lastClick = false;
                //点击音频播放按钮
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://39.106.122.103:8080/TourServer/")
                        .addConverterFactory(JacksonConverterFactory.create())//retrofit已经把Json解析封装在内部了 你需要传入你想要的解析工具就行了 默认支持Gson解析
                        .build();

                // 创建 网络请求接口 的实例
                AudioPlayInterface audioplayrequest = retrofit.create(AudioPlayInterface.class);
                Map<String, String> audioplayMap = new HashMap<>();

                // 对 发送请求 进行封装(设置内容)
                Call<List<Explanation>> audioplaycall = audioplayrequest.ListExplanation(audioplayMap);
                audioplaycall.enqueue(new Callback<List<Explanation>>() {
                    //请求成功时回调
                    @Override
                    public void onResponse(Call<List<Explanation>> call, Response<List<Explanation>> response) {
                        // 请求处理,输出结果
                        // 输出翻译的内容
                        // System.out.println("翻译是："+ response.body().getTranslateResult().get(0).get(0).getTgt());
                        Log.i("response---->", "有响应");
                        if(response.isSuccessful()) {

                            //初始化景区ArrayList
                            List<Explanation> exp=response.body();
                            String str="";
                            try{
                                ObjectMapper mapper = new ObjectMapper();
                                str=mapper.writeValueAsString(exp);
                            }catch(JsonProcessingException e){
                                e.printStackTrace();
                            }

                            lastClick = false;
                            Intent intent = new Intent(MainActivity.this, AudioListActivity.class);
                            intent.putExtra("exp",str);
                           /* Bundle bundle = new Bundle();
                            bundle.putString("exp", str);*/
                            startActivity(intent);

                        }
                    }

                    //请求失败时回调，可能网络异常，也可能Gson解析数据转换出错
                    @Override
                    public void onFailure(Call<List<Explanation>> call, Throwable throwable) {
                        throwable.printStackTrace();
                        // Toast.makeText(SpotListContentFragment.this, "网络异常，请检查网络", Toast.LENGTH_SHORT ).show();
                    }
                });
                break;


            case R.id.item_attractions:
                //点击景区按钮
                //toolbar.setTitle(R.string.title_area_list_title);
                //碎片列表


               /* Retrofit retrofit = new Retrofit.Builder()
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
                            exp=response.body();
                            if(!exp.isEmpty()){
                                handler.sendEmptyMessage(1);
                            }
                            *//*String str="";
                            try{
                                ObjectMapper mapper = new ObjectMapper();
                                str=mapper.writeValueAsString(exp);
                                System.out.println(str);
                            }catch(JsonProcessingException e){
                                e.printStackTrace();
                            }

                            Bundle bundle = new Bundle();
                            bundle.putString("exp", str);
                            SpotListFragment spotListFragment=new SpotListFragment();
                            spotListFragment.setArguments(bundle);
                            lastClick = false;
                            switchFragment(spotListFragment);*//*

                        }
                    }

                    //请求失败时回调，可能网络异常，也可能Gson解析数据转换出错
                    @Override
                    public void onFailure(Call<List<Expobject>> call, Throwable throwable) {
                        throwable.printStackTrace();
                        // Toast.makeText(SpotListContentFragment.this, "网络异常，请检查网络", Toast.LENGTH_SHORT ).show();
                    }
                });*/
                SpotListFragment spotListFragment=new SpotListFragment();
                lastClick = false;
                switchFragment(spotListFragment);
                break;




            case R.id.item_user:
                //toolbar.setTitle(R.string.title_user);
                lastClick = false;
                switchFragment(new UserModuleFragment());
                break;
        }
        return true;
    }

    /**
     * 显示默认的BaseToolbar
     */
    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().show();
    }
}
