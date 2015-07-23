package com.lacueva.control.commons;

import java.util.Comparator;

import com.lacueva.control.bean.Item;

public class ItemPriorityComparator implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
	if (item1.getItemPriority() != null) {
	    if (item2.getItemPriority() != null) {
		return item1.getItemPriority().compareTo(item2.getItemPriority());
	    } else {
		return 1;
	    }
	} else {
	    return 0;
	}
    }
}
