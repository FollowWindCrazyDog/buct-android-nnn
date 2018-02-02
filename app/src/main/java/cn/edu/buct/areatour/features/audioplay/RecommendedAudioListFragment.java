package cn.edu.buct.areatour.features.audioplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.databind.JavaType;
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
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/13
 *     desc   : 音频列表页面的ViewPager对于的碎片
 *     version: 1.0
 * </pre>
 */

public class RecommendedAudioListFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.recommended_audio_recycler_view)
    RecyclerView recommendedAudioRecyclerView;

    public RecommendedAudioListFragment() {
        // Required empty public constructor
    }

    private String str="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommened_audio_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        str=getArguments().getString("exp");
        List<Explanation> eList=new ArrayList();
        try{
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Explanation.class);
            eList = (List<Explanation>)mapper.readValue(str, javaType);
        }catch(IOException e){
            e.printStackTrace();
        }

        List<AudioModule> audioList = new ArrayList<>();

        for (int i = 0; i < eList.size(); i++) {
            AudioModule audio1 = new AudioModule(eList.get(i).getExpid(),eList.get(i).getExpname(),eList.get(i).getExpcommentedcount(),eList.get(i).getExpuploadtime(),eList.get(i).getExpduration());
            audioList.add(audio1);
        }
        recommendedAudioRecyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
        AudioListRecyclerViewAdapter adapter = new AudioListRecyclerViewAdapter(audioList);
        recommendedAudioRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
