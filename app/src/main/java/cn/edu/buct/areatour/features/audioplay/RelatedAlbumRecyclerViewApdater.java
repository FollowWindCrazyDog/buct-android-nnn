package cn.edu.buct.areatour.features.audioplay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/14
 *     desc   : 音频详情页面的专辑推荐的
 *     version: 1.0
 * </pre>
 */
public class RelatedAlbumRecyclerViewApdater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<RelatedAlbumBean> mList;

    public RelatedAlbumRecyclerViewApdater(List<RelatedAlbumBean> mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_related_audio,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
