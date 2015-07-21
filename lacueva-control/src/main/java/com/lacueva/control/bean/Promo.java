package com.lacueva.control.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Promo
 *
 */
@Entity
@Table(name = "CUEVA_PROMOS")
@NamedQueries({
	@NamedQuery(name = "Promos.findPromoByShopAndItemAndQty", query = "SELECT promos FROM Promo promos WHERE promos.promoShop = :shop AND promos.promoItem = :item AND promos.promoQuantity <= :quantity ORDER BY promos.promoQuantity DESC"),
	@NamedQuery(name = "Promos.findPromosByShop", query = "SELECT promos FROM Promo promos WHERE promos.promoShop = :shop") })
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
    @JoinColumn(name = "PROMO_SHOP_ID")
    private Shop promoShop;

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

    public Shop getPromoShop() {
	return promoShop;
    }

    public void setPromoShop(Shop promoShop) {
	this.promoShop = promoShop;
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
	StringBuilder sb = new StringBuilder("Promo [");
	sb.append("promoId=").append(getId()).append(", promoShopName=");
	if (getPromoShop() != null) {
	    sb.append(getPromoShop().getShopName());
	}
	sb.append(", promoItemType=");
	if (getPromoItem() != null) {
	    sb.append(getPromoItem().getItemType());
	}
	sb.append(", promoQuantity=").append(getPromoQuantity())
		.append(", promoValue=").append(getPromoValue()).append("]");

	return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if ((obj != null) && (obj instanceof Promo) && getId() != null) {
	    final Promo that = (Promo) obj;
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
