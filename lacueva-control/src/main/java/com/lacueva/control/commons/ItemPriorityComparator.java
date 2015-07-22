package com.lacueva.control.commons;

import java.util.Comparator;

import com.lacueva.control.bean.Item;

public class ItemPriorityComparator implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
	return item1.getItemPriority().compareTo(item2.getItemPriority());
    }
}
