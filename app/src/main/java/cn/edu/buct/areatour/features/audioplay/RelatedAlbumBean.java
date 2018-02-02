package cn.edu.buct.areatour.features.audioplay;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/14
 *     desc   : 相关专辑数据模型类
 *     version: 1.0
 * </pre>
 */
public class RelatedAlbumBean {
    private int imageId;
    private String name;
    private String recommendString;
    private String playNum;
    private int episodeNum;

    public RelatedAlbumBean(int imageId, String name, String recommendString, String playNum, int episodeNum) {
        this.imageId = imageId;
        this.name = name;
        this.recommendString = recommendString;
        this.playNum = playNum;
        this.episodeNum = episodeNum;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecommendString() {
        return recommendString;
    }

    public void setRecommendString(String recommendString) {
        this.recommendString = recommendString;
    }

    public String getPlayNum() {
        return playNum;
    }

    public void setPlayNum(String playNum) {
        this.playNum = playNum;
    }

    public int getEpisodeNum() {
        return episodeNum;
    }

    public void setEpisodeNum(int episodeNum) {
        this.episodeNum = episodeNum;
    }
}
