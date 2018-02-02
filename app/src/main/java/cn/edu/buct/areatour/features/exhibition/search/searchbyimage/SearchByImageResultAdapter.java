package cn.edu.buct.areatour.features.exhibition.search.searchbyimage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/08
 *     desc   : 以图搜索返回的数据的界面中的RecyclerView的Adapter
 *     version: 1.0
 * </pre>
 */
public class SearchByImageResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<ImageSearchResultsModule> moduleList;

    public SearchByImageResultAdapter(List<ImageSearchResultsModule> moduleList) {
        this.moduleList = moduleList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_by_image_results, parent, false);

        return new SearchByImageResultsViewHolder(view);
    }

    /**
     * 对RecyclerView的每一项进行设置处理
     * 1. 设置识别结果的序列
     * 2. 设置识别结果的名称
     * 3. 设置识别结果的置信度
     * 4. 因为菜品识别的接口返回的数据格式不同，需要对菜品识别的返回结果进行单独处理
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchByImageResultsViewHolder searchByImageResultsViewHolder = (SearchByImageResultsViewHolder)holder ;
        ImageSearchResultsModule module = moduleList.get(position);

        if (position == 1 ||position == 2 || position == 0){
            searchByImageResultsViewHolder.searchRank.setTextColor(UIUtils.getResources().getColor(R.color.green_accent));
        }


        searchByImageResultsViewHolder.searchRank.setText((position + 1) + "");
        if (module.getCalorie() != -1){
            searchByImageResultsViewHolder.ll_search_calorie.setVisibility(View.VISIBLE);
            searchByImageResultsViewHolder.searchName.setText(module.getName());
            searchByImageResultsViewHolder.searchConfidence.setText(module.getProbability());
            searchByImageResultsViewHolder.searchCalorie.setText(module.getCalorie() + "");
        }else{
            searchByImageResultsViewHolder.searchName.setText(module.getName());
            searchByImageResultsViewHolder.searchConfidence.setText(module.getScore());
        }

    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    /**
     * RecyclerView的ViewHolder 类
     */
    class SearchByImageResultsViewHolder extends RecyclerView.ViewHolder {

        TextView searchRank;
        TextView searchName;
        TextView searchCalorie;
        TextView searchConfidence;
        View ll_search_calorie;

        public SearchByImageResultsViewHolder(View itemView) {
            super(itemView);
            searchRank = itemView.findViewById(R.id.search_rank);
            searchName = itemView.findViewById(R.id.search_name);
            searchConfidence = itemView.findViewById(R.id.search_confidence);
            searchCalorie = itemView.findViewById(R.id.search_calorie);
            ll_search_calorie = itemView.findViewById(R.id.ll_search_calorie);
        }

    }
}
