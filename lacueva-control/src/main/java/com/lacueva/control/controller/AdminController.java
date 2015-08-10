package com.lacueva.control.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.ItemType;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.ItemTypeDao;
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

    @Inject
    private ItemTypeDao itemTypeDao;

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String items(Model model) {
	logger.info("Getting all Items...");

	List<Item> itemsList = itemDao.getAll();

	List<ItemType> itemTypesList = itemTypeDao.getAll();

	model.addAttribute("items", itemsList);
	model.addAttribute("itemTypes", itemTypesList);

	return "admin/items";
    }

    @RequestMapping(value = "/items/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createItem(@RequestBody Item item) {
	logger.info("Entering Items Create: " + item.toString());

	Item createdItem = itemDao.create(item);

	String message = "";

	if (createdItem != null && createdItem.getId() != null) {
	    message = "Artículo borrado correctamente";
	} else {
	    message = "Ha ocurrido un error al guardar";
	}

	return message;
    }

    @RequestMapping(value = "/items/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteItemById(@RequestParam Long id) {
	logger.info("Entering Items Delete for ID: " + id);

	itemDao.delete(id);

	String message = "Artículo borrado correctamente";

	return message;
    }

    @RequestMapping(value = "/items/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String editItem(@RequestBody Item updatedItem) {
	logger.info("Entering Items Edit for ID: " + updatedItem.getId());

	itemDao.update(updatedItem);

	String message = "Artículo editado correctamente";

	return message;
    }

    @RequestMapping(value = "/shops", method = RequestMethod.GET)
    public String shops(Model model) {
	logger.info("Getting all Shops...");

	List<Shop> shopsList = shopDao.getAll();

	model.addAttribute("shops", shopsList);

	return "admin/shops";
    }

    @RequestMapping(value = "/shops/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createShop(@RequestBody Shop shop) {
	logger.info("Entering Shops Create: " + shop.toString());

	Shop createdShop = shopDao.create(shop);

	String message = "";

	if (createdShop != null && createdShop.getId() != null) {
	    message = "Local borrado correctamente";
	} else {
	    message = "Ha ocurrido un error al guardar";
	}

	return message;
    }

    @RequestMapping(value = "/shops/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteShopById(@RequestParam Long id) {
	logger.info("Entering Shops Delete for ID: " + id);

	shopDao.delete(id);

	String message = "Local borrado correctamente";

	return message;
    }

    @RequestMapping(value = "/shops/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String editShop(@RequestBody Shop updatedShop) {
	logger.info("Entering Shops Edit for ID: " + updatedShop.getId());

	shopDao.update(updatedShop);

	String message = "Local editado correctamente";

	return message;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(SessionStatus status) {
	status.setComplete();
	return "redirect:/";

    }

}