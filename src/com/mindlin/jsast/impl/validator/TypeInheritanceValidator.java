package com.mindlin.jsast.impl.validator;

import com.mindlin.jsast.tree.Tree;
import com.mindlin.jsast.tree.TypeTree;
import com.mindlin.jsast.tree.type.ArrayTypeTree;
import com.mindlin.jsast.tree.type.UnionTypeTree;

/**
 * Computes type inheritance calculations
 */
public class TypeInheritanceValidator {
	public static boolean canExtend(TypeTree base, TypeTree child) {
		switch (base.getKind()) {
			case ANY_TYPE:
				return true;
			case TYPE_UNION: {
				UnionTypeTree union = (UnionTypeTree) base;
				return canExtend(union.getLeftType(), child) || canExtend(union.getRightType(), child);
			}
			case ARRAY_TYPE: {
				if (child.getKind() != Tree.Kind.ARRAY_TYPE)
					return false;
				return canExtend(((ArrayTypeTree)base).getBaseType(), ((ArrayTypeTree)child).getBaseType());
			}
			
			default:
				throw new IllegalArgumentException("Unsupported type: " + base.getKind());
		}
	}
}
