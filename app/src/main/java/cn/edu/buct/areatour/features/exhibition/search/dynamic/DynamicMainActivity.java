package cn.edu.buct.areatour.features.exhibition.search.dynamic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;

public class DynamicMainActivity extends AppCompatActivity {

    @BindView(R.id.content_recycler_view)
    RecyclerView contentRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("最新动态");

        List<DynamicContent> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(new DynamicContent());
        }
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
        DynamicContentRecyclerViewAdapter adapter = new DynamicContentRecyclerViewAdapter(mList);
        contentRecyclerView.setAdapter(adapter);
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
