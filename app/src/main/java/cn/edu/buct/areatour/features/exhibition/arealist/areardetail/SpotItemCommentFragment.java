package cn.edu.buct.areatour.features.exhibition.arealist.areardetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.buct.areatour.Entities.Comexpobj;
import cn.edu.buct.areatour.Entities.Expobject;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.module.CommentModule;

/**
 * Created by Elsa on 2017/11/1.
 */

public class SpotItemCommentFragment extends Fragment {
    @BindView(R.id.spot_item_comment_list_recycler_view)
    RecyclerView spotItemCommentListRecyclerView;
    Unbinder unbinder;

    public SpotItemCommentFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spot_item_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        String expobjInfo = getArguments().getString("ExpobjInfo");
        spotItemCommentListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        try {
            ObjectMapper mapper = new ObjectMapper();
            Expobject expobject = mapper.readValue(expobjInfo, Expobject.class);
            List<Comexpobj> comexpobjs = expobject.getComexpobj();
            ArrayList<CommentModule> commentList = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i =0;i<comexpobjs.size();i++){
                commentList.add(new CommentModule("guest",comexpobjs.get(i).getComexpobjcontent(),dateFormat.format(comexpobjs.get(i).getComexpobjtime()),"1"));
            }
            spotItemCommentListRecyclerView.setAdapter(new SpotItemCommentAdapter(commentList));
        }catch (IOException e){
            e.printStackTrace();
        }

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
