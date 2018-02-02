package cn.edu.buct.areatour.features.usermodule.anchor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.edu.buct.areatour.R;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/22
 *     desc   : 主播界面的专辑列表RecyclerView 适配器
 *     version: 1.0
 * </pre>
 */
public class AlbumListRecyclerViewAdapter  extends RecyclerView.Adapter<AlbumListRecyclerViewAdapter.ViewHolder>{

    private List<AlbumBean> mList;

    public AlbumListRecyclerViewAdapter(List<AlbumBean> mList) {
        this.mList = mList;
    }

    @Override
    public AlbumListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumListRecyclerViewAdapter.ViewHolder holder, int position) {


    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
