package com.lacueva.control.model;

public enum ItemType {
	DVD("DVD"), BD("BD"), CONSUMABLE("CONSUMABLE"), ENVELOPE("ENVELOPE"), ENVELOPE_WITH_FLAP(
			"ENVELOPE_WITH_FLAP"), BOX_STD("BOX_STD"), BOX_BIG("BOX_BIG"), BOX_PAPERBOARD(
			"BOX_PAPERBOARD");

	private String text;

	ItemType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
}
