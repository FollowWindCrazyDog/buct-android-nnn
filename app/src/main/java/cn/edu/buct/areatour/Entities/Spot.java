package cn.edu.buct.areatour.Entities;

public class Spot {
    private Integer spotid;

    private Integer expobjid;

    private Integer museumid;

    private String spotname;

    private String spotyear;

    private String spotstyle;

    Expobject expobject;

    public Expobject getExpobject() {
        return expobject;
    }

    public void setExpobject(Expobject expobject) {
        this.expobject = expobject;
    }

    public Integer getSpotid() {
        return spotid;
    }

    public void setSpotid(Integer spotid) {
        this.spotid = spotid;
    }

    public Integer getExpobjid() {
        return expobjid;
    }

    public void setExpobjid(Integer expobjid) {
        this.expobjid = expobjid;
    }

    public Integer getMuseumid() {
        return museumid;
    }

    public void setMuseumid(Integer museumid) {
        this.museumid = museumid;
    }

    public String getSpotname() {
        return spotname;
    }

    public void setSpotname(String spotname) {
        this.spotname = spotname == null ? null : spotname.trim();
    }

    public String getSpotyear() {
        return spotyear;
    }

    public void setSpotyear(String spotyear) {
        this.spotyear = spotyear == null ? null : spotyear.trim();
    }

    public String getSpotstyle() {
        return spotstyle;
    }

    public void setSpotstyle(String spotstyle) {
        this.spotstyle = spotstyle == null ? null : spotstyle.trim();
    }
}