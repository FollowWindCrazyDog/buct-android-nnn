package cn.edu.buct.areatour.features.exhibition.search.rank;


import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/09
 *     desc   : 排行榜每一栏的碎片
 *     version: 1.0
 * </pre>
 */
public class RankItemFragment extends Fragment {


    @BindView(R.id.rank_list_exhibition_recycler_view)
    RecyclerView rankListExhibitionRecyclerView;
    Unbinder unbinder;

    public RankItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rank_item, container, false);
        unbinder = ButterKnife.bind(this, view);

        //设置RecyclerView的适配器
        rankListExhibitionRecyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
        List<RankItemModule>moduleList = new ArrayList<>();
        for (int i =0;i<10;i++){
            moduleList.add(new RankItemModule(1,"",""));
        }
        RankItemRecyclerViewAdapter adapter = new RankItemRecyclerViewAdapter(moduleList);
        rankListExhibitionRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
