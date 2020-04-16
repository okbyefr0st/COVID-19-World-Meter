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

import io.fr0st.coronavirusmeter.model.Cov19IndiaDataService.VO.Cov19DistrictVO;
import io.fr0st.coronavirusmeter.model.Cov19IndiaDataService.VO.Cov19IndiaDataVO;
import io.fr0st.coronavirusmeter.model.Cov19IndiaDataService.VO.Cov19IndiaListVO;
import io.fr0st.coronavirusmeter.model.Cov19IndiaDataService.VO.Cov19IndiaOutputVO;

@Service
public class Cov19IndiaDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(Cov19IndiaDataService.class);

	public final static String COVID_19_INDIA_DATA_URL = "https://api.covid19india.org/v2/state_district_wise.json";
	private static String VALUE_ZERO_PERCENT = "00.00";
	private static String VALUE_100_PERCENT = "100.00";

	List<Cov19IndiaListVO> outputList = new ArrayList<Cov19IndiaListVO>();

	@PostConstruct
	public Cov19IndiaOutputVO fetchCovid19IndiaData() {

		LOGGER.info("Entering in fetchCovid19IndiaData()");

		final String url = COVID_19_INDIA_DATA_URL;
		List<Cov19IndiaListVO> stats = new ArrayList<>();
		Cov19IndiaOutputVO cov19IndiaOutputVO = new Cov19IndiaOutputVO();

		try {
			RestTemplate resttemplate = new RestTemplate();
			String responseJson = resttemplate.getForObject(url, String.class);

			ObjectMapper mapper = new ObjectMapper();

			Cov19IndiaDataVO[] cov19IndiaDataVO = mapper.readValue(responseJson.trim(), Cov19IndiaDataVO[].class);

			if (null != cov19IndiaDataVO) {
				for (int i = 0; i < cov19IndiaDataVO.length; i++) {
					List<Cov19DistrictVO> outputDistrictData = cov19IndiaDataVO[i].getCovid19DistrictVO();
					for (int j = 0; j < outputDistrictData.size(); j++) {
						Cov19IndiaListVO outputData = new Cov19IndiaListVO();
						outputData.setState(cov19IndiaDataVO[i].getState());
						outputData.setDistrict(outputDistrictData.get(j).getDistrict());
						outputData.setTotalCases(outputDistrictData.get(j).getConfirmed());
						outputData.setNewCasesToday(outputDistrictData.get(j).getDeltaDataVO().getTodaysNewCase());

						double totalCases = outputDistrictData.get(j).getConfirmed();
						double todaysNewCase = outputDistrictData.get(j).getDeltaDataVO().getTodaysNewCase();

						if (todaysNewCase != 0 && totalCases != todaysNewCase) {
							double todaysSurge = (todaysNewCase / (totalCases - todaysNewCase)) * 100;
							outputData.setTodaysSurge((Math.round(todaysSurge)));
						} else if (todaysNewCase != 0 && totalCases == todaysNewCase) {
							outputData.setTodaysSurge(Double.parseDouble(VALUE_100_PERCENT));
						} else {
							outputData.setTodaysSurge(Double.parseDouble(VALUE_ZERO_PERCENT));
						}
						stats.add(outputData);
					}
				}
			}
			this.outputList = stats;

			if (null != outputList) {
				cov19IndiaOutputVO = getCov19IndiaOutputData(outputList);
			}
		} catch (JsonMappingException e) {
			LOGGER.error("Error occured in JsonMapping");
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			LOGGER.error("Error occured in JsonProcessing");
			e.printStackTrace();
		}
		LOGGER.info("Exiting from fetchCovid19IndiaData()");

		return cov19IndiaOutputVO;
	}

	public Cov19IndiaOutputVO getCov19IndiaOutputData(List<Cov19IndiaListVO> outputList) {

		LOGGER.info("Entering in getCov19IndiaOutputData()");

		Cov19IndiaOutputVO cov19IndiaOutputVO = new Cov19IndiaOutputVO();
		if (null != outputList) {
			int totalReportedCases = outputList.stream().mapToInt(Data -> Data.getTotalCases()).sum();
			int totalNewCases = outputList.stream().mapToInt(Data -> Data.getNewCasesToday()).sum();
			double totalSurge = ((double) totalNewCases / ((double) totalReportedCases - (double) totalNewCases));
			double avgSurge = Math.round(totalSurge * 100);

			cov19IndiaOutputVO.setCovid19IndiaDataList(outputList);
			cov19IndiaOutputVO.setTotalReportedCases(totalReportedCases);
			cov19IndiaOutputVO.setTotalNewCases(totalNewCases);
			cov19IndiaOutputVO.setAvgSurge(avgSurge);
		}
		LOGGER.info("Exiting from getCov19IndiaOutputData()");

		return cov19IndiaOutputVO;
	}
}
