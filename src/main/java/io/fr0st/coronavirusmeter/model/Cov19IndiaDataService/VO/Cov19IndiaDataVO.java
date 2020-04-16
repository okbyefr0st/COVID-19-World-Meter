package io.fr0st.coronavirusmeter.model.Cov19IndiaDataService.VO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cov19IndiaDataVO {

	@JsonProperty("state")
	private String state;

	@JsonProperty("districtData")
	private List<Cov19DistrictVO> districtData;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Cov19DistrictVO> getCovid19DistrictVO() {
		return districtData;
	}

	public void setCovid19DistrictVO(List<Cov19DistrictVO> districtData) {
		this.districtData = districtData;
	}

	@Override
	public String toString() {
		return "Covid19IndiaVO [state=" + state + ", districtData=" + districtData + "]";
	}

}
