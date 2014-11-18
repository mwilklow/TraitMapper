package com.mvw.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.mvw.core.Boost;
import com.mvw.core.Node;
import com.mvw.core.Trait;

public class GraphBuilder {
	public Map<Integer, Node> buildGraph() throws IOException {
		Map<Integer, Node> nodesMap = new HashMap<>();
		
		InputStream graphStream = this.getClass().getClassLoader().getResourceAsStream("graph.csv"); 
		BufferedReader br = new BufferedReader(new InputStreamReader(graphStream));
		
		String line;
		while ( (line = br.readLine()) != null) {
			int nodeIndex = Integer.parseInt(line.split(",")[0]);
			
			line = br.readLine();
			List<Integer> boosts = parseInts(line);
			Boost intelligenceBoost = new Boost(Trait.INTELLIGENCE, boosts.get(0));
			Boost maxLifeBoost = new Boost(Trait.MAX_LIFE, boosts.get(1));
			Boost strengthBoost = new Boost(Trait.STRENGTH, boosts.get(2));
			
			line = br.readLine();
			List<Integer> adjacentNodes = parseInts(line);
			
			Node node = new Node(nodeIndex, 
					Arrays.asList(intelligenceBoost, maxLifeBoost, strengthBoost),
					adjacentNodes);
			nodesMap.put(nodeIndex, node);
		}
		
		return nodesMap;
	}

	private List<Integer> parseInts(String line) {
		List<Integer> parsedInts = new ArrayList<>();
		
		for (String str: line.split(",")) {
			if (str != null && str != "") {
				parsedInts.add(Integer.parseInt(str));
			} else {
				break;
			}
		}
		
		return parsedInts;
	}
}
