package cn.edu.buct.areatour.features.exhibition.search.searchbyimage;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.alibaba.fastjson.JSON;
import com.blankj.ALog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.activity.BaseActivity;
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/08
 *     desc   : 以图搜图返回结果显示的活动
 *     version: 1.0
 * </pre>
 */

public class SearchImageResultsActivity extends BaseActivity {

    @BindView(R.id.search_image_result_recycler_view)
    RecyclerView searchImageResultRecyclerView;
    @BindView(R.id.search_image_result_toolbar)
    Toolbar searchImageResultToolbar;

    private List<ImageSearchResultsModule> moduleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_image_results);
        ButterKnife.bind(this);

        setSupportActionBar(searchImageResultToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //获取传递的数据
        Bundle bundle = getIntent().getExtras();
        String result = bundle.getString("SEARCH_RESULTS");
        ALog.d(result);

        //解析数据
        SearchResultJson searchResultJson = JSON.parseObject(result, SearchResultJson.class);
        moduleList = searchResultJson.getResult();

        ALog.d(moduleList.toArray().toString());
        //设置数据到RecyclerView 布局中
        searchImageResultRecyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
        SearchByImageResultAdapter adapter = new SearchByImageResultAdapter(moduleList);
        searchImageResultRecyclerView.setAdapter(adapter);
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
