package com.lacueva.control.model.item;

import com.lacueva.control.commons.Constants;

public class DVD extends ItemSellable {

	private int burnt;

	public DVD() {
		super("DVD", Constants.DVD_WEIGHT, Constants.DVD_PRICE);
		setBurnt(1);
	}

	public int getBurnt() {
		return burnt;
	}

	/**
	 * @param burnt
	 *            0 for virgin, 1 for burnt
	 */
	public void setBurnt(int burnt) {
		this.burnt = burnt;
	}
}
