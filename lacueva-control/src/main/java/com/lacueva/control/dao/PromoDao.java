package com.lacueva.control.dao;

import java.util.List;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Promo;
import com.lacueva.control.bean.Shop;

public interface PromoDao extends GenericDao<Promo> {

    public Promo findPromoByShopAndItemAndQty(final Shop shop, final Item item, final Integer quantity);
    
    public List<Promo> findPromosByShop(final Shop shop);
}
