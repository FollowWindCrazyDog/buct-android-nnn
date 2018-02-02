package cn.edu.buct.areatour.Entities;

public class Expobjimage {
    private Integer expobjimageid;

    private Integer expobjid;

    private String expobjimagename;

    public Expobjimage() {
        super();
    }

    public Integer getExpobjimageid() {
        return expobjimageid;
    }

    public void setExpobjimageid(Integer expobjimageid) {
        this.expobjimageid = expobjimageid;
    }

    public Integer getExpobjid() {
        return expobjid;
    }

    public void setExpobjid(Integer expobjid) {
        this.expobjid = expobjid;
    }

    public String getExpobjimagename() {
        return expobjimagename;
    }

    public void setExpobjimagename(String expobjimagename) {
        this.expobjimagename = expobjimagename == null ? null : expobjimagename.trim();
    }
}