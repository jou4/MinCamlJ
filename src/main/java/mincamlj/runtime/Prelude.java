package mincamlj.runtime;

public class Prelude {

	public static void min_caml_print_bool(int v) {
		System.out.println((v == 1) ? "true" : "false");
	}

	public static void min_caml_print_int(int v) {
		System.out.println(v);
	}

	public static void min_caml_print_float(float v) {
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

	public static float[] min_caml_create_float_array(int n, float initialize) {
		float[] array = new float[n];
		for (int i = 0; i < array.length; i++) {
			array[i] = initialize;
		}
		return array;
	}

}
