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
	
	private static final Logger logger = LoggerFactory.getLogger(ShopsController.class);
	
	@RequestMapping(value = "/locales", method = RequestMethod.GET)
	public String locales() {
		logger.info("Menu Locales");
		
		return "shops";
	}
	
}
