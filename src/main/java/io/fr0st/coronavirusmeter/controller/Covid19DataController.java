package io.fr0st.coronavirusmeter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.fr0st.coronavirusmeter.model.Covid19DataVO;
import io.fr0st.coronavirusmeter.model.Covid19IndiaOutputVO;
import io.fr0st.coronavirusmeter.model.Covid19ResourceOutputVO;
import io.fr0st.coronavirusmeter.model.ResourceDetailsVO;
import io.fr0st.coronavirusmeter.service.Covid19DataService;
import io.fr0st.coronavirusmeter.service.Covid19IndiaDataService;
import io.fr0st.coronavirusmeter.service.Covid19IndiaEssentialsService;

@RestController
public class Covid19DataController {

	@Autowired
	Covid19DataService covid19DataService;
	
	
		@RequestMapping(value = "/")
		public ModelAndView index (Model model) {
		    ModelAndView modelAndView = new ModelAndView();
		    List<Covid19DataVO> covid19DataList = covid19DataService.getCovid19DataList();
		    int totalReportedCases = covid19DataList.stream().mapToInt(Data -> Data.getTotalNumberOfCases()).sum();
		    int totalNewCases = covid19DataList.stream().mapToInt(Data -> Data.getTodaysNewCases()).sum();
		    double totalSurge = ((double)totalNewCases/((double)totalReportedCases-(double)totalNewCases));
		    double avgSurge = Math.round(totalSurge * 100);
		    model.addAttribute("coronaDetails", covid19DataList);
		    model.addAttribute("totalReportedCases", totalReportedCases);
		    model.addAttribute("totalNewCases", totalNewCases);
		    model.addAttribute("avgSurge", avgSurge);
		    modelAndView.setViewName("index");
		    return modelAndView;
		}
	
	@Autowired	
	Covid19IndiaDataService covid19IndiaDataService;
	
		@RequestMapping(value = "/india")
		public ModelAndView india (Model model) {
			ModelAndView modelAndView = new ModelAndView();
			List<Covid19IndiaOutputVO> covid19IndiaDataList = covid19IndiaDataService.getOutputList();
			int totalReportedCases = covid19IndiaDataList.stream().mapToInt(Data -> Data.getTotalCases()).sum();
		    int totalNewCases = covid19IndiaDataList.stream().mapToInt(Data -> Data.getNewCasesToday()).sum();
		    double totalSurge = ((double)totalNewCases/((double)totalReportedCases-(double)totalNewCases));
		    double avgSurge = Math.round(totalSurge * 100);
			model.addAttribute("coronaIndiaDetails", covid19IndiaDataList);
			model.addAttribute("totalReportedCases", totalReportedCases);
		    model.addAttribute("totalNewCases", totalNewCases);
		    model.addAttribute("avgSurge", avgSurge);
			modelAndView.setViewName("india");
			return modelAndView;
		}
		
		@Autowired
		Covid19IndiaEssentialsService covid19IndiaEssentialsService;
		
			@RequestMapping(value = "/resources")
			public ModelAndView resoures(Model model) {
				ModelAndView modelAndView = new ModelAndView();
				List<Covid19ResourceOutputVO> resourceDetails = covid19IndiaEssentialsService.getOutputList();
				model.addAttribute("resourceDetails", resourceDetails);
				modelAndView.setViewName("resources");
				return modelAndView;
				
			}
}
