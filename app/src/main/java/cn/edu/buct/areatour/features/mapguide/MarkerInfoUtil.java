package cn.edu.buct.areatour.features.mapguide;

/**
 * Created by Xiaopeng on 2017/11/8.
 */

import java.io.Serializable;

/**
 * <pre>
 *     author : iSharpener 李炳
 *     e-mail : 1437138419@qq.com
 *     time   : 2017/11/8
 *     desc   :地图覆盖物Marker类
 *     version: 1.0
 * </pre>
 */
public class MarkerInfoUtil implements Serializable{
    private double latitude;//纬度
    private double longitude;//经度
    private String name;//名字
    private int imgId;//图片
    private String description;//描述
    //构造方法
    public MarkerInfoUtil() {}
    public MarkerInfoUtil(double latitude, double longitude, String name, int imgId, String description) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.imgId = imgId;
        this.description = description;
    }
    //toString方法
    @Override
    public String toString() {
        return "MarkerInfoUtil [latitude=" + latitude + ", longitude=" + longitude + ", name=" + name + ", imgId="
                + imgId + ", description=" + description + "]";
    }
    //getter setter
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}