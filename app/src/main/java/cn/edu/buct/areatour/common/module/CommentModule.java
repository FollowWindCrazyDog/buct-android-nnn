package cn.edu.buct.areatour.common.module;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CommentModule {

    private String userName;
    private String commentContent;
    private String commentDate;
    private String userAvatar;


    public CommentModule(String userName, String commentContent, String commentDate, String userAvatar) {
        this.userName = userName;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
