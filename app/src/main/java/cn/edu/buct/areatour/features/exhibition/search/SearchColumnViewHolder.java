package cn.edu.buct.areatour.features.exhibition.search;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/27
 *     desc   : 搜索界面的栏目列表
 *     version: 1.0
 * </pre>
 */
public class SearchColumnViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {




    @BindView(R.id.search_by_scan)
    RelativeLayout searchByScan;
    @BindView(R.id.search_by_image)
    RelativeLayout searchByImage;
    @BindView(R.id.faxian_bottom_discovery_new)
    ImageView mDiscoveryNew;
    @BindView(R.id.recommended_spot_rank)
    RelativeLayout recommendSpotRank;
    @BindView(R.id.dynamic)
    View dynamic;

    public SearchColumnViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        //设置动画
        AnimationDrawable ad = (AnimationDrawable) mDiscoveryNew.getBackground();
        ad.start();
    }



    @Override
    public void onClick(View view) {

    }
}
