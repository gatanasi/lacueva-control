package com.lacueva.control.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Authority
 *
 */
@Entity
@Table(name = "CUEVA_AUTHORITIES")
@NamedQueries({
	@NamedQuery(name = "Authorities.findAuthoritiesByUsername", query = "SELECT authorities FROM Authority authorities WHERE authorities.user.username = :username") })
public class Authority implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2095585636240139318L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_AUTHORITIES_ID")
    @SequenceGenerator(name = "CUEVA_SEQ_AUTHORITIES_ID", sequenceName = "CUEVA_SEQ_AUTHORITIES_ID", initialValue = 1, allocationSize = 1)
    @Column(name = "AUTHORITY_ID")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHORITY_USER", nullable = false)
    private UserEntity user;

    @NotNull
    @Column(name = "AUTHORITY_ROLE", length = 50, nullable = false)
    private String role;

    public Authority() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public UserEntity getUser() {
	return user;
    }

    public void setUser(UserEntity user) {
	this.user = user;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String toString()

    {
	StringBuilder sb = new StringBuilder("Authority [");
	sb.append("username=").append(getUser().getUsername()).append(", role=").append(getRole()).append("]");

	return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if ((obj != null) && (obj instanceof Authority) && getId() != null) {
	    final Authority that = (Authority) obj;
	    return getId().equals(that.getId());
	} else {
	    return false;
	}
    }

    @Override
    public int hashCode() {
	if (getId() != null) {
	    return getId().intValue();
	} else {
	    return 0;
	}
    }
}
