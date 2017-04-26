package com.mindlin.jsast.example;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;

import com.mindlin.jsast.impl.parser.JSParser;
import com.mindlin.jsast.impl.writer.JSWriterImpl;
import com.mindlin.jsast.tree.CompilationUnitTree;
import com.mindlin.jsast.writer.JSWriterOptions;

public class REPL {
	public static void main(String[] args) throws IOException {
		JSParser parser = new JSParser();
		JSWriterOptions options = new JSWriterOptions();
		options.indentStyle = "\t";
		JSWriterImpl writer = new JSWriterImpl(options);
		Scanner s = new Scanner(System.in);
		System.out.println("Nautilus JS transpiler");
		while (true) {
			System.out.print(">>> ");
			String line = s.nextLine();
			CompilationUnitTree ast = parser.apply("tmp", line);
			StringWriter out = new StringWriter();
			writer.write(ast, out);
			System.out.println(out.toString());
		}
	}
}
