package cn.edu.buct.areatour.Entities;

import java.util.List;

public class Museum {
    private Integer museumid;

    private Integer expobjid;

    private String museumopentime;

    private String museumticketdetail;

    private String museumphonecall;

    private String museumtype;

    private String museumenglishname;

    List<Spot> spot;

    List<Exhibition> exhibition;

//    Expobject expobject;

    public Museum() {
        super();
    }

/*    public Expobject getExpobject() {
        return expobject;
    }

    public void setExpobject(Expobject expobject) {
        this.expobject = expobject;
    }*/

    public List<Exhibition> getExhibition() {
        return exhibition;
    }

    public void setExhibition(List<Exhibition> exhibition) {
        this.exhibition = exhibition;
    }

    public List<Spot> getSpot() {
        return spot;
    }

    public void setSpot(List<Spot> spot) {
        this.spot = spot;
    }

    public Integer getMuseumid() {
        return museumid;
    }

    public void setMuseumid(Integer museumid) {
        this.museumid = museumid;
    }

    public Integer getExpobjid() {
        return expobjid;
    }

    public void setExpobjid(Integer expobjid) {
        this.expobjid = expobjid;
    }

    public String getMuseumopentime() {
        return museumopentime;
    }

    public void setMuseumopentime(String museumopentime) {
        this.museumopentime = museumopentime == null ? null : museumopentime.trim();
    }

    public String getMuseumticketdetail() {
        return museumticketdetail;
    }

    public void setMuseumticketdetail(String museumticketdetail) {
        this.museumticketdetail = museumticketdetail == null ? null : museumticketdetail.trim();
    }

    public String getMuseumphonecall() {
        return museumphonecall;
    }

    public void setMuseumphonecall(String museumphonecall) {
        this.museumphonecall = museumphonecall == null ? null : museumphonecall.trim();
    }

    public String getMuseumtype() {
        return museumtype;
    }

    public void setMuseumtype(String museumtype) {
        this.museumtype = museumtype == null ? null : museumtype.trim();
    }

    public String getMuseumenglishname() {
        return museumenglishname;
    }

    public void setMuseumenglishname(String museumenglishname) {
        this.museumenglishname = museumenglishname;
    }
}