package cn.edu.buct.areatour.features.exhibition.arealist.areardetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/03
 *     desc   : 相关景点展览的GridView的适配器
 *     version: 1.0
 * </pre>
 */
public class RelatedSpotItemGridViewAdapter extends BaseAdapter {

    private List<RelatedSpotItemModule> moduleList;
    private GridView gridView;

    public RelatedSpotItemGridViewAdapter(List<RelatedSpotItemModule> moduleList, GridView gridView) {
        this.moduleList = moduleList;
        this.gridView = gridView;
    }

    @Override
    public int getCount() {
        return moduleList.size();
    }

    @Override
    public Object getItem(int i) {
        return moduleList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_related_spot, viewGroup, false);


            TextView textView = view.findViewById(R.id.tv_related_spot_item_title);
            ImageView imageView = view.findViewById(R.id.tv_related_spot_item_image);
        RelatedSpotItemModule relatedSpotItem = moduleList.get(i);
        textView.setText(relatedSpotItem.getName());
        String url="http://39.106.122.103:8080/documents/image/";
        Glide.with(UIUtils.getContext()).load(url+relatedSpotItem.getImageName()).into(imageView);
        imageView.setTag(view);

        /*AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,android.view.ViewGroup.LayoutParams.FILL_PARENT);
        view.setLayoutParams(param);*/
        return view;
    }
    /*public class Holder {

        public TextView textView;
        public ImageView imageView;

        public void update() {

            // 精确计算GridView的item高度

            imageView.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            int position = (Integer) imageView.getTag();

                            // 这里是保证同一行的item高度是相同的！！也就是同一行是齐整的 height相等

                            if (position > 0 ) {
                                View v = (View) textView.getTag();
                                int height = v.getHeight();

                                View view = gridView.getChildAt(position - 1);
                                int lastheight = view.getHeight();

                                // 得到同一行的最后一个item和前一个item想比较，把谁的height大，就把两者中
                                // height小的item的高度设定为height较大的item的高度一致，也就是保证同一
                                // // 行高度相等即可

                                if (height > lastheight) {
                                    view.setLayoutParams(new GridView.LayoutParams(
                                            GridView.LayoutParams.FILL_PARENT,
                                            height));
                                } else if (height < lastheight) {
                                    v.setLayoutParams(new GridView.LayoutParams(
                                            GridView.LayoutParams.FILL_PARENT,
                                            lastheight));
                                }
                            }
                        }
                    });
        }
    }*/
}
