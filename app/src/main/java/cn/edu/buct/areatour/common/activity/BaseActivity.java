package cn.edu.buct.areatour.common.activity;

import android.support.v7.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;

import cn.edu.buct.areatour.R;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/28
 *     desc   : 基于AppCompatActivity的类，新建活动建议继承这个类，以便实现一些更多的方法，如修改状态栏的颜色
 *     version: 1.0
 * </pre>
 */

public class BaseActivity extends AppCompatActivity {


    /**
     * 在onStart绘制状态栏，这个时候布局都已经赋值完毕
     */
    /*@Override
    protected void onStart() {
        super.onStart();
        setStatusBar();
    }*/

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.green_primary));
    }
}
