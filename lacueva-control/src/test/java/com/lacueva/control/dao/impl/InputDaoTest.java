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

import com.lacueva.control.bean.Input;
import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Provider;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.InputDao;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.ProviderDao;
import com.lacueva.control.dao.ShopDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class InputDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private InputDao inputDao;

	@Inject
	private ItemDao itemDao;

	@Inject
	private ProviderDao providerDao;

	@Inject
	private ShopDao shopDao;

	public Item item;

	public Provider provider;

	public Input input;

	public Shop shop;

	@Before
	public void before() throws ParseException {
		item = new Item();
		item.setItemType("DVD");
		item.setItemWeight(16.4f);
		item.setItemBurnable(false);

		itemDao.create(item);

		provider = new Provider();
		provider.setProviderName("Provider1");

		providerDao.create(provider);

		shop = new Shop();
		shop.setShopDate(DateUtilThreadSafe.parse("2010-03-20"));
		shop.setShopName("Shop1");
		shop.setShopCash(2000);

		shopDao.create(shop);

		input = new Input();
		input.setInputDate(DateUtilThreadSafe.parse("2015-02-10"));
		input.setInputItem(item);
		input.setInputQuantity(50);
		input.setInputProvider(provider);
		input.setInputShop(shop);

		inputDao.create(input);
	}

	@Test
	public void testCreate() throws ParseException {
		Input inputCreate = new Input();
		inputCreate.setInputDate(DateUtilThreadSafe.parse("2015-06-01"));
		inputCreate.setInputItem(item);
		inputCreate.setInputQuantity(12);
		inputCreate.setInputProvider(provider);
		inputCreate.setInputShop(shop);

		inputDao.create(inputCreate);

		Input foundInput = inputDao.find(inputCreate.getId());

		assertEquals(inputCreate, foundInput);
		assertEquals(inputCreate.getInputDate(), foundInput.getInputDate());
		assertEquals(inputCreate.getInputItem(), foundInput.getInputItem());
		assertEquals(inputCreate.getInputQuantity(),
				foundInput.getInputQuantity());
		assertEquals(inputCreate.getInputProvider(),
				foundInput.getInputProvider());
		assertEquals(inputCreate.getInputShop(), foundInput.getInputShop());
	}

	@Test
	public void testFind() {
		Input foundInput = inputDao.find(input.getId());

		assertEquals(input, foundInput);
		assertEquals(input.getInputDate(), foundInput.getInputDate());
		assertEquals(input.getInputItem(), foundInput.getInputItem());
		assertEquals(input.getInputQuantity(), foundInput.getInputQuantity());
		assertEquals(input.getInputProvider(), foundInput.getInputProvider());
		assertEquals(input.getInputShop(), foundInput.getInputShop());
	}

	@Test
	public void testDelete() {
		inputDao.delete(input.getId());

		Input foundInput = inputDao.find(input.getId());

		assertNull(foundInput);
	}

	@Test
	public void testUpdate() {
		Input updatedInput = new Input();
		updatedInput.setId(input.getId());
		updatedInput.setInputDate(input.getInputDate());
		updatedInput.setInputItem(input.getInputItem());
		updatedInput.setInputQuantity(3);
		updatedInput.setInputProvider(input.getInputProvider());
		updatedInput.setInputShop(input.getInputShop());

		inputDao.update(updatedInput);

		Input foundInput = inputDao.find(input.getId());

		assertEquals(updatedInput, foundInput);
		assertEquals(updatedInput.getInputDate(), foundInput.getInputDate());
		assertEquals(updatedInput.getInputItem(), foundInput.getInputItem());
		assertEquals(updatedInput.getInputQuantity(),
				foundInput.getInputQuantity());
		assertEquals(updatedInput.getInputProvider(),
				foundInput.getInputProvider());
		assertEquals(updatedInput.getInputShop(), foundInput.getInputShop());
	}
}
