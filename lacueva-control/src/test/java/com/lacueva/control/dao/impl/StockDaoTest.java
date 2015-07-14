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
import com.lacueva.control.bean.Shop;
import com.lacueva.control.bean.Stock;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.ShopDao;
import com.lacueva.control.dao.StockDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class StockDaoTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private StockDao stockDao;

    @Inject
    private ItemDao itemDao;

    @Inject
    private ShopDao shopDao;

    public Item item;

    public Stock stock;

    public Shop shop;

    @Before
    public void before() throws ParseException {
	item = new Item();
	item.setItemType("DVD");
	item.setItemWeight(16.4f);
	item.setItemBurnable(false);

	itemDao.create(item);

	shop = new Shop();
	shop.setShopDate(DateUtilThreadSafe.parse("2010-03-20"));
	shop.setShopName("Shop1");
	shop.setShopCash(2000);

	shopDao.create(shop);

	stock = new Stock();
	stock.setStockDate(DateUtilThreadSafe.parse("2015-02-10"));
	stock.setStockShop(shop);
	stock.setStockItem(item);
	stock.setStockQuantity(500);

	stockDao.create(stock);
    }

    @Test
    public void testCreate() throws ParseException {
	Stock stockCreate = new Stock();
	stockCreate.setStockDate(DateUtilThreadSafe.parse("2015-06-01"));
	stockCreate.setStockShop(shop);
	stockCreate.setStockItem(item);
	stockCreate.setStockQuantity(120);

	stockDao.create(stockCreate);

	Stock foundStock = stockDao.find(stockCreate.getId());

	assertEquals(stockCreate, foundStock);
	assertEquals(stockCreate.getStockDate(), foundStock.getStockDate());
	assertEquals(stockCreate.getStockShop(), foundStock.getStockShop());
	assertEquals(stockCreate.getStockItem(), foundStock.getStockItem());
	assertEquals(stockCreate.getStockQuantity(), foundStock.getStockQuantity());
    }

    @Test
    public void testFind() {
	Stock foundStock = stockDao.find(stock.getId());

	assertEquals(stock, foundStock);
	assertEquals(stock.getStockDate(), foundStock.getStockDate());
	assertEquals(stock.getStockShop(), foundStock.getStockShop());
	assertEquals(stock.getStockItem(), foundStock.getStockItem());
	assertEquals(stock.getStockQuantity(), foundStock.getStockQuantity());
    }

    @Test
    public void testDelete() {
	stockDao.delete(stock.getId());

	Stock foundStock = stockDao.find(stock.getId());

	assertNull(foundStock);
    }

    @Test
    public void testUpdate() {
	Stock updatedStock = new Stock();
	updatedStock.setId(stock.getId());
	updatedStock.setStockDate(stock.getStockDate());
	updatedStock.setStockShop(stock.getStockShop());
	updatedStock.setStockItem(stock.getStockItem());
	updatedStock.setStockQuantity(30);

	stockDao.update(updatedStock);

	Stock foundStock = stockDao.find(stock.getId());

	assertEquals(updatedStock, foundStock);
	assertEquals(updatedStock.getStockDate(), foundStock.getStockDate());
	assertEquals(updatedStock.getStockShop(), foundStock.getStockShop());
	assertEquals(updatedStock.getStockItem(), foundStock.getStockItem());
	assertEquals(updatedStock.getStockQuantity(), foundStock.getStockQuantity());
    }
}
