package io.fr0st.coronavirusmeter.model.Cov19WorldDataService.VO;

import java.util.List;

public class Cov19WorldOutputVO {
	private List<Cov19WorldDataVO> covid19DataList;
	private int totalReportedCases;
	private int totalNewCases;
	private double avgSurge;

	public List<Cov19WorldDataVO> getCovid19DataList() {
		return covid19DataList;
	}

	public void setCovid19DataList(List<Cov19WorldDataVO> covid19DataList) {
		this.covid19DataList = covid19DataList;
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
