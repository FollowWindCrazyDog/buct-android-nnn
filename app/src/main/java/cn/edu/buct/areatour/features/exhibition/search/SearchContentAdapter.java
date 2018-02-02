package cn.edu.buct.areatour.features.exhibition.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.edu.buct.areatour.features.exhibition.search.dynamic.DynamicMainActivity;
import cn.edu.buct.areatour.features.exhibition.search.rank.RankListActivity;
import cn.edu.buct.areatour.features.exhibition.search.scancode.ScanActivity;
import cn.edu.buct.areatour.features.exhibition.search.searchbyimage.SearchByImageActivity;


/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/27
 *     desc   : 搜索界面除了搜索框的内容适配器（RecyclerView）
 *     version: 1.0
 * </pre>
 */
public class SearchContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int SEARCH_HOT_INFO = 0;
    private static final int SEARCH_COLUMN = 1;
    private Context mContext;


    /**
     * 根据不同的viewType，返回不同的布局文件
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        if (viewType == SEARCH_HOT_INFO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_hot_info, parent, false);
            return new SearchHotInfoViewHolder(view);

        } else if (viewType == SEARCH_COLUMN) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_colum, parent, false);
            return new SearchColumnViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == SEARCH_COLUMN){
            SearchColumnViewHolder searchColumnViewHolder = (SearchColumnViewHolder)holder;
            //启动二维码扫描功能
            searchColumnViewHolder.searchByScan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(UIUtils.getContext(),ScanActivity.class);
                    UIUtils.getContext().startActivity(intent);
                }
            });
            //启动以图搜图活动
            searchColumnViewHolder.searchByImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UIUtils.getContext(), SearchByImageActivity.class);
                    UIUtils.getContext().startActivity(intent);
                }
            });
            //启动景区排行榜活动
            searchColumnViewHolder.recommendSpotRank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UIUtils.getContext(), RankListActivity.class);
                    UIUtils.getContext().startActivity(intent);
                }
            });
            //启动最新动态活动
            searchColumnViewHolder.dynamic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UIUtils.getContext(), DynamicMainActivity.class);
                    UIUtils.getContext().startActivity(intent);
                }
            });



        }
    }

    /**
     * RecyclerView 有两种用法，一种是填充同一种布局文件，另一种是填充不同的布局文件，根据getItemViewType的类型不同。
     * 这里就是第二种用法。
     *
     * @param position
     * @return
     */

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return SEARCH_HOT_INFO;
        }else if (position == 1){
            return SEARCH_COLUMN;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
