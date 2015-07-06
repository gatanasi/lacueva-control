package com.lacueva.control.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.lacueva.control.bean.Sale;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.SaleDao;

/**
 * Handles requests for the application inputs.
 */
@Controller
@EnableWebMvc
@RequestMapping(value = "/withdrawals")
@SessionAttributes("currShop")
public class WithdrawalsController {

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
		logger.info("Welcome withdrawals!");

		List<Sale> salesList = new ArrayList<Sale>();
		try {
			salesList = saleDao.findSalesByShopAndBetweenDates(currShop,
					DateUtilThreadSafe.parse("2015-02-09"),
					DateUtilThreadSafe.parse("2015-02-12"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("sales", salesList);

		logger.info(salesList.toString());

		return "withdrawals";
	}

	@RequestMapping(value = "/prueba", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Sale getSaleinJSON(
			@RequestParam(value = "id") String id, Model model) {
		if (id != null) {
			Sale sale = saleDao.find(Long.parseLong(id));
			return sale;
		} else
			return null;
	}

}