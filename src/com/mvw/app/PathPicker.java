package com.mvw.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;

import com.mvw.core.Boost;
import com.mvw.core.Edge;
import com.mvw.core.Node;
import com.mvw.core.Path;
import com.mvw.strategy.BestPathStrategy;
import com.mvw.strategy.SimpleSumStrategy;

public class PathPicker {
	private static final int PATH_LENGTH = 15;
	
	private static Set<Integer> visitedNodesHashes;
	
	static {
		visitedNodesHashes = new HashSet<>();
	}
	
	public static void main(String[] args) {
		Map<Integer, Node> nodesMap = null;
		
		try {
			nodesMap = new GraphBuilder().buildGraph();
		} catch (IOException ioe) {
			System.out.println("Error loading graph");
			System.exit(0);
		}
		
		//printNodes(nodesMap);
		
		Path startPath = new Path(null, nodesMap.get(11), null);
		BestPathStrategy strategy = new SimpleSumStrategy();
		
		System.out.println("Calculating...");
		Path bestPath = pickPath(startPath, nodesMap, strategy);
		System.out.println(bestPath);
	}

	private static Path pickPath(Path path, Map<Integer, Node> nodesMap, 
			BestPathStrategy strategy) {
		
		Path bestPath = path;
		List<Node> visitedNodes = new ArrayList<>(path.getVisitedNodes());
		int visitedNodesHash = visitedNodes.get(0).hashCode();
		for (int i = 1; i < visitedNodes.size(); i++) {
			visitedNodesHash *= visitedNodes.get(i).hashcode();
		}
		
		
		if (!visitedNodesHashes.contains(visitedNodesHash)) {
			visitedNodesHashes.add(visitedNodesHash);
			if (visitedNodes.size() <= PATH_LENGTH) {
				for (Node visitedNode: visitedNodes) {
					for (Integer adjacentNodeIndex: visitedNode.getAdjacentNodes()) {
						Node adjacentNode = nodesMap.get(adjacentNodeIndex);
						if (!visitedNodes.contains(adjacentNode)) {
							Path nextBestPath = pickPath(new Path(path, adjacentNode, 
									new Edge(visitedNode, adjacentNode)), nodesMap, strategy);
							bestPath = strategy.pickPath(bestPath, nextBestPath);
						}
					}
				}
			}
		}
		
		return strategy.pickPath(path, bestPath);
	}

	private static void printNodes(Map<Integer, Node> nodesMap) {
		for (Node node: nodesMap.values()) {
			System.out.println("Node Index: " + node.getNodeIndex());
			
			boolean first = true;
			for (Boost boost: node.getBoosts()) {
				if (first) {
					first = false;
				} else {
					System.out.print(", ");
				}
				System.out.print(boost.getTrait() + " boost: " + boost.getBump());
			}
			
			System.out.println("\nAdjacent nodes: ");
			first = true;
			for (Integer adjacentNode: node.getAdjacentNodes()) {
				if (first) {
					first = false;
				} else {
					System.out.print(", ");
				}
				System.out.print(adjacentNode);
			}
			System.out.println("\n");
		}
	}
}
