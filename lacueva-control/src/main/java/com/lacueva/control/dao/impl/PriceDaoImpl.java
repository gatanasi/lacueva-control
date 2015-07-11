package com.lacueva.control.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Price;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.dao.PriceDao;

@Repository("priceDao")
public class PriceDaoImpl extends GenericDaoImpl<Price>implements PriceDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Price findPriceByShopAndItem(final Shop shop, final Item item) {
		logger.debug("Finding Price by Shop and Item");

		Price price = new Price();

		if (shop != null && shop.getId() != null && item != null && item.getId() != null) {
			logger.debug("Finding Price with Shop= " + shop.getId() + ", Item= " + item.getId());

			TypedQuery<Price> query = entityManager.createNamedQuery("Prices.findPriceByShopAndItem", Price.class);
			query.setParameter("shop", shop);
			query.setParameter("item", item);

			List<Price> foundList = query.getResultList();
			if (!foundList.isEmpty()) {
				logger.debug("Found with data= " + foundList.get(0));
				return foundList.get(0);
			}
		}
		return price;
	}
}
