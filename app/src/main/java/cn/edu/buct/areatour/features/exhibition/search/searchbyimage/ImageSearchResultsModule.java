package cn.edu.buct.areatour.features.exhibition.search.searchbyimage;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/08
 *     desc   : 以图搜索的返回结果的每条数据的对象
 *     version: 1.0
 * </pre>
 */
public class ImageSearchResultsModule {

    private String name;
    private int calorie = -1;
    private String probability;
    private String score;

    public ImageSearchResultsModule() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
