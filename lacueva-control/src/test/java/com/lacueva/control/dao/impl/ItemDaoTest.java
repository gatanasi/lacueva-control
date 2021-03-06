package com.lacueva.control.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

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
import com.lacueva.control.dao.ItemDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class ItemDaoTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ItemDao itemDao;

    public Item item;

    @Before
    public void before() {
	item = new Item();
	item.setItemName("DVD");
	item.setItemWeight(16.4f);
	item.setItemBurnable(false);
	item.setItemPriority(1);

	itemDao.create(item);
    }

    @Test
    public void testCreate() {
	Item itemCreate = new Item();
	itemCreate.setItemName("DVD");
	itemCreate.setItemWeight(16.4f);
	itemCreate.setItemBurnable(false);
	itemCreate.setItemPriority(2);

	itemDao.create(itemCreate);

	Item foundItem = itemDao.find(itemCreate.getId());

	assertEquals(itemCreate, foundItem);
	assertEquals(itemCreate.getItemName(), foundItem.getItemName());
	assertEquals(itemCreate.getItemWeight(), foundItem.getItemWeight());
	assertEquals(itemCreate.getItemBurnable(), foundItem.getItemBurnable());
	assertEquals(itemCreate.getItemPriority(), foundItem.getItemPriority());
    }

    @Test
    public void testFind() {
	Item foundItem = itemDao.find(item.getId());

	assertEquals(item, foundItem);
	assertEquals(item.getItemName(), foundItem.getItemName());
	assertEquals(item.getItemWeight(), foundItem.getItemWeight());
	assertEquals(item.getItemBurnable(), foundItem.getItemBurnable());
	assertEquals(item.getItemPriority(), foundItem.getItemPriority());
    }

    @Test
    public void testFindError() {
	Item foundItem = itemDao.find(Long.valueOf(Integer.MAX_VALUE));

	assertNull(foundItem);
    }

    @Test
    public void testGetAll() {
	List<Item> foundItems = itemDao.getAll();

	assertNotEquals(0, foundItems.size());
    }

    @Test
    public void testDelete() {
	itemDao.delete(item.getId());

	Item foundItem = itemDao.find(item.getId());

	assertNull(foundItem);
    }

    @Test
    public void testUpdate() {
	Item updatedItem = new Item();
	updatedItem.setId(item.getId());
	updatedItem.setItemName(item.getItemName());
	updatedItem.setItemWeight(6f);
	updatedItem.setItemBurnable(item.getItemBurnable());
	updatedItem.setItemPriority(item.getItemPriority());

	itemDao.update(updatedItem);

	Item foundItem = itemDao.find(item.getId());

	assertEquals(updatedItem, foundItem);
	assertEquals(updatedItem.getItemName(), foundItem.getItemName());
	assertEquals(updatedItem.getItemWeight(), foundItem.getItemWeight());
	assertEquals(updatedItem.getItemBurnable(), foundItem.getItemBurnable());
	assertEquals(updatedItem.getItemPriority(), foundItem.getItemPriority());
    }

}
