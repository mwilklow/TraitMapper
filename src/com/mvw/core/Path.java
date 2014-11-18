package com.mvw.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Path {
	private List<Node> visitedNodes;
	private Map<Trait, Integer> totalBoosts;
	private List<Edge> edges;
	
	public Path(Path previousPath, Node addedNode, Edge addedEdge) {
		if (previousPath != null) {
			this.visitedNodes = new ArrayList<>(previousPath.getVisitedNodes());
			this.totalBoosts = new HashMap<>(previousPath.getTotalBoosts());
			this.edges = new ArrayList<>(previousPath.getEdges());
		} else {
			this.edges = new ArrayList<>();
			this.visitedNodes = new ArrayList<>();
			this.totalBoosts = new HashMap<>();
			this.totalBoosts.put(Trait.STRENGTH, 0);
			this.totalBoosts.put(Trait.INTELLIGENCE, 0);
			this.totalBoosts.put(Trait.MAX_LIFE, 0);
		}
		
		for (Boost boost: addedNode.getBoosts()) {
			Trait trait = boost.getTrait();
			int value = totalBoosts.get(trait);
			value += boost.getBump();
			this.totalBoosts.put(trait, value);
		}
		
		if (addedEdge != null) {
			edges.add(addedEdge);
		}
		visitedNodes.add(addedNode);
	}
	
	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public List<Node> getVisitedNodes() {
		return visitedNodes;
	}

	public Map<Trait, Integer> getTotalBoosts() {
		return totalBoosts;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Best Path Nodes: ");
		
		boolean first = true;
		for (Node visitedNode: visitedNodes) {
			if (first) {
				first = false;
			} else {
				builder.append(", ");
			}
			builder.append(visitedNode.getNodeIndex());
		}
		builder.append("\nEdges: ");
		
		first = true;
		for (Edge edge: edges) {
			if (first) {
				first = false;
			} else {
				builder.append(", ");
			}
			builder.append("(").append(edge.getSourceNode().getNodeIndex())
				.append(", ").append(edge.getDestinationNode().getNodeIndex()).append(")");
		}
		
		int intelligence = totalBoosts.get(Trait.INTELLIGENCE);
		int maxHealth = totalBoosts.get(Trait.MAX_LIFE);
		int strength = totalBoosts.get(Trait.STRENGTH);
		int total = intelligence + maxHealth + strength;
		
		builder.append("\nIntelligence: ").append(intelligence);
		builder.append("\nMax Health: ").append(maxHealth);
		builder.append("\nStrength: ").append(strength);
		builder.append("\nTotal: ").append(total);
		
		return builder.toString();
	}
}
