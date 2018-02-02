package cn.edu.buct.areatour.Entities;

public class Explanation {
    private Integer expid;

    private Integer expobjid;

    private Integer anchorid;

    private String expname;

    private String exptextcontent;

    private String expsubject;

    private String exptype;

    private Integer expreportedcount;

    private Integer explikedcount;

    private Integer expcollectedcount;

    private Integer expbrowsedcount;

    private Integer expdownloadedcount;

    private Integer expcommentedcount;

    private Integer explistenedcount;

    private Integer expprice;

    private String expuploadtime;

    private String expduration;

    private Expobject expobject;

    public Integer getExpid() {
        return expid;
    }

    public void setExpid(Integer expid) {
        this.expid = expid;
    }

    public Integer getExpobjid() {
        return expobjid;
    }

    public void setExpobjid(Integer expobjid) {
        this.expobjid = expobjid;
    }

    public Integer getAnchorid() {
        return anchorid;
    }

    public void setAnchorid(Integer anchorid) {
        this.anchorid = anchorid;
    }

    public String getExpname() {
        return expname;
    }

    public void setExpname(String expname) {
        this.expname = expname == null ? null : expname.trim();
    }

    public String getExptextcontent() {
        return exptextcontent;
    }

    public void setExptextcontent(String exptextcontent) {
        this.exptextcontent = exptextcontent == null ? null : exptextcontent.trim();
    }

    public String getExpsubject() {
        return expsubject;
    }

    public void setExpsubject(String expsubject) {
        this.expsubject = expsubject == null ? null : expsubject.trim();
    }

    public String getExptype() {
        return exptype;
    }

    public void setExptype(String exptype) {
        this.exptype = exptype == null ? null : exptype.trim();
    }

    public Integer getExpreportedcount() {
        return expreportedcount;
    }

    public void setExpreportedcount(Integer expreportedcount) {
        this.expreportedcount = expreportedcount;
    }

    public Integer getExplikedcount() {
        return explikedcount;
    }

    public void setExplikedcount(Integer explikedcount) {
        this.explikedcount = explikedcount;
    }

    public Integer getExpcollectedcount() {
        return expcollectedcount;
    }

    public void setExpcollectedcount(Integer expcollectedcount) {
        this.expcollectedcount = expcollectedcount;
    }

    public Integer getExpbrowsedcount() {
        return expbrowsedcount;
    }

    public void setExpbrowsedcount(Integer expbrowsedcount) {
        this.expbrowsedcount = expbrowsedcount;
    }

    public Integer getExpdownloadedcount() {
        return expdownloadedcount;
    }

    public void setExpdownloadedcount(Integer expdownloadedcount) {
        this.expdownloadedcount = expdownloadedcount;
    }

    public Integer getExpcommentedcount() {
        return expcommentedcount;
    }

    public void setExpcommentedcount(Integer expcommentedcount) {
        this.expcommentedcount = expcommentedcount;
    }

    public Integer getExplistenedcount() {
        return explistenedcount;
    }

    public void setExplistenedcount(Integer explistenedcount) {
        this.explistenedcount = explistenedcount;
    }

    public Integer getExpprice() {
        return expprice;
    }

    public void setExpprice(Integer expprice) {
        this.expprice = expprice;
    }

    public String getExpuploadtime() {
        return expuploadtime;
    }

    public void setExpuploadtime(String expuploadtime) {
        this.expuploadtime = expuploadtime == null ? null : expuploadtime.trim();
    }

    public String getExpduration() {
        return expduration;
    }

    public void setExpduration(String expduration) {
        this.expduration = expduration == null ? null : expduration.trim();
    }

    public Expobject getExpobject() {
        return expobject;
    }

    public void setExpobject(Expobject expobject) {
        this.expobject = expobject;
    }
}