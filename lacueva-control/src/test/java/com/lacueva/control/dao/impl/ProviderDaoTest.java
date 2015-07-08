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

import com.lacueva.control.bean.Provider;
import com.lacueva.control.dao.ProviderDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class ProviderDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private ProviderDao providerDao;

	public Provider provider;

	@Before
	public void before() {
		provider = new Provider();
		provider.setProviderName("ProviderBefore");

		providerDao.create(provider);
	}

	@Test
	public void testCreate() {
		Provider providerCreate = new Provider();
		providerCreate.setProviderName("ProviderCreate");

		providerDao.create(providerCreate);

		Provider foundProvider = providerDao.find(providerCreate.getId());

		assertEquals(providerCreate, foundProvider);
		assertEquals(providerCreate.getProviderName(), foundProvider.getProviderName());
	}

	@Test
	public void testFind() {
		Provider foundProvider = providerDao.find(provider.getId());

		assertEquals(provider, foundProvider);
		assertEquals(provider.getProviderName(), foundProvider.getProviderName());
	}

	@Test
	public void testDelete() {
		providerDao.delete(provider.getId());

		Provider foundProvider = providerDao.find(provider.getId());

		assertNull(foundProvider);
	}

	@Test
	public void testUpdate() {
		Provider updatedProvider = new Provider();
		updatedProvider.setId(provider.getId());
		updatedProvider.setProviderName("ProviderUpdate");

		providerDao.update(updatedProvider);

		Provider foundProvider = providerDao.find(provider.getId());

		assertEquals(updatedProvider, foundProvider);
		assertEquals(updatedProvider.getProviderName(), foundProvider.getProviderName());
	}
}
