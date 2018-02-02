package cn.edu.buct.areatour.features.exhibition.search.dynamic;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/18
 *     desc   : 动态页面的用户动态内容的RecyclerView 的内容适配器
 *     version: 1.0
 * </pre>
 */
public class DynamicContentRecyclerViewAdapter extends RecyclerView.Adapter<DynamicContentRecyclerViewAdapter.DynamicContentViewHolder> {

    private List<DynamicContent> mList = new ArrayList<>();

    public DynamicContentRecyclerViewAdapter(List<DynamicContent> mList) {
        this.mList = mList;
    }

    @Override
    public DynamicContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dynamic, parent, false);
        return new DynamicContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DynamicContentViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动单个的评论详情页面
                Intent intent = new Intent(UIUtils.getContext(),DynamicItemActivity.class);
                UIUtils.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class DynamicContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgPhoto)
        CircleImageView imgPhoto;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.imgVerified)
        ImageView imgVerified;
        @BindView(R.id.txtDesc)
        TextView txtDesc;
        @BindView(R.id.txtContent)
        TextView txtContent;
        @BindView(R.id.layReDivider)
        View layReDivider;
        @BindView(R.id.txtReContent)
        TextView txtReContent;
        @BindView(R.id.layRe)
        RelativeLayout layRe;
        @BindView(R.id.txtPics)
        TextView txtPics;
        @BindView(R.id.txtVisiable)
        TextView txtVisiable;
        @BindView(R.id.imgLike)
        ImageView imgLike;
        @BindView(R.id.txtLike)
        TextView txtLike;
        @BindView(R.id.btnLike)
        LinearLayout btnLike;
        @BindView(R.id.txtComment)
        TextView txtComment;
        @BindView(R.id.btnCmt)
        LinearLayout btnCmt;
        @BindView(R.id.txtRepost)
        TextView txtRepost;
        @BindView(R.id.btnRepost)
        LinearLayout btnRepost;
        @BindView(R.id.btnMenus)
        ImageView btnMenus;

        View itemView;

        public DynamicContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
