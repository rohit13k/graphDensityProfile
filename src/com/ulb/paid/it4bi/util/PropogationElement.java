package com.ulb.paid.it4bi.util;

import com.ulb.padi.it4bi.main.Node;
import com.ulb.padi.it4bi.main.SlidingHLL;

public class PropogationElement {

	private Node targetNode;

	private SlidingHLL sourceElement;
	private String sourceNode;
	private long timestamp;
	private int distance;

	public PropogationElement(Node targetNode, String sourceNode,SlidingHLL sourceElement,
			long timestamp, int distance) {
		this.targetNode = targetNode;
		this.sourceNode=sourceNode;
		this.sourceElement = sourceElement;
		this.timestamp = timestamp;
		this.distance = distance;

	}

	public Node getTargetNode() {
		return targetNode;
	}

	public void setTargetNode(Node targetNode) {
		this.targetNode = targetNode;
	}

	public SlidingHLL getSourceElement() {
		return sourceElement;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public int getDistance() {
		return distance;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof PropogationElement))
			return false;
		if (obj == this)
			return true;
		else {
			PropogationElement pe=(PropogationElement) obj;
			if(pe.targetNode.equals(targetNode)&&pe.distance==this.distance&&pe.sourceNode.equals(this.sourceNode)&&pe.timestamp==this.timestamp){
				return true;
			}else{
				return false;
			}
			
		}
	}

}
