package cn.edu.buct.areatour.features.exhibition.arealist.areardetail;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.buct.areatour.Entities.Expobject;
import cn.edu.buct.areatour.Entities.Museum;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/01
 *     desc   : 景区详情页面的TabLayout的[简单介绍]碎片
 *     version: 1.0
 * </pre>
 */
public class SpotItemIntroAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SPOT_ITEM_INTRO = 0;
    private static final int SPOT_ITEM_RELATED_SPOT = 1;
    private static final int SPOT_ITEM_RELATED_EXHIBITION = 2;

    GridView relatedSpotGridView;
    GridView relatedExhibitionGridView;

    private String expobjInfo;
    Expobject expobject;
    Museum museum;

    public SpotItemIntroAdapter(String str){
        expobjInfo = str;
        Log.i("SpotItemIntroAdapter-->", expobjInfo);
        try {
            ObjectMapper mapper = new ObjectMapper();
            expobject = mapper.readValue(expobjInfo, Expobject.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        museum = expobject.getMuseum();

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SPOT_ITEM_INTRO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spot_item_intro, parent, false);
            TextView spotItemIntroContent = (TextView) view.findViewById(R.id.spot_item_intro_content);
            spotItemIntroContent.setText(expobject.getExpobjintroduction());
            relatedSpotGridView = view.findViewById(R.id.related_spot_grid_view);
            relatedExhibitionGridView = view.findViewById(R.id.related_exhibition_grid_view);
            return new SpotItemIntroViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //给[相关景点]的GridView 设置数据
        setGridView(initRelatedSpotData(),relatedSpotGridView);
        //给[相关展览]的GridView 设置数据
        setGridView(intiRelatedExhibitionData(),relatedExhibitionGridView);

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return SPOT_ITEM_INTRO;
        }
        return super.getItemViewType(position);

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    private List<RelatedSpotItemModule>  initRelatedSpotData(){

        List<RelatedSpotItemModule> relatedSpotItemModuleList = new ArrayList<>();

        //设置相关景点数据
        for (int i = 0; i < museum.getSpot().size(); i++) {
            RelatedSpotItemModule relatedSpotItemModule = new RelatedSpotItemModule(expobject.getExpobjid(), museum.getSpot().get(i).getExpobject().getExpobjimage().get(0).getExpobjimagename(), museum.getSpot().get(i).getExpobject().getExpobjname());
            relatedSpotItemModuleList.add(relatedSpotItemModule);
        }

        return relatedSpotItemModuleList;

    }

    private List<RelatedSpotItemModule> intiRelatedExhibitionData(){

        List<RelatedSpotItemModule> relatedSpotItemModuleList = new ArrayList<>();

        //设置相关展览数据
        for (int i = 0; i < museum.getExhibition().size(); i++) {
//            RelatedSpotItemModule relatedSpotItemModule = new RelatedSpotItemModule(R.drawable.gugong, "故宫博物馆");
            RelatedSpotItemModule relatedSpotItemModule = new RelatedSpotItemModule(museum.getExhibition().get(i).getExpobjid(), museum.getExhibition().get(i).getExpobject().getExpobjimage().get(0).getExpobjimagename(), museum.getExhibition().get(i).getExpobject().getExpobjname());
            relatedSpotItemModuleList.add(relatedSpotItemModule);
        }

        return relatedSpotItemModuleList;
    }

    /**
     * 设置相关景点的GridView适配器
     *
     * @param dataList GridView数据，List数据类型
     */
    private void setGridView(List<RelatedSpotItemModule> dataList,GridView gridView) {

        // item宽度
        int itemWidth = UIUtils.dip2px(125);
        // item之间的间隔
        int itemPaddingH = UIUtils.dip2px(5);
        //item高度
        int itemHeight = UIUtils.getResources().getDimensionPixelSize(R.dimen.height);
        int size = dataList.size();

        // 计算GridView宽度
        int gridviewWidth = size * (itemWidth + itemPaddingH);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

        gridView.setLayoutParams(params);
        gridView.setColumnWidth(itemWidth);
        gridView.setHorizontalSpacing(itemPaddingH);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size);

        RelatedSpotItemGridViewAdapter adapter = new RelatedSpotItemGridViewAdapter(dataList, gridView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }


    class SpotItemIntroViewHolder extends RecyclerView.ViewHolder {

        public SpotItemIntroViewHolder(View itemView) {
            super(itemView);


        }


    }

}

