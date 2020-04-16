package io.fr0st.coronavirusmeter.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.fr0st.coronavirusmeter.model.Cov19IndiaResourcesService.VO.Cov19IndiaResourcesOutputVO;
import io.fr0st.coronavirusmeter.model.Cov19IndiaResourcesService.VO.Cov19IndiaResourcesVO;
import io.fr0st.coronavirusmeter.model.Cov19IndiaResourcesService.VO.ResourceDetailsVO;

@Service
public class Cov19IndiaResourcesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(Cov19IndiaResourcesService.class);

	public final static String COVID_19_INDIA_RESOURCES_URL = "https://api.covid19india.org/resources/resources.json";
	List<Cov19IndiaResourcesOutputVO> outputList = new ArrayList<>();

	@PostConstruct
	public List<Cov19IndiaResourcesOutputVO> fetchIndianResources() {

		LOGGER.info("Entering in fetchIndianResources()");

		final String url = COVID_19_INDIA_RESOURCES_URL;
		List<Cov19IndiaResourcesOutputVO> details = new ArrayList<>();

		try {
			RestTemplate resttemplate = new RestTemplate();
			String responseJson = resttemplate.getForObject(url, String.class);

			ObjectMapper objectMapper = new ObjectMapper();

			Cov19IndiaResourcesVO cov19IndiaResourcesVO = objectMapper.readValue(responseJson,
					Cov19IndiaResourcesVO.class);

			if (null != cov19IndiaResourcesVO) {
				List<ResourceDetailsVO> responseList = cov19IndiaResourcesVO.getResources();
				for (int j = 0; j < responseList.size(); j++) {
					Cov19IndiaResourcesOutputVO resourceDetails = new Cov19IndiaResourcesOutputVO();
					resourceDetails.setCategory(responseList.get(j).getCategory());
					resourceDetails.setCity(responseList.get(j).getCity());
					resourceDetails.setContact(responseList.get(j).getContact());
					resourceDetails.setDescriptionandorserviceprovided(
							responseList.get(j).getDescriptionandorserviceprovided());
					resourceDetails.setNameoftheorganisation(responseList.get(j).getNameoftheorganisation());
					resourceDetails.setPhonenumber(responseList.get(j).getPhonenumber());
					resourceDetails.setState(responseList.get(j).getState());
					details.add(resourceDetails);
				}
			}
			this.outputList = details;
		} catch (JsonMappingException e) {
			LOGGER.error("Error occured in JsonMapping");
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			LOGGER.error("Error occured in JsonProcessing");
			e.printStackTrace();
		}
		LOGGER.info("Exiting from fetchIndianResources()");

		return outputList;
	}
}
