package cn.edu.buct.areatour.Entities;

import java.util.Date;

public class Comexpobj {
    private Integer comexpobjid;

    private Integer userid;

    private Integer expobjid;

    private String comexpobjcontent;

    private Integer comexpobjrepliedcount;

    private Integer comexpobjreplyid;

    private Integer comexpobjlikedcount;

    private Integer comexpobjreportedcount;

    private Date comexpobjtime;

    private Date comexpobjdeletedtime;

    public Integer getComexpobjid() {
        return comexpobjid;
    }

    public void setComexpobjid(Integer comexpobjid) {
        this.comexpobjid = comexpobjid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getExpobjid() {
        return expobjid;
    }

    public void setExpobjid(Integer expobjid) {
        this.expobjid = expobjid;
    }

    public String getComexpobjcontent() {
        return comexpobjcontent;
    }

    public void setComexpobjcontent(String comexpobjcontent) {
        this.comexpobjcontent = comexpobjcontent == null ? null : comexpobjcontent.trim();
    }

    public Integer getComexpobjrepliedcount() {
        return comexpobjrepliedcount;
    }

    public void setComexpobjrepliedcount(Integer comexpobjrepliedcount) {
        this.comexpobjrepliedcount = comexpobjrepliedcount;
    }

    public Integer getComexpobjreplyid() {
        return comexpobjreplyid;
    }

    public void setComexpobjreplyid(Integer comexpobjreplyid) {
        this.comexpobjreplyid = comexpobjreplyid;
    }

    public Integer getComexpobjlikedcount() {
        return comexpobjlikedcount;
    }

    public void setComexpobjlikedcount(Integer comexpobjlikedcount) {
        this.comexpobjlikedcount = comexpobjlikedcount;
    }

    public Integer getComexpobjreportedcount() {
        return comexpobjreportedcount;
    }

    public void setComexpobjreportedcount(Integer comexpobjreportedcount) {
        this.comexpobjreportedcount = comexpobjreportedcount;
    }

    public Date getComexpobjtime() {
        return comexpobjtime;
    }

    public void setComexpobjtime(Date comexpobjtime) {
        this.comexpobjtime = comexpobjtime;
    }

    public Date getComexpobjdeletedtime() {
        return comexpobjdeletedtime;
    }

    public void setComexpobjdeletedtime(Date comexpobjdeletedtime) {
        this.comexpobjdeletedtime = comexpobjdeletedtime;
    }
}