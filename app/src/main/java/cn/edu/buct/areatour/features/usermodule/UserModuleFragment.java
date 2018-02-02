package cn.edu.buct.areatour.features.usermodule;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * <pre>
 *     author :
 *     e-mail :
 *     time   : 2017/11/10
 *     desc   : 用户中心界面
 *     version: 1.0
 * </pre>
 */
public class UserModuleFragment extends Fragment {


    @BindView(R.id.user_interface_recycler_view)
    RecyclerView userInterfaceRecyclerView;
    @BindView(R.id.floating_search_view)
    FloatingSearchView floatingSearchView;
    Unbinder unbinder;

    private static boolean isLogin = false;

    public UserModuleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_module, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.color_white));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userInterfaceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UserModuleRecyclerViewAdapter adapter = new UserModuleRecyclerViewAdapter(isLogin,this);
        userInterfaceRecyclerView.setAdapter(adapter);
    }

    /**
     *
     * 从登录页面登录成功后返回的数据处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //TODO:这里应该根据返回的数据判断是否登录成功
        isLogin = true;

        Toast.makeText(UIUtils.getContext(),"登录成功",Toast.LENGTH_LONG).show();
        userInterfaceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UserModuleRecyclerViewAdapter adapter = new UserModuleRecyclerViewAdapter(isLogin,this);
        userInterfaceRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.green_primary));
    }
}
