package com.ulb.padi.it4bi.main;

import java.util.ArrayList;
import java.util.HashMap;

import com.ulb.paid.it4bi.util.PropogationElement;
import com.ulb.paid.it4bi.util.PropogationList;

public class GraphSummary {

	public static final int numberOfBuckets = 1024;
	private int distance;
	private HashMap<String, Node> graph;
	private PropogationList propogationListCurrent, propogationListNext;

	public GraphSummary(int distance) {
		this.distance = distance;
		this.graph = new HashMap<String, Node>();

	}

	public void addEdge(String node1, String node2, long time) {
		Node firstNode, secondNode;

		if (graph.containsKey(node1)) {
			firstNode = graph.get(node1);
		} else {
			firstNode = new Node(node1);
			graph.put(node1, firstNode);
		}

		if (graph.containsKey(node2)) {
			secondNode = graph.get(node2);
		} else {
			secondNode = new Node(node2);
			graph.put(node2, secondNode);
		}

		// updating the nigbourhood
		firstNode.addNeighbour(secondNode, time);
		secondNode.addNeighbour(firstNode, time);
		this.propogationListNext = new PropogationList();

		// updating distance 1 for both the nodes
		SlidingHLL d_1_a = firstNode.getDistanceWiseSummaries().get(0);
		if (d_1_a == null) {
			d_1_a = new SlidingHLL(GraphSummary.numberOfBuckets);
		}
		d_1_a.add(secondNode.getNodeName(), time);

		SlidingHLL d_1_b = secondNode.getDistanceWiseSummaries().get(0);
		if (d_1_b == null) {
			d_1_b = new SlidingHLL(GraphSummary.numberOfBuckets);
		}
		d_1_b.add(firstNode.getNodeName(), time);

		for (NeighbourNode nn : firstNode.getNeighbours()) {
			propogationListNext.add(new PropogationElement(nn.getNode(), node1,
					d_1_a, nn.getTimestamp(), 2));
		}

		for (NeighbourNode nn : secondNode.getNeighbours()) {
			propogationListNext.add(new PropogationElement(nn.getNode(), node2,
					d_1_b, nn.getTimestamp(), 2));
		}

		for (int r = 2; r <= distance; r++) {
			propogationListCurrent = propogationListNext;
			propogationListNext = new PropogationList();
			while (!propogationListCurrent.isEmpty()) {
				ArrayList<PropogationElement> pe = propogationListCurrent
						.getNext();
				String targetNode = pe.get(0).getTargetNode().getNodeName();
				boolean hasChanged = false;
				for (int i = 0; i < pe.size(); i++) {
					hasChanged = merge(pe.get(i));
				}
				propogationListCurrent.remove(targetNode);
				if (hasChanged) {
					for (NeighbourNode nn : graph.get(targetNode)
							.getNeighbours()) {
						propogationListNext.add(new PropogationElement(nn
								.getNode(), targetNode, nn.getNode()
								.getDistanceWiseSummaries().get(r - 1), nn
								.getTimestamp(), r + 1));
					}
				}
			}

		}

	}

	public boolean merge(PropogationElement pe) {
		
		
		
		return false;
	}
}
