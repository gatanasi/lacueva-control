package com.lacueva.control.security;

import com.lacueva.control.dao.GenericDao;

public interface UserEntityDao extends GenericDao<UserEntity> {
    public UserEntity findUserByUsername(final String username);

}
