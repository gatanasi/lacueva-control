package com.lacueva.control.model.item;

public abstract class ItemSellable extends Item {

	private double price;

	protected ItemSellable(String name, double weight, double price) {
		super(name, weight);
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
