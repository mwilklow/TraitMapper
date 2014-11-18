package com.mvw.strategy;

import com.mvw.core.Path;

public interface BestPathStrategy {
	public Path pickPath(Path firstPath, Path secondPath);
}
