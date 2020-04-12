package io.fr0st.coronavirusmeter.model;

public class Covid19IndiaOutputVO {

	private String state;
	private String district;
	private int totalCases;
	private int newCasesToday;
	private double todaysSurge;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	public int getNewCasesToday() {
		return newCasesToday;
	}
	public void setNewCasesToday(int newCasesToday) {
		this.newCasesToday = newCasesToday;
	}
	public double getTodaysSurge() {
		return todaysSurge;
	}
	public void setTodaysSurge(double todaysSurge) {
		this.todaysSurge = todaysSurge;
	}
}
