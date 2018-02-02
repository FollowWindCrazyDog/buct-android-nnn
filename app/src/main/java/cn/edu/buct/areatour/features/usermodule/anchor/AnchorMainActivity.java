package cn.edu.buct.areatour.features.usermodule.anchor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.edu.buct.areatour.features.audioplay.AudioIntroViewPagerAdapter;
import cn.edu.buct.areatour.features.audioplay.album.AlbumItemListFragment;
import cn.edu.buct.areatour.features.usermodule.setting.SettingActivity;
import cn.edu.buct.areatour.features.usermodule.user.UserDataActivity;
import cn.edu.buct.areatour.features.usermodule.user.UserDataFragment;

public class AnchorMainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.to_user)
    TextView toUser;
    @BindView(R.id.upload_voice)
    TextView uploadVoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anchor_main);
        ButterKnife.bind(this);

        StatusBarUtil.setTranslucentForImageView(this, toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle("");
        initTabLayout();


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
        fragmentList.add(new AlbumItemListFragment());
        fragmentList.add(new AlbumListFragment());
        fragmentList.add(new UserDataFragment());

        //标题列表
        List<String> pageTitleList = new ArrayList<>();
        pageTitleList.add("热门");
        pageTitleList.add("专辑");
        pageTitleList.add("主播信息");

        //新建适配器
        AudioIntroViewPagerAdapter adapter = new AudioIntroViewPagerAdapter(getSupportFragmentManager(), fragmentList, pageTitleList);

        //设置ViewPager
        viewPager.setAdapter(adapter);
    }

    @OnClick({R.id.to_user, R.id.upload_voice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.to_user:
                //启动返回到用户个人主页页面
                Intent intent = new Intent(UIUtils.getContext(), UserDataActivity.class);
                UIUtils.getContext().startActivity(intent);
                break;
            case R.id.upload_voice:
                //启动录制音频界面
                Intent intent2 = new Intent(UIUtils.getContext(), UploadVoiceActivity.class);
                UIUtils.getContext().startActivity(intent2);
                break;
        }
    }
}
