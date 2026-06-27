package com.lacueva.control.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class CuevaUserDetailsService implements UserDetailsService {

    @Inject
    private UserEntityDao userEntityDao;
    @Inject
    private AuthorityDao authorityDao;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

	UserEntity userEntity = userEntityDao.findUserByUsername(username);
	if (userEntity == null || userEntity.getUsername() == null) {
	    throw new UsernameNotFoundException("User not found");
	}

	List<Authority> authorities = authorityDao.findAuthoritiesByUsername(username);

	List<SimpleGrantedAuthority> roles = buildUserAuthority(authorities);

	return buildUserForAuthentication(userEntity, roles);
    }

    private User buildUserForAuthentication(UserEntity user, List<SimpleGrantedAuthority> authorities) {
	return new User(user.getUsername(), user.getPassword(), user.isEnabled(), user.isEnabled(), user.isEnabled(),
		user.isEnabled(), authorities);
    }

    private List<SimpleGrantedAuthority> buildUserAuthority(List<Authority> userRoles) {

	List<SimpleGrantedAuthority> setAuths = new ArrayList<SimpleGrantedAuthority>();

	for (Authority userRole : userRoles) {
	    setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
	}

	List<SimpleGrantedAuthority> result = new ArrayList<SimpleGrantedAuthority>(setAuths);

	return result;
    }
}
