package cn.edu.buct.areatour.features.usermodule.anchor;


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
import cn.edu.buct.areatour.common.module.AudioModule;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.edu.buct.areatour.features.audioplay.AudioListRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumListFragment extends Fragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;

    public AlbumListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        //设置RecyclerView适配器
        List<AlbumBean> audioList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            AlbumBean albumBean = new AlbumBean();
            audioList.add(albumBean);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
        AlbumListRecyclerViewAdapter adapter = new AlbumListRecyclerViewAdapter(audioList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
