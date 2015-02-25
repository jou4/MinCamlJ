package mincamlj;

import mincamlj.closure.CProg;
import mincamlj.knormal.KNormalExpr;
import mincamlj.parser.SyntaxVisitor;
import mincamlj.parser.grammer.MinCamlLexer;
import mincamlj.parser.grammer.MinCamlParser;
import mincamlj.syntax.SyntaxExpr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.junit.Test;

public class ParserTest {

	@Test
	public void test() {
		String s = "";
		// s = "if a = 10 then a + 2 else a - 3";
		// s = "let a = 1 in let b = 2.0 in let c = (a, b) in let (d, e) = c in print_float e";
		s = "let h = if 1.2 > 3.4 then 1 else 2 in print_int h";
		MinCamlLexer lexer = new MinCamlLexer(new ANTLRInputStream(s));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MinCamlParser parser = new MinCamlParser(tokens);

		RuleContext context = parser.exp();
		System.out.println("-- " + context.toStringTree(parser));

		SyntaxVisitor visitor = new SyntaxVisitor();
		SyntaxExpr e = visitor.visit(context);
		System.out.println(e);

		SyntaxExpr e2 = new Typing().typing(e);
		System.out.println(e2);

		System.out.println("-- extenv: " + Typing.extEnv);

		KNormalExpr e3 = new KNormal().transform(e2);
		System.out.println(e3);

		KNormalExpr e4 = new Alpha().transform(e3);
		System.out.println(e4);

		KNormalExpr e5 = new Beta().transform(e4);
		System.out.println(e5);

		KNormalExpr e6 = new Assoc().transform(e5);
		System.out.println(e6);

		KNormalExpr e7 = new Inline().transform(e6);
		System.out.println(e7);

		KNormalExpr e8 = new ConstFold().transform(e7);
		System.out.println(e8);

		KNormalExpr e9 = new Elim().transform(e8);
		System.out.println(e9);

		CProg prog = new Closure().transform(e9);
		System.out.println(prog);

		byte[] bytes = new Emit("test").emit(prog);
	}

}
