package cn.edu.buct.areatour.features.exhibition.arealist.areardetail;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.edu.buct.areatour.common.module.AudioModule;
import cn.edu.buct.areatour.features.audioplay.AudioDetailActivity;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/01
 *     desc   : 解说列表的RecyclerView 的适配器
 *     version: 1.0
 * </pre>
 */
public class SpotItemSpeechAdapter extends RecyclerView.Adapter<SpotItemSpeechAdapter.ViewHolder> {

    private List<AudioModule> moduleList;

    public SpotItemSpeechAdapter(List<AudioModule> moduleList) {
        this.moduleList = moduleList;
    }

    @Override
    public SpotItemSpeechAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_audio_play,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpotItemSpeechAdapter.ViewHolder holder, int position) {
        AudioModule audioModule = moduleList.get(position);
        holder.audioCommentedCount.setText(audioModule.getCommentNum()+"");
        holder.audioDuration.setText(audioModule.getAudiotime());
        holder.audioTitle.setText(audioModule.getAudioName());
        holder.audioDate.setText(audioModule.getAudiodata());
        holder.audioItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UIUtils.getContext(), AudioDetailActivity.class);
                UIUtils.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        View audioItem;
        ImageView audioAvatar;
        TextView audioDate;
        TextView audioTitle;
        TextView audioDuration;
        TextView audioCommentedCount;

        public ViewHolder(View itemView) {
            super(itemView);
            audioItem = itemView.findViewById(R.id.audio_list_item);
            audioAvatar =  itemView.findViewById(R.id.audio_avatar);
            audioDate = itemView.findViewById(R.id.audio_date);
            audioTitle = itemView.findViewById(R.id.audio_title);
            audioDuration = itemView.findViewById(R.id.audio_time);
            audioCommentedCount = itemView.findViewById(R.id.audio_comment);
        }
    }
}
