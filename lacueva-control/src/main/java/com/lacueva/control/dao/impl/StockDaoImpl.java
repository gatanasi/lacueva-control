package com.lacueva.control.dao.impl;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Stock;
import com.lacueva.control.dao.StockDao;

@Repository("stockDao")
public class StockDaoImpl extends GenericDaoImpl<Stock> implements StockDao {

}
