package com.lacueva.control.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.ShopDao;

/**
 * Handles requests for the application admin.
 */
@Controller
@RequestMapping(value = "/admin")
@SessionAttributes({ "currShop", "shopList" })
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private ShopDao shopDao;

    @Inject
    private ItemDao itemDao;

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String items(Model model) {
	logger.info("Getting all Items...");

	List<Item> itemsList = itemDao.getAll();

	model.addAttribute("items", itemsList);

	logger.info(itemsList.toString());

	return "admin/items";
    }

    @RequestMapping(value = "/items/create", method = RequestMethod.GET)
    public ModelAndView itemsCreate() {
	return new ModelAndView("editItem", "item", new Item());

    }

    @RequestMapping(value = "/items/create", method = RequestMethod.POST)
    public String processSubmit(@Valid @ModelAttribute Item item, BindingResult result, Model model,
	    RedirectAttributes redirectAttrs) {
	if (result.hasErrors()) {
	    return "editItem";
	}

	if (item != null) {
	    logger.info(item.toString());
	}

	String message = "Información grabada correctamente";

	redirectAttrs.addFlashAttribute("message", message);

	return "redirect:/admin/items/create";
    }

    @RequestMapping(value = "/items/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteItemById(@RequestParam Long id) {
	logger.info("Entering Items Delete for ID: " + id);

	itemDao.delete(id);

	String message = "Artículo borrado correctamente";

	return message;
    }

    @RequestMapping(value = "/shops", method = RequestMethod.GET)
    public String shops(Model model) {
	logger.info("Welcome items!");

	List<Shop> shopsList = shopDao.getAll();

	model.addAttribute("shops", shopsList);

	logger.info(shopsList.toString());

	return "admin/shops";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(SessionStatus status) {
	status.setComplete();
	return "redirect:/";

    }

}