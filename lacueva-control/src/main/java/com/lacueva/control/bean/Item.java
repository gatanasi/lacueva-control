package com.lacueva.control.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity
@Table(name = "CUEVA_ITEMS")
public class Item implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 9155589603647542985L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_ITEMS_ID")
    @SequenceGenerator(name = "CUEVA_SEQ_ITEMS_ID", sequenceName = "CUEVA_SEQ_ITEMS_ID", initialValue = 1, allocationSize = 1)
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(name = "ITEM_TYPE", nullable = false)
    @NotNull
    @Size(min = 1)
    private String itemType;

    @Column(name = "ITEM_WEIGHT")
    @NumberFormat
    private Float itemWeight;

    @Column(name = "ITEM_BURNABLE")
    private Boolean itemBurnable;

    @Column(name = "ITEM_PRIORITY")
    private Integer itemPriority;

    public Item() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getItemType() {
	return itemType;
    }

    public void setItemType(String itemType) {
	this.itemType = itemType;
    }

    public Float getItemWeight() {
	return itemWeight;
    }

    public void setItemWeight(Float itemWeight) {
	this.itemWeight = itemWeight;
    }

    public Boolean getItemBurnable() {
	return itemBurnable;
    }

    public void setItemBurnable(Boolean itemBurnable) {
	this.itemBurnable = itemBurnable;
    }

    public Integer getItemPriority() {
	return itemPriority;
    }

    public void setItemPriority(Integer itemPriority) {
	this.itemPriority = itemPriority;
    }

    @Override
    public String toString()

    {
	StringBuilder sb = new StringBuilder("Item [");
	sb.append("itemId=").append(getId()).append(", itemType=").append(getItemType()).append(", itemWeight=")
		.append(getItemWeight()).append(", itemBurnable=").append(getItemBurnable()).append(", itemPriority=")
		.append(getItemPriority()).append("]");

	return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if ((obj != null) && (obj instanceof Item) && getId() != null) {
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
