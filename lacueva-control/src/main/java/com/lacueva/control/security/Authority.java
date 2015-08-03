package com.lacueva.control.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Authority
 *
 */
@Entity
@Table(name = "CUEVA_AUTHORITIES")
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_AUTHORITIES_ID")
    @SequenceGenerator(name = "CUEVA_SEQ_AUTHORITIES_ID", sequenceName = "CUEVA_SEQ_AUTHORITIES_ID", initialValue = 1, allocationSize = 1)
    @Column(name = "AUTHORITY_ID")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "AUTHORITY_USERNAME", nullable = false)
    private String username;

    @NotNull
    @Column(name = "AUTHORITY_AUTHORITY", nullable = false)
    private String password;

    public Authority() {
	super();
    }

    public String toString()

    {
	StringBuilder sb = new StringBuilder("User [");
	sb.append("username=").append(getUsername()).append(", password=**PROTECTED**").append("]");

	return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if ((obj != null) && (obj instanceof Authority) && getUsername() != null) {
	    final Authority that = (Authority) obj;
	    return getUsername().equals(that.getUsername());
	} else {
	    return false;
	}
    }

    @Override
    public int hashCode() {
	if (getUsername() != null) {
	    return getUsername().intValue();
	} else {
	    return 0;
	}
    }
}
