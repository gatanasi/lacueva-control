package com.lacueva.control.dao.impl;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Sale;
import com.lacueva.control.dao.SaleDao;

@Repository("saleDao")
public class SaleDaoImpl extends GenericDaoImpl<Sale> implements SaleDao {

}
