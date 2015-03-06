package com.ulb.paid.it4bi.util;

import java.util.ArrayList;
import java.util.HashMap;

public class PropogationList {

	private HashMap<String, ArrayList<PropogationElement>> liHashMap;

	public PropogationList() {
		this.liHashMap = new HashMap<String, ArrayList<PropogationElement>>();
	}

	public boolean isEmpty() {
		if (liHashMap.size() == 0)
			return true;
		else
			return false;
	}

	public void add(PropogationElement pe) {
		if (liHashMap.containsKey(pe.getTargetNode().getNodeName()))
			liHashMap.get(pe.getTargetNode().getNodeName()).add(pe);
		else {
			ArrayList<PropogationElement> newPelist = new ArrayList<PropogationElement>();
			newPelist.add(pe);
			liHashMap.put(pe.getTargetNode().getNodeName(), newPelist);
		}
	}

	public void remove(PropogationElement pe) {
		this.liHashMap.get(pe.getTargetNode().getNodeName()).remove(pe);
	}

	public void remove(String targetNode) {
		this.liHashMap.remove(targetNode);
	}

	public ArrayList<PropogationElement> getNext(){
		for(String node:liHashMap.keySet()){
			return liHashMap.get(node);
		}
		return null;
	}
}
