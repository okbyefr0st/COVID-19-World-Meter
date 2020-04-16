package io.fr0st.coronavirusmeter.model.Cov19IndiaDataService.VO;

import java.util.List;

public class Cov19IndiaOutputVO {
	private List<Cov19IndiaListVO> covid19IndiaDataList;
	private int totalReportedCases;
	private int totalNewCases;
	private double avgSurge;

	public List<Cov19IndiaListVO> getCovid19IndiaDataList() {
		return covid19IndiaDataList;
	}

	public void setCovid19IndiaDataList(List<Cov19IndiaListVO> covid19IndiaDataList) {
		this.covid19IndiaDataList = covid19IndiaDataList;
	}

	public int getTotalReportedCases() {
		return totalReportedCases;
	}

	public void setTotalReportedCases(int totalReportedCases) {
		this.totalReportedCases = totalReportedCases;
	}

	public int getTotalNewCases() {
		return totalNewCases;
	}

	public void setTotalNewCases(int totalNewCases) {
		this.totalNewCases = totalNewCases;
	}

	public double getAvgSurge() {
		return avgSurge;
	}

	public void setAvgSurge(double avgSurge) {
		this.avgSurge = avgSurge;
	}
}
