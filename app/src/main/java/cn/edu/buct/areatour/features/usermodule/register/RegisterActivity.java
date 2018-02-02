package cn.edu.buct.areatour.features.usermodule.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.MobSDK;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.link_signup)
    TextView linkSignUp;
    private EventHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobSDK.init(this, "23776190205a4","e584e333d05686f52b333649521e0ba6");
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = inputEmail.getText().toString();
                // 邮箱验证规则
                String regEx = "^1[0-9]{10}$";
                // 编译正则表达式
                Pattern pattern = Pattern.compile(regEx);
                // 忽略大小写的写法
                Matcher matcher = pattern.matcher(str);
                // 字符串是否与正则表达式相匹
                boolean b11=matcher.matches();
                System.out.println(b11);
                if(matcher.matches()){
                    submit();
                }else{
                    Toast.makeText(UIUtils.getContext(),"请输入正确的手机号",Toast.LENGTH_LONG).show();
                }
            }
        });
        handler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE){
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               Toast.makeText(RegisterActivity.this,"验证成功",Toast.LENGTH_SHORT).show();
                                /*Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                                startActivity(intent);*/
                            }
                        });

                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this,"验证码已发送",Toast.LENGTH_SHORT).show();
                                submitSuccess();
                            }
                        });
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){



                    }
                }else{
                    ((Throwable)data).printStackTrace();
                    Throwable throwable = (Throwable) data;
                }

            }
        };
        SMSSDK.registerEventHandler(handler);
    }

    /**
     * 输入手机获获取验证码操作
     */
    private void submit(){
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                R.style.AppTheme_Dark_Dialog);
        SMSSDK.getVerificationCode("86",inputEmail.getText().toString());
       /* progressDialog.setIndeterminate(true);
        progressDialog.setMessage("发送验证码中...");
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        //跳转到输入验证码活动
                        //TODO:可能存在两种情况：验证码发送失败，输出错误信息；验证码发送成功，跳转到输入验证码活动的界面
                        // submitFailed();
                        progressDialog.dismiss();
                    }
                }, 2000);*/
    }


    /**
     * 发送验证码成功
     */
    private void submitSuccess(){
        Intent intent = new Intent(UIUtils.getContext(),VerificationCodeActivity.class);
        intent.putExtra("phonenumber",inputEmail.getText().toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UIUtils.getContext().startActivity(intent);
    }

    /**
     * 发送验证码失败
     */
    private void submitFailed(){

    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(handler);
    }
}
