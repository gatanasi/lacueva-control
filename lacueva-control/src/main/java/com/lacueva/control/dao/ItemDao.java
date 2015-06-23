package com.lacueva.control.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: ItemDao
 *
 */
@Entity
@Table(name = "ITEMS")
public class ItemDao implements Serializable {

	@Id
	private String Name;
	private static final long serialVersionUID = 1L;

	public ItemDao() {
		super();
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

}
