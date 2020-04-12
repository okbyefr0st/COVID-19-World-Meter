package io.fr0st.coronavirusmeter.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.json.ParseException;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.fr0st.coronavirusmeter.model.Covid19DistrictVO;
import io.fr0st.coronavirusmeter.model.Covid19IndiaOutputVO;
import io.fr0st.coronavirusmeter.model.Covid19IndiaVO;

@Service
public class Covid19IndiaDataService {
	
	public final static String COVID_19_INDIA_DATA_URL = "https://api.covid19india.org/v2/state_district_wise.json";
	private static String VALUE_ZERO_PERCENT = "00.00";
	private static String VALUE_100_PERCENT = "100.00";
	
	List<Covid19IndiaOutputVO> outputList = new ArrayList<Covid19IndiaOutputVO>();
	
	public List<Covid19IndiaOutputVO> getOutputList() {
		return outputList;
	}

	public void setOutputList(List<Covid19IndiaOutputVO> outputList) {
		this.outputList = outputList;
	}

	@PostConstruct
	@Scheduled (fixedRate = 900000)
	public void fetchCovid19IndiaData() throws ParseException, JsonMappingException, JsonProcessingException {
		
		final String url = COVID_19_INDIA_DATA_URL;
		List<Covid19IndiaOutputVO> stats = new ArrayList<>();
		
		RestTemplate resttemplate = new RestTemplate();
		String responseJson = resttemplate.getForObject(url, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		Covid19IndiaVO[] covid19IndiaVO = mapper.readValue(responseJson.trim(), Covid19IndiaVO[].class);
		
		if(null!=covid19IndiaVO) {
			for(int i=0;i<covid19IndiaVO.length;i++) {
				List<Covid19DistrictVO> outputDistrictData = covid19IndiaVO[i].getCovid19DistrictVO();
				for(int j=0;j<outputDistrictData.size();j++) {
					Covid19IndiaOutputVO outputData = new Covid19IndiaOutputVO();
					outputData.setState(covid19IndiaVO[i].getState());
					outputData.setDistrict(outputDistrictData.get(j).getDistrict());
					outputData.setTotalCases(outputDistrictData.get(j).getConfirmed());
					outputData.setNewCasesToday(outputDistrictData.get(j).getDeltaDataVO().getTodaysNewCase());
					
					double totalCases = outputDistrictData.get(j).getConfirmed();
					double todaysNewCase = outputDistrictData.get(j).getDeltaDataVO().getTodaysNewCase();
					
					if(todaysNewCase != 0 && totalCases != todaysNewCase) {
	            		double todaysSurge  = (todaysNewCase / (totalCases - todaysNewCase))*100;
	            		outputData.setTodaysSurge((Math.round(todaysSurge)));
	            	}
					else if(todaysNewCase !=0 && totalCases == todaysNewCase){
						outputData.setTodaysSurge(Double.parseDouble(VALUE_100_PERCENT));
					}
	            	else {
	            		outputData.setTodaysSurge(Double.parseDouble(VALUE_ZERO_PERCENT));
	            	}
					stats.add(outputData);
				}
			}
		}
		this.outputList = stats;
	}
}
