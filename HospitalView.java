package com.easycarehub;

public class HospitalView {
	private String  thumbnailUrl;
	private String hospname;

	public String getBedsava() {
		return bedsava;
	}

	public void setBedsava(String bedsava) {
		this.bedsava = bedsava;
	}

	private  String bedsava;
	public String getHospname() {
		return hospname;
	}

	public void setHospname(String hospname) {
		this.hospname = hospname;
	}

	public String getCatgry() {
		return catgry;
	}

	public void setCatgry(String catgry) {
		this.catgry = catgry;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHospid() {
		return hospid;
	}

	public void setHospid(String hospid) {
		this.hospid = hospid;
	}

	private String catgry;
	private String city;
	private String hospid;

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}


}
