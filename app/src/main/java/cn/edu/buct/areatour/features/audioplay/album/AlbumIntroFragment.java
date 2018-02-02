package cn.edu.buct.areatour.features.audioplay.album;


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
import cn.edu.buct.areatour.features.audioplay.RelatedAlbumBean;
import cn.edu.buct.areatour.features.audioplay.RelatedAlbumRecyclerViewApdater;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumIntroFragment extends Fragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;

    public AlbumIntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album_intro, container, false);
        unbinder = ButterKnife.bind(this, view);

        //设置相关专辑推荐的RecyclerView数据
        List<RelatedAlbumBean> moduleList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            moduleList.add(new RelatedAlbumBean(1, "", ",", "", 0));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
        RelatedAlbumRecyclerViewApdater adapter = new RelatedAlbumRecyclerViewApdater(moduleList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
