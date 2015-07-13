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

import com.lacueva.control.bean.Shop;
import com.lacueva.control.bean.Withdrawal;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.ShopDao;
import com.lacueva.control.dao.WithdrawalDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class WithdrawalDaoTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private WithdrawalDao withdrawalDao;

    @Inject
    private ShopDao shopDao;
    public Withdrawal withdrawal;

    public Shop shop;

    @Before
    public void before() throws ParseException {
	shop = new Shop();
	shop.setShopDate(DateUtilThreadSafe.parse("2010-03-20"));
	shop.setShopName("Shop1");
	shop.setShopCash(2000);

	shopDao.create(shop);

	withdrawal = new Withdrawal();
	withdrawal.setWithdrawalDate(DateUtilThreadSafe.parse("2015-02-10"));
	withdrawal.setWithdrawalShop(shop);
	withdrawal.setWithdrawalAmount(10f);
	;

	withdrawalDao.create(withdrawal);
    }

    @Test
    public void testCreate() throws ParseException {
	Withdrawal withdrawalCreate = new Withdrawal();
	withdrawalCreate.setWithdrawalDate(DateUtilThreadSafe
		.parse("2015-06-01"));
	withdrawalCreate.setWithdrawalShop(shop);
	withdrawalCreate.setWithdrawalAmount(15f);

	withdrawalDao.create(withdrawalCreate);

	Withdrawal foundWithdrawal = withdrawalDao.find(withdrawalCreate
		.getId());

	assertEquals(withdrawalCreate, foundWithdrawal);
	assertEquals(withdrawalCreate.getWithdrawalDate(),
		foundWithdrawal.getWithdrawalDate());
	assertEquals(withdrawalCreate.getWithdrawalShop(),
		foundWithdrawal.getWithdrawalShop());
	assertEquals(withdrawalCreate.getWithdrawalAmount(),
		foundWithdrawal.getWithdrawalAmount());
    }

    @Test
    public void testFind() {
	Withdrawal foundWithdrawal = withdrawalDao.find(withdrawal.getId());

	assertEquals(withdrawal, foundWithdrawal);
	assertEquals(withdrawal.getWithdrawalDate(),
		foundWithdrawal.getWithdrawalDate());
	assertEquals(withdrawal.getWithdrawalShop(),
		foundWithdrawal.getWithdrawalShop());
	assertEquals(withdrawal.getWithdrawalAmount(),
		foundWithdrawal.getWithdrawalAmount());
    }

    @Test
    public void testDelete() {
	withdrawalDao.delete(withdrawal.getId());

	Withdrawal foundWithdrawal = withdrawalDao.find(withdrawal.getId());

	assertNull(foundWithdrawal);
    }

    @Test
    public void testUpdate() {
	Withdrawal updatedWithdrawal = new Withdrawal();
	updatedWithdrawal.setId(withdrawal.getId());
	updatedWithdrawal.setWithdrawalDate(withdrawal.getWithdrawalDate());
	updatedWithdrawal.setWithdrawalShop(withdrawal.getWithdrawalShop());
	updatedWithdrawal.setWithdrawalAmount(30f);

	withdrawalDao.update(updatedWithdrawal);

	Withdrawal foundWithdrawal = withdrawalDao.find(withdrawal.getId());

	assertEquals(updatedWithdrawal, foundWithdrawal);
	assertEquals(updatedWithdrawal.getWithdrawalDate(),
		foundWithdrawal.getWithdrawalDate());
	assertEquals(updatedWithdrawal.getWithdrawalShop(),
		foundWithdrawal.getWithdrawalShop());
	assertEquals(updatedWithdrawal.getWithdrawalAmount(),
		foundWithdrawal.getWithdrawalAmount());
    }
}
