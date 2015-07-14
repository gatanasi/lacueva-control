package com.lacueva.control.controller;

import java.text.ParseException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteSaleById(@RequestParam Long id, RedirectAttributes redirectAttrs) {
	logger.info("Welcome sales delete path variable!");

	saleDao.delete(id);

	String message = "Venta borrada correctamente";

	redirectAttrs.addFlashAttribute("message", message);

	return message;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editSale(@RequestParam Long id, RedirectAttributes redirectAttrs) {
	logger.info("Entering Sales Edit for ID: " + id);

	Sale updatedSale = new Sale();
	updatedSale.setId(id);

	saleDao.update(updatedSale);

	String message = "Venta editada correctamente";

	redirectAttrs.addFlashAttribute("message", message);

	return message;
    }

    @RequestMapping(value = "/prueba", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Sale getSaleinJSON(@RequestParam(value = "id") Long id, Model model) {
	if (id != null) {
	    Sale sale = saleDao.find(id);
	    return sale;
	} else
	    return null;
    }
}