package com.lacueva.control.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assembler")
public class Assembler {

    @Transactional(readOnly = true)
    User buildUserFromUserEntity(UserEntity userEntity) {

	String username = userEntity.getUsername();
	String password = userEntity.getPassword();
	boolean enabled = userEntity.isEnabled();
	boolean accountNonExpired = userEntity.isEnabled();
	boolean credentialsNonExpired = userEntity.isEnabled();
	boolean accountNonLocked = userEntity.isEnabled();

	Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	for (SecurityRoleEntity role : userEntity.getRoles()) {
	    authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
	}

	User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
		authorities, id);
	return user;
    }
}
