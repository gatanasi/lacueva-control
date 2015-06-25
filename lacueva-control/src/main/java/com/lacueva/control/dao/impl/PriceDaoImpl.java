package com.lacueva.control.dao.impl;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Price;
import com.lacueva.control.dao.PriceDao;

@Repository("priceDao")
public class PriceDaoImpl extends GenericDaoImpl<Price> implements PriceDao {

}
