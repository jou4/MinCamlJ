package mincamlj.runtime;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import mincamlj.type.ArrayType;
import mincamlj.type.FloatType;
import mincamlj.type.FunType;
import mincamlj.type.IntType;
import mincamlj.type.Type;

public class Prelude {

	public static Map<String, Type> preset = new HashMap<>();
	static {
		preset.put(
				"create_array",
				new FunType(Arrays.asList(IntType.getInstance(),
						IntType.getInstance()), new ArrayType(IntType
						.getInstance())));
		preset.put(
				"create_float_array",
				new FunType(Arrays.asList(IntType.getInstance(),
						FloatType.getInstance()), new ArrayType(FloatType
						.getInstance())));
	}

	public static void min_caml_print_bool(int v) {
		System.out.println((v == 1) ? "true" : "false");
	}

	public static void min_caml_print_int(int v) {
		System.out.println(v);
	}

	public static void min_caml_print_float(double v) {
		System.out.println(v);
	}

	public static void min_caml_print_tuple(Object o) {
		System.out.println(o);
	}

	public static int[] min_caml_create_array(int n, int initialize) {
		int[] array = new int[n];
		for (int i = 0; i < array.length; i++) {
			array[i] = initialize;
		}
		return array;
	}

	public static double[] min_caml_create_float_array(int n, double initialize) {
		double[] array = new double[n];
		for (int i = 0; i < array.length; i++) {
			array[i] = initialize;
		}
		return array;
	}

	public static void min_caml_print_array(int[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		String sep = "";
		for (int i = 0; i < array.length; i++) {
			sb.append(sep);
			sb.append(array[i]);
			sep = ", ";
		}
		sb.append("]");
		System.out.println(sb.toString());
	}

	public static void min_caml_print_float_array(double[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		String sep = "";
		for (int i = 0; i < array.length; i++) {
			sb.append(sep);
			sb.append(array[i]);
			sep = ", ";
		}
		sb.append("]");
		System.out.println(sb.toString());
	}

}
