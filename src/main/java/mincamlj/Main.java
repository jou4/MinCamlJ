package mincamlj;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import mincamlj.closure.CProg;
import mincamlj.knormal.KNormalExpr;
import mincamlj.syntax.SyntaxExpr;

public class Main {

	private static final int limit = 1000;

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("file path is not passed.");
			return;
		}

		String path = args[0];
		String code = new String(Files.readAllBytes(FileSystems.getDefault()
				.getPath(path)));

		SyntaxExpr e0 = new Parser().parse(code);
		e0 = new Typing().typing(e0);

		KNormalExpr e1 = new KNormal().transform(e0);
		e1 = new Alpha().transform(e1);

		for (int i = 0; i < limit; i++) {
			e1 = new Beta().transform(e1);
			e1 = new Assoc().transform(e1);
			e1 = new Inline().transform(e1);
			e1 = new ConstFold().transform(e1);
			e1 = new Elim().transform(e1);
		}

		CProg prog = new Closure().transform(e1);
		System.out.println(prog);
	}

}
