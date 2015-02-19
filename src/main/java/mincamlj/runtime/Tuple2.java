package mincamlj.runtime;

public class Tuple2<T1, T2> {

	private T1 val1;
	private T2 val2;

	public Tuple2(T1 val1, T2 val2) {
		super();
		this.val1 = val1;
		this.val2 = val2;
	}

	public T1 getVal1() {
		return val1;
	}

	public T2 getVal2() {
		return val2;
	}

	@Override
	public String toString() {
		return "(" + val1 + ", " + val2 + ")";
	}

}
