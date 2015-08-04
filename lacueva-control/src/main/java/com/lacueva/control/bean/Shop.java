package com.lacueva.control.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lacueva.control.commons.ItemPriorityComparator;

/**
 * Entity implementation class for Entity: Shop
 *
 */
@Entity
@Table(name = "CUEVA_SHOPS")
public class Shop implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6244165518004783894L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_SHOPS_ID")
    @SequenceGenerator(name = "CUEVA_SEQ_SHOPS_ID", sequenceName = "CUEVA_SEQ_SHOPS_ID", initialValue = 1, allocationSize = 1)
    @Column(name = "SHOP_ID")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "SHOP_DATE", nullable = false)
    private Date shopDate;

    @Column(name = "SHOP_NAME", length = 20, nullable = false, unique = true)
    private String shopName;

    @Column(name = "SHOP_CASH")
    private int shopCash;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "SHOP_ITEMS")
    private List<Item> shopItems;

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

    public List<Item> getShopItems() {
	return shopItems;
    }

    public void setShopItems(List<Item> shopItems) {
	if (shopItems.size() > 1) {
	    Collections.sort(shopItems, new ItemPriorityComparator());
	}
	this.shopItems = shopItems;
    }

    @Override
    public String toString()

    {
	StringBuilder sb = new StringBuilder("Shop [");
	sb.append("shopId=").append(getId()).append(", shopDate=").append(getShopDate()).append(", shopName=")
		.append(getShopName()).append(", shopCash=").append(getShopCash()).append(", shopItems=")
		.append(getShopItems()).append("]");

	return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if ((obj != null) && (obj instanceof Shop) && getId() != null) {
	    final Shop that = (Shop) obj;
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
