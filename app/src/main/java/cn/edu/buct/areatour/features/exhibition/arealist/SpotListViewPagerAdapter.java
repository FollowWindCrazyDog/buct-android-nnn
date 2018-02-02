package cn.edu.buct.areatour.features.exhibition.arealist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/31
 *     desc   : 景区列表的ViewPager适配器
 *     version: 1.0
 * </pre>
 */
public class SpotListViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;
    List<String> pageTitleList;

    public SpotListViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> pageTitleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.pageTitleList = pageTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitleList.get(position);
    }
}
