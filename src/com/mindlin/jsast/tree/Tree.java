package com.mindlin.jsast.tree;

import java.util.Iterator;
import java.util.List;

import com.mindlin.jsast.tree.UnaryTree.AwaitTree;
import com.mindlin.jsast.tree.type.AnyTypeTree;
import com.mindlin.jsast.tree.type.ArrayTypeTree;
import com.mindlin.jsast.tree.type.CompositeTypeTree;
import com.mindlin.jsast.tree.type.EnumDeclarationTree;
import com.mindlin.jsast.tree.type.FunctionTypeTree;
import com.mindlin.jsast.tree.type.GenericParameterTree;
import com.mindlin.jsast.tree.type.IdentifierTypeTree;
import com.mindlin.jsast.tree.type.IndexSignatureTree;
import com.mindlin.jsast.tree.type.KeyofTypeTree;
import com.mindlin.jsast.tree.type.LiteralTypeTree;
import com.mindlin.jsast.tree.type.MemberTypeTree;
import com.mindlin.jsast.tree.type.ObjectTypeTree;
import com.mindlin.jsast.tree.type.SpecialTypeTree;
import com.mindlin.jsast.tree.type.TupleTypeTree;
import com.mindlin.jsast.tree.type.TypeTree;

//see http://download.java.net/java/jdk9/docs/jdk/api/nashorn/jdk/nashorn/api/tree/package-summary.html
//http://docs.oracle.com/javase/8/docs/jdk/api/javac/tree/index.html
//https://github.com/javaparser/javaparser
public interface Tree {
	public static enum Kind {
		// ===== Data structures =====
		// Program
		COMPILATION_UNIT(CompilationUnitTree.class),
		
		// Comments
		COMMENT(CommentNode.class),
		
		// Function
		PARAMETER(ParameterTree.class),

		// ===== Statements =====
		EXPRESSION_STATEMENT(ExpressionStatementTree.class),
		EMPTY_STATEMENT(EmptyStatementTree.class),
		
		// Control flow structures
		BLOCK(BlockTree.class),
		DEBUGGER(DebuggerTree.class),
		
		// Control flow statements
		LABELED_STATEMENT(LabeledStatementTree.class),
		BREAK(BreakTree.class),
		CONTINUE(ContinueTree.class),
		RETURN(ReturnTree.class),
		AWAIT(AwaitTree.class),
		YIELD(UnaryTree.class),
		YIELD_GENERATOR(UnaryTree.class),
		
		// Choice
		IF(IfTree.class),
		SWITCH(SwitchTree.class),
		CASE(CaseTree.class),
		WITH(WithTree.class),
		
		// Exceptions
		THROW(ThrowTree.class),
		TRY(TryTree.class),
		CATCH(CatchTree.class),
		
		// Loops
		WHILE_LOOP(WhileLoopTree.class),
		DO_WHILE_LOOP(DoWhileLoopTree.class),
		FOR_LOOP(ForLoopTree.class),
		FOR_IN_LOOP(ForEachLoopTree.class),
		FOR_OF_LOOP(ForEachLoopTree.class), // Special
		
		// ===== Declarations =====
		FUNCTION_DECLARATION(FunctionDeclarationTree.class),
		VARIABLE_DECLARATION(VariableDeclarationTree.class),
		VARIABLE_DECLARATOR(VariableDeclaratorTree.class),
		

		// ===== Expressions =====
		
		// Prototype-based
		THIS_EXPRESSION(ThisExpressionTree.class),
		SUPER_EXPRESSION(SuperExpressionTree.class),
		
		//TODO: categorize
		FUNCTION_EXPRESSION(FunctionExpressionTree.class),

		// Literals
		BOOLEAN_LITERAL(BooleanLiteralTree.class),
		NULL_LITERAL(NullLiteralTree.class),
		NUMERIC_LITERAL(NumericLiteralTree.class),
		REGEXP_LITERAL(RegExpLiteralTree.class),
		STRING_LITERAL(StringLiteralTree.class),
		
		// Literal-like (but more expression-y)
		ARRAY_LITERAL(ArrayLiteralTree.class),
		OBJECT_LITERAL(ObjectLiteralTree.class),
		OBJECT_LITERAL_PROPERTY(ObjectLiteralPropertyTree.class),
		
		// Templates
		TAGGED_TEMPLATE,//TODO: fix
		TEMPLATE_LITERAL(TemplateLiteralTree.class),
		TEMPLATE_ELEMENT(TemplateElementTree.class),
		
		// Unary operators
		UNARY_PLUS(UnaryTree.class),
		UNARY_MINUS(UnaryTree.class),
		TYPEOF(UnaryTree.class),
		VOID(UnaryTree.class),
		DELETE(UnaryTree.class),
		
		// Update expressions
		POSTFIX_DECREMENT(UnaryTree.class),
		POSTFIX_INCREMENT(UnaryTree.class),
		PREFIX_DECREMENT(UnaryTree.class),
		PREFIX_INCREMENT(UnaryTree.class),

