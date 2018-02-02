package cn.edu.buct.areatour.common.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/25
 *     desc   : ViewPager中最常用的Fragment的适配器
 *     version: 1.1
 * </pre>
 */

public class BaseFragmentViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;//碎片列表
    List<String> pageTitleList;//标题列表

    public BaseFragmentViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> pageTitleList) {
        super(fm);
        //赋值
        this.fragmentList = fragmentList;
        this.pageTitleList = pageTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        //获取当前ViewPager对应的Fragment
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        //获取ViewPager数目
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //获取当前位置的ViewPager对应的TabLayout的标题
        return pageTitleList.get(position);
    }


}
