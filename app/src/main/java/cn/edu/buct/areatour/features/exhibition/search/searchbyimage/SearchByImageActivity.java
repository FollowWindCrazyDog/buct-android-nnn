package cn.edu.buct.areatour.features.exhibition.search.searchbyimage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.activity.BaseActivity;
import cn.edu.buct.areatour.features.exhibition.arealist.SpotListViewPagerAdapter;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/07
 *     desc   : 以图搜图的主活动
 *     version: 1.0
 * </pre>
 */
public class SearchByImageActivity extends BaseActivity {

    @BindView(R.id.tl_search_by_image_type_tab)
    TabLayout tlSearchByImageTypeTab;
    @BindView(R.id.search_by_image_view_pager)
    ViewPager searchByImageViewPager;
    @BindView(R.id.search_by_image_tool_bar)
    Toolbar searchByImageToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_image);
        ButterKnife.bind(this);

        setSupportActionBar(searchByImageToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        actionBar.setTitle("以图搜图");
        initTabLayout();
    }


    /**
     * 初始化TabLayout信息：
     * 1. 初始化ViewPager
     * 2. 绑定TabLayout与数据源ViewPager
     * 3. 设置TabLayout的模式，是固定的还是可以滑动的的Tab
     */
    private void initTabLayout() {
        setViewPager(searchByImageViewPager);
        tlSearchByImageTypeTab.setupWithViewPager(searchByImageViewPager);
        tlSearchByImageTypeTab.setTabMode(TabLayout.MODE_FIXED);
    }

    /**
     * 设置ViewPage的数据
     * 1. 创建ViewPager的适配器的对象
     * 2. 绑定适配器到ViewPager上
     *
     * @param viewPager
     */
    private void setViewPager(ViewPager viewPager) {
        //碎片列表
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new SearchImageByImageFragment());
        fragmentList.add(new SearchImageByWordFragment());

        //标题列表
        List<String> pageTitleList = new ArrayList<>();
        pageTitleList.add("以图搜图");
        pageTitleList.add("关键字搜索");

        //新建适配器
        SpotListViewPagerAdapter adapter = new SpotListViewPagerAdapter(getSupportFragmentManager(), fragmentList, pageTitleList);

        //设置ViewPager
        viewPager.setAdapter(adapter);
    }

    /**
     * 引入顶部toolbar的菜单栏的布局
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);

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
