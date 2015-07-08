package com.lacueva.control.dao.impl;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Shop;
import com.lacueva.control.dao.ShopDao;

@Repository("shopDao")
public class ShopDaoImpl extends GenericDaoImpl<Shop>implements ShopDao {

}
