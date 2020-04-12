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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.fr0st.coronavirusmeter.model.Covid19DataVO;

@Service
public class Covid19DataService {
	
	private static String COVID_19_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static String VALUE_ZERO_PERCENT = "00.00";
	private static String VALUE_100_PERCENT = "100.00";
	
	List<Covid19DataVO> covid19DataList = new ArrayList<>(); 
	
	public List<Covid19DataVO> getCovid19DataList() {
		return covid19DataList;
	}

	public void setCovid19DataList(List<Covid19DataVO> covid19DataList) {
		this.covid19DataList = covid19DataList;
	}

	@PostConstruct
	@Scheduled (fixedRate = 900000)
	public void fetchCovid19Data() throws MalformedURLException {
		File file = new File("D://covid19data.csv");
		URL url = new URL(COVID_19_DATA_URL);
		List<Covid19DataVO> stats = new ArrayList<>();
		try {
			//Create connection, send request and download file
			//openStream is a shorthand to URL.openConnection
			if(!file.exists()) {
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
						    e.printStackTrace();
						    System.out.println("Error occured while downloading a file");
						}
			}
			else {
				try (BufferedInputStream in = new BufferedInputStream(url.openStream());
						  FileOutputStream fileOutputStream = new FileOutputStream(file)) {
						    byte dataBuffer[] = new byte[1024];
						    int bytesRead;
						    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
						        fileOutputStream.write(dataBuffer, 0, bytesRead);
						    }
						    fileOutputStream.close();
						} catch (IOException e) {
						    e.printStackTrace();
						    System.out.println("Error occured while downloading a file");
						}
			}
			
			//Iterate response stream using CSV API parser
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new FileReader(file));
            for (CSVRecord record : records) {
            	Covid19DataVO covid19DataVO = new Covid19DataVO();
            	covid19DataVO.setProvince(record.get("Province/State"));
            	covid19DataVO.setCountry(record.get("Country/Region"));
            	covid19DataVO.setTotalNumberOfCases(Integer.parseInt(record.get(record.size()-1)));
            	
            	int todaysNewCase = Integer.parseInt(record.get(record.size()-1)) - Integer.parseInt(record.get(record.size()-2)); 
            	covid19DataVO.setTodaysNewCases(todaysNewCase);
            	
            	if(todaysNewCase != 0 && todaysNewCase != Integer.parseInt(record.get(record.size()-1))) {
            		double todaysSurge  = ((double)todaysNewCase / Double.parseDouble(record.get(record.size()-2)))*100;
            		covid19DataVO.setTodaysSurge((Math.round(todaysSurge)));
            	} 
            	else if(todaysNewCase != 0 && todaysNewCase == Integer.parseInt(record.get(record.size()-1))){
            		covid19DataVO.setTodaysSurge(Double.parseDouble(VALUE_100_PERCENT));
				}
            	else {
            		covid19DataVO.setTodaysSurge(Double.parseDouble(VALUE_ZERO_PERCENT));
            	}
            	stats.add(covid19DataVO);
            }
            this.covid19DataList = stats;
            
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
