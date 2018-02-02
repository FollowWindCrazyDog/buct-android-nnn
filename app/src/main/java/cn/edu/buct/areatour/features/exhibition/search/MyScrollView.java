package cn.edu.buct.areatour.features.exhibition.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/27
 *     desc   : 自定义控件
 *     version: 1.0
 * </pre>
 */
public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean canScroll;

    public void setCanScroll(boolean isCan) {
        canScroll = isCan;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //LogUtils.d("scrollY:" + getScrollY() + "---height:" + getHeight());
        if (canScroll) {
            getParent().requestDisallowInterceptTouchEvent(true);

        }
        return super.dispatchTouchEvent(ev);
    }
}
