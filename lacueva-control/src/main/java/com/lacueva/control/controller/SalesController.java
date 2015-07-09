package com.lacueva.control.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.lacueva.control.bean.Sale;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.dao.SaleDao;

/**
 * Handles requests for the application sales.
 */
@Controller
@EnableWebMvc
@RequestMapping(value = "/sales")
@SessionAttributes("currShop")
public class SalesController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SaleDao saleDao;

	/**
	 * Populates the current Shop if it's null
	 * 
	 * @return new Shop
	 */
	@ModelAttribute("currShop")
	public Shop populateShop() {
		return new Shop();
	}

	@ExceptionHandler(HttpSessionRequiredException.class)
	public String handleHttpSessionException() {
		return "redirect:/login";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String sales(Model model, @ModelAttribute("currShop") Shop currShop) {
		logger.info("Welcome sales!");

		List<Sale> salesList = saleDao.findSalesByShopAndDate(currShop, new Date());

		model.addAttribute("sales", salesList);

		logger.info(salesList.toString());

		return "sales";
	}

	@RequestMapping(value = "/{date}", method = RequestMethod.GET)
	public String salesByDate(Model model, @ModelAttribute("currShop") Shop currShop,
			@PathVariable @DateTimeFormat(iso = ISO.DATE) Date date) {
		logger.info("Welcome sales path variable!");

		List<Sale> salesList = saleDao.findSalesByShopAndDate(currShop, date);

		model.addAttribute("sales", salesList);

		logger.info(salesList.toString());

		return "sales";
	}

	@RequestMapping(value = "/prueba", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Sale getSaleinJSON(@RequestParam(value = "id") String id, Model model) {
		if (id != null) {

			Sale sale = saleDao.find(Long.parseLong(id));

			return sale;
		} else
			return null;
	}

}