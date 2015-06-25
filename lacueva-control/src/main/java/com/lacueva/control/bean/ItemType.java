package com.lacueva.control.bean;

public enum ItemType {
	DVD("DVD"), BD("BD"), CONSUMABLE("CONSUMABLE"), ENVELOPE("ENVELOPE"), ENVELOPE_WITH_FLAP(
			"ENVELOPE_WITH_FLAP"), BOX_STD("BOX_STD"), BOX_BIG("BOX_BIG"), BOX_PAPERBOARD(
			"BOX_PAPERBOARD"), BULK10("BULK10"), BULK25("BULK25"), BULK50(
			"BULK50"), BULK100("BULK100"), ;

	private String text;

	ItemType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
}
