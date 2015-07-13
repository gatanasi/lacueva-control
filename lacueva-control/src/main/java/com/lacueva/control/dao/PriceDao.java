package com.lacueva.control.dao;

import com.lacueva.control.bean.Item;
import com.lacueva.control.bean.Price;
import com.lacueva.control.bean.Shop;

public interface PriceDao extends GenericDao<Price> {

    public Price findPriceByShopAndItem(final Shop shop, final Item item);
}
