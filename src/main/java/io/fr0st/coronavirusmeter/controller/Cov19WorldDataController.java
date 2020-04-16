package io.fr0st.coronavirusmeter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.fr0st.coronavirusmeter.model.Cov19WorldDataService.VO.Cov19WorldOutputVO;
import io.fr0st.coronavirusmeter.service.Cov19WorldDataService;

@RestController
public class Cov19WorldDataController {

	private static final Logger LOGGER = LoggerFactory.getLogger(Cov19WorldDataController.class);

	@Autowired
	Cov19WorldDataService cov19WorldDataService;

	@RequestMapping(value = "/")
	public ModelAndView index(Model model) {

		LOGGER.info("Entering in index()");

		cov19WorldDataService = new Cov19WorldDataService();
		Cov19WorldOutputVO cov19WorldOutputVO = cov19WorldDataService.fetchCovid19Data();

		model.addAttribute("coronaDetails", cov19WorldOutputVO.getCovid19DataList());
		model.addAttribute("totalReportedCases", cov19WorldOutputVO.getTotalReportedCases());
		model.addAttribute("totalNewCases", cov19WorldOutputVO.getTotalNewCases());
		model.addAttribute("avgSurge", cov19WorldOutputVO.getAvgSurge());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		LOGGER.info("Existing from index()");

		return modelAndView;
	}
}
