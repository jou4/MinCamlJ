package mincamlj.runtime;

public class Tuple3<T1, T2, T3> {

	private T1 val1;
	private T2 val2;
	private T3 val3;

	public Tuple3(T1 val1, T2 val2, T3 val3) {
		super();
		this.val1 = val1;
		this.val2 = val2;
		this.val3 = val3;
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

	@Override
	public String toString() {
		return "(" + val1 + ", " + val2 + ", " + val3 + ")";
	}

}
