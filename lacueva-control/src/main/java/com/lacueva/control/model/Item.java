package com.lacueva.control.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity
@Table(name = "CUEVA_ITEMS")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_ITEMS_ID")
	@SequenceGenerator(name = "CUEVA_SEQ_ITEMS_ID", sequenceName = "CUEVA_SEQ_ITEMS_ID", initialValue = 1, allocationSize = 1)
	@Column(name = "ITEM_ID")
	private Long id;

	@Column(name = "ITEM_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private ItemType itemType;

	@Column(name = "ITEM_WEIGHT")
	private Float itemWeight;

	@Column(name = "ITEM_BURNABLE", nullable = false)
	private Boolean itemBurnable;

	public Item() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
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

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Item)) {
			final Item thatTask = (Item) obj;
			return getId().equals(thatTask.getId());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getId().intValue();
	}
}
