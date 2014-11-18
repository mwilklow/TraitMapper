package com.mvw.strategy;

import com.mvw.core.Path;

public class SimpleSumStrategy implements BestPathStrategy {

	@Override
	public Path pickPath(Path firstPath, Path secondPath) {
		int firstSum=0, secondSum=0;
		
		for (int value: firstPath.getTotalBoosts().values()) {
			firstSum += value;
		}
		
		for (int value: secondPath.getTotalBoosts().values()) {
			secondSum += value;
		}
		
		return firstSum >= secondSum ? firstPath : secondPath;
	}
	
}
