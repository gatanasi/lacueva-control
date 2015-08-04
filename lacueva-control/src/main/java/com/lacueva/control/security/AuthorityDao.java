package com.lacueva.control.security;

import java.util.List;

import com.lacueva.control.dao.GenericDao;

public interface AuthorityDao extends GenericDao<Authority> {
    public List<Authority> findAuthoritiesByUsername(final String username);

}
