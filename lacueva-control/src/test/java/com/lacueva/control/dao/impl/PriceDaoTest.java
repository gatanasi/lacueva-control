package com.lacueva.control.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import com.lacueva.control.bean.Price;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.PriceDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class PriceDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private PriceDao priceDao;

	@Inject
	private ItemDao itemDao;

	public Item item;

	public Price price;

	@Before
	public void before() {
		item = new Item();
		item.setItemType(ItemType.DVD);
		item.setItemWeight(16.4f);
		item.setItemBurnable(false);

		itemDao.create(item);

		price = new Price();
		price.setPriceItem(item);
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
		assertEquals(priceCreate.getPriceItem(), foundPrice.getPriceItem());
		assertEquals(priceCreate.getPriceValue(), foundPrice.getPriceValue());
	}

	@Test
	public void testFind() {
		Price foundPrice = priceDao.find(price.getId());

		assertEquals(price, foundPrice);
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
		assertEquals(updatedPrice.getPriceItem(), foundPrice.getPriceItem());
		assertEquals(updatedPrice.getPriceValue(), foundPrice.getPriceValue());
	}
}
