package mincamlj;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.lang.invoke.MethodType;

import mincamlj.closure.CProg;
import mincamlj.runtime.Tuple2;

import org.junit.Test;

public class InvokeTest {

	@Test
	public void test0() {
		int a = 1;
		if (a == 1) {
			int b = 2;
			int c = 3;
		} else {
			int d = 4;
		}
		if (a == 2) {
			int e = 5;
		} else {
			Tuple2<Integer, Float> t = new Tuple2<Integer, Float>(1, 2.0f);
			float g = 7.0f;
		}
	}

	@Test
	public void test1() throws Exception {
		String code = "let a = 1 in let b = 2 in let c = (a, b) in let (d, e) = c in print_int e";
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

	public static void main(String[] args) {
		
		int a = 1;
		int b = 2;
		Tuple2<Integer, Integer> t2 = new Tuple2<Integer, Integer>(a, b);
		int d = t2.getVal1();
		int e = t2.getVal2();
		System.out.println(e);
	}

}
