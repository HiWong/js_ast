package com.mindlin.jsast.tree;

import java.util.List;

import com.mindlin.jsast.impl.tree.LineMap;

public interface CompilationUnitTree extends Tree {
	LineMap getLineMap();

	List<? extends Tree> getSourceElements();

	String getSourceName();

	boolean isStrict();

	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.COMPILATION_UNIT;
	}
}