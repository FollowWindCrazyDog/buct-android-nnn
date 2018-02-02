package cn.edu.buct.areatour.features.exhibition.search.rank;

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
 *     time   : 2017/11/09
 *     desc   : 排行榜每一栏的RecyclerView的适配器
 *     version: 1.0
 * </pre>
 */
public class RankItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RankItemModule>moduleList;

    public RankItemRecyclerViewAdapter(List<RankItemModule> moduleList) {
        this.moduleList = moduleList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank_item,parent,false);
        return new RankItemViewHoder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RankItemModule module = moduleList.get(position);
        holder = (RankItemViewHoder)holder;
        ((RankItemViewHoder) holder).rankItemRank.setText((position + 1) + "");

        if (position == 0){
            ((RankItemViewHoder) holder).rankItemRank.setTextColor(UIUtils.getResources().getColor(R.color.green_accent));
            ((RankItemViewHoder) holder).rankItemRank.setTextSize(28);
        }else if (position == 1){
            ((RankItemViewHoder) holder).rankItemRank.setTextColor(UIUtils.getResources().getColor(R.color.green_accent));
            ((RankItemViewHoder) holder).rankItemRank.setTextSize(23);
        }else if (position == 2){
            ((RankItemViewHoder) holder).rankItemRank.setTextColor(UIUtils.getResources().getColor(R.color.green_accent));
            ((RankItemViewHoder) holder).rankItemRank.setTextSize(18);
        }


    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }


}
