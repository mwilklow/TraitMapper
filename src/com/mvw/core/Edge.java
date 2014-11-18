package com.mvw.core;

public class Edge {
	private Node sourceNode;
	private Node destinationNode;
	
	public Edge(Node sourceNode, Node destinationNode) {
		this.sourceNode = sourceNode;
		this.destinationNode = destinationNode;
	}

	public Node getSourceNode() {
		return sourceNode;
	}

	public Node getDestinationNode() {
		return destinationNode;
	}
	
}
