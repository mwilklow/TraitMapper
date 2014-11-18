package com.mvw.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node implements Comparable<Node>{
	private List<Boost> boosts;
	private int nodeIndex;
	private List<Integer> adjacentNodes;
	
	public Node(int nodeIndex, List<Boost> boosts, List<Integer> adjacentNodes) {
		this.nodeIndex = nodeIndex;
		this.boosts = boosts;
		this.adjacentNodes = adjacentNodes;
	}

	public List<Boost> getBoosts() {
		return boosts;
	}

	public int getNodeIndex() {
		return nodeIndex;
	}

	public List<Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Node o) {
		if (this.nodeIndex > o.getNodeIndex()) {
			return 1;
		} else if (this.nodeIndex < o.getNodeIndex()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public int hashcode() {
		return this.getClass().hashCode() * (nodeIndex * 31) * 19;
	}
	
}
