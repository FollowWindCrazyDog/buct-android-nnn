package cn.edu.buct.areatour.common.module;

import java.io.Serializable;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/04
 *     desc   : 音频播放的模型类
 *     version: 1.0
 * </pre>
 */
public class AudioModule  implements Serializable {
    private int audioId;
    private String audioName;
    private int commentNum;
    private String audiodata;
    private String audiotime;

    public AudioModule(int audioId, String audioName, int commentNum, String audiodata,String audiotime) {
        this.audioId = audioId;
        this.audioName = audioName;
        this.commentNum = commentNum;
        this.audiodata = audiodata;
        this.audiotime = audiotime;
    }

    public AudioModule() {
        super();
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getAudiodata() {
        return audiodata;
    }

    public void setAudiodata(String audiodata) {
        this.audiodata = audiodata;
    }

    public String getAudiotime() {
        return audiotime;
    }

    public void setAudiotime(String audiotime) {
        this.audiotime = audiotime;
    }
}
