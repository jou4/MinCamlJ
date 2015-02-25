package mincamlj.runtime;


public class Prelude {

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
