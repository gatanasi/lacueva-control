package com.lacueva.control.controller;

import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Sale;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.SaleDao;
import com.lacueva.control.dao.ShopDao;

/**
 * Handles requests for the application admin.
 */
@Controller
public class AdminController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SaleDao saleDao;

	@Inject
	private ShopDao shopDao;

	@Inject
	private ItemDao itemDao;

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String items(Model model) {
		logger.info("Welcome items!");

		prepare(model);
		List<Item> itemsList = itemDao.getAll();

		model.addAttribute("items", itemsList);

		logger.info(itemsList.toString());

		return "items";
	}

	@RequestMapping(value = "/items/create", method = RequestMethod.GET)
	public ModelAndView itemsCreate() {
		return new ModelAndView("editItem", "item", new Item());

	}

	@RequestMapping(value = "/items/create", method = RequestMethod.POST)
	public String processSubmit(@Valid @ModelAttribute Item item,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {
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

	@RequestMapping(value = "/shops", method = RequestMethod.GET)
	public String shops(Model model) {
		logger.info("Welcome items!");

		prepare(model);
		List<Shop> shopsList = shopDao.getAll();

		model.addAttribute("shops", shopsList);

		logger.info(shopsList.toString());

		return "shops";
	}

	public void prepare(Model model) {
		Shop shop;

		List<Item> itemsList = new ArrayList<Item>();
		Item itemforList = new Item();
		itemforList.setItemType("ENVELOPE_WITH_FLAP");
		itemforList.setItemWeight(1.3f);
		itemforList.setItemBurnable(false);
		itemDao.create(itemforList);

		itemsList.add(itemforList);

		Item itemforList2 = new Item();
		itemforList2.setItemType("DVD");
		itemforList2.setItemWeight(14.8f);
		itemforList2.setItemBurnable(true);
		itemDao.create(itemforList2);

		itemsList.add(itemforList2);

		shop = new Shop();
		try {
			shop.setShopDate(DateUtilThreadSafe.parse("2010-03-20"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		shop.setShopName("Shop1");
		shop.setShopCash(2000);
		shop.setShopItems(itemsList);

		shopDao.create(shop);

		Item item = new Item();
		item.setItemType("DVD");
		item.setItemWeight(16.4f);
		item.setItemBurnable(false);

		itemDao.create(item);

		Sale sale = new Sale();
		try {
			sale.setSaleDate(DateUtilThreadSafe.parse("2015-02-10"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sale.setSaleShop(shop);
		sale.setSaleItem(item);
		sale.setSaleQuantity(50);
		sale.setSaleAmount(10f);
		;

		saleDao.create(sale);

		Item item2 = new Item();
		item2.setItemType("BD");
		item2.setItemWeight(10f);
		item2.setItemBurnable(true);

		itemDao.create(item2);

		Sale sale2 = new Sale();
		try {
			sale2.setSaleDate(DateUtilThreadSafe.parse("2015-02-11"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sale2.setSaleShop(shop);
		sale2.setSaleItem(item2);
		sale2.setSaleQuantity(50);
		sale2.setSaleAmount(10f);
		;

		saleDao.create(sale2);

		model.addAttribute("shop", shop);
	}
}