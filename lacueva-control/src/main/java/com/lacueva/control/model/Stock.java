package com.lacueva.control.model;

public class Stock {

	private int dvd;

	private int bd;

	public Stock(int dvd, int bd) {
		super();
		this.dvd = dvd;
		this.bd = bd;
	}

	public int getDvd() {
		return dvd;
	}

	public void setDvd(int dvd) {
		this.dvd = dvd;
	}

	public int getBd() {
		return bd;
	}

	public void setBd(int bd) {
		this.bd = bd;
	}
}
