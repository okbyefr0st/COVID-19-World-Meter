package io.fr0st.coronavirusmeter.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.fr0st.coronavirusmeter.model.Cov19WorldDataService.VO.Cov19WorldDataVO;
import io.fr0st.coronavirusmeter.model.Cov19WorldDataService.VO.Cov19WorldOutputVO;

@Service
public class Cov19WorldDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(Cov19WorldDataService.class);

	private static String COVID_19_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static String VALUE_ZERO_PERCENT = "00.00";
	private static String VALUE_100_PERCENT = "100.00";

	List<Cov19WorldDataVO> covid19DataList = new ArrayList<>();

	@PostConstruct
	public Cov19WorldOutputVO fetchCovid19Data() {

		LOGGER.info("Entering in fetchCovid19Data()");

		Cov19WorldOutputVO cov19WorldOutputVO = new Cov19WorldOutputVO();
		try {
			File file = new File(
					"C:\\Workspaces\\PracticeWS\\coronavirus-meter\\coronavirus-meter\\src\\Cov19WorldData\\covid19data.csv");
			URL url = new URL(COVID_19_DATA_URL);

			List<Cov19WorldDataVO> stats = new ArrayList<>();
			// Create connection, send request and download file
			// openStream is a shorthand to URL.openConnection
			if (!file.exists()) {
				file.createNewFile();
				try (BufferedInputStream in = new BufferedInputStream(url.openStream());
						FileOutputStream fileOutputStream = new FileOutputStream(file)) {
					byte dataBuffer[] = new byte[1024];
					int bytesRead;
					while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
						fileOutputStream.write(dataBuffer, 0, bytesRead);
					}
					fileOutputStream.close();
				} catch (IOException e) {
					LOGGER.error("Signals that an I/O exception of some sort has occurred.");
					e.printStackTrace();
				}
			} else {
				try (BufferedInputStream in = new BufferedInputStream(url.openStream());
						FileOutputStream fileOutputStream = new FileOutputStream(file)) {
					byte dataBuffer[] = new byte[1024];
					int bytesRead;
					while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
						fileOutputStream.write(dataBuffer, 0, bytesRead);
					}
					fileOutputStream.close();
				} catch (IOException e) {
					LOGGER.error("Signals that an I/O exception of some sort has occurred.");
					e.printStackTrace();
				}
			}

			// Iterate response stream using OpenCSV API for parsing
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new FileReader(file));
			for (CSVRecord record : records) {
				Cov19WorldDataVO cov19WorldDataVO = new Cov19WorldDataVO();
				cov19WorldDataVO.setProvince(record.get("Province/State"));
				cov19WorldDataVO.setCountry(record.get("Country/Region"));
				cov19WorldDataVO.setTotalNumberOfCases(Integer.parseInt(record.get(record.size() - 1)));

				int todaysNewCase = Integer.parseInt(record.get(record.size() - 1))
						- Integer.parseInt(record.get(record.size() - 2));
				cov19WorldDataVO.setTodaysNewCases(todaysNewCase);

				if (todaysNewCase != 0 && todaysNewCase != Integer.parseInt(record.get(record.size() - 1))) {
					double todaysSurge = ((double) todaysNewCase / Double.parseDouble(record.get(record.size() - 2)))
							* 100;
					cov19WorldDataVO.setTodaysSurge((Math.round(todaysSurge)));
				} else if (todaysNewCase != 0 && todaysNewCase == Integer.parseInt(record.get(record.size() - 1))) {
					cov19WorldDataVO.setTodaysSurge(Double.parseDouble(VALUE_100_PERCENT));
				} else {
					cov19WorldDataVO.setTodaysSurge(Double.parseDouble(VALUE_ZERO_PERCENT));
				}
				stats.add(cov19WorldDataVO);
			}
			this.covid19DataList = stats;
			if (null != covid19DataList) {
				cov19WorldOutputVO = getCov19WorldOutputData(covid19DataList);
			}
		} catch (MalformedURLException e) {
			LOGGER.error(
					"Either nolegal protocol could be found in a specification string or thestring could not be parsed.");
			e.printStackTrace();
		} catch (ProtocolException e) {
			LOGGER.error("There is an error in the underlyingprotocol, such as a TCP error");
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error("Signals that an I/O exception of some sort has occurred.");
			e.printStackTrace();
		}
		LOGGER.info("Exiting from fetchCovid19Data()");

		return cov19WorldOutputVO;
	}

	public Cov19WorldOutputVO getCov19WorldOutputData(List<Cov19WorldDataVO> covid19DataList) {

		LOGGER.info("Entering in getCov19WorldOutputData()");

		Cov19WorldOutputVO cov19WorldOutputVO = new Cov19WorldOutputVO();
		if (null != covid19DataList) {
			int totalReportedCases = covid19DataList.stream().mapToInt(Data -> Data.getTotalNumberOfCases()).sum();
			int totalNewCases = covid19DataList.stream().mapToInt(Data -> Data.getTodaysNewCases()).sum();
			double totalSurge = ((double) totalNewCases / ((double) totalReportedCases - (double) totalNewCases));
			double avgSurge = Math.round(totalSurge * 100);

			cov19WorldOutputVO.setCovid19DataList(covid19DataList);
			cov19WorldOutputVO.setTotalReportedCases(totalReportedCases);
			cov19WorldOutputVO.setTotalNewCases(totalNewCases);
			cov19WorldOutputVO.setAvgSurge(avgSurge);
		}
		LOGGER.info("Exiting from getCov19WorldOutputData()");

		return cov19WorldOutputVO;
	}
}
