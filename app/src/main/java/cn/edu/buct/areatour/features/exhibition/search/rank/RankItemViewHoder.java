package cn.edu.buct.areatour.features.exhibition.search.rank;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RankItemViewHoder extends RecyclerView.ViewHolder {

    @BindView(R.id.rank_item_rank)
    TextView rankItemRank;
    @BindView(R.id.rank_list_image)
    ImageView rankListImage;
    @BindView(R.id.rank_list_spot_title)
    TextView rankListSpotTitle;
    @BindView(R.id.rank_list_spot_score)
    TextView rankListSpotScore;

    public RankItemViewHoder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