		// Binary operators
		IN(BinaryTree.class),
		INSTANCEOF(BinaryTree.class),
		
		// Binary math
		ADDITION(BinaryTree.class),
		SUBTRACTION(BinaryTree.class),
		MULTIPLICATION(BinaryTree.class),
		DIVISION(BinaryTree.class),
		REMAINDER(BinaryTree.class),
		EXPONENTIATION(BinaryTree.class),

		// Bitwise operators
		BITWISE_AND(BinaryTree.class),
		BITWISE_OR(BinaryTree.class),
		BITWISE_XOR(BinaryTree.class),
		BITWISE_NOT(UnaryTree.class),
		LEFT_SHIFT(BinaryTree.class),
		RIGHT_SHIFT(BinaryTree.class),
		UNSIGNED_RIGHT_SHIFT(BinaryTree.class),

		// Logical operators
		LOGICAL_AND(BinaryTree.class),
		LOGICAL_OR(BinaryTree.class),
		LOGICAL_NOT(UnaryTree.class),

		// Comparison operators
		EQUAL(BinaryTree.class),
		NOT_EQUAL(BinaryTree.class),
		STRICT_EQUAL(BinaryTree.class),
		STRICT_NOT_EQUAL(BinaryTree.class),
		GREATER_THAN(BinaryTree.class),
		LESS_THAN(BinaryTree.class),
		GREATER_THAN_EQUAL(BinaryTree.class),
		LESS_THAN_EQUAL(BinaryTree.class),

		// Assignment operators
		ASSIGNMENT(AssignmentTree.class),
		ADDITION_ASSIGNMENT(AssignmentTree.class),
		SUBTRACTION_ASSIGNMENT(AssignmentTree.class),
		MULTIPLICATION_ASSIGNMENT(AssignmentTree.class),
		DIVISION_ASSIGNMENT(AssignmentTree.class),
		REMAINDER_ASSIGNMENT(AssignmentTree.class),

		// Assignment bitwise operators
		BITWISE_AND_ASSIGNMENT(AssignmentTree.class),
		BITWISE_OR_ASSIGNMENT(AssignmentTree.class),
		BITWISE_XOR_ASSIGNMENT(AssignmentTree.class),
		EXPONENTIATION_ASSIGNMENT(AssignmentTree.class),
		LEFT_SHIFT_ASSIGNMENT(AssignmentTree.class),
		RIGHT_SHIFT_ASSIGNMENT(AssignmentTree.class),
		UNSIGNED_RIGHT_SHIFT_ASSIGNMENT(AssignmentTree.class),

		// Misc. operators
		SEQUENCE(SequenceTree.class),
		PARENTHESIZED(ParenthesizedTree.class),

		// Member access
		ARRAY_ACCESS(BinaryTree.class),
		MEMBER_SELECT(MemberExpressionTree.class),

		// Control flow modifiers
		CONDITIONAL(ConditionalExpressionTree.class),

		// Module stuff
		IMPORT(ImportTree.class),
		IMPORT_SPECIFIER(ImportSpecifierTree.class),
		EXPORT(ExportTree.class),

		// Array stuff
		SPREAD(UnaryTree.class),//TODO: make custom inheritance

		// Method invocation
		NEW(NewTree.class),
		FUNCTION_INVOCATION(FunctionCallTree.class),

		// Variable stuff
		IDENTIFIER(IdentifierTree.class),

		ERROR(ErroneousTree.class),
		OTHER,
		
		// ===== Properties =====
		// These are used for object literals, classes, interfaces, etc.
		PROPERTY(PropertyTree.class),//TODO: finish
		ASSIGNMENT_PROPERTY,
		METHOD_DEFINITION(MethodDefinitionTree.class),
		INDEX_TYPE(IndexSignatureTree.class),
		CALL_SIGNATURE,
		
		// ===== Patterns =====
		OBJECT_PATTERN(ObjectPatternTree.class),
		ARRAY_PATTERN(ArrayPatternTree.class),
		ASSIGNMENT_PATTERN(AssignmentPatternTree.class),
		
		// ===== Classes =====
		CLASS_DECLARATION(ClassDeclarationTree.class),
		CLASS_EXPRESSION(ClassDeclarationTree.class),//TODO: custom type
		CLASS_PROPERTY(ClassPropertyTree.class),
		
		// ===== Typing ======
		
		// Type-modified expressions
		CAST(CastTree.class),
		
		// Type declarations
		TYPE_ALIAS(TypeAliasTree.class),
		INTERFACE_DECLARATION(InterfaceDeclarationTree.class),
		ENUM_DECLARATION(EnumDeclarationTree.class),
		
		// Built-in types
		ANY_TYPE(AnyTypeTree.class),
		SPECIAL_TYPE(SpecialTypeTree.class),
		NEVER_TYPE,
		LITERAL_TYPE(LiteralTypeTree.class),
		
