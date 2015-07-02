package com.lacueva.control.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.ShopDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class ShopDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private ShopDao shopDao;

	@Inject
	private ItemDao itemDao;

	public List<Item> itemsList;

	public Shop shop;

	@Before
	public void before() throws ParseException {
		itemsList = new ArrayList<Item>();
		Item itemforList = new Item();
		itemforList.setItemType("ENVELOPE_WITH_FLAP");
		itemforList.setItemWeight(1.3f);
		itemforList.setItemBurnable(false);
		itemDao.create(itemforList);

		itemsList.add(itemforList);

		shop = new Shop();
		shop.setShopDate(DateUtilThreadSafe.parse("2010-03-20"));
		shop.setShopName("Shop1");
		shop.setShopCash(2000);
		shop.setShopItems(itemsList);

		shopDao.create(shop);
	}

	@Test
	public void testCreate() throws ParseException {
		List<Item> itemsListCreate = new ArrayList<Item>();
		Item itemforListCreate = new Item();
		itemforListCreate.setItemType("DVD");
		itemforListCreate.setItemWeight(16.4f);
		itemforListCreate.setItemBurnable(false);
		itemDao.create(itemforListCreate);

		itemsListCreate.add(itemforListCreate);

		Shop shopCreate = new Shop();
		shopCreate.setShopDate(DateUtilThreadSafe.parse("2015-06-01"));
		shopCreate.setShopName("ShopCreate");
		shopCreate.setShopCash(3000);
		shopCreate.setShopItems(itemsListCreate);

		shopDao.create(shopCreate);

		Shop foundShop = shopDao.find(shopCreate.getId());

		assertEquals(shopCreate, foundShop);
		assertEquals(shopCreate.getShopDate(), foundShop.getShopDate());
		assertEquals(shopCreate.getShopName(), foundShop.getShopName());
		assertEquals(shopCreate.getShopCash(), foundShop.getShopCash());
		assertEquals(new HashSet<Item>().addAll(shopCreate.getShopItems()),
				new HashSet<Item>().addAll(foundShop.getShopItems()));
	}

	@Test
	public void testFind() {
		Shop foundShop = shopDao.find(shop.getId());

		assertEquals(shop, foundShop);
		assertEquals(shop.getShopDate(), foundShop.getShopDate());
		assertEquals(shop.getShopName(), foundShop.getShopName());
		assertEquals(shop.getShopCash(), foundShop.getShopCash());
		assertEquals(new HashSet<Item>().addAll(shop.getShopItems()),
				new HashSet<Item>().addAll(foundShop.getShopItems()));
	}

	@Test
	public void testDelete() {
		shopDao.delete(shop.getId());

		Shop foundShop = shopDao.find(shop.getId());

		assertNull(foundShop);
	}

	@Test
	public void testUpdate() {
		Shop updatedShop = new Shop();
		updatedShop.setId(shop.getId());
		updatedShop.setShopDate(shop.getShopDate());
		updatedShop.setShopName("ShopUpdate");
		updatedShop.setShopCash(1000);
		updatedShop.setShopItems(shop.getShopItems());

		shopDao.update(updatedShop);

		Shop foundShop = shopDao.find(shop.getId());

		assertEquals(updatedShop, foundShop);
		assertEquals(updatedShop.getShopDate(), foundShop.getShopDate());
		assertEquals(updatedShop.getShopName(), foundShop.getShopName());
		assertEquals(updatedShop.getShopCash(), foundShop.getShopCash());
		assertEquals(new HashSet<Item>().addAll(updatedShop.getShopItems()),
				new HashSet<Item>().addAll(foundShop.getShopItems()));
	}
}
