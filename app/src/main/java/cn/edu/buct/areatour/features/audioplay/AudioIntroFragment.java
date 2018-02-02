package cn.edu.buct.areatour.features.audioplay;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.andexert.library.RippleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.edu.buct.areatour.features.audioplay.album.AlbumDetailActivity;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/14
 *     desc   : 音频详情页面——简介部分碎片
 *     version: 1.0
 * </pre>
 */


public class AudioIntroFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.album_item)
    RelativeLayout albumItem;

    public AudioIntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio_intro, container, false);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        albumItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UIUtils.getContext(), AlbumDetailActivity.class);
                UIUtils.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
