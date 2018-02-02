package cn.edu.buct.areatour.features.usermodule.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.edu.buct.areatour.features.exhibition.search.dynamic.DynamicContent;
import cn.edu.buct.areatour.features.exhibition.search.dynamic.DynamicContentRecyclerViewAdapter;

/**
 * <pre>
 *     author :
 *     e-mail :
 *     time   : 2017/11/19
 *     desc   : 用户资料卡——个人动态
 *     version: 1.0
 * </pre>
 */

public class UserDynamicFragment extends Fragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;

    public UserDynamicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_dynamic, container, false);
        unbinder = ButterKnife.bind(this, view);

        //设置RecyclerView的适配器
        List<DynamicContent> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(new DynamicContent());
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
        DynamicContentRecyclerViewAdapter adapter = new DynamicContentRecyclerViewAdapter(mList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
