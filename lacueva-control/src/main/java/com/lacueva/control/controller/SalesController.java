package com.lacueva.control.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@PreAuthorize("hasRole('ROLE_USER')")
@EnableWebMvc
@RequestMapping(value = "/sales")
@SessionAttributes({ "currShop", "shopList" })
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
    private Shop populateCurrShop() {
	return new Shop();
    }

    /**
     * Populates the Shop List if it's null
     * 
     * @return new ArrayList<Shop>
     */
    @ModelAttribute("shopList")
    private List<Shop> populateShopList() {
	return new ArrayList<Shop>();
    }

    @ExceptionHandler(HttpSessionRequiredException.class)
    public String handleHttpSessionException() {
	return "redirect:/login";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String sales(Model model, @ModelAttribute("currShop") Shop currShop) throws ParseException {
	return salesByDate(model, currShop, new Date());
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public String salesByDate(Model model, @ModelAttribute("currShop") Shop currShop,
	    @PathVariable @DateTimeFormat(iso = ISO.DATE) Date date) throws ParseException {
	logger.info("Welcome sales path variable!");

	List<Sale> salesList = saleDao.findSalesByShopAndDate(currShop, date);

	model.addAttribute("sales", salesList);
	model.addAttribute("searchDate", date);

	logger.info(salesList.toString());

	return "sales";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteSaleById(@RequestParam Long id) {
	logger.info("Entering Sales Delete for ID: " + id);

	saleDao.delete(id);

	String message = "Venta borrada correctamente";

	return message;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String editSale(@RequestBody Sale updatedSale) {
	logger.info("Entering Sales Edit for ID: " + updatedSale.getId());

	saleDao.update(updatedSale);

	String message = "Venta editada correctamente";

	return message;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createSale(@RequestBody Sale sale) {

	Sale createdSale = saleDao.create(sale);

	return createdSale.getId().toString();
    }

    @RequestMapping(value = "/prueba", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Sale getSaleinJSON(@RequestParam(value = "id") Long id) {
	if (id != null) {
	    Sale sale = saleDao.find(id);
	    return sale;
	} else
	    return null;
    }
}