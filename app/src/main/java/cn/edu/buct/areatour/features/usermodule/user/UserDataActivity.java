package cn.edu.buct.areatour.features.usermodule.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.edu.buct.areatour.features.audioplay.AudioIntroViewPagerAdapter;
import cn.edu.buct.areatour.features.usermodule.anchor.AnchorMainActivity;

/**
 * <pre>
 *     author :
 *     e-mail :
 *     time   : 2017/11/19
 *     desc   : 用户资料卡界面
 *     version: 1.0
 * </pre>
 */

public class UserDataActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.to_anchor)
    TextView toAnchor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("UserAccount",""+111);
        setContentView(R.layout.activity_user_data);
        ButterKnife.bind(this);

        StatusBarUtil.setTranslucentForImageView(this, toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("");

        //初始化用户界面的TabLayout
        initTabLayout();
        //跳转到主播页面
        toAnchor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToAnchor();
            }
        });

        /*
        * TODO
        * 添加背景逻辑
        * 添加头像逻辑
        * 添加用户名逻辑
        *
        * */

    }


    /**
     * 跳转到主播页面，如果用户是主播的话
     */
    private void setToAnchor(){
        Intent intent = new Intent(UIUtils.getContext(), AnchorMainActivity.class);
        UIUtils.getContext().startActivity(intent);
    }


    /**
     * 初始化TabLayout 数据
     */
    private void initTabLayout() {
        setViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    /**
     * 设置ViewPager
     */
    private void setViewPager(ViewPager viewPager) {

        //碎片列表
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new UserHomeFragment());
        fragmentList.add(new UserDynamicFragment());
        fragmentList.add(new UserDataFragment());

        //标题列表
        List<String> pageTitleList = new ArrayList<>();
        pageTitleList.add("主页");
        pageTitleList.add("动态");
        pageTitleList.add("资料");

        //新建适配器
        AudioIntroViewPagerAdapter adapter = new AudioIntroViewPagerAdapter(getSupportFragmentManager(), fragmentList, pageTitleList);

        //设置ViewPager
        viewPager.setAdapter(adapter);
    }
}
