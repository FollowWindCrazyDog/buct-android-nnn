package cn.edu.buct.areatour.features.audioplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.activity.BaseActivity;
import cn.edu.buct.areatour.common.adapter.BaseFragmentViewPagerAdapter;
import cn.edu.buct.areatour.features.exhibition.arealist.SpotListContentFragment;

/**
 * <pre>
 *     author : hewro  曹原
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/12
 *     desc   : 音频列表页面，当第一次点击首页的按钮时候，会弹出这个推荐歌曲列表
 *     version: 1.0
 * </pre>
 */


public class AudioListActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.audio_list_tab)
    TabLayout audioListTab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("推荐音频");
        Intent getIntent = getIntent();
        //System.out.println(getIntent.getStringExtra("exp"));
        initTabLayout(getIntent.getStringExtra("exp"));
    }

    private void initTabLayout(String exp) {
        setUpViewPager(viewPager,exp);
        audioListTab.setupWithViewPager(viewPager);
        audioListTab.setTabMode(TabLayout.MODE_FIXED);

    }

    private void setUpViewPager(ViewPager viewPager,String exp) {
        //碎片列表
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        RecommendedAudioListFragment recommendedaudiolistfragment=new RecommendedAudioListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("exp", exp);
        recommendedaudiolistfragment.setArguments(bundle);
        fragmentList.add(recommendedaudiolistfragment);
        //fragmentList.add(new RecommendedAudioListFragment());

        //标题列表
        List<String> pageTitleList = new ArrayList<>();
        pageTitleList.add("景区");
       // pageTitleList.add("景点");

        //新建适配器
        BaseFragmentViewPagerAdapter adapter = new BaseFragmentViewPagerAdapter(getSupportFragmentManager(), fragmentList, pageTitleList);

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
