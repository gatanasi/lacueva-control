package com.lacueva.control.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.ItemType;
import com.lacueva.control.bean.Sale;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.SaleDao;
import com.lacueva.control.dao.ShopDao;

/**
 * Handles requests for the application sales.
 */
@Controller
@RequestMapping(value = "/sales")
public class SalesController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SaleDao saleDao;

	@Inject
	private ShopDao shopDao;

	@Inject
	private ItemDao itemDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String sales(Model model) {
		logger.info("Welcome sales!");

		prepare(model);
		List<Sale> salesList = new ArrayList<Sale>();
		try {
			salesList = saleDao.findSalesByShopAndBetweenDates((Shop) model
					.asMap().get("shop"), DateUtilThreadSafe
					.parse("2015-02-09"), DateUtilThreadSafe
					.parse("2015-02-12"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		model.addAttribute("sales", salesList);

		logger.info(salesList.toString());

		return "sales";
	}

	public void prepare(Model model) {
		List<Item> itemsList;

		Shop shop;

		itemsList = new ArrayList<Item>();
		Item itemforList = new Item();
		itemforList.setItemType(ItemType.ENVELOPE_WITH_FLAP);
		itemforList.setItemWeight(1.3f);
		itemforList.setItemBurnable(false);
		itemDao.create(itemforList);

		itemsList.add(itemforList);

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
		item.setItemType(ItemType.DVD);
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
		item2.setItemType(ItemType.BD);
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