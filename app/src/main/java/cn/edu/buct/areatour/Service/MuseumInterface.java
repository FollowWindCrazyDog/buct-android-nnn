package cn.edu.buct.areatour.Service;

import java.util.Map;

import cn.edu.buct.areatour.Entities.Museum;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Freyao on 2018/1/9.
 */

public interface MuseumInterface {

    @FormUrlEncoded
    @POST("museum/showMuseumRelateInfo")
    public Call<Museum> showMuseumRelateInfo(@Field("expobjid") String expobjid);
    @GET("museum/showMuseumRelateInfo")
    public Call<String> showMuseumRelateInfo(@QueryMap Map<String,String> pramsMap);
}
