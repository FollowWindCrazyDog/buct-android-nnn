package cn.edu.buct.areatour.features.usermodule.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.buct.areatour.R;

/**
 * <pre>
 *     author :
 *     e-mail :
 *     time   : 2017/11/19
 *     desc   : 用户资料卡——个人主页碎片
 *     version: 1.0
 * </pre>
 */
public class UserHomeFragment extends Fragment {


    public UserHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home, container, false);
    }

}
