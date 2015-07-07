package com.lacueva.control.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Sale
 *
 */
@Entity
@Table(name = "CUEVA_SALES")
@NamedQuery(name = "Sales.findByShopAndBetweenDates", query = "SELECT sales FROM Sale sales WHERE sales.saleShop = :shop AND sales.saleDate BETWEEN :startDate AND :endDate ORDER BY sales.id ASC")
public class Sale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3923828485542175774L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_SALES_ID")
	@SequenceGenerator(name = "CUEVA_SEQ_SALES_ID", sequenceName = "CUEVA_SEQ_SALES_ID", initialValue = 1, allocationSize = 1)
	@Column(name = "SALE_ID")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SALE_DATE", nullable = false)
	private Date saleDate;

	@OneToOne
	@JoinColumn(name = "SALE_SHOP_ID")
	private Shop saleShop;

	@OneToOne
	@JoinColumn(name = "SALE_ITEM_ID")
	private Item saleItem;

	@Column(name = "SALE_QUANTITY")
	private Integer saleQuantity;

	@Column(name = "SALE_AMOUNT", nullable = false)
	private Float saleAmount;

	public Sale() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Shop getSaleShop() {
		return saleShop;
	}

	public void setSaleShop(Shop saleShop) {
		this.saleShop = saleShop;
	}

	public Item getSaleItem() {
		return saleItem;
	}

	public void setSaleItem(Item saleItem) {
		this.saleItem = saleItem;
	}

	public Integer getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(Integer saleQuantity) {
		this.saleQuantity = saleQuantity;
	}

	public Float getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(Float saleAmount) {
		this.saleAmount = saleAmount;
	}

	@Override
	public String toString()

	{
		StringBuilder sb = new StringBuilder("Sale [");
		sb.append("saleId=").append(getId()).append(", saleDate=").append(getSaleDate()).append(", saleShopName=");
		if (getSaleShop() != null) {
			sb.append(getSaleShop().getShopName());
		}
		sb.append(", saleItemType=");
		if (getSaleItem() != null) {
			sb.append(getSaleItem().getItemType());
		}
		sb.append(", saleQuantity=").append(getSaleQuantity()).append(", saleAmount=").append(getSaleAmount())
				.append("]");

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Sale) && getId() != null) {
			final Sale that = (Sale) obj;
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
