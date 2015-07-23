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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.ItemType;
import com.lacueva.control.bean.Price;
import com.lacueva.control.bean.Promo;
import com.lacueva.control.bean.Provider;
import com.lacueva.control.bean.Sale;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.ItemTypeDao;
import com.lacueva.control.dao.PriceDao;
import com.lacueva.control.dao.PromoDao;
import com.lacueva.control.dao.ProviderDao;
import com.lacueva.control.dao.SaleDao;
import com.lacueva.control.dao.ShopDao;

/**
 * Handles requests for the application admin.
 */
@Controller
@SessionAttributes({ "currShop", "shopList" })
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private SaleDao saleDao;

    @Inject
    private ShopDao shopDao;

    @Inject
    private ItemTypeDao itemTypeDao;

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

    @RequestMapping(value = "/changeShop", method = RequestMethod.POST)
    public @ResponseBody String changeCurrShop(@RequestParam Long id, Model model) {

	Shop newShop = shopDao.find(id);
	model.addAttribute("currShop", newShop);
	return "bien ahi";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String prepare(Model model) {

	ItemType itemType1 = new ItemType();
	itemType1.setItemTypeName("DVD");
	itemTypeDao.create(itemType1);

	ItemType itemType2 = new ItemType();
	itemType2.setItemTypeName("BD");
	itemTypeDao.create(itemType2);

	Item item1 = new Item();
	item1.setItemName("DVD Estreno");
	item1.setItemType(itemType1);
	item1.setItemWeight(16.4f);
	item1.setItemBurnable(true);
	item1.setItemPriority(0);
	itemDao.create(item1);

	Item item2 = new Item();
	item2.setItemName("DVD + sobre");
	item2.setItemWeight(18f);
	item2.setItemBurnable(false);
	itemDao.create(item2);

	Item item3 = new Item();
	item3.setItemName("BD");
	item3.setItemType(itemType2);
	item3.setItemWeight(16.4f);
	item3.setItemBurnable(true);
	item3.setItemPriority(5);
	itemDao.create(item3);

	Item item4 = new Item();
	item4.setItemName("Sobre");
	item4.setItemWeight(1.56f);
	item4.setItemBurnable(false);
	itemDao.create(item4);

	Item item5 = new Item();
	item5.setItemName("Sobre c/solapa");
	item5.setItemWeight(2.66f);
	item5.setItemBurnable(false);
	itemDao.create(item5);

	Item item6 = new Item();
	item6.setItemName("Bulk x 10");
	item6.setItemWeight(40f);
	item6.setItemBurnable(false);
	itemDao.create(item6);

	Item item7 = new Item();
	item7.setItemName("Bulk x 25");
	item7.setItemWeight(40f);
	item7.setItemBurnable(false);
	itemDao.create(item7);

	Item item8 = new Item();
	item8.setItemName("Bulk x 50");
	item8.setItemWeight(41.25f);
	item8.setItemBurnable(false);
	itemDao.create(item8);

	Item item9 = new Item();
	item9.setItemName("Bulk x 100");
	item9.setItemWeight(57f);
	item9.setItemBurnable(false);
	itemDao.create(item9);

	Item item10 = new Item();
	item10.setItemName("Cajon");
	item10.setItemWeight(1334f);
	item10.setItemBurnable(false);
	itemDao.create(item10);

	Item item11 = new Item();
	item11.setItemName("Insumos");
	item11.setItemBurnable(false);
	item11.setItemPriority(10);
	itemDao.create(item11);

	Item item12 = new Item();
	item12.setItemName("Descuento");
	item12.setItemBurnable(false);
	item12.setItemPriority(15);
	itemDao.create(item12);

	Item item13 = new Item();
	item13.setItemName("DVD Series");
	item13.setItemType(itemType1);
	item13.setItemBurnable(false);
	item13.setItemPriority(2);
	itemDao.create(item13);

	Item item14 = new Item();
	item14.setItemName("Juego PC");
	item14.setItemType(itemType1);
	item14.setItemBurnable(false);
	item14.setItemPriority(3);
	itemDao.create(item14);

	Item item15 = new Item();
	item15.setItemName("Juego Wii");
	item15.setItemType(itemType1);
	item15.setItemBurnable(false);
	item15.setItemPriority(4);
	itemDao.create(item15);

	Item item16 = new Item();
	item16.setItemName("Juego Xbox 360");
	item16.setItemType(itemType1);
	item16.setItemBurnable(false);
	item16.setItemPriority(6);
	itemDao.create(item16);

	Item item17 = new Item();
	item17.setItemName("BD + Caja");
	item17.setItemType(itemType2);
	item17.setItemBurnable(false);
	item17.setItemPriority(7);
	itemDao.create(item17);

	Item item18 = new Item();
	item18.setItemName("BD 3D");
	item18.setItemType(itemType2);
	item18.setItemBurnable(false);
	item18.setItemPriority(8);
	itemDao.create(item18);

	Item item19 = new Item();
	item19.setItemName("DVD Catalogo");
	item19.setItemType(itemType1);
	item19.setItemBurnable(false);
	item19.setItemPriority(1);
	itemDao.create(item19);

	List<Item> itemsForShop1 = new ArrayList<Item>();
	List<Item> itemsForShop2 = new ArrayList<Item>();

	itemsForShop1.add(item1);
	itemsForShop1.add(item3);
	itemsForShop1.add(item11);
	itemsForShop1.add(item12);
	itemsForShop1.add(item13);
	itemsForShop1.add(item14);
	itemsForShop1.add(item15);
	itemsForShop1.add(item16);
	itemsForShop1.add(item17);
	itemsForShop1.add(item18);
	itemsForShop1.add(item19);

	itemsForShop2.add(item1);
	itemsForShop2.add(item3);
	itemsForShop2.add(item11);
	itemsForShop2.add(item12);
	itemsForShop2.add(item13);
	itemsForShop2.add(item14);
	itemsForShop2.add(item15);
	itemsForShop2.add(item16);
	itemsForShop2.add(item17);
	itemsForShop2.add(item18);
	itemsForShop2.add(item19);

	Shop shop1 = new Shop();

	shop1.setShopDate(new Date());
	shop1.setShopName("Galeria");
	shop1.setShopCash(2000);
	shop1.setShopItems(itemsForShop1);
	shopDao.create(shop1);

	Shop shop2 = new Shop();

	shop2.setShopDate(new Date());
	shop2.setShopName("Morgan");
	shop2.setShopCash(6000);
	shop2.setShopItems(itemsForShop2);
	shopDao.create(shop2);

	Price price = new Price();
	price.setPriceItem(item1);
	price.setPriceShop(shop1);
	price.setPriceValue(15f);
	priceDao.create(price);

	Price price3 = new Price();
	price3.setPriceItem(item3);
	price3.setPriceShop(shop1);
	price3.setPriceValue(45f);
	priceDao.create(price3);

	Price price13 = new Price();
	price13.setPriceItem(item13);
	price13.setPriceShop(shop1);
	price13.setPriceValue(20f);
	priceDao.create(price13);

	Price price14 = new Price();
	price14.setPriceItem(item14);
	price14.setPriceShop(shop1);
	price14.setPriceValue(30f);
	priceDao.create(price14);

	Price price15 = new Price();
	price15.setPriceItem(item15);
	price15.setPriceShop(shop1);
	price15.setPriceValue(20f);
	priceDao.create(price15);

	Price price16 = new Price();
	price16.setPriceItem(item16);
	price16.setPriceShop(shop1);
	price16.setPriceValue(30f);
	priceDao.create(price16);

	Price price17 = new Price();
	price17.setPriceItem(item17);
	price17.setPriceShop(shop1);
	price17.setPriceValue(50f);
	priceDao.create(price17);

	Price price18 = new Price();
	price18.setPriceItem(item18);
	price18.setPriceShop(shop1);
	price18.setPriceValue(149f);
	priceDao.create(price18);

	Price price19 = new Price();
	price19.setPriceItem(item19);
	price19.setPriceShop(shop1);
	price19.setPriceValue(20f);
	priceDao.create(price19);

	Price price2 = new Price();
	price2.setPriceItem(item1);
	price2.setPriceShop(shop2);
	price2.setPriceValue(17f);
	priceDao.create(price2);

	Price price4 = new Price();
	price4.setPriceItem(item3);
	price4.setPriceShop(shop2);
	price4.setPriceValue(45f);
	priceDao.create(price4);

	Price price20 = new Price();
	price20.setPriceItem(item13);
	price20.setPriceShop(shop2);
	price20.setPriceValue(20f);
	priceDao.create(price20);

	Price price21 = new Price();
	price21.setPriceItem(item14);
	price21.setPriceShop(shop2);
	price21.setPriceValue(30f);
	priceDao.create(price21);

	Price price22 = new Price();
	price22.setPriceItem(item15);
	price22.setPriceShop(shop2);
	price22.setPriceValue(20f);
	priceDao.create(price22);

	Price price23 = new Price();
	price23.setPriceItem(item16);
	price23.setPriceShop(shop2);
	price23.setPriceValue(30f);
	priceDao.create(price23);

	Price price24 = new Price();
	price24.setPriceItem(item17);
	price24.setPriceShop(shop2);
	price24.setPriceValue(50f);
	priceDao.create(price24);

	Price price25 = new Price();
	price25.setPriceItem(item18);
	price25.setPriceShop(shop2);
	price25.setPriceValue(149f);
	priceDao.create(price25);

	Price price26 = new Price();
	price26.setPriceItem(item19);
	price26.setPriceShop(shop2);
	price26.setPriceValue(20f);
	priceDao.create(price26);

	Promo promo1 = new Promo();
	promo1.setPromoItem(item1);
	promo1.setPromoShop(shop1);
	promo1.setPromoQuantity(4);
	promo1.setPromoValue(55f);
	promoDao.create(promo1);

	Promo promo2 = new Promo();
	promo2.setPromoItem(item1);
	promo2.setPromoShop(shop1);
	promo2.setPromoQuantity(8);
	promo2.setPromoValue(110f);
	promoDao.create(promo2);

	Promo promo3 = new Promo();
	promo3.setPromoItem(item19);
	promo3.setPromoShop(shop1);
	promo3.setPromoQuantity(4);
	promo3.setPromoValue(70f);
	promoDao.create(promo3);

	Promo promo4 = new Promo();
	promo4.setPromoItem(item1);
	promo4.setPromoShop(shop2);
	promo4.setPromoQuantity(4);
	promo4.setPromoValue(60f);
	promoDao.create(promo4);

	Promo promo5 = new Promo();
	promo5.setPromoItem(item1);
	promo5.setPromoShop(shop2);
	promo5.setPromoQuantity(8);
	promo5.setPromoValue(120f);
	promoDao.create(promo5);

	Promo promo6 = new Promo();
	promo6.setPromoItem(item19);
	promo6.setPromoShop(shop2);
	promo6.setPromoQuantity(4);
	promo6.setPromoValue(70f);
	promoDao.create(promo6);

	Promo promo7 = new Promo();
	promo7.setPromoItem(item19);
	promo7.setPromoShop(shop2);
	promo7.setPromoQuantity(8);
	promo7.setPromoValue(140f);
	promoDao.create(promo7);

	Sale sale1 = new Sale();
	sale1.setSaleDate(new Date());
	sale1.setSaleShop(shop1);
	sale1.setSaleItem(item1);
	sale1.setSaleQuantity(15);
	sale1.setSaleAmount(225f);
	saleDao.create(sale1);

	Sale sale2 = new Sale();
	sale2.setSaleDate(new Date());
	sale2.setSaleShop(shop1);
	sale2.setSaleItem(item3);
	sale2.setSaleQuantity(3);
	sale2.setSaleAmount(135f);
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
	sale4.setSaleAmount(260f);
	saleDao.create(sale4);

	Sale sale5 = new Sale();
	sale5.setSaleDate(new Date());
	sale5.setSaleShop(shop2);
	sale5.setSaleItem(item3);
	sale5.setSaleQuantity(7);
	sale5.setSaleAmount(295f);
	saleDao.create(sale5);

	Provider provider1 = new Provider();
	provider1.setProviderName("Gustavo");
	providerDao.create(provider1);

	Provider provider2 = new Provider();
	provider2.setProviderName("Adriana");
	providerDao.create(provider2);

	List<Shop> shopList = new ArrayList<Shop>();
	shopList.add(shop1);
	shopList.add(shop2);

	model.addAttribute("shopList", shopList);
	model.addAttribute("currShop", shop1);

	return "redirect:/";
    }
}