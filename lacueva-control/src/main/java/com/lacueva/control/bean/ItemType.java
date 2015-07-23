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

/**
 * Entity implementation class for Entity: ItemType
 *
 */
@Entity
@Table(name = "CUEVA_ITEM_TYPES")
public class ItemType implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2804710693062186840L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_ITEM_TYPES_ID")
    @SequenceGenerator(name = "CUEVA_SEQ_ITEM_TYPES_ID", sequenceName = "CUEVA_SEQ_ITEM_TYPES_ID", initialValue = 1, allocationSize = 1)
    @Column(name = "ITEM_TYPE_ID")
    private Long id;

    @Column(name = "ITEM_TYPE_NAME", nullable = false)
    @NotNull
    @Size(min = 1)
    private String itemTypeName;

    public ItemType() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getItemTypeName() {
	return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
	this.itemTypeName = itemTypeName;
    }

    @Override
    public String toString()

    {
	StringBuilder sb = new StringBuilder("ItemType [");
	sb.append("itemTypeId=").append(getId()).append(", itemTypeName=").append(getItemTypeName()).append("]");

	return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if ((obj != null) && (obj instanceof Item) && getId() != null) {
	    final ItemType that = (ItemType) obj;
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
