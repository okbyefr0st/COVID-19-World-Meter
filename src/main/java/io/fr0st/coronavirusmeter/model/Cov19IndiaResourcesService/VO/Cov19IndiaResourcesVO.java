package io.fr0st.coronavirusmeter.model.Cov19IndiaResourcesService.VO;

import java.util.List;

public class Cov19IndiaResourcesVO {

	private List<ResourceDetailsVO> resources;

	public List<ResourceDetailsVO> getResources() {
		return resources;
	}

	public void setResources(List<ResourceDetailsVO> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "IndianResourcesVO [resources=" + resources + "]";
	}

}
