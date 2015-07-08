package com.lacueva.control.dao.impl;

import org.springframework.stereotype.Repository;

import com.lacueva.control.bean.Input;
import com.lacueva.control.dao.InputDao;

@Repository("inputDao")
public class InputDaoImpl extends GenericDaoImpl<Input>implements InputDao {

}
