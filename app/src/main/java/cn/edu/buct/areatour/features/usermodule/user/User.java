package cn.edu.buct.areatour.features.usermodule.user;

/**
 * Created by 11633 on 2018/1/30.
 */


public class User {
    private Integer userid;

    private Integer anchorid;

    private String useraccount;

    private String username;

    private String userpassword;

    private String userlogintype;

    private Integer userfancount;

    private Integer userfollowercount;

    private Integer usercoin;

    private Integer userbuyexpcount;

    private Integer userordercount;

    private Integer useruploadexpcount;

    private Integer usercollectcount;

    private Integer userdownloadexpcount;

    private Integer userbrowsecount;

    private Integer userrewardcount;

    private Boolean usertoanchor;

    private String useravatar;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAnchorid() {
        return anchorid;
    }

    public void setAnchorid(Integer anchorid) {
        this.anchorid = anchorid;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount == null ? null : useraccount.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getUserlogintype() {
        return userlogintype;
    }

    public void setUserlogintype(String userlogintype) {
        this.userlogintype = userlogintype == null ? null : userlogintype.trim();
    }

    public Integer getUserfancount() {
        return userfancount;
    }

    public void setUserfancount(Integer userfancount) {
        this.userfancount = userfancount;
    }

    public Integer getUserfollowercount() {
        return userfollowercount;
    }

    public void setUserfollowercount(Integer userfollowercount) {
        this.userfollowercount = userfollowercount;
    }

    public Integer getUsercoin() {
        return usercoin;
    }

    public void setUsercoin(Integer usercoin) {
        this.usercoin = usercoin;
    }

    public Integer getUserbuyexpcount() {
        return userbuyexpcount;
    }

    public void setUserbuyexpcount(Integer userbuyexpcount) {
        this.userbuyexpcount = userbuyexpcount;
    }

    public Integer getUserordercount() {
        return userordercount;
    }

    public void setUserordercount(Integer userordercount) {
        this.userordercount = userordercount;
    }

    public Integer getUseruploadexpcount() {
        return useruploadexpcount;
    }

    public void setUseruploadexpcount(Integer useruploadexpcount) {
        this.useruploadexpcount = useruploadexpcount;
    }

    public Integer getUsercollectcount() {
        return usercollectcount;
    }

    public void setUsercollectcount(Integer usercollectcount) {
        this.usercollectcount = usercollectcount;
    }

    public Integer getUserdownloadexpcount() {
        return userdownloadexpcount;
    }

    public void setUserdownloadexpcount(Integer userdownloadexpcount) {
        this.userdownloadexpcount = userdownloadexpcount;
    }

    public Integer getUserbrowsecount() {
        return userbrowsecount;
    }

    public void setUserbrowsecount(Integer userbrowsecount) {
        this.userbrowsecount = userbrowsecount;
    }

    public Integer getUserrewardcount() {
        return userrewardcount;
    }

    public void setUserrewardcount(Integer userrewardcount) {
        this.userrewardcount = userrewardcount;
    }

    public Boolean getUsertoanchor() {
        return usertoanchor;
    }

    public void setUsertoanchor(Boolean usertoanchor) {
        this.usertoanchor = usertoanchor;
    }

    public String getUseravatar() {
        return useravatar;
    }

    public void setUseravatar(String useravatar) {
        this.useravatar = useravatar == null ? null : useravatar.trim();
    }
}