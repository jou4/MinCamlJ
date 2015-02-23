package mincamlj;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;

import mincamlj.closure.CProg;
import mincamlj.knormal.KNormalExpr;
import mincamlj.syntax.SyntaxExpr;
import mincamlj.type.ArrayType;
import mincamlj.type.FloatType;
import mincamlj.type.FunType;
import mincamlj.type.IntType;
import mincamlj.type.Type;

public class Main {

	private static final int limit = 1000;

	public static CProg compile(String code) {
		Typing.extEnv = new HashMap<String, Type>();
		Typing.extEnv.put(
				"create_array",
				new FunType(Arrays.asList(IntType.getInstance(),
						IntType.getInstance()), new ArrayType(IntType
						.getInstance())));
		Typing.extEnv.put(
				"create_float_array",
				new FunType(Arrays.asList(IntType.getInstance(),
						FloatType.getInstance()), new ArrayType(FloatType
						.getInstance())));

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

		return new Closure().transform(e1);
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.err.println("file path and class name is necessary.");
			return;
		}

		Path path = FileSystems.getDefault().getPath(args[0]);
		String className = args[1];
		String outputDir = (args.length > 2) ? args[2] : ".";

		String code = new String(Files.readAllBytes(path));
		CProg prog = compile(code);
		byte[] bytes = new Emit(className).emit(prog);

		File file = new File(outputDir + '\\' + className.replace('.', '\\')
				+ ".class");
		File dir = file.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		DataOutputStream out = new DataOutputStream(new FileOutputStream(file));

		out.write(bytes);
		out.flush();
		out.close();
	}

}
