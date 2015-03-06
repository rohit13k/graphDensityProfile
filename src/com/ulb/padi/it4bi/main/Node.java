package com.ulb.padi.it4bi.main;

import java.util.ArrayList;
import java.util.HashSet;

public class Node {

	private String nodeName;
	private SlidingHLL nodeSummary;
	private ArrayList<SlidingHLL> distanceWiseSummaries;
	private HashSet<NeighbourNode> neighbours;
	private boolean isChanged=false;
	public Node(String name){
		this.nodeName=name;
		this.nodeSummary=new SlidingHLL(GraphSummary.numberOfBuckets);
		this.distanceWiseSummaries=new ArrayList<SlidingHLL>();
		this.neighbours=new HashSet<NeighbourNode>();
		
	}
	
	public SlidingHLL getNodeSummary() {
		return nodeSummary;
	}
	public void setNodeSummary(SlidingHLL nodeSummary) {
		this.nodeSummary = nodeSummary;
	}
	public ArrayList<SlidingHLL> getDistanceWiseSummaries() {
		return distanceWiseSummaries;
	}
	public void setDistanceWiseSummaries(ArrayList<SlidingHLL> distanceWiseSummaries) {
		this.distanceWiseSummaries = distanceWiseSummaries;
	}
	public HashSet<NeighbourNode> getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(HashSet<NeighbourNode> neighbours) {
		this.neighbours = neighbours;
	}
	public void addNeighbour(Node node,long timestamp){
		NeighbourNode newNeighbour=new NeighbourNode(node, timestamp);
		//checking if the node is already present if yes removing it
		if(neighbours.contains(newNeighbour)){
			this.neighbours.remove(newNeighbour);
		}
		// adding with new timestamp
		this.neighbours.add(newNeighbour);
	}
	public String getNodeName() {
		return nodeName;
	}
	@Override
	public String toString(){
		return this.nodeName;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node))
			return false;
		if (obj == this)
			return true;
		else {
			Node newNode=(Node)obj;
			if(newNode.getNodeName().equals(this.nodeName)){
				return true;
			}else{
				return false;
			}
		}
	}
	 @Override
	    public int hashCode() {
		 return this.getNodeName().hashCode();
	 }

	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
}
