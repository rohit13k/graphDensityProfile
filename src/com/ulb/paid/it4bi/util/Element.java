package com.ulb.paid.it4bi.util;

public class Element {
	int value;
	long timestamp;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Element))
			return false;
		if (obj == this)
			return true;
		else {
			Element ne = (Element) obj;
			if (ne.value == this.value) {
				return true;
			} else {
				return false;
			}
		}

	}
	public boolean isBigger(Element ne){
		if(this.value>ne.value){
			return true;
		}
		return false;
	}
	public boolean isLater(Element ne){
		if(this.timestamp>ne.timestamp){
			return true;
		}
		return false;
	}
}
