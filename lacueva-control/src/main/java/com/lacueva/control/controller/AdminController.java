package com.lacueva.control.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Price;
import com.lacueva.control.bean.Promo;
import com.lacueva.control.bean.Provider;
import com.lacueva.control.bean.Sale;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.PriceDao;
import com.lacueva.control.dao.PromoDao;
import com.lacueva.control.dao.ProviderDao;
import com.lacueva.control.dao.SaleDao;
import com.lacueva.control.dao.ShopDao;

/**
 * Handles requests for the application admin.
 */
@Controller
@SessionAttributes("currShop")
public class AdminController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SaleDao saleDao;

	@Inject
	private ShopDao shopDao;

	@Inject
	private ItemDao itemDao;

	@Inject
	private PriceDao priceDao;

	@Inject
	private PromoDao promoDao;

	@Inject
	private ProviderDao providerDao;

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String items(Model model) {
		logger.info("Welcome items!");

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

	@RequestMapping(value = "/shops", method = RequestMethod.GET)
	public String shops(Model model) {
		logger.info("Welcome items!");

		List<Shop> shopsList = shopDao.getAll();

		model.addAttribute("shops", shopsList);

		logger.info(shopsList.toString());

		return "shops";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(SessionStatus status) {
		status.setComplete();
		return "redirect:/";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String prepare(Model model) {

		Item item1 = new Item();
		item1.setItemType("DVD");
		item1.setItemWeight(16.4f);
		item1.setItemBurnable(true);
		itemDao.create(item1);

		Item item2 = new Item();
		item2.setItemType("DVD + sobre");
		item2.setItemWeight(18f);
		item2.setItemBurnable(false);
		itemDao.create(item2);

		Item item3 = new Item();
		item3.setItemType("BD");
		item3.setItemWeight(16.4f);
		item3.setItemBurnable(true);
		itemDao.create(item3);

		Item item4 = new Item();
		item4.setItemType("Sobre");
		item4.setItemWeight(1.56f);
		item4.setItemBurnable(false);
		itemDao.create(item4);

		Item item5 = new Item();
		item5.setItemType("Sobre c/solapa");
		item5.setItemWeight(2.66f);
		item5.setItemBurnable(false);
		itemDao.create(item5);

		Item item6 = new Item();
		item6.setItemType("Bulk x 10");
		item6.setItemWeight(40f);
		item6.setItemBurnable(false);
		itemDao.create(item6);

		Item item7 = new Item();
		item7.setItemType("Bulk x 25");
		item7.setItemWeight(40f);
		item7.setItemBurnable(false);
		itemDao.create(item7);

		Item item8 = new Item();
		item8.setItemType("Bulk x 50");
		item8.setItemWeight(41.25f);
		item8.setItemBurnable(false);
		itemDao.create(item8);

		Item item9 = new Item();
		item9.setItemType("Bulk x 100");
		item9.setItemWeight(57f);
		item9.setItemBurnable(false);
		itemDao.create(item9);

		Item item10 = new Item();
		item10.setItemType("Cajon");
		item10.setItemWeight(1334f);
		item10.setItemBurnable(false);
		itemDao.create(item10);

		Item item11 = new Item();
		item11.setItemType("Insumos");
		item11.setItemBurnable(false);
		itemDao.create(item11);

		List<Item> itemsForShop1 = new ArrayList<Item>();
		List<Item> itemsForShop2 = new ArrayList<Item>();

		itemsForShop1.add(item1);
		itemsForShop1.add(item3);
		itemsForShop1.add(item11);

		itemsForShop2.add(item1);
		itemsForShop2.add(item3);
		itemsForShop2.add(item11);

		Shop shop1 = new Shop();

		shop1.setShopDate(new Date());
		shop1.setShopName("Shop1");
		shop1.setShopCash(2000);
		shop1.setShopItems(itemsForShop1);
		shopDao.create(shop1);

		Shop shop2 = new Shop();

		shop2.setShopDate(new Date());
		shop2.setShopName("Shop2");
		shop2.setShopCash(6000);
		shop2.setShopItems(itemsForShop2);
		shopDao.create(shop2);

		Price price = new Price();
		price.setPriceItem(item1);
		price.setPriceShop(shop1);
		price.setPriceValue(12f);

		priceDao.create(price);

		Price price2 = new Price();
		price2.setPriceItem(item1);
		price2.setPriceShop(shop2);
		price2.setPriceValue(10f);

		priceDao.create(price2);

		Price price3 = new Price();
		price3.setPriceItem(item3);
		price3.setPriceShop(shop1);
		price3.setPriceValue(40f);

		priceDao.create(price3);

		Price price4 = new Price();
		price4.setPriceItem(item3);
		price4.setPriceShop(shop2);
		price4.setPriceValue(35f);

		priceDao.create(price4);

		Promo promo1 = new Promo();
		promo1.setPromoItem(item1);
		promo1.setPromoShop(shop1);
		promo1.setPromoQuantity(5);
		promo1.setPromoValue(50f);

		promoDao.create(promo1);

		Promo promo2 = new Promo();
		promo2.setPromoItem(item1);
		promo2.setPromoShop(shop1);
		promo2.setPromoQuantity(10);
		promo2.setPromoValue(90f);

		promoDao.create(promo2);

		Sale sale1 = new Sale();

		sale1.setSaleDate(new Date());
		sale1.setSaleShop(shop1);
		sale1.setSaleItem(item1);
		sale1.setSaleQuantity(15);
		sale1.setSaleAmount(180f);
		saleDao.create(sale1);

		Sale sale2 = new Sale();

		sale2.setSaleDate(new Date());
		sale2.setSaleShop(shop1);
		sale2.setSaleItem(item3);
		sale2.setSaleQuantity(3);
		sale2.setSaleAmount(120f);
		saleDao.create(sale2);

		Sale sale3 = new Sale();

		sale3.setSaleDate(new Date());
		sale3.setSaleShop(shop1);
		sale3.setSaleItem(item11);
		sale3.setSaleQuantity(1);
		sale3.setSaleAmount(40f);
		saleDao.create(sale3);

		Sale sale4 = new Sale();

		sale4.setSaleDate(new Date());
		sale4.setSaleShop(shop2);
		sale4.setSaleItem(item1);
		sale4.setSaleQuantity(20);
		sale4.setSaleAmount(240f);
		saleDao.create(sale4);

		Sale sale5 = new Sale();

		sale5.setSaleDate(new Date());
		sale5.setSaleShop(shop2);
		sale5.setSaleItem(item3);
		sale5.setSaleQuantity(7);
		sale5.setSaleAmount(280f);
		saleDao.create(sale5);

		Provider provider1 = new Provider();
		provider1.setProviderName("Gustavo");

		providerDao.create(provider1);

		Provider provider2 = new Provider();
		provider2.setProviderName("Adriana");

		providerDao.create(provider2);

		model.addAttribute("currShop", shop1);

		return "redirect:/";
	}
}