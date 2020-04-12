package io.fr0st.coronavirusmeter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Covid19DistrictVO {
	
	@JsonProperty("district")
	private String district;
	
	@JsonProperty("confirmed")
	private int confirmed;
	
	@JsonProperty("lastupdatedtime")
	private String lastupdatedtime;
	
	@JsonProperty("delta")
	private DeltaDataVO delta;
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}
	public String getLastupdatedtime() {
		return lastupdatedtime;
	}
	public void setLastupdatedtime(String lastupdatedtime) {
		this.lastupdatedtime = lastupdatedtime;
	}
	public DeltaDataVO getDeltaDataVO() {
		return delta;
	}
	public void setDeltaDataVO(DeltaDataVO delta) {
		this.delta = delta;
	}
	
	@Override
	public String toString() {
		return "Covid19DistrictVO [district=" + district + ", confirmed=" + confirmed + ", lastupdatedtime="
				+ lastupdatedtime + ", delta=" + delta + "]";
	}
}
