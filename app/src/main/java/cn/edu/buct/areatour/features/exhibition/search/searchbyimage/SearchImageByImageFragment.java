package cn.edu.buct.areatour.features.exhibition.search.searchbyimage;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.ImageUtil;
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/08
 *     desc   : 以图搜索之类别搜索的碎片，即ViewPager的第一页的碎片
 *     version: 1.0
 * </pre>
 */
public class SearchImageByImageFragment extends Fragment {

    private static int REQUEST_ANIMAL = 1;
    private static int REQUEST_PLANT = 2;
    private static int REQUEST_DISH = 3;
    private static int REQUEST_CAR = 4;

    /**
     * 申请权限列表
     */
    private static String[] UPLOAD_IMAGE_PERMIISSION_ARRAY = new String[]{

    };

    Unbinder unbinder;
    @BindView(R.id.mask)
    View mask;
    @BindView(R.id.multiple_actions)
    FloatingActionsMenu multipleActions;
    @BindView(R.id.search_animal)
    FloatingActionButton searchAnimal;
    @BindView(R.id.search_plant)
    FloatingActionButton searchPlant;
    @BindView(R.id.search_dish)
    FloatingActionButton searchDish;
    @BindView(R.id.search_car)
    FloatingActionButton searchCar;


    public SearchImageByImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_image_by_image, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //点击浮动按钮的菜单按钮，弹出遮罩，再次点击，隐藏遮罩
        multipleActions.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                mask.setVisibility(View.VISIBLE);
            }

            @Override
            public void onMenuCollapsed() {
                mask.setVisibility(View.GONE);
            }
        });
    }

    /**
     *
     * 启动相机，上传图片
     *
     * @param requestCode 请求码代表了选择的不同按钮
     */
    private void startCamera(int requestCode) {
        Toast.makeText(UIUtils.getContext(), "点击了相机按钮", Toast.LENGTH_SHORT).show();
        //已经申请了读写存储卡的权限,选择照片

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, requestCode);
    }

    /**
     * 选择图片成功后返回的数据
     *
     * @param requestCode 请求码
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri uri = data.getData();
            ObjectDetect objectDetect = new ObjectDetect();
            objectDetect.detect(ImageUtil.getImageAbsolutePath(getActivity(), uri),requestCode,getActivity());
        }
    }


    /**
     * 碎片销毁的事件
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 菜单按钮的点击事件
     *
     * @param view
     */
    @OnClick({R.id.search_animal, R.id.search_plant, R.id.search_dish, R.id.search_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_animal:
                startCamera(REQUEST_ANIMAL);
                break;
            case R.id.search_plant:
                startCamera(REQUEST_PLANT);
                break;
            case R.id.search_dish:
                startCamera(REQUEST_DISH);
                break;
            case R.id.search_car:
                startCamera(REQUEST_CAR);
                break;
        }
    }


}
