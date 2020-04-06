package io.fr0st.coronavirusmeter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.fr0st.coronavirusmeter.model.Covid19DataVO;
import io.fr0st.coronavirusmeter.service.Covid19DataService;

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
		
		@RequestMapping(value = "/india")
		public ModelAndView india () {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("india");
			return modelAndView;
		}
}
