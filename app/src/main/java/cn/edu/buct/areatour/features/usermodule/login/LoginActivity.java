package cn.edu.buct.areatour.features.usermodule.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.ALog;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.features.usermodule.register.RegisterActivity;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private int userId = 0;

    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.link_signup)
    TextView linkSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        linkSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 启动注册活动
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: 实现成功注册返回到该活动之后的逻辑

                this.finish();
            }
        }
    }
    /**
     * 用户登录操作
     */
    private void login(){

        ALog.d("登录开始");

        //开始检查失败，直接跳转到失败提示函数里
        if (!validate()){
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("正在登录...");
        //progressDialog.show();

        final String account = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();
        loginFun(account,password,progressDialog);
    }


    private void loginFun(String account,String password,ProgressDialog progressDialog){
        MyThread myThread = new MyThread(account,password,progressDialog,btnLogin,this);
        new Thread(myThread).start();
    }
    /**
     * 检查用户输入是否合法
     *
     * @return
     */
    public boolean validate() {
        boolean valid = true;

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("请输入一个合法的email地址");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            inputPassword.setError("密码必须超过4个字符");
            valid = false;
        } else {
            inputPassword.setError(null);
        }
        return valid;
    }

    /**
     * 登录失败提示函数
     */
    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), "登录失败，请重试", Toast.LENGTH_LONG).show();
        btnLogin.setEnabled(true);

    }
}
class MyThread implements Runnable{
    private String account = "";
    private String password = "";
    ProgressDialog progressDialog = null;
    Button btnLogin = null;
    AppCompatActivity context = null;

    public MyThread(String account, String password, ProgressDialog progressDialog,Button btnLogin,AppCompatActivity appCompatActivity) {
        this.account = account;
        this.password = password;
        this.progressDialog = progressDialog;
        this.btnLogin = btnLogin;
        this.context = appCompatActivity;
    }

    @Override
    public void run() {
        // TODO: 增加具体的用户名验证逻辑
        Log.d("thread","run");
        String url = "http://192.168.0.6:8080";
        url+="//TourServer/user/login";
//                http://localhost:8080/TourServer
//                        http://localhost:8080/TourServer/user/login?userAccount='123123123'&userPassword='12323123'
        OkHttpClient OkHttpClient = new OkHttpClient();
                RequestBody RequestBody = new FormBody.Builder().add("userAccount",account)
                        .add("userPassword",password).build();
                        Request Request = new Request.Builder().url(url).post(RequestBody).build();
//        Request Request = new Request.Builder().url("http://www.baidu.com").build();
        String responseMessage = null;
        try {
            Response response = OkHttpClient.newCall(Request).execute();
            responseMessage = response.body().string();
            Log.d("response",responseMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.runOnUiThread(new UiThread(btnLogin,context,(responseMessage==null),progressDialog));
    }

}

class UiThread implements Runnable{
    private Button btnLogin = null;
    private AppCompatActivity context = null;
    private boolean flag = false;
    ProgressDialog progressDialog = null;

    public UiThread(Button btnLogin, AppCompatActivity appCompatActivity, boolean flag, ProgressDialog progressDialog) {
        this.btnLogin = btnLogin;
        this.context = appCompatActivity;
        this.flag = flag;
        this.progressDialog = progressDialog;
    }

    @Override
    public void run() {
        if(flag){
            onLoginFailed();
        }
        else {
            onLoginSuccess();
        }
        progressDialog.dismiss();
    }
    private void onLoginFailed() {
        Toast.makeText(context.getBaseContext(), "登录失败，请重试", Toast.LENGTH_LONG).show();
        btnLogin.setEnabled(true);
    }
    /**
     * 登录成功执行函数
     */
    private void onLoginSuccess() {
        btnLogin.setEnabled(true);
        context.finish();
    }
}