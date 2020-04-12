package io.fr0st.coronavirusmeter.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Covid19IndiaVO {

	@JsonProperty("state")
	private String state;
	
	@JsonProperty("districtData")
	private List<Covid19DistrictVO> districtData ;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Covid19DistrictVO> getCovid19DistrictVO() {
		return districtData;
	}
	public void setCovid19DistrictVO(List<Covid19DistrictVO> districtData) {
		this.districtData = districtData;
	}
	@Override
	public String toString() {
		return "Covid19IndiaVO [state=" + state + ", districtData=" + districtData + "]";
	}
	
}
