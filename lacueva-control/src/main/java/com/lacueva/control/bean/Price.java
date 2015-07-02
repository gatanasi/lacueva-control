package com.lacueva.control.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Price
 *
 */
@Entity
@Table(name = "CUEVA_PRICES")
public class Price implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -417359377983858106L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_PRICES_ID")
	@SequenceGenerator(name = "CUEVA_SEQ_PRICES_ID", sequenceName = "CUEVA_SEQ_PRICES_ID", initialValue = 1, allocationSize = 1)
	@Column(name = "PRICE_ID")
	private Long id;

	@OneToOne
	@JoinColumn(name = "PRICE_ITEM_ID")
	private Item priceItem;

	@Column(name = "PRICE_VALUE", nullable = false)
	private Float priceValue;

	public Price() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getPriceItem() {
		return priceItem;
	}

	public void setPriceItem(Item priceItem) {
		this.priceItem = priceItem;
	}

	public Float getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(Float priceValue) {
		this.priceValue = priceValue;
	}

	@Override
	public String toString()

	{
		return "Price [priceId=" + getId() + ", priceItemType="
				+ getPriceItem().getItemType() + ", priceValue="
				+ getPriceValue() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Price) && getId() != null) {
			final Price that = (Price) obj;
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
