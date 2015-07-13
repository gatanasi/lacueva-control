package com.lacueva.control.bean;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Stock
 *
 */
@Entity
@Table(name = "CUEVA_STOCK")
public class Stock implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3995750419283746001L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_STOCK_ID")
    @SequenceGenerator(name = "CUEVA_SEQ_STOCK_ID", sequenceName = "CUEVA_SEQ_STOCK_ID", initialValue = 1, allocationSize = 1)
    @Column(name = "STOCK_ID")
    private Long id;

    @Temporal(TemporalType.DATE)
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
    public String toString()

    {
	StringBuilder sb = new StringBuilder("Stock [");
	sb.append("stockId=").append(getId()).append(", stockDate=")
		.append(getStockDate()).append(", stockShopName=");
	if (getStockShop() != null) {
	    sb.append(getStockShop().getShopName());
	}
	sb.append(", stockItemType=");
	if (getStockItem() != null) {
	    sb.append(getStockItem().getItemType());
	}
	sb.append(", stockQuantity=").append(getStockQuantity()).append("]");

	return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if ((obj != null) && (obj instanceof Stock) && getId() != null) {
	    final Stock that = (Stock) obj;
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
