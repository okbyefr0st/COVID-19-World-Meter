package io.fr0st.coronavirusmeter.model.Cov19WorldDataService.VO;

public class Cov19WorldDataVO {

	private String province;
	private String country;
	private int totalNumberOfCases;
	private int todaysNewCases;
	private double todaysSurge;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getTotalNumberOfCases() {
		return totalNumberOfCases;
	}

	public void setTotalNumberOfCases(int totalNumberOfCases) {
		this.totalNumberOfCases = totalNumberOfCases;
	}

	public int getTodaysNewCases() {
		return todaysNewCases;
	}

	public void setTodaysNewCases(int todaysNewCases) {
		this.todaysNewCases = todaysNewCases;
	}

	public double getTodaysSurge() {
		return todaysSurge;
	}

	public void setTodaysSurge(double todaysSurge) {
		this.todaysSurge = todaysSurge;
	}

	@Override
	public String toString() {
		return "Covid19DataVO [province=" + province + ", country=" + country + ", totalNumberOfCases="
				+ totalNumberOfCases + ", todaysNewCases=" + todaysNewCases + ", todaysSurge=" + todaysSurge + "]";
	}
}
