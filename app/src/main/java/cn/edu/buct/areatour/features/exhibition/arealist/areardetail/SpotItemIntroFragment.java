package cn.edu.buct.areatour.features.exhibition.arealist.areardetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.buct.areatour.Entities.Expobject;
import cn.edu.buct.areatour.R;


/**
 * Created by Elsa on 2017/11/1.
 */

public class SpotItemIntroFragment extends Fragment {

    @BindView(R.id.spot_item_intro_recyclerView)
    RecyclerView spotItemIntroRecyclerView;
    Unbinder unbinder;

    Expobject expobject;
    public SpotItemIntroFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spot_item_introduction, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        String expobjInfo = getArguments().getString("ExpobjInfo");
        SpotItemIntroAdapter spotItemIntroAdapter = new SpotItemIntroAdapter(expobjInfo);
        spotItemIntroRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        spotItemIntroRecyclerView.setAdapter(spotItemIntroAdapter);
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}