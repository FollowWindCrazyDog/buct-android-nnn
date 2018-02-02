package cn.edu.buct.areatour.features.exhibition.arealist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.buct.areatour.Entities.Expobject;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.edu.buct.areatour.features.exhibition.LocationModule;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/25
 *     desc   : 景区列表的RecyclerView 适配器
 *     version: 1.0
 * </pre>
 */

public class SpotsListRecyclerViewAdapter extends RecyclerView.Adapter<SpotsListRecyclerViewAdapter.ViewHolder> {

    private List<LocationModule> mLocationList;
    private Activity activity;
    private String str;
    private String exp;
    Expobject expobject;

    public SpotsListRecyclerViewAdapter(List<LocationModule> locationList){
        mLocationList = locationList;
    }

    public SpotsListRecyclerViewAdapter(List<LocationModule> mLocationList, Activity activity, String str) {
        this.mLocationList = mLocationList;
        this.activity = activity;
        this.str = str;
    }

    /**
     *
     * 用于创建ViewHolder实例onCreateViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public SpotsListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spots,parent,false);

        final  ViewHolder holder = new ViewHolder(view);
        // recyclerView 每个子项的点击事件
        holder.spotItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                LocationModule location = mLocationList.get(position);
                Log.i("从列表页面传来的讲解对象字符串：", str);
                List<Expobject> eList=new ArrayList();
                try{
                    ObjectMapper mapper = new ObjectMapper();
                    JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Expobject.class);
                    eList = (List<Expobject>)mapper.readValue(str, javaType);
                    Log.i("lalalala", eList.toString());
                }catch(IOException e){
                    e.printStackTrace();
                }
                expobject = eList.get(position);
                try{
                    ObjectMapper mapper1 = new ObjectMapper();
                    exp = mapper1.writeValueAsString(expobject);
                }catch(IOException e){
                    e.printStackTrace();
                }
//                Intent intent = new Intent(UIUtils.getContext(),SpotItemActivity.class);
                //向景点子项活动传入当前子项的名称和图片ID
                //以后应该改成传递对象，而不是一个个传递值
                // 数据库链接可以使用LitePal开源项目
                String expobjid = String.valueOf(location.getExpobjid());
                Log.i("详情页面请求参数expobjid：", expobjid);

                /*Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://39.106.122.103:8080/TourServer/")
                        .addConverterFactory(JacksonConverterFactory.create())//retrofit已经把Json解析封装在内部了 你需要传入你想要的解析工具就行了 默认支持Gson解析
                        .build();

                // 创建 网络请求接口 的实例
                MuseumInterface featurerequest = retrofit.create(MuseumInterface.class);
//                Map<String, String> paramsMap = new HashMap<>();
//                paramsMap.put("expobjid", expobjid);

                // 对 发送请求 进行封装(设置内容)
//            Call<String> call = loginrequest.login( userName.getText().toString(),  password.getText().toString());
                Call<Museum> call = featurerequest.showMuseumRelateInfo(expobjid);
                call.enqueue(new Callback<Museum>() {
                    //请求成功时回调
                    @Override
                    public void onResponse(Call<Museum> call, Response<Museum> response) {
                        // 请求处理,输出结果
                        // 输出翻译的内容
                        // System.out.println("翻译是："+ response.body().getTranslateResult().get(0).get(0).getTgt());
                        Log.i("response---->", "有响应");
                        if(response.isSuccessful()) {

                            //初始化景区ArrayList
//                            Expobject exp=response.body();

                            Museum museum = response.body();
                            *//*try {
                                ObjectMapper objectMapper = new ObjectMapper();
                                String mum = objectMapper.writeValueAsString(museum);
                                Log.i("museum--->", mum);
                            }catch (JsonProcessingException e){
                                e.printStackTrace();
                            }*//*

                            expobject.setMuseum(museum);

                            try{
                                *//*ObjectMapper expmapper = new ObjectMapper();
                                Expobject exp = expmapper.readValue(str, Expobject.class);
                                exp.setMuseum(museum);*//*
                                ObjectMapper mapper = new ObjectMapper();
                                exp=mapper.writeValueAsString(expobject);
                            }catch(IOException e2){
                                e2.printStackTrace();
                            }
                            Intent intent = new Intent(UIUtils.getContext(),SpotItemActivity.class);
                            intent.putExtra("ExpobjInfo", exp);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            UIUtils.getContext().startActivity(intent);
                        }
                    }

                    //请求失败时回调，可能网络异常，也可能Gson解析数据转换出错
                    @Override
                    public void onFailure(Call<Museum> call, Throwable throwable) {
                        throwable.printStackTrace();
                        // Toast.makeText(SpotListContentFragment.this, "网络异常，请检查网络", Toast.LENGTH_SHORT ).show();
                    }
                });*/

                Intent intent = new Intent(UIUtils.getContext(),SpotItemActivity.class);
                intent.putExtra("ExpobjId", expobjid);
                intent.putExtra("ExpobjInfo", exp);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                UIUtils.getContext().startActivity(intent);

            }
        });


        return holder;
    }
    /**
     *
     * 对RecyclerView子项的数据进行赋值，会在每个子项滚动到屏幕内的时候执行
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(SpotsListRecyclerViewAdapter.ViewHolder holder, int position) {
        LocationModule location = mLocationList.get(position);
        holder.locationText.setText(location.getLocationName());
        if (position % 2 == 0){
            UIUtils.setViewMargin(holder.cardView,false, 12, 6, 12, 0);
        }else{
            UIUtils.setViewMargin(holder.cardView,false, 6, 12, 12, 0);
        }
        //Glide.with(UIUtils.getContext()).load(location.getImageId()).into(holder.locationImage);
        String url="http://39.106.122.103:8080/documents/image/";
        Glide.with(UIUtils.getContext()).load(url+location.getLocationImagename()).into(holder.locationImage);

    }

    @Override
    public int getItemCount() {
        return mLocationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView locationImage;
        TextView locationText;
        View spotItem;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            locationImage = cardView.findViewById(R.id.location_image);
            locationText = cardView.findViewById(R.id.location_name);
            spotItem = cardView.findViewById(R.id.spot_item);
        }
    }
}
