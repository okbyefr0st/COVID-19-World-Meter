package io.fr0st.coronavirusmeter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.fr0st.coronavirusmeter.model.Cov19IndiaDataService.VO.Cov19IndiaOutputVO;
import io.fr0st.coronavirusmeter.service.Cov19IndiaDataService;

@RestController
public class Cov19IndiaDataController {

	private static final Logger LOGGER = LoggerFactory.getLogger(Cov19IndiaDataController.class);

	@Autowired
	Cov19IndiaDataService cov19IndiaDataService;

	@RequestMapping(value = "/india")
	public ModelAndView india(Model model) {

		LOGGER.info("Entering in india()");

		cov19IndiaDataService = new Cov19IndiaDataService();
		Cov19IndiaOutputVO cov19IndiaOutputVO = cov19IndiaDataService.fetchCovid19IndiaData();
		model.addAttribute("coronaIndiaDetails", cov19IndiaOutputVO.getCovid19IndiaDataList());
		model.addAttribute("totalReportedCases", cov19IndiaOutputVO.getTotalReportedCases());
		model.addAttribute("totalNewCases", cov19IndiaOutputVO.getTotalNewCases());
		model.addAttribute("avgSurge", cov19IndiaOutputVO.getAvgSurge());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("india");
		LOGGER.info("Existing from india()");

		return modelAndView;
	}
}
