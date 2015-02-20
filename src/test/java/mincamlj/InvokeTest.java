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
		//String code = "let n = 3 in let a = Array.make n 0 in let _ = a.(0) <- 9 in let _ = a.(1) <- 8 in let _ = a.(2) <- 7 in print_int a.(1)";
		String code = "let n = 3 in let a = Array.make n 0 in let _ = a.(1) <- 10 in print_int a.(1)";
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
		int a[] = { 1, 2, 3 };
		int b = a[2];
		System.out.println(b);
	}

}
