package com.lacueva.control.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.lacueva.control.bean.Sale;
import com.lacueva.control.bean.Shop;

public interface SaleDao extends GenericDao<Sale> {
    public List<Sale> findSalesByShopAndDate(final Shop shop, final Date date) throws ParseException;

    public List<Sale> findSalesByShopAndBetweenDates(final Shop shop, final Date startDate, final Date endDate);
}
