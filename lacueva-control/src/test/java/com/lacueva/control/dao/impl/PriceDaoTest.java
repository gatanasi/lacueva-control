package com.lacueva.control.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Price;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.PriceDao;
import com.lacueva.control.dao.ShopDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class PriceDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private PriceDao priceDao;

	@Inject
	private ItemDao itemDao;

	@Inject
	private ShopDao shopDao;

	public Item item;

	public Shop shop;

	public Price price;

	@Before
	public void before() throws ParseException {
		item = new Item();
		item.setItemType("DVD");
		item.setItemWeight(16.4f);
		item.setItemBurnable(false);

		itemDao.create(item);

		ArrayList<Item> itemList = new ArrayList<Item>();
		itemList.add(item);

		shop = new Shop();
		shop.setShopDate(DateUtilThreadSafe.parse("2010-03-20"));
		shop.setShopName("Shop1");
		shop.setShopCash(2000);
		shop.setShopItems(itemList);

		shopDao.create(shop);

		price = new Price();
		price.setPriceItem(item);
		price.setPriceShop(shop);
		price.setPriceValue(13f);

		priceDao.create(price);
	}

	@Test
	public void testCreate() {
		Price priceCreate = new Price();
		priceCreate.setPriceItem(item);
		priceCreate.setPriceValue(6f);

		priceDao.create(priceCreate);

		Price foundPrice = priceDao.find(priceCreate.getId());

		assertEquals(priceCreate, foundPrice);
		assertEquals(priceCreate.getPriceShop(), foundPrice.getPriceShop());
		assertEquals(priceCreate.getPriceItem(), foundPrice.getPriceItem());
		assertEquals(priceCreate.getPriceValue(), foundPrice.getPriceValue());
	}

	@Test
	public void testFind() {
		Price foundPrice = priceDao.find(price.getId());

		assertEquals(price, foundPrice);
		assertEquals(price.getPriceShop(), foundPrice.getPriceShop());
		assertEquals(price.getPriceItem(), foundPrice.getPriceItem());
		assertEquals(price.getPriceValue(), foundPrice.getPriceValue());
	}

	@Test
	public void testDelete() {
		priceDao.delete(price.getId());

		Price foundPrice = priceDao.find(price.getId());

		assertNull(foundPrice);
	}

	@Test
	public void testUpdate() {
		Price updatedPrice = new Price();
		updatedPrice.setId(price.getId());
		updatedPrice.setPriceItem(price.getPriceItem());
		updatedPrice.setPriceValue(3f);

		priceDao.update(updatedPrice);

		Price foundPrice = priceDao.find(price.getId());

		assertEquals(updatedPrice, foundPrice);
		assertEquals(updatedPrice.getPriceShop(), foundPrice.getPriceShop());
		assertEquals(updatedPrice.getPriceItem(), foundPrice.getPriceItem());
		assertEquals(updatedPrice.getPriceValue(), foundPrice.getPriceValue());
	}

	@Test
	public void testFindPriceByShopAndItem() {
		Price foundPrice = priceDao.findPriceByShopAndItem(shop, item);

		assertEquals(price, foundPrice);
		assertEquals(price.getPriceShop(), foundPrice.getPriceShop());
		assertEquals(price.getPriceItem(), foundPrice.getPriceItem());
		assertEquals(price.getPriceValue(), foundPrice.getPriceValue());
	}
}
