package com.lacueva.control.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Promo;
import com.lacueva.control.bean.Shop;
import com.lacueva.control.dao.PromoDao;

@Repository("promoDao")
public class PromoDaoImpl extends GenericDaoImpl<Promo> implements PromoDao {

	@Override
	public Promo findPromoByShopAndItemAndQty(final Shop shop, final Item item,
			final Integer quantity) {
		if (shop == null || shop.getId() == null || item == null
				|| item.getId() == null) {
			return new Promo();
		} else {
			Integer quantityFind = (quantity == null) ? 0 : quantity;

			TypedQuery<Promo> query = entityManager.createNamedQuery(
					"Promos.findPromoByShopAndItemAndQty", Promo.class);
			query.setParameter("shop", shop);
			query.setParameter("item", item);
			query.setParameter("quantity", quantityFind);

			List<Promo> foundList = query.setMaxResults(1).getResultList();
			if (!foundList.isEmpty()) {
				return foundList.get(0);
			} else {
				return null;
			}
		}
	}
}
