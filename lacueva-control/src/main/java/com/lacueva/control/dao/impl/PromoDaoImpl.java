package com.lacueva.control.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Promo;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.dao.PromoDao;

@Repository("promoDao")
public class PromoDaoImpl extends GenericDaoImpl<Promo>implements PromoDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Promo findPromoByShopAndItemAndQty(final Shop shop, final Item item, final Integer quantity) {
		logger.debug("Finding Promo by Shop, Item and Quantity");

		Promo promo = new Promo();

		if (shop != null && shop.getId() != null && item != null && item.getId() != null && quantity != null) {
			logger.debug(
					"Finding Promo with Shop= " + shop.getId() + ", Item= " + item.getId() + ", Quantity= " + quantity);

			TypedQuery<Promo> query = entityManager.createNamedQuery("Promos.findPromoByShopAndItemAndQty",
					Promo.class);
			query.setParameter("shop", shop);
			query.setParameter("item", item);
			query.setParameter("quantity", quantity);

			List<Promo> foundList = query.setMaxResults(1).getResultList();
			if (!foundList.isEmpty()) {
				logger.debug("Found with data= " + foundList.get(0));
				return foundList.get(0);
			}
		}
		return promo;
	}
}
