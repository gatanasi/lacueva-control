package com.lacueva.control.security;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserDetailsService {

    @Inject
    private UserDao userDao;
    @Inject
    private Assembler assembler;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

	UserDetails userDetails = null;
	UserEntity userEntity = userDao.findByName(username);
	if (userEntity == null)
	    throw new UsernameNotFoundException("User not found");

	return assembler.buildUserFromUserEntity(userEntity);
    }
}
