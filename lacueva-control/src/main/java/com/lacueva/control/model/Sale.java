package com.lacueva.control.model;

import java.util.Date;

public class Sale {

	private Date date;

	private Item article;

	private int amount;

	public Sale(Date date, Item article, int amount) {
		super();
		this.date = date;
		this.article = article;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Item getArticle() {
		return article;
	}

	public void setArticle(Item article) {
		this.article = article;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
