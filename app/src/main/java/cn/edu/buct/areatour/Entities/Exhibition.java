package cn.edu.buct.areatour.Entities;

public class Exhibition {
    private Integer exhibitionid;

    private Integer expobjid;

    private String exhibitiontheme;

    private String exhibitiontype;

    private String exhibitionsponsor;

    private String exhibitiondirector;

    private String exhibitionsponsorphone;

    private String exhibitionstarttime;

    private String exhibitionendtime;

    Expobject expobject;

    public Expobject getExpobject() {
        return expobject;
    }

    public void setExpobject(Expobject expobject) {
        this.expobject = expobject;
    }

    public Integer getExhibitionid() {
        return exhibitionid;
    }

    public void setExhibitionid(Integer exhibitionid) {
        this.exhibitionid = exhibitionid;
    }

    public Integer getExpobjid() {
        return expobjid;
    }

    public void setExpobjid(Integer expobjid) {
        this.expobjid = expobjid;
    }

    public String getExhibitiontheme() {
        return exhibitiontheme;
    }

    public void setExhibitiontheme(String exhibitiontheme) {
        this.exhibitiontheme = exhibitiontheme == null ? null : exhibitiontheme.trim();
    }

    public String getExhibitiontype() {
        return exhibitiontype;
    }

    public void setExhibitiontype(String exhibitiontype) {
        this.exhibitiontype = exhibitiontype == null ? null : exhibitiontype.trim();
    }

    public String getExhibitionsponsor() {
        return exhibitionsponsor;
    }

    public void setExhibitionsponsor(String exhibitionsponsor) {
        this.exhibitionsponsor = exhibitionsponsor == null ? null : exhibitionsponsor.trim();
    }

    public String getExhibitiondirector() {
        return exhibitiondirector;
    }

    public void setExhibitiondirector(String exhibitiondirector) {
        this.exhibitiondirector = exhibitiondirector == null ? null : exhibitiondirector.trim();
    }

    public String getExhibitionsponsorphone() {
        return exhibitionsponsorphone;
    }

    public void setExhibitionsponsorphone(String exhibitionsponsorphone) {
        this.exhibitionsponsorphone = exhibitionsponsorphone == null ? null : exhibitionsponsorphone.trim();
    }

    public String getExhibitionstarttime() {
        return exhibitionstarttime;
    }

    public void setExhibitionstarttime(String exhibitionstarttime) {
        this.exhibitionstarttime = exhibitionstarttime == null ? null : exhibitionstarttime.trim();
    }

    public String getExhibitionendtime() {
        return exhibitionendtime;
    }

    public void setExhibitionendtime(String exhibitionendtime) {
        this.exhibitionendtime = exhibitionendtime == null ? null : exhibitionendtime.trim();
    }
}