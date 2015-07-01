package com.lacueva.control.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application shops page.
 */
@Controller
public class ShopsController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/locales", method = RequestMethod.GET)
	public String shops() {
		logger.info("Menu Locales");

		return "shops";
	}

}
