package cn.edu.buct.areatour.features.exhibition.arealist.areardetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.module.CommentModule;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class SpotItemCommentAdapter extends RecyclerView.Adapter<SpotItemCommentAdapter.ViewHolder> {

    private ArrayList<CommentModule> commentList;

    public SpotItemCommentAdapter(ArrayList<CommentModule> commentList) {
        this.commentList = commentList;
    }

    @Override
    public SpotItemCommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline_comment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpotItemCommentAdapter.ViewHolder holder, int position) {
        CommentModule commentModule = commentList.get(position);
        holder.tvCommentContent.setText(commentModule.getCommentContent());
        holder.tvCommentDate.setText(commentModule.getCommentDate());

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView cvUserAvatar;
        TextView tvCommentDate;
        TextView tvUserName;
        TextView tvCommentContent;


        public ViewHolder(View itemView) {
            super(itemView);
            cvUserAvatar = itemView.findViewById(R.id.imgPhoto);
            tvCommentDate = itemView.findViewById(R.id.txtDesc);
            tvUserName = itemView.findViewById(R.id.txtName);
            tvCommentContent = itemView.findViewById(R.id.txtContent);

        }
    }

}

