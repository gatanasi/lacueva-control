package com.lacueva.control.model;

public class Shop {

	private String name;

	private int cash;

	private Stock stock;

	public Shop(String name, int cash, Stock stock) {
		super();
		this.name = name;
		this.cash = cash;
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
