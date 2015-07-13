package com.lacueva.control.dao.impl;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Provider;
import com.lacueva.control.dao.ProviderDao;

@Repository("providerDao")
public class ProviderDaoImpl extends GenericDaoImpl<Provider> implements
	ProviderDao {

}
