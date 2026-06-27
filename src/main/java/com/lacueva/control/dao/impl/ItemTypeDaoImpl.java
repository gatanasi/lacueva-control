package com.lacueva.control.dao.impl;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.ItemType;
import com.lacueva.control.dao.ItemTypeDao;

@Repository("itemTypeDao")
public class ItemTypeDaoImpl extends GenericDaoImpl<ItemType>implements ItemTypeDao {

}
