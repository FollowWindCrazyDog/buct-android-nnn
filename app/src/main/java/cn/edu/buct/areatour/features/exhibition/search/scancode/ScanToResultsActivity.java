package cn.edu.buct.areatour.features.exhibition.search.scancode;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/29
 *     desc   : 二维码扫描结果显示页面
 *     version: 1.0
 * </pre>
 */

public class ScanToResultsActivity extends AppCompatActivity {

    @BindView(R.id.tv_scan_results)
    TextView tvScanResults;
    @BindView(R.id.scan_result_toolbar)
    Toolbar scanResultToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_to_results);
        ButterKnife.bind(this);

        setSupportActionBar(scanResultToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /**

         /**
         * 对扫描活动返回的数据进行处理
         */
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
            String result = bundle.getString(CodeUtils.RESULT_STRING);
            tvScanResults.setText("解析结果:" + result);
        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
            tvScanResults.setText("解析二维码失败");
        }
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
