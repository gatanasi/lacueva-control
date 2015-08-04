package com.lacueva.control.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lacueva.control.dao.impl.GenericDaoImpl;

@Repository("authorityDao")
public class AuthorityDaoImpl extends GenericDaoImpl<Authority>implements AuthorityDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Authority> findAuthoritiesByUsername(final String username) {
	logger.debug("Finding Authorities with Username= " + username);

	List<Authority> authorities = new ArrayList<Authority>();

	if (username != null) {
	    TypedQuery<Authority> query = entityManager.createNamedQuery("Authorities.findAuthoritiesByUsername",
		    Authority.class);
	    query.setParameter("username", username);

	    List<Authority> foundList = query.getResultList();
	    if (!foundList.isEmpty()) {
		logger.debug("Found with data= " + foundList);
		authorities.addAll(foundList);
	    }
	}
	return authorities;
    }
}
