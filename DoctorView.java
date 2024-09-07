package com.easycarehub;

public class DoctorView {
	private String  thumbnailUrl;

	public String getDoctname() {
		return doctname;
	}

	public void setDoctname(String doctname) {
		this.doctname = doctname;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getDoctid() {
		return doctid;
	}

	public void setDoctid(String doctid) {
		this.doctid = doctid;
	}

	private String doctname,exp;



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



	private String catgry;
	private String city;
	private String doctid;

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}


}
