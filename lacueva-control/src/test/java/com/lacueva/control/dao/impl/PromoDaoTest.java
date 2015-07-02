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
import com.lacueva.control.bean.Promo;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.PromoDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class PromoDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private PromoDao promoDao;

	@Inject
	private ItemDao itemDao;

	public Item item;

	public Promo promo;

	@Before
	public void before() {
		item = new Item();
		item.setItemType("DVD");
		item.setItemWeight(16.4f);
		item.setItemBurnable(false);

		itemDao.create(item);

		promo = new Promo();
		promo.setPromoItem(item);
		promo.setPromoQuantity(5);
		promo.setPromoValue(13f);

		promoDao.create(promo);
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
		assertEquals(promoCreate.getPromoQuantity(),
				foundPromo.getPromoQuantity());
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
		assertEquals(updatedPromo.getPromoQuantity(),
				foundPromo.getPromoQuantity());
		assertEquals(updatedPromo.getPromoValue(), foundPromo.getPromoValue());
	}
}
