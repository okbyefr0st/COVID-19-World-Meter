package io.fr0st.coronavirusmeter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.fr0st.coronavirusmeter.model.Cov19IndiaResourcesService.VO.Cov19IndiaResourcesOutputVO;
import io.fr0st.coronavirusmeter.service.Cov19IndiaResourcesService;

@RestController
public class Cov19IndianResourcesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(Cov19IndianResourcesController.class);

	@Autowired
	Cov19IndiaResourcesService cov19IndiaResourcesService;

	@RequestMapping(value = "/resources")
	public ModelAndView resoures(Model model) {

		LOGGER.info("Entering in resources()");

		cov19IndiaResourcesService = new Cov19IndiaResourcesService();
		List<Cov19IndiaResourcesOutputVO> resourceDetails = cov19IndiaResourcesService.fetchIndianResources();

		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("resourceDetails", resourceDetails);
		modelAndView.setViewName("resources");
		LOGGER.info("Exiting from resources()");

		return modelAndView;
	}
}
