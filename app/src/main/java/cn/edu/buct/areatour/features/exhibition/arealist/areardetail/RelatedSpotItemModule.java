package cn.edu.buct.areatour.features.exhibition.arealist.areardetail;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RelatedSpotItemModule {
    private int expobjId;
    private String imageName;
    private String name;

    public RelatedSpotItemModule(int expobjId, String imageName, String name) {
        this.expobjId = expobjId;
        this.imageName = imageName;
        this.name = name;
    }

    public int getExpobjId() {
        return expobjId;
    }

    public void setExpobjId(int expobjId) {
        this.expobjId = expobjId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
