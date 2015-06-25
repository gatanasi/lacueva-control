package com.lacueva.control.model;

import java.util.Date;

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
 * Entity implementation class for Entity: Stock
 *
 */
@Entity
@Table(name = "CUEVA_STOCK")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_STOCK_ID")
	@SequenceGenerator(name = "CUEVA_SEQ_STOCK_ID", sequenceName = "CUEVA_SEQ_STOCK_ID", initialValue = 1, allocationSize = 1)
	@Column(name = "STOCK_ID")
	private Long id;

	@Column(name = "STOCK_DATE", nullable = false)
	private Date stockDate;

	@OneToOne
	@JoinColumn(name = "STOCK_SHOP_ID")
	private Shop stockShop;

	@OneToOne
	@JoinColumn(name = "STOCK_ITEM_ID")
	private Item stockItem;

	@Column(name = "STOCK_QUANTITY", nullable = false)
	private Integer stockQuantity;

	public Stock() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public Shop getStockShop() {
		return stockShop;
	}

	public void setStockShop(Shop stockShop) {
		this.stockShop = stockShop;
	}

	public Item getStockItem() {
		return stockItem;
	}

	public void setStockItem(Item stockItem) {
		this.stockItem = stockItem;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
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
