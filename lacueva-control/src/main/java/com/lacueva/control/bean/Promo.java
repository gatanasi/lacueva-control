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
 * Entity implementation class for Entity: Promo
 *
 */
@Entity
@Table(name = "CUEVA_PROMOS")
public class Promo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8277072360031739945L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_PROMOS_ID")
	@SequenceGenerator(name = "CUEVA_SEQ_PROMOS_ID", sequenceName = "CUEVA_SEQ_PROMOS_ID", initialValue = 1, allocationSize = 1)
	@Column(name = "PROMO_ID")
	private Long id;

	@OneToOne
	@JoinColumn(name = "PROMO_ITEM_ID")
	private Item promoItem;

	@Column(name = "PROMO_QUANTITY", nullable = false)
	private Integer promoQuantity;

	@Column(name = "PROMO_VALUE", nullable = false)
	private Float promoValue;

	public Promo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getPromoItem() {
		return promoItem;
	}

	public void setPromoItem(Item promoItem) {
		this.promoItem = promoItem;
	}

	public Integer getPromoQuantity() {
		return promoQuantity;
	}

	public void setPromoQuantity(Integer promoQuantity) {
		this.promoQuantity = promoQuantity;
	}

	public Float getPromoValue() {
		return promoValue;
	}

	public void setPromoValue(Float promoValue) {
		this.promoValue = promoValue;
	}

	@Override
	public String toString()

	{
		return "Promo [promoId=" + getId() + ", promoItemType="
				+ getPromoItem().getItemType().getText() + ", promoQuantity="
				+ getPromoQuantity() + ", promoValue=" + getPromoValue() + "]";
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
