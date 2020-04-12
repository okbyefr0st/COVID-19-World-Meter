package io.fr0st.coronavirusmeter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeltaDataVO {
	@JsonProperty("confirmed")
	private int confirmed;

	public int getTodaysNewCase() {
		return confirmed;
	}

	public void setTodaysNewCase(int confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	public String toString() {
		return "DeltaDataVO [confirmed=" + confirmed + "]";
	}

}
