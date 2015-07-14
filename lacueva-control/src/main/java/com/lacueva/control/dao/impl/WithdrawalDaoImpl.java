package com.lacueva.control.dao.impl;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Withdrawal;
import com.lacueva.control.dao.WithdrawalDao;

@Repository("withdrawalDao")
public class WithdrawalDaoImpl extends GenericDaoImpl<Withdrawal>implements WithdrawalDao {

}
