package com.lacueva.control.bean.dto;

import java.io.Serializable;

import com.lacueva.control.bean.Item;

/**
 * DTO implementation class for Entity: Promo
 *
 */
public class PromoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2164985230994105139L;

    private Long id;

    private Item promoItem;

    private Integer promoQuantity;

    private Float promoValue;

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

}
