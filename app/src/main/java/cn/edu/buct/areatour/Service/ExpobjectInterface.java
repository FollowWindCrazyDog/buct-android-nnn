package cn.edu.buct.areatour.Service;

import java.util.List;
import java.util.Map;

import cn.edu.buct.areatour.Entities.Expobject;
import cn.edu.buct.areatour.Entities.Museum;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Freyao on 2017/11/22.
 */

public interface ExpobjectInterface {

    /**接口名字：aa
     * @FormUrlEncoded  post请求需要添加的编码
     * @POST("LoginServlet")  post请求和请求接口(url地址的最后一个/后的字符串)
     * @Field("username")  post请求的参数名称(服务器接口参数名称)，会自动添加到参数集合
     * String uname:本地的参数名称，unname是自己定义的
     *返回值： Call<T>,T是返回的类型
     */
    @FormUrlEncoded
    @POST("expObject/listExpobjects")
    public Call<List<Expobject>> listExpobjects(@FieldMap Map<String,String> pramsMap);
    @GET("expObject/listExpobjects")
    public Call<String> listExpobject(@QueryMap Map<String,String> pramsMap);

    @FormUrlEncoded
    @POST("expObject/showExpobject")
    public Call<Museum> showExpobjectInfo(String expobjid);
    @GET("expObject/showExpobject")
    public Call<String> showExpobject(@QueryMap Map<String,String> pramsMap);
}