		// Generics
		GENERIC_PARAM(GenericParameterTree.class),
		IDENTIFIER_TYPE(IdentifierTypeTree.class),
		
		// Type literals
		TUPLE_TYPE(TupleTypeTree.class),
		FUNCTION_TYPE(FunctionTypeTree.class),
		OBJECT_TYPE(ObjectTypeTree.class),
		
		// Type expressions
		MEMBER_TYPE(MemberTypeTree.class),
		ARRAY_TYPE(ArrayTypeTree.class),//TODO merge w/ KEYOF_TYPE to unary generic iface?
		KEYOF_TYPE(KeyofTypeTree.class),
		TYPE_UNION(CompositeTypeTree.class),
		TYPE_INTERSECTION(CompositeTypeTree.class),
		//TODO: clean up
		INTERFACE_PROPERTY(InterfacePropertyTree.class),
		;
		
		private final Class<? extends Tree> iface;
		private final boolean expr, litr, stmt, typ;

		Kind() {
			this(Tree.class);// TODO fix
		}

		Kind(Class<? extends Tree> clazz) {
			this.iface = clazz;
			this.litr = LiteralTree.class.isAssignableFrom(clazz);
			this.expr = litr || ExpressionTree.class.isAssignableFrom(clazz);//literals are expressions
			this.stmt = StatementTree.class.isAssignableFrom(clazz);
			this.typ = TypeTree.class.isAssignableFrom(clazz);
		}
		
		Kind(Class<? extends Tree> clazz, boolean litr, boolean expr, boolean stmt, boolean typ) {
			this.iface = clazz;
			this.litr = litr;
			this.expr = expr;
			this.stmt = stmt;
			this.typ = typ;
		}

		public Class<? extends Tree> asInterface() {
			return iface;
		}

		public boolean isExpression() {
			return this.expr;
		}

		public boolean isLiteral() {
			return this.litr;
		}

		public boolean isStatement() {
			return this.stmt;
		}
		
		public boolean isType() {
			return this.typ;
		}
	}
	
	public static boolean equivalentTo(Tree a, Tree b) {
		if (a == b)
			return true;
		
		if (a == null || b == null)
			return false;
		
		return a.equivalentTo(b);
	}
	
	public static <T extends Tree> boolean equivalentTo(List<? extends T> a, List<? extends T> b) {
		if (a == b)
			return true;
		if (a == null || b == null || a.size() != b.size())
			return false;
		
		for (Iterator<? extends T> i = a.iterator(), j = b.iterator(); i.hasNext();)
			if (!Tree.equivalentTo(i.next(), j.next()))
				return false;
		return true;
	}
	
	/**
	 * Get the kind of this tree. Useful for switch statements.
	 * @return the kind of this tree.
	 */
	Kind getKind();

	/**
	 * Get the start position of this tree.
	 * @return the start position of this element, else -1 if not available
	 * @see #getEnd()
	 */
	long getStart();

	/**
	 * Get the end position of this tree.
	 * 
	 * @return the end position of this element, else -1 if not available
	 * @see #getStart()
	 */
	long getEnd();
	
	boolean isMutable();

	<R, D> R accept(TreeVisitor<R, D> visitor, D data);
	
	/**
	 * Indicates whether another tree is 'equivalent to' this one.
	 * <p>
	 * This method is less strict than {@link #equals(Object)}, as it does not require all
	 * fields to be the same. Rather, it is to test a looser 'is pretty much the same' equivalence that ignores
	 * certain attributes.
	 * For example, the values of {@link #getStart()}, {@link #getEnd()}, and {@link #isMutable()} should
	 * be ignored.
	 * </p>
	 * <p>
	 * Implementations of this method should satisfy the general contract provided by {@link #equals(Object)}. Specifically,
	 * <ul>
	 * <li>For any {@code x} and {@code y}, {@code x.equals(y)} implies {@code x.equivalentTo(y)}.</li>
	 * <li>It is <i>reflexive</i>: For any non-null {@code x}, {@code x.equivalentTo(x) == true}.</li>
	 * <li>It is <i>symmetric</i>: For any non-null {@code x} and {@code b}, {@code a.equivalentTo(b) == b.equivalentTo(a)}</li>
	 * <li>It is <i>transitive</i>: For any non-null  {@code x}, {@code y}, and {@code z},
	 * 		if {@code x.equivalentTo(y)} returns {@code true} and {@code y.equivalentTo(z)} returns {@code true},
	 * 		then {@code x.equivalentTo(z)} should return {@code true}.</li>
	 * <li>It is <i>consistent</i>: For any non-null {@code x} and {@code y}, multiple calls of </li>
	 * <li>Like {@link #equals(Object)}, {@code a.equivalentTo(null)} should return {@code false}.</li>
	 * </p>
	 * @param other
	 * @return equivalency
	 */
	default boolean equivalentTo(Tree other) {
		return this == other;
	}
}