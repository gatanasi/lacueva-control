package com.lacueva.control.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Price;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.dao.PriceDao;

@Repository("priceDao")
public class PriceDaoImpl extends GenericDaoImpl<Price>implements PriceDao {

	@Override
	public Price findPriceByShopAndItem(final Shop shop, final Item item) {
		if (shop == null || shop.getId() == null || item == null || item.getId() == null) {
			return new Price();
		} else {
			TypedQuery<Price> query = entityManager.createNamedQuery("Prices.findPriceByShopAndItem", Price.class);
			query.setParameter("shop", shop);
			query.setParameter("item", item);

			List<Price> foundList = query.getResultList();
			if (!foundList.isEmpty()) {
				return foundList.get(0);
			} else {
				return null;
			}
		}
	}
}
