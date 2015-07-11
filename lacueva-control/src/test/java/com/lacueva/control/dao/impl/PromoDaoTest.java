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
import com.lacueva.control.bean.Promo;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.PromoDao;
import com.lacueva.control.dao.ShopDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class PromoDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private PromoDao promoDao;

	@Inject
	private ItemDao itemDao;

	@Inject
	private ShopDao shopDao;

	public Item item;

	public Shop shop;

	public Promo promo;

	public Promo promo2;

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

		promo = new Promo();
		promo.setPromoItem(item);
		promo.setPromoShop(shop);
		promo.setPromoQuantity(5);
		promo.setPromoValue(15f);

		promoDao.create(promo);

		promo2 = new Promo();
		promo2.setPromoItem(item);
		promo2.setPromoShop(shop);
		promo2.setPromoQuantity(10);
		promo2.setPromoValue(25f);

		promoDao.create(promo2);
	}

	@Test
	public void testCreate() {
		Promo promoCreate = new Promo();
		promoCreate.setPromoItem(item);
		promoCreate.setPromoQuantity(7);
		promoCreate.setPromoValue(6f);

		promoDao.create(promoCreate);

		Promo foundPromo = promoDao.find(promoCreate.getId());

		assertEquals(promoCreate, foundPromo);
		assertEquals(promoCreate.getPromoItem(), foundPromo.getPromoItem());
		assertEquals(promoCreate.getPromoQuantity(), foundPromo.getPromoQuantity());
		assertEquals(promoCreate.getPromoValue(), foundPromo.getPromoValue());
	}

	@Test
	public void testFind() {
		Promo foundPromo = promoDao.find(promo.getId());

		assertEquals(promo, foundPromo);
		assertEquals(promo.getPromoItem(), foundPromo.getPromoItem());
		assertEquals(promo.getPromoQuantity(), foundPromo.getPromoQuantity());
		assertEquals(promo.getPromoValue(), foundPromo.getPromoValue());
	}

	@Test
	public void testDelete() {
		promoDao.delete(promo.getId());

		Promo foundPromo = promoDao.find(promo.getId());

		assertNull(foundPromo);
	}

	@Test
	public void testUpdate() {
		Promo updatedPromo = new Promo();
		updatedPromo.setId(promo.getId());
		updatedPromo.setPromoItem(promo.getPromoItem());
		updatedPromo.setPromoQuantity(15);
		updatedPromo.setPromoValue(3f);

		promoDao.update(updatedPromo);

		Promo foundPromo = promoDao.find(promo.getId());

		assertEquals(updatedPromo, foundPromo);
		assertEquals(updatedPromo.getPromoItem(), foundPromo.getPromoItem());
		assertEquals(updatedPromo.getPromoQuantity(), foundPromo.getPromoQuantity());
		assertEquals(updatedPromo.getPromoValue(), foundPromo.getPromoValue());
	}

	@Test
	public void testFindPromoByShopAndItemAndQty() {
		Promo foundPromo = promoDao.findPromoByShopAndItemAndQty(shop, item, 7);
		Promo foundPromo2 = promoDao.findPromoByShopAndItemAndQty(shop, item, 15);
		Promo notFoundPromo = promoDao.findPromoByShopAndItemAndQty(shop, item, 3);

		assertEquals(promo, foundPromo);
		assertEquals(promo.getPromoShop(), foundPromo.getPromoShop());
		assertEquals(promo.getPromoItem(), foundPromo.getPromoItem());
		assertEquals(promo.getPromoQuantity(), foundPromo.getPromoQuantity());
		assertEquals(promo.getPromoValue(), foundPromo.getPromoValue());

		assertEquals(promo2, foundPromo2);
		assertEquals(promo2.getPromoShop(), foundPromo2.getPromoShop());
		assertEquals(promo2.getPromoItem(), foundPromo2.getPromoItem());
		assertEquals(promo2.getPromoQuantity(), foundPromo2.getPromoQuantity());
		assertEquals(promo2.getPromoValue(), foundPromo2.getPromoValue());

		assertNull(notFoundPromo.getId());
	}
}
