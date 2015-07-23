package org.gtri.helper;

import java.util.ArrayList;

public class ExpandListGroup {

	private String name;
	private ArrayList<ExpandListChild> items;

	public ExpandListGroup(String name) {
		this.name = name;
		this.items = new ArrayList<ExpandListChild>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ExpandListChild> getItems() {
		return items;
	}

	public void setItems(ArrayList<ExpandListChild> Items) {
		this.items = Items;
	}

}
