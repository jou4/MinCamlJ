package mincamlj.runtime;

public class Tuple4<T1, T2, T3, T4> {

	private T1 val1;
	private T2 val2;
	private T3 val3;
	private T4 val4;

	public Tuple4(T1 val1, T2 val2, T3 val3, T4 val4) {
		super();
		this.val1 = val1;
		this.val2 = val2;
		this.val3 = val3;
		this.val4 = val4;
	}

	public T1 getVal1() {
		return val1;
	}

	public T2 getVal2() {
		return val2;
	}

	public T3 getVal3() {
		return val3;
	}

	public T4 getVal4() {
		return val4;
	}

	@Override
	public String toString() {
		return "(" + val1 + ", " + val2 + ", " + val3 + ", " + val4 + ")";
	}

}
