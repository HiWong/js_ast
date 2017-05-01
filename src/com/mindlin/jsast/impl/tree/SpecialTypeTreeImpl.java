package com.mindlin.jsast.impl.tree;

import com.mindlin.jsast.impl.lexer.Token;
import com.mindlin.jsast.tree.type.SpecialTypeTree;

public class SpecialTypeTreeImpl extends AbstractTypeTree implements SpecialTypeTree {
	public static SpecialType mapType(Token t) {
		String name = t.<String>getValue();
		switch (name) {
			case "null":
				return SpecialType.NULL;
			case "undefined":
				return SpecialType.UNDEFINED;
			case "number":
				return SpecialType.NUMBER;
			case "string":
				return SpecialType.STRING;
			case "boolean":
				return SpecialType.BOOLEAN;
			case "never":
				return SpecialType.NEVER;
			case "any":
				return SpecialType.ANY;
		}
		return null;
	}
	protected final SpecialType type;
	public SpecialTypeTreeImpl(Token t) {
		this(t.getStart(), t.getEnd(), mapType(t), false);
	}

	public SpecialTypeTreeImpl(long start, long end, SpecialType type, boolean implicit) {
		super(start, end, implicit);
		this.type = type;
	}

	@Override
	public SpecialType getType() {
		return this.type;
	}
	
}
