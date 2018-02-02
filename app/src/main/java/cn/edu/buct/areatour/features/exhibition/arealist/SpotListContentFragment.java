package cn.edu.buct.areatour.features.exhibition.arealist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.buct.areatour.Entities.Expobject;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.features.exhibition.LocationModule;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpotListContentFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {


    Unbinder unbinder;
    @BindView(R.id.swipe_target)
    RecyclerView recyclerView;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    private SpotsListRecyclerViewAdapter adapter;

    //景区测试arrayList
    private List<LocationModule> spotList = new ArrayList<>();
    private List<Expobject> expobjects = new ArrayList<>();
    //景区测试数据
    public static final String spotItemContent = "北京故宫是中国明清两代的皇家宫殿，旧称为紫禁城，位于北京中轴线的中心，是中国古代宫廷建筑之精华。北京故宫以三大殿为中心，占地面积72万平方米，建筑面积约15万平方米，有大小宫殿七十多座，房屋九千余间。是世界上现存规模最大、保存最为完整的木质结构古建筑之一。\n" +
            "北京故宫于明成祖永乐四年（1406年）开始建设，以南京故宫为蓝本营建，到永乐十八年（1420年）建成。它是一座长方形城池，南北长961米，东西宽753米，四面围有高10米的城墙，城外有宽52米的护城河。紫禁城内的建筑分为外朝和内廷两部分。外朝的中心为太和殿、中和殿、保和殿，统称三大殿，是国家举行大典礼的地方。内廷的中心是乾清宫、交泰殿、坤宁宫，统称后三宫，是皇帝和皇后居住的正宫。";

    private String str;

    public SpotListContentFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spot_list_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        str=getArguments().getString("exp");
        initSpots(str);
        //设置RecyclerView数据
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SpotsListRecyclerViewAdapter(spotList,getActivity(),str);
        recyclerView.setAdapter(adapter);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        //打开该碎片时自动刷新
        //autoRefresh();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 初始化列表数据
     */
    private void initSpots(String exp) {
        spotList.clear();
        List<Expobject> eList=new ArrayList();
        try{
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Expobject.class);
            eList = (List<Expobject>)mapper.readValue(exp, javaType);
        }catch(IOException e){
            e.printStackTrace();
        }


        for (int i = 0; i < eList.size(); i++) {
            LocationModule location = new LocationModule(eList.get(i).getExpobjname(), eList.get(i).getExpobjid(), R.drawable.gugong, "Forbbidden City", eList.get(i).getExpobjintroduction(), eList.get(i).getExpobjimage().get(0).getExpobjimagename());
            spotList.add(location);
        }
    }


    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        refreshSpotList();

    }

    private void refreshSpotList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initSpots(str);
                        adapter.notifyDataSetChanged();
                        swipeToLoadLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void autoRefresh() {
        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        });
    }
}
