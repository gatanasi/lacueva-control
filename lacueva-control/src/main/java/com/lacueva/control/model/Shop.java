package com.lacueva.control.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Shop
 *
 */
@Entity
@Table(name = "CUEVA_SHOPS")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_SHOPS_ID")
	@SequenceGenerator(name = "CUEVA_SEQ_SHOPS_ID", sequenceName = "CUEVA_SEQ_SHOPS_ID", initialValue = 1, allocationSize = 1)
	@Column(name = "SHOP_ID")
	private Long id;

	@Column(name = "SHOP_DATE", nullable = false)
	private Date shopDate;

	@Column(name = "SHOP_NAME", nullable = false)
	private String shopName;

	@Column(name = "SHOP_CASH")
	private int shopCash;

	public Shop() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getShopDate() {
		return shopDate;
	}

	public void setShopDate(Date shopDate) {
		this.shopDate = shopDate;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getShopCash() {
		return shopCash;
	}

	public void setShopCash(int shopCash) {
		this.shopCash = shopCash;
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Item)) {
			final Item that = (Item) obj;
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
