package com.lacueva.control.dao.impl;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Item;
import com.lacueva.control.dao.ItemDao;

@Repository("itemDao")
public class ItemDaoImpl extends GenericDaoImpl<Item>implements ItemDao {

}
