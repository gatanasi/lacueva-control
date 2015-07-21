package com.lacueva.control.bean.dto;

import java.io.Serializable;

import com.lacueva.control.bean.Item;

/**
 * DTO implementation class for Entity: Price
 *
 */
public class PriceDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2366829847148875705L;

    private Long id;

    private Item priceItem;

    private Float priceValue;

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

}
