package io.fr0st.coronavirusmeter.service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.fr0st.coronavirusmeter.model.Covid19ResourceOutputVO;
import io.fr0st.coronavirusmeter.model.IndianResourcesVO;
import io.fr0st.coronavirusmeter.model.ResourceDetailsVO;

@Service
public class Covid19IndiaEssentialsService {

	public final static String COVID_19_INDIA_RESOURCES_URL = "https://api.covid19india.org/resources/resources.json";
	List<Covid19ResourceOutputVO> outputList = new ArrayList<>();

	public List<Covid19ResourceOutputVO> getOutputList() {
		return outputList;
	}

	public void setOutputList(List<Covid19ResourceOutputVO> outputList) {
		this.outputList = outputList;
	}

	@PostConstruct
	public void fetchIndianResources() throws MalformedURLException, JsonMappingException, JsonProcessingException {
		
		final String url = COVID_19_INDIA_RESOURCES_URL;
		List<Covid19ResourceOutputVO> details = new ArrayList<>(); 
		RestTemplate resttemplate = new RestTemplate();
		String responseJson = resttemplate.getForObject(url, String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		IndianResourcesVO indianResources = objectMapper.readValue(responseJson, IndianResourcesVO.class);
		
		if(null != indianResources) {
				List<ResourceDetailsVO> responseList = indianResources.getResources();
				for(int j=0;j<responseList.size();j++) {
					Covid19ResourceOutputVO resourceDetails = new Covid19ResourceOutputVO();
					resourceDetails.setCategory(responseList.get(j).getCategory());
					resourceDetails.setCity(responseList.get(j).getCity());
					resourceDetails.setContact(responseList.get(j).getContact());
					resourceDetails.setDescriptionandorserviceprovided(responseList.get(j).getDescriptionandorserviceprovided());
					resourceDetails.setNameoftheorganisation(responseList.get(j).getNameoftheorganisation());
					resourceDetails.setPhonenumber(responseList.get(j).getPhonenumber());
					resourceDetails.setState(responseList.get(j).getState());
					details.add(resourceDetails);
				}
			}
		this.outputList = details;
	}
}
