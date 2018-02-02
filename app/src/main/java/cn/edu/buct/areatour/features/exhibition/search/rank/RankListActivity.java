package cn.edu.buct.areatour.features.exhibition.search.rank;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/09
 *     desc   : 全站景区排行榜
 *     version: 1.0
 * </pre>
 */
public class RankListActivity extends AppCompatActivity {

    @BindView(R.id.rank_list_toolbar)
    Toolbar rankListToolbar;
    @BindView(R.id.tl_rank_list_tab)
    TabLayout tlRankListTab;
    @BindView(R.id.rank_list_viewPager)
    ViewPager rankListViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_list);
        ButterKnife.bind(this);

        setSupportActionBar(rankListToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initTabLayout();
    }

    /**
     * 初始化TabLayout
     */
    private void initTabLayout() {
        setUpViewPager(rankListViewPager);
        tlRankListTab.setupWithViewPager(rankListViewPager);
        tlRankListTab.setTabMode(TabLayout.MODE_FIXED);
    }

    private void setUpViewPager(ViewPager viewPager) {

        //碎片列表
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new RankItemFragment());
        fragmentList.add(new RankItemFragment());
        fragmentList.add(new RankItemFragment());

        //标题列表
        List<String> pageTitleList = new ArrayList<>();
        pageTitleList.add("景区");
        pageTitleList.add("景点");
        pageTitleList.add("展览");

        //新建适配器
        RankListViewPagerAdapter adapter = new RankListViewPagerAdapter(getSupportFragmentManager(),fragmentList,pageTitleList);
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
