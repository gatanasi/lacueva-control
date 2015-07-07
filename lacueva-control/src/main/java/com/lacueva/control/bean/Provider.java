package com.lacueva.control.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Provider
 *
 */
@Entity
@Table(name = "CUEVA_PROVIDERS")
public class Provider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1188774249747124397L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_PROVIDERS_ID")
	@SequenceGenerator(name = "CUEVA_SEQ_PROVIDERS_ID", sequenceName = "CUEVA_SEQ_PROVIDERS_ID", initialValue = 1, allocationSize = 1)
	@Column(name = "PROVIDER_ID")
	private Long id;

	@Column(name = "PROVIDER_NAME", nullable = false)
	private String providerName;

	public Provider() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	@Override
	public String toString()

	{
		StringBuilder sb = new StringBuilder("Provider [");
		sb.append("providerId=").append(getId()).append(", providerName=")
				.append(getProviderName()).append("]");

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Provider) && getId() != null) {
			final Provider that = (Provider) obj;
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
