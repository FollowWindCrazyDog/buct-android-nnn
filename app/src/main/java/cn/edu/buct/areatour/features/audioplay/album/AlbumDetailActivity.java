package cn.edu.buct.areatour.features.audioplay.album;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.features.audioplay.AudioIntroViewPagerAdapter;

public class AlbumDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.blurredview)
    ImageView blurredview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        ButterKnife.bind(this);

        StatusBarUtil.setTranslucentForImageView(this, toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initTabLayout();
    }

    /**
     * 初始化TabLayout 数据
     */
    private void initTabLayout() {
        setViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    /**
     * 设置ViewPager
     */
    private void setViewPager(ViewPager viewPager) {

        //碎片列表
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new AlbumItemListFragment());
        fragmentList.add(new AlbumIntroFragment());

        //标题列表
        List<String> pageTitleList = new ArrayList<>();
        pageTitleList.add("列表");
        pageTitleList.add("简介");

        //新建适配器
        AudioIntroViewPagerAdapter adapter = new AudioIntroViewPagerAdapter(getSupportFragmentManager(), fragmentList, pageTitleList);

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
