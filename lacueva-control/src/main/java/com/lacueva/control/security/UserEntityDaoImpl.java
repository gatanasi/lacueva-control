package com.lacueva.control.security;

import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lacueva.control.dao.impl.GenericDaoImpl;

@Repository("userEntityDao")
public class UserEntityDaoImpl extends GenericDaoImpl<UserEntity>implements UserEntityDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserEntity findUserByUsername(final String username) {
	logger.debug("Finding User with Username= " + username);

	UserEntity user = new UserEntity();

	if (username != null) {
	    TypedQuery<UserEntity> query = entityManager.createNamedQuery("Users.findUserByUsername", UserEntity.class);
	    query.setParameter("username", username);

	    List<UserEntity> foundList = query.getResultList();
	    if (!foundList.isEmpty()) {
		logger.debug("Found with data= " + foundList);
		user = foundList.get(0);
	    }
	}
	return user;
    }
}
