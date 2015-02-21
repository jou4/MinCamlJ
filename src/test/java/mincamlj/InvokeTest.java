package mincamlj;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.function.Function;

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
		// String code =
		// "let n = 3 in let a = Array.make n 0 in let _ = a.(0) <- 9 in let _ = a.(1) <- 8 in let _ = a.(2) <- 7 in print_int a.(1)";
		// String code =
		// "let n = 3 in let a = Array.make n 0 in let _ = a.(1) <- 10 in print_int a.(1)";
		// String code =
		// "let a = (1, 2) in let b = (0, a) in let (c, d) = b in let (e, f) = d in print_int f";
		// String code = "let rec add x = x +. x in print_float (add 100.5)";
		// String code = "let rec add x = x + x in print_int (add 100)";
		// String code =
		// "let rec add a b c = a + b + c in print_int (add 1 2 3)";
		// String code =
		// "let f = let rec add x y = x + y in add in print_int (f 10 20)";
		// String code =
		// "let f = let rec add x y = x + y in add in let a = f 10 20 in print_int a";
		String code = "let rec make_adder x = let rec adder y = x + y in adder in print_int ((make_adder 100) 200)";
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

	@Test
	public void test3() {
		String code = "let rec make_adder x = let rec adder y = x + y in adder in print_int ((make_adder 10) 20)";
		String className = "Test1";

		CProg prog = Main.compile(code);
		System.out.println(prog);
	}

	public static void main(String[] args) {
		Function<int[], int[]> f = ia -> ia;
		System.out.println(f.apply(new int[] { 1, 2, 3 })[2]);

	}

}
