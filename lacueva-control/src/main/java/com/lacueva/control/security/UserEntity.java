package com.lacueva.control.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name = "CUEVA_USERS")
public class UserEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6118598858658416727L;

    @Id
    @NotNull
    @Column(name = "USERS_USERNAME", nullable = false)
    private String username;

    @NotNull
    @Column(name = "USERS_PASSWORD", nullable = false)
    private String password;

    @NotNull
    @Column(name = "USERS_ENABLED", nullable = false)
    private Boolean enabled;

    public UserEntity() {
	super();
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
    }

    public String toString()

    {
	StringBuilder sb = new StringBuilder("User [");
	sb.append("username=").append(getUsername()).append(", password=**PROTECTED**").append("enabled=")
		.append(isEnabled()).append("]");

	return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if ((obj != null) && (obj instanceof UserEntity) && getUsername() != null) {
	    final UserEntity that = (UserEntity) obj;
	    return getUsername().equals(that.getUsername());
	} else {
	    return false;
	}
    }

    @Override
    public int hashCode() {
	if (getUsername() != null) {
	    return getUsername().hashCode();
	} else {
	    return 0;
	}
    }
}
