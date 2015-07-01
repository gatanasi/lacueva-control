package com.lacueva.control.dao;

import java.util.Date;
import java.util.List;

import com.lacueva.control.bean.Sale;
import com.lacueva.control.bean.Shop;

public interface SaleDao extends GenericDao<Sale> {
	public List<Sale> findSalesByShopAndDate(Shop shop, Date date);

	public List<Sale> findSalesByShopAndBetweenDates(Shop shop, Date startDate,
			Date endDate);
}
