package com.lacueva.control.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Sale;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.dao.SaleDao;

@Repository("saleDao")
public class SaleDaoImpl extends GenericDaoImpl<Sale> implements SaleDao {

	@Override
	public List<Sale> findSalesByShopAndDate(Shop shop, Date date) {
		if (shop == null || shop.getId() == null || date == null) {
			return new ArrayList<Sale>();
		} else {
			TypedQuery<Sale> query = entityManager.createNamedQuery(
					"Sales.findByShopAndDate", Sale.class);
			query.setParameter("shop", shop);
			query.setParameter("date", date, TemporalType.DATE);
			return query.getResultList();
		}
	}

	@Override
	public List<Sale> findSalesByShopAndBetweenDates(Shop shop, Date startDate,
			Date endDate) {
		if (shop == null || shop.getId() == null || startDate == null
				|| endDate == null) {
			return new ArrayList<Sale>();
		} else {
			TypedQuery<Sale> query = entityManager.createNamedQuery(
					"Sales.findByShopAndBetweenDates", Sale.class);
			query.setParameter("shop", shop);
			query.setParameter("startDate", startDate, TemporalType.DATE);
			query.setParameter("endDate", endDate, TemporalType.DATE);
			return query.getResultList();
		}
	}
}
