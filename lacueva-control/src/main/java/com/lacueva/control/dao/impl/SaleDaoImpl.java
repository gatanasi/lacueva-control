package com.lacueva.control.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Sale;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.commons.DateUtilThreadSafe;
import com.lacueva.control.dao.SaleDao;

@Repository("saleDao")
public class SaleDaoImpl extends GenericDaoImpl<Sale>implements SaleDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<Sale> findSalesByShopAndDate(final Shop shop, final Date date) throws ParseException {
		Date formattedDate = DateUtilThreadSafe.parse(DateUtilThreadSafe.format(date));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		Date nextDay = calendar.getTime();

		return findSalesByShopAndBetweenDates(shop, formattedDate, nextDay);
	}

	@Override
	public List<Sale> findSalesByShopAndBetweenDates(final Shop shop, final Date startDate, final Date endDate) {
		logger.debug("Finding sales by shop and dates");
		if (shop == null || shop.getId() == null || startDate == null || endDate == null) {
			logger.debug("Received a null parameter");
			return new ArrayList<Sale>();
		} else {
			logger.debug(
					"Finding sales with Shop= " + shop.getId() + ", StartDate= " + startDate + ", EndDate= " + endDate);

			TypedQuery<Sale> query = entityManager.createNamedQuery("Sales.findByShopAndBetweenDates", Sale.class);
			query.setParameter("shop", shop);
			query.setParameter("startDate", startDate, TemporalType.DATE);
			query.setParameter("endDate", endDate, TemporalType.DATE);

			List<Sale> salesList = query.getResultList();
			if (salesList == null || salesList.size() == 0) {
				logger.debug("No Sales FOUND");
			} else {
				logger.debug("Found with data= " + salesList);
			}
			return salesList;
		}
	}
}
