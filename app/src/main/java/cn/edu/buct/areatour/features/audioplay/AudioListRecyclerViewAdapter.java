package cn.edu.buct.areatour.features.audioplay;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.module.AudioModule;
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * Created by caoyuan on 2017/11/6.
 *
 */

public class AudioListRecyclerViewAdapter extends RecyclerView.Adapter<AudioListRecyclerViewAdapter.ViewHolder> {
    private List<AudioModule> mAudioList;


    public AudioListRecyclerViewAdapter(List<AudioModule>audioList){
        mAudioList=audioList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_audio_play,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){
        AudioModule audio=mAudioList.get(position);
        holder.audioName.setText(audio.getAudioName());
        holder.audioDate.setText(audio.getAudiodata());
        holder.audioTime.setText(audio.getAudiotime());
        holder.audioComment.setText(Integer.toString(audio.getCommentNum()));
        //holder.audioImage.setImageResource(audio.getCoverId());
        //holder.audioName.setText(audio.getAudioName());
        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击某一项，启动音频播放详情页面
                Intent intent = new Intent(UIUtils.getContext(),AudioDetailActivity.class);
                intent.putExtra("mAudioList", (Serializable) mAudioList);
                intent.putExtra("current",holder.getAdapterPosition());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          //      System.out.println(Integer.toString(holder.getAdapterPosition()));
                UIUtils.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount(){
        return mAudioList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView audioImage;
        TextView audioName;
        TextView audioDate;
        TextView audioTime;
        TextView audioComment;
        View listItem;


        public ViewHolder(View view){
            super(view);
            audioImage= view.findViewById(R.id.audio_avatar);
            audioName = view.findViewById(R.id.audio_title);
            audioDate = view.findViewById(R.id.audio_date);
            audioTime = view.findViewById(R.id.audio_time);
            audioComment = view.findViewById(R.id.audio_comment);
            listItem = view.findViewById(R.id.audio_list_item);
        }
    }
}
