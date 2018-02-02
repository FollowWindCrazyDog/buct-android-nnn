package cn.edu.buct.areatour.features.exhibition.search.searchbyimage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.aip.util.Base64Util;
import com.blankj.ALog;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import cn.edu.buct.areatour.common.activity.BaseActivity;
import cn.edu.buct.areatour.common.utils.FileUtil;
import cn.edu.buct.areatour.common.utils.HttpUtil;
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/07
 *     desc   : 以图搜图方法类
 *     version: 1.0
 * </pre>
 */
public class ObjectDetect {

    private String filePath;
    private static final int REQUEST_ANIMAL = 1;
    private static final int REQUEST_PLANT = 2;
    private static final int REQUEST_DISH = 3;
    private static final int REQUEST_CAR = 4;
    private static int REQUEST_CODE;

    private BaseActivity parentActivity;



    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "FIvEBBrj9g9NUXeBzn0gRWzj";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "FIvEBBrj9g9NUXeBzn0gRWzj";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }



    public  void detect(String filePath, int requestCode, Activity parentActivity) {

        this.filePath = filePath;

        //设置全局变量：请求码
        REQUEST_CODE = requestCode;

        //设置全局变量：主活动
        this.parentActivity = (BaseActivity) parentActivity;

        new detectImage().execute();
    }


    /**
     * AsyncTask 三个泛型参数的具体含义
     *
     * Params：在执行AsyncTask时需要传入的参数，可用于在后台任务中使用,这里是void，即为空
     * Progress：后台任务执行时，如果需要在界面上显示当前的进度，则使用这里的泛型作为进度单位
     * Result：当任务执行完毕后，如果需要对结果返回，则使用这里的泛型最为返回值类型
     */
    class detectImage extends AsyncTask<Integer,Integer,String> {

        ProgressDialog waitingDialog=
                new ProgressDialog(parentActivity);

        /**
         * 在后台任务执行之前调用这个函数，一般进行界面上的初始化工作，比如显示一个进度条或者加载中的对话框。
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitingDialog.setMessage("图片分析中……");
            waitingDialog.setIndeterminate(true);
            waitingDialog.setCancelable(false);
            waitingDialog.show();
        }

        /**
         * 我们应该在这里处理所有的耗时任务。任务一旦完成就可以通过return语句将任务的执行结果返回。
         * AsyncTask的第三个泛型参数就是指定这里的任务返回的数据类型。
         *
         * @param integers
         * @return
         */
        protected String doInBackground(Integer... integers) {
            String url;
            //根据请求码的不同，选择不同的请求地址
            switch (REQUEST_CODE){
                case REQUEST_ANIMAL:
                    url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/animal";
                    break;
                case REQUEST_PLANT:
                    url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant";
                    break;
                case REQUEST_DISH:
                    url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/dish";
                    break;
                case REQUEST_CAR:
                    url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/car";
                    break;
                default:
                    url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/object_detect";
                    break;
            }

            try {
                byte[] imgData = FileUtil.readFileByBytes(filePath);

                //图片编码成Base64
                String imgStr = Base64Util.encode(imgData);
                String imgParam = URLEncoder.encode(imgStr, "UTF-8");

                String param = "image=" + imgParam + "&with_face=" + 1;

                // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
                String accessToken = "24.81881188d00fcd3633f5f3ee85fb1f28.2592000.1512649689.282335-10335218";

                String result = HttpUtil.post(url, accessToken, param);

                ALog.d(result);
                //System.out.println(result);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            waitingDialog.hide();//关闭等待对话框

            //处理结果信息,启动识别结果的活动
            super.onPostExecute(s);
            Toast.makeText(UIUtils.getContext(),s,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(UIUtils.getContext(),SearchImageResultsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("SEARCH_RESULTS",s);
            intent.putExtras(bundle);
            UIUtils.getContext().startActivity(intent);

        }
    }





}
