package cn.edu.buct.areatour.common.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorMatrixColorFilter;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.danikula.videocache.HttpProxyCacheServer;

import cn.edu.buct.areatour.common.BaseApplication;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/27
 *     desc   : 关于UI控件操作的一些工具方法，如果有需要，你可以在这里补充一些{静态方法}
 *     version: 1.0
 * </pre>
 */

public class UIUtils {

    /**
     * 获取全局Context，静态方法，你可以在任何位置调用该方法获取Context
     * @return
     */


    public static Context getContext() {
        return BaseApplication.getContext();
    }

    /**
     * 获取资源对象
     *
     * @return
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取资源文件字符串
     *
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取资源文件字符串数组
     *
     * @param resId
     * @return
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取资源文件颜色
     *
     * @param resId
     * @return
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     *
     *
     * @return
     */
    public static Thread getMainThread() {
        return BaseApplication.getMainThread();
    }

    public static Handler getMainHandler() {
        return BaseApplication.getMainHandler();
    }

    public static void post(Runnable task) {
        getMainHandler().post(task);
    }

    public static void postDelayed(Runnable task, long delayMillis) {
        getMainHandler().postDelayed(task, delayMillis);
    }

    public static void removeCallbacks(Runnable task) {
        getMainHandler().removeCallbacks(task);
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dip
     * @return
     */
    public static int dip2px(int dip) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dip * scale + .5f);
    }

    /**
     * 根据手机的分辨率从 px 的单位 转成为 dp(像素)
     *
     * @param px
     * @return
     */
    public static int dx2dip(int px) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (px / scale + .5f);
    }


    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }


    /**
     * 设置纯色图标的颜色
     *
     * @param icon 图标对象
     * @param r
     * @param g
     * @param b
     * @param a
     */
    public static void setIconColor(ImageView icon, int r, int g, int b, int a) {
        float[] colorMatrix = new float[]{
                0, 0, 0, 0, r,
                0, 0, 0, 0, g,
                0, 0, 0, 0, b,
                0, 0, 0, (float) a / 255, 0
        };
        icon.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    /**
     * 设置某个View的margin
     *
     * @param view   需要设置的view
     * @param isDp   需要设置的数值是否为DP
     * @param left   左边距
     * @param right  右边距
     * @param top    上边距
     * @param bottom 下边距
     * @return
     */
    public static ViewGroup.LayoutParams setViewMargin(View view, boolean isDp, int left, int right, int top, int bottom) {
        if (view == null) {
            return null;
        }

        int leftPx = left;
        int rightPx = right;
        int topPx = top;
        int bottomPx = bottom;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }
        //根据DP与PX转换计算值
        if (isDp) {
            leftPx = dip2px(left);
            rightPx = dip2px(right);
            topPx = dip2px(top);
            bottomPx = dip2px(bottom);
        }
        //设置margin
        marginParams.setMargins(leftPx, topPx, rightPx, bottomPx);
        view.setLayoutParams(marginParams);
        return marginParams;
    }
}
