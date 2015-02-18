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

}
