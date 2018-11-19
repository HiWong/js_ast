package com.mindlin.jsast.impl.parser;

import static com.mindlin.jsast.impl.parser.JSParserTest.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.mindlin.jsast.tree.Tree.Kind;
import com.mindlin.jsast.tree.type.InterfaceDeclarationTree;

public class InterfaceDeclarationTest {
	
	@Test
	public void testEmptyInterfaceDeclaration() {
		InterfaceDeclarationTree iface = parseStatement("interface Foo{}", Kind.INTERFACE_DECLARATION);
		assertIdentifier("Foo", iface.getName());
	}
	
}
