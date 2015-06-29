package com.lacueva.control.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.ItemType;
import com.lacueva.control.bean.Sale;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.SaleDao;
import com.lacueva.control.dao.ShopDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class SaleDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private SaleDao saleDao;

	@Inject
	private ItemDao itemDao;

	@Inject
	private ShopDao shopDao;

	public Item item;

	public Sale sale;

	public Shop shop;

	@Before
	public void before() throws ParseException {
		item = new Item();
		item.setItemType(ItemType.DVD);
		item.setItemWeight(16.4f);
		item.setItemBurnable(false);

		itemDao.create(item);

		shop = new Shop();
		shop.setShopDate(DateUtilThreadSafe.parse("2010-03-20"));
		shop.setShopName("Shop1");
		shop.setShopCash(2000);

		shopDao.create(shop);

		sale = new Sale();
		sale.setSaleDate(DateUtilThreadSafe.parse("2015-02-10"));
		sale.setSaleShop(shop);
		sale.setSaleItem(item);
		sale.setSaleQuantity(50);
		sale.setSaleAmount(10f);
		;

		saleDao.create(sale);
	}

	@Test
	public void testCreate() throws ParseException {
		Sale saleCreate = new Sale();
		saleCreate.setSaleDate(DateUtilThreadSafe.parse("2015-06-01"));
		saleCreate.setSaleShop(shop);
		saleCreate.setSaleItem(item);
		saleCreate.setSaleQuantity(12);
		saleCreate.setSaleAmount(15f);
		;

		saleDao.create(saleCreate);

		Sale foundSale = saleDao.find(saleCreate.getId());

		assertEquals(saleCreate, foundSale);
		assertEquals(saleCreate.getSaleDate(), foundSale.getSaleDate());
		assertEquals(saleCreate.getSaleShop(), foundSale.getSaleShop());
		assertEquals(saleCreate.getSaleItem(), foundSale.getSaleItem());
		assertEquals(saleCreate.getSaleQuantity(), foundSale.getSaleQuantity());
		assertEquals(saleCreate.getSaleAmount(), foundSale.getSaleAmount());
	}

	@Test
	public void testFind() {
		Sale foundSale = saleDao.find(sale.getId());

		assertEquals(sale, foundSale);
		assertEquals(sale.getSaleDate(), foundSale.getSaleDate());
		assertEquals(sale.getSaleShop(), foundSale.getSaleShop());
		assertEquals(sale.getSaleItem(), foundSale.getSaleItem());
		assertEquals(sale.getSaleQuantity(), foundSale.getSaleQuantity());
		assertEquals(sale.getSaleAmount(), foundSale.getSaleAmount());
	}

	@Test
	public void testDelete() {
		saleDao.delete(sale.getId());

		Sale foundSale = saleDao.find(sale.getId());

		assertNull(foundSale);
	}

	@Test
	public void testUpdate() {
		Sale updatedSale = new Sale();
		updatedSale.setId(sale.getId());
		updatedSale.setSaleDate(sale.getSaleDate());
		updatedSale.setSaleShop(sale.getSaleShop());
		updatedSale.setSaleItem(sale.getSaleItem());
		updatedSale.setSaleQuantity(3);
		updatedSale.setSaleAmount(30f);

		saleDao.update(updatedSale);

		Sale foundSale = saleDao.find(sale.getId());

		assertEquals(updatedSale, foundSale);
		assertEquals(updatedSale.getSaleDate(), foundSale.getSaleDate());
		assertEquals(updatedSale.getSaleShop(), foundSale.getSaleShop());
		assertEquals(updatedSale.getSaleItem(), foundSale.getSaleItem());
		assertEquals(updatedSale.getSaleQuantity(), foundSale.getSaleQuantity());
		assertEquals(updatedSale.getSaleAmount(), foundSale.getSaleAmount());
	}
}
