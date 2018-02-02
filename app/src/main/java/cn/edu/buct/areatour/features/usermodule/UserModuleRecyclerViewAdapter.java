package cn.edu.buct.areatour.features.usermodule;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.edu.buct.areatour.features.usermodule.anchor.AnchorMainActivity;
import cn.edu.buct.areatour.features.usermodule.login.LoginActivity;
import cn.edu.buct.areatour.features.usermodule.setting.SettingActivity;
import cn.edu.buct.areatour.features.usermodule.user.UserDataActivity;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class UserModuleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int LOGIN_META = 0;
    private static final int LOGIN_COLUMN = 1;

    private  boolean isLogin  = false;
    private Fragment fragment;


    public UserModuleRecyclerViewAdapter() {
    }

    public UserModuleRecyclerViewAdapter(boolean isLogin,Fragment fragment) {
        this.isLogin  = isLogin;
        this.fragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LOGIN_META){
            View view;
            if (isLogin){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_data_entrance, parent, false);
                return new UserEntranceHolder(view);
            }else{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_login_type, parent, false);
                return new UserLoginTypeViewHolder(view);
            }

        }else if (viewType == LOGIN_COLUMN){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_login_column, parent, false);
            return new UserLoginColumnHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder.getItemViewType() == LOGIN_META){
            /**
             * 点击{其他登录方式}启动登录活动界面
             */

            if (isLogin){
                ((UserEntranceHolder)holder).userEntrance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //启动用户资料卡页面
                        Intent intent = new Intent(UIUtils.getContext(), UserDataActivity.class);
                        UIUtils.getContext().startActivity(intent);
                    }
                });

            }else{
                ((UserLoginTypeViewHolder)holder).otherTypeLoginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UIUtils.getContext(), LoginActivity.class);
                        fragment.startActivityForResult(intent,1);
                    }
                });
            }
        }else if (holder.getItemViewType() == LOGIN_COLUMN){
            if(isLogin){

            }else{

            }

            ((UserLoginColumnHolder)holder).userSettingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //启动设置界面
                    Intent intent = new Intent(UIUtils.getContext(), SettingActivity.class);
                    UIUtils.getContext().startActivity(intent);
                }
            });
            ((UserLoginColumnHolder)holder).anchorCenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //启动主播中心
                    Intent intent = new Intent(UIUtils.getContext(), AnchorMainActivity.class);
                    UIUtils.getContext().startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return LOGIN_META;
        }else if (position == 1){
            return LOGIN_COLUMN;
        }
        return super.getItemViewType(position);
    }

    /**
     * 用户登录类型的ViewHolder
     */
    class UserLoginTypeViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.login_other_type_button)
        View otherTypeLoginButton;



        public UserLoginTypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 界面的行界面的ViewHolder
     */
    class UserLoginColumnHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_settings)
        View userSettingBtn;
        @BindView(R.id.anchor_center)
        View anchorCenter;

        public UserLoginColumnHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 用户资料卡的入口ViewHolder
     */
    class UserEntranceHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_entrance)
        View userEntrance;

        public UserEntranceHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
