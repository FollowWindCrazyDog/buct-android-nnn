package cn.edu.buct.areatour.features.exhibition.search.rank;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RankListViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;
    List<String> pageTitleList;

    public RankListViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> pageTitleList) {
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
