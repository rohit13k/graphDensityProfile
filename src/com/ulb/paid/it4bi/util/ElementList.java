package com.ulb.paid.it4bi.util;

import java.util.ArrayList;

public class ElementList {
	private ArrayList<Element> elements;

	public ElementList() {
		this.elements = new ArrayList<Element>();
	}

	public Element getTopElement() {
		return elements.get(0);
	}

	public int getTopElementValue() {
		return elements.get(0).value;
	}

	public int getElementValue(long window) {
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).timestamp >= window) {
				return elements.get(i).value;
			}
		}
		return getTopElementValue();
	}

	public void addNewElement(int value, long timestamp) {
		Element newElement = new Element();
		newElement.value = value;
		newElement.timestamp = timestamp;
		if (elements.size() == 0) {
			elements.add(newElement);
		} else {
			// need to complete
			ArrayList<Element> newList = new ArrayList<Element>();
			for (int i = 0; i < elements.size(); i++) {
				Element oldElement = elements.get(i);
				if (oldElement.isBigger(newElement)) {
					newList.add(oldElement);
				} else {
					newList.add(newElement);
					break;
				}
			}
			this.elements = newList;
		}
	}
}
