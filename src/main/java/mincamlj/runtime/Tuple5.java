package mincamlj.runtime;

public class Tuple5<T1, T2, T3, T4, T5> {

	private T1 val1;
	private T2 val2;
	private T3 val3;
	private T4 val4;
	private T5 val5;

	public Tuple5(T1 val1, T2 val2, T3 val3, T4 val4, T5 val5) {
		super();
		this.val1 = val1;
		this.val2 = val2;
		this.val3 = val3;
		this.val4 = val4;
		this.val5 = val5;
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

	public T5 getVal5() {
		return val5;
	}

	@Override
	public String toString() {
		return "(" + val1 + ", " + val2 + ", " + val3 + ", " + val4 + ", "
				+ val5 + ")";
	}

}
