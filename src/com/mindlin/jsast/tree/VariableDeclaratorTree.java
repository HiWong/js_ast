package com.mindlin.jsast.tree;

public interface VariableDeclaratorTree extends Tree {

	/**
	 * Get any initializer for this variable. For parameters, this is the
	 * default value. An empty Optional means that there was no
	 * initializer/default value.
	 * 
	 * @return initializer
	 */
	ExpressionTree getIntitializer();

	/**
	 * Get the type of this variable. Return null for any.
	 * 
	 * @return type (or null)
	 */
	TypeTree getType();
	
	PatternTree getIdentifier();

	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.VARIABLE_DECLARATOR;
	}

	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		throw new UnsupportedOperationException();
	}
}
