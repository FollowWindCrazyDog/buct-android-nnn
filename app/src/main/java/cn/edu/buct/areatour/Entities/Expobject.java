package cn.edu.buct.areatour.Entities;

import java.util.ArrayList;
import java.util.List;

public class Expobject{
    private Integer expobjid;

    private Integer spotid;

    private Integer collectionid;

    private Integer exhibitionid;

    private Integer museumid;

    private String expobjname;

    private String expobjintroduction;

    private Float expobjlatitude;

    private Float expobjlongitude;

    private String expobjaddress;

    private Float expobjscore;

    private Integer expobjrank;

    private String expobjsubject;

    private Integer expobjimagecount;

    private Integer expobjexpcount;

    private Integer expobjcommentcount;

    private Integer expobjlikecount;

    private Integer expobjsavecount;

    private Integer expobjvisitcount;

    private Integer expobjectbrowsecount;
    
    private Museum museum;

    private Spot spot;

    private Exhibition exhibition;
    
    private List<Expobjimage> expobjimage;

    private List<Explanation> explanation;

    private List<Comexpobj> comexpobj;

    public Expobject() {
		super();
	}
    
    

	public Expobject(Integer expobjid, Integer spotid, Integer collectionid, Integer exhibitionid, Integer museumid,
			String expobjname, String expobjintroduction, Float expobjlatitude, Float expobjlongitude,
			String expobjaddress, Float expobjscore, Integer expobjrank, String expobjsubject, Integer expobjimagecount,
			Integer expobjexpcount, Integer expobjcommentcount, Integer expobjlikecount, Integer expobjsavecount,
			Integer expobjvisitcount, Integer expobjectbrowsecount, Museum museum,
			ArrayList<Expobjimage> expobjimages) {
		super();
		this.expobjid = expobjid;
		this.spotid = spotid;
		this.collectionid = collectionid;
		this.exhibitionid = exhibitionid;
		this.museumid = museumid;
		this.expobjname = expobjname;
		this.expobjintroduction = expobjintroduction;
		this.expobjlatitude = expobjlatitude;
		this.expobjlongitude = expobjlongitude;
		this.expobjaddress = expobjaddress;
		this.expobjscore = expobjscore;
		this.expobjrank = expobjrank;
		this.expobjsubject = expobjsubject;
		this.expobjimagecount = expobjimagecount;
		this.expobjexpcount = expobjexpcount;
		this.expobjcommentcount = expobjcommentcount;
		this.expobjlikecount = expobjlikecount;
		this.expobjsavecount = expobjsavecount;
		this.expobjvisitcount = expobjvisitcount;
		this.expobjectbrowsecount = expobjectbrowsecount;
		this.museum = museum;
		this.expobjimage  = expobjimage;
	}



	public Integer getExpobjid() {
        return expobjid;
    }

    public void setExpobjid(Integer expobjid) {
        this.expobjid = expobjid;
    }

    public Integer getSpotid() {
        return spotid;
    }

    public void setSpotid(Integer spotid) {
        this.spotid = spotid;
    }

    public Integer getCollectionid() {
        return collectionid;
    }

    public void setCollectionid(Integer collectionid) {
        this.collectionid = collectionid;
    }

    public Integer getExhibitionid() {
        return exhibitionid;
    }

    public void setExhibitionid(Integer exhibitionid) {
        this.exhibitionid = exhibitionid;
    }

    public Integer getMuseumid() {
        return museumid;
    }

    public void setMuseumid(Integer museumid) {
        this.museumid = museumid;
    }

    public String getExpobjname() {
        return expobjname;
    }

    public void setExpobjname(String expobjname) {
        this.expobjname = expobjname == null ? null : expobjname.trim();
    }

    public String getExpobjintroduction() {
        return expobjintroduction;
    }

    public void setExpobjintroduction(String expobjintroduction) {
        this.expobjintroduction = expobjintroduction == null ? null : expobjintroduction.trim();
    }

    public Float getExpobjlatitude() {
        return expobjlatitude;
    }

    public void setExpobjlatitude(Float expobjlatitude) {
        this.expobjlatitude = expobjlatitude;
    }

    public Float getExpobjlongitude() {
        return expobjlongitude;
    }

    public void setExpobjlongitude(Float expobjlongitude) {
        this.expobjlongitude = expobjlongitude;
    }

    public String getExpobjaddress() {
        return expobjaddress;
    }

    public void setExpobjaddress(String expobjaddress) {
        this.expobjaddress = expobjaddress == null ? null : expobjaddress.trim();
    }

    public Float getExpobjscore() {
        return expobjscore;
    }

    public void setExpobjscore(Float expobjscore) {
        this.expobjscore = expobjscore;
    }

    public Integer getExpobjrank() {
        return expobjrank;
    }

    public void setExpobjrank(Integer expobjrank) {
        this.expobjrank = expobjrank;
    }

    public String getExpobjsubject() {
        return expobjsubject;
    }

    public void setExpobjsubject(String expobjsubject) {
        this.expobjsubject = expobjsubject == null ? null : expobjsubject.trim();
    }

    public Integer getExpobjimagecount() {
        return expobjimagecount;
    }

    public void setExpobjimagecount(Integer expobjimagecount) {
        this.expobjimagecount = expobjimagecount;
    }

    public Integer getExpobjexpcount() {
        return expobjexpcount;
    }

    public void setExpobjexpcount(Integer expobjexpcount) {
        this.expobjexpcount = expobjexpcount;
    }

    public Integer getExpobjcommentcount() {
        return expobjcommentcount;
    }

    public void setExpobjcommentcount(Integer expobjcommentcount) {
        this.expobjcommentcount = expobjcommentcount;
    }

    public Integer getExpobjlikecount() {
        return expobjlikecount;
    }

    public void setExpobjlikecount(Integer expobjlikecount) {
        this.expobjlikecount = expobjlikecount;
    }

    public Integer getExpobjsavecount() {
        return expobjsavecount;
    }

    public void setExpobjsavecount(Integer expobjsavecount) {
        this.expobjsavecount = expobjsavecount;
    }

    public Integer getExpobjvisitcount() {
        return expobjvisitcount;
    }

    public void setExpobjvisitcount(Integer expobjvisitcount) {
        this.expobjvisitcount = expobjvisitcount;
    }

    public Integer getExpobjectbrowsecount() {
        return expobjectbrowsecount;
    }

    public void setExpobjectbrowsecount(Integer expobjectbrowsecount) {
        this.expobjectbrowsecount = expobjectbrowsecount;
    }

	public Museum getMuseum() {
		return museum;
	}

	public void setMuseum(Museum museum) {
		this.museum = museum;
	}

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public List<Expobjimage> getExpobjimage() {
		return expobjimage;
	}

	public void setExpobjimage(List<Expobjimage> expobjimage) {
		this.expobjimage = expobjimage;
	}

    public List<Explanation> getExplanation() {
        return explanation;
    }

    public void setExplanation(List<Explanation> explanation) {
        this.explanation = explanation;
    }

    public List<Comexpobj> getComexpobj() {
        return comexpobj;
    }

    public void setComexpobj(List<Comexpobj> comexpobj) {
        this.comexpobj = comexpobj;
    }
}