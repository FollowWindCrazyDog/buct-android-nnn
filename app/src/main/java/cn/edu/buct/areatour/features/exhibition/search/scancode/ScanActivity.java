package cn.edu.buct.areatour.features.exhibition.search.scancode;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.activity.BaseActivity;
import cn.edu.buct.areatour.common.utils.CheckPermissionUtils;
import cn.edu.buct.areatour.common.utils.ImageUtil;
import cn.edu.buct.areatour.common.utils.UIUtils;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/29
 *     desc   : 二维码扫描界面
 *     version: 1.0
 * </pre>
 */

public class ScanActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.fl_my_container)
    FrameLayout flMyContainer;
    @BindView(R.id.button_open_photo)
    CircleImageView buttonOpenPhoto;
    @BindView(R.id.button_open_flash)
    CircleImageView buttonOpenFlash;
    @BindView(R.id.activity_second)
    FrameLayout activitySecond;
    @BindView(R.id.scan_toolbar)
    Toolbar toolbar;

    private boolean isLightOpen = false;
    private static final int REQUEST_IMAGE = 1;
    /**
     * 请求打开相机唯一请求码
     */
    public static final int REQUEST_CAMERA_PERM = 101;
    private CaptureFragment captureFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        /**
         * 执行扫面Fragment的初始化操作
         */
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.item_camera);


        captureFragment.setAnalyzeCallback(analyzeCallback);
        /**
         * 替换我们的扫描控件
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();

        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };
        permissions = CheckPermissionUtils.checkPermission(this, permissions);
        if (permissions.length != 0) {
            ActivityCompat.requestPermissions(this, permissions, 100);
        }

    }


    /**
     * 打开相册、打开闪光灯按钮绑定事件
     *
     * @param view
     */

    @OnClick({R.id.button_open_photo, R.id.button_open_flash})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_open_photo:
                String[] permissions = new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                };
                permissions = CheckPermissionUtils.checkPermission(this, permissions);
                if (permissions.length == 0) {
                    startCamera();
                } else {
                    //申请权限
                    ActivityCompat.requestPermissions(this, permissions, 100);
                }

                break;
            case R.id.button_open_flash:
                if (isLightOpen) {
                    CodeUtils.isLightEnable(false);
                    isLightOpen = false;
                    buttonOpenFlash.setImageDrawable(UIUtils.getContext().getResources().getDrawable(R.drawable.ic_lightbulb_outline_white_48dp));
                } else {
                    CodeUtils.isLightEnable(true);
                    isLightOpen = true;
                    buttonOpenFlash.setImageDrawable(UIUtils.getContext().getResources().getDrawable(R.drawable.ic_lightbulb_clicked));
                }
                break;
        }
    }


    /**
     * 选择相册中的二维码
     */
    private void startCamera() {
        Toast.makeText(UIUtils.getContext(), "点击了相机按钮", Toast.LENGTH_SHORT).show();
        //已经申请了读写存储卡的权限,选择照片

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE);
    }


    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        /**
         * 二维码解析成功回调函数
         *
         * @param mBitmap
         * @param result
         */
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent intent = new Intent(UIUtils.getContext(), ScanToResultsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            intent.putExtras(bundle);
            UIUtils.getContext().startActivity(intent);
        }

        /**
         * 二维码解析失败回调函数
         */
        @Override
        public void onAnalyzeFailed() {
            Intent intent = new Intent(UIUtils.getContext(), ScanToResultsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            intent.putExtras(bundle);
            UIUtils.getContext().startActivity(intent);

        }
    };

    /**
     * 扫描成功后返回的信息处理，将该数据传递到扫描结果活动页面
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA_PERM) {
            Toast.makeText(this, "从设置页面返回...", Toast.LENGTH_SHORT)
                    .show();
        } else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Intent intent = new Intent(UIUtils.getContext(), ScanToResultsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                            bundle.putString(CodeUtils.RESULT_STRING, result);
                            intent.putExtras(bundle);
                            UIUtils.getContext().startActivity(intent);
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Intent intent = new Intent(UIUtils.getContext(), ScanToResultsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
                            bundle.putString(CodeUtils.RESULT_STRING, "");
                            intent.putExtras(bundle);
                            UIUtils.getContext().startActivity(intent);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * EsayPermissions接管权限处理逻辑
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    /**
     * 权限申请成功
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //startCamera();
    }

    /**
     * 权限申请失败
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
        Toast.makeText(this, "执行onPermissionsDenied()...Completed", Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击toolbar上的按钮事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.black));

    }
}
