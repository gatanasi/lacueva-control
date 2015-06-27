package com.lacueva.control.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.ItemType;
import com.lacueva.control.dao.ItemDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class ItemDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private ItemDao itemDao;

	@Test
	public void testCreateAndFind() {

		Item item = new Item();
		item.setItemType(ItemType.DVD);
		item.setItemWeight(16.4f);
		item.setItemBurnable(false);

		itemDao.create(item);

		Item foundItem = itemDao.find(item.getId());

		assertEquals(item, foundItem);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
