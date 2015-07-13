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

import com.lacueva.control.bean.BlankDisc;
import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Provider;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.BlankDiscDao;
import com.lacueva.control.dao.ItemDao;
import com.lacueva.control.dao.ProviderDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class BlankDiscDaoTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private BlankDiscDao blankDiscDao;

    @Inject
    private ItemDao itemDao;

    @Inject
    private ProviderDao providerDao;

    public Item item;

    public Provider provider;

    public BlankDisc blankDisc;

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

	blankDisc = new BlankDisc();
	blankDisc.setBlankDiscDate(DateUtilThreadSafe.parse("2015-02-10"));
	blankDisc.setBlankDiscItem(item);
	blankDisc.setBlankDiscQuantity(50);
	blankDisc.setBlankDiscProvider(provider);

	blankDiscDao.create(blankDisc);
    }

    @Test
    public void testCreate() throws ParseException {
	BlankDisc blankDiscCreate = new BlankDisc();
	blankDiscCreate
		.setBlankDiscDate(DateUtilThreadSafe.parse("2015-06-01"));
	blankDiscCreate.setBlankDiscItem(item);
	blankDiscCreate.setBlankDiscQuantity(12);
	blankDiscCreate.setBlankDiscProvider(provider);

	blankDiscDao.create(blankDiscCreate);

	BlankDisc foundBlankDisc = blankDiscDao.find(blankDiscCreate.getId());

	assertEquals(blankDiscCreate, foundBlankDisc);
	assertEquals(blankDiscCreate.getBlankDiscDate(),
		foundBlankDisc.getBlankDiscDate());
	assertEquals(blankDiscCreate.getBlankDiscItem(),
		foundBlankDisc.getBlankDiscItem());
	assertEquals(blankDiscCreate.getBlankDiscQuantity(),
		foundBlankDisc.getBlankDiscQuantity());
	assertEquals(blankDiscCreate.getBlankDiscProvider(),
		foundBlankDisc.getBlankDiscProvider());
    }

    @Test
    public void testFind() {
	BlankDisc foundBlankDisc = blankDiscDao.find(blankDisc.getId());

	assertEquals(blankDisc, foundBlankDisc);
	assertEquals(blankDisc.getBlankDiscDate(),
		foundBlankDisc.getBlankDiscDate());
	assertEquals(blankDisc.getBlankDiscItem(),
		foundBlankDisc.getBlankDiscItem());
	assertEquals(blankDisc.getBlankDiscQuantity(),
		foundBlankDisc.getBlankDiscQuantity());
	assertEquals(blankDisc.getBlankDiscProvider(),
		foundBlankDisc.getBlankDiscProvider());
    }

    @Test
    public void testDelete() {
	blankDiscDao.delete(blankDisc.getId());

	BlankDisc foundBlankDisc = blankDiscDao.find(blankDisc.getId());

	assertNull(foundBlankDisc);
    }

    @Test
    public void testUpdate() {
	BlankDisc updatedBlankDisc = new BlankDisc();
	updatedBlankDisc.setId(blankDisc.getId());
	updatedBlankDisc.setBlankDiscDate(blankDisc.getBlankDiscDate());
	updatedBlankDisc.setBlankDiscItem(blankDisc.getBlankDiscItem());
	updatedBlankDisc.setBlankDiscQuantity(3);
	updatedBlankDisc.setBlankDiscProvider(blankDisc.getBlankDiscProvider());

	blankDiscDao.update(updatedBlankDisc);

	BlankDisc foundBlankDisc = blankDiscDao.find(blankDisc.getId());

	assertEquals(updatedBlankDisc, foundBlankDisc);
	assertEquals(updatedBlankDisc.getBlankDiscDate(),
		foundBlankDisc.getBlankDiscDate());
	assertEquals(updatedBlankDisc.getBlankDiscItem(),
		foundBlankDisc.getBlankDiscItem());
	assertEquals(updatedBlankDisc.getBlankDiscQuantity(),
		foundBlankDisc.getBlankDiscQuantity());
	assertEquals(updatedBlankDisc.getBlankDiscProvider(),
		foundBlankDisc.getBlankDiscProvider());
    }
}
