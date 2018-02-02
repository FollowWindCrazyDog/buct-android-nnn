package cn.edu.buct.areatour.features.exhibition.arealist.areardetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.buct.areatour.Entities.Explanation;
import cn.edu.buct.areatour.Entities.Expobject;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.module.AudioModule;

/**
 * Created by Elsa on 2017/11/1.
 */

public class SpotItemSpeechFragment extends Fragment {
    @BindView(R.id.spot_item_speech_recyclerView)
    RecyclerView spotItemSpeechRecyclerView;
    Unbinder unbinder;


    public SpotItemSpeechFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spot_item_speech, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String expobjInfo = getArguments().getString("ExpobjInfo");
//        SpotItemSpeechAdapter spotItemSpeechAdapter = new SpotItemSpeechAdapter(expobjInfo);
        spotItemSpeechRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        try {
            ObjectMapper mapper = new ObjectMapper();
            Expobject expobject = mapper.readValue(expobjInfo, Expobject.class);
            List<Explanation> explanations = expobject.getExplanation();
            List<AudioModule> moduleList = new ArrayList<>();
            for (int i =0;i<explanations.size();i++){
                Log.i("----->", explanations.get(i).getExpname()+"AND"+explanations.get(i).getExpuploadtime()+"AND"+explanations.get(i).getExpduration());
                AudioModule audio = new AudioModule(explanations.get(i).getExpid(),explanations.get(i).getExpname(),explanations.get(i).getExpcommentedcount(),explanations.get(i).getExpuploadtime(),explanations.get(i).getExpduration());
                moduleList.add(audio);
            }
            spotItemSpeechRecyclerView.setAdapter(new SpotItemSpeechAdapter(moduleList));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
