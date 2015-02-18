package mincamlj;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.lang.invoke.MethodType;

import mincamlj.closure.CProg;

import org.junit.Test;

public class InvokeTest {

	@Test
	public void test0() {
		MethodType mt;
		mt = MethodType.methodType(void.class, int.class, int.class);
		System.out.println(mt.toMethodDescriptorString());
		mt = MethodType.methodType(void.class, int.class, String.class);
		System.out.println(mt.toMethodDescriptorString());
	}

	@Test
	public void test1() throws Exception {
		String code = "let z = 100 in let a = z + 1 in if a = 1 then print_bool true else print_bool false";
		String className = "Test1";

		CProg prog = Main.compile(code);
		System.out.println(prog);
		byte[] bytes = new Emit(className).emit(prog);

		DataOutputStream out = new DataOutputStream(new FileOutputStream(
				className + ".class"));
		out.write(bytes);
		out.flush();
		out.close();
	}

}
