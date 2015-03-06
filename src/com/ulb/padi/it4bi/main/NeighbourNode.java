package com.ulb.padi.it4bi.main;



public class NeighbourNode {

	private Node node;
	private long timestamp;
	
	public NeighbourNode(Node node,long timestamp){
		this.node=node;
		this.timestamp=timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Node getNode() {
		return node;
	}
	@Override
	public boolean equals(Object obj) {
			if (!(obj instanceof NeighbourNode))
			return false;
		if (obj == this)
			return true;
		else {
			NeighbourNode newnn=(NeighbourNode)obj;
			if(newnn.getNode().equals(this.node)){
				return true;
			}else{
				return false;
			}
		}
	}
	@Override
	public String toString(){
		return this.node.toString()+" @ "+this.timestamp;
	}
	 @Override
	    public int hashCode() {
		 return this.node.getNodeName().hashCode();
	 }
}
