package cn.edu.buct.areatour.features.exhibition.search.searchbyimage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/08
 *     desc   : 接收以图搜图之类别搜索的接口返回的数据
 *     version: 1.0
 * </pre>
 */
public class SearchResultJson implements Serializable {

    public Long log_id;
    public int result_num;
    public List<ImageSearchResultsModule> result = new ArrayList<ImageSearchResultsModule>();

    public SearchResultJson() {
    }

    public Long getLog_id() {
        return log_id;
    }

    public void setLog_id(Long log_id) {
        this.log_id = log_id;
    }

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public List<ImageSearchResultsModule> getResult() {
        return result;
    }

    public void setResult(List<ImageSearchResultsModule> result) {
        this.result = result;
    }

    public void addResult(ImageSearchResultsModule user) {
        result.add(user);
    }
}
