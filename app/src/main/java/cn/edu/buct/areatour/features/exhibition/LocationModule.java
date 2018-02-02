package cn.edu.buct.areatour.features.exhibition;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class LocationModule {
    private String locationName;
    private int expobjid;
    private int imageId;
    private String locationEnglishName;
    private String locationIntroduction;
    private String locationImagename;

    public LocationModule(String locationName, int expobjid, int imageId, String locationEnglishName, String locationIntroduction,String locationImagename){
        this.locationName = locationName;
        this.expobjid = expobjid;
        this.imageId = imageId;
        this.locationEnglishName = locationEnglishName;
        this.locationIntroduction = locationIntroduction;
        this.locationImagename = locationImagename;
    }

    public int getExpobjid(){ return expobjid; }

    public  void setExpobjid(int expobjid){
        this.expobjid = expobjid;
    }

    public String getLocationEnglishName() {
        return locationEnglishName;
    }

    public void setLocationEnglishName(String locationEnglishName) {
        this.locationEnglishName = locationEnglishName;
    }

    public String getLocationIntroduction() {
        return locationIntroduction;
    }

    public void setLocationIntroduction(String locationIntroduction) {
        this.locationIntroduction = locationIntroduction;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    public String getLocationImagename() {
        return locationImagename;
    }

    public void setLocationImagename(String locationImagename) {
        this.locationImagename = locationImagename;
    }
}
