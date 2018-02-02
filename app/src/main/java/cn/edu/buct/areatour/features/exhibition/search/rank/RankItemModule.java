package cn.edu.buct.areatour.features.exhibition.search.rank;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/09
 *     desc   : 排行榜Item的数据模型类
 *     version: 1.0
 * </pre>
 */
public class RankItemModule {
    private int imageId;
    private String title;
    private String score;

    public RankItemModule(int imageId, String title, String score) {
        this.imageId = imageId;
        this.title = title;
        this.score = score;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
