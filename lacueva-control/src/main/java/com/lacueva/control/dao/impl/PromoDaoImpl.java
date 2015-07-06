package com.lacueva.control.dao.impl;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Promo;
import com.lacueva.control.dao.PromoDao;

@Repository("promoDao")
public class PromoDaoImpl extends GenericDaoImpl<Promo>implements PromoDao {

}
