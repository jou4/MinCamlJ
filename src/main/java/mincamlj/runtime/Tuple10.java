package mincamlj.runtime;

public class Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> {

	private T1 val1;
	private T2 val2;
	private T3 val3;
	private T4 val4;
	private T5 val5;
	private T6 val6;
	private T7 val7;
	private T8 val8;
	private T9 val9;
	private T10 val10;

	public Tuple10(T1 val1, T2 val2, T3 val3, T4 val4, T5 val5, T6 val6,
			T7 val7, T8 val8, T9 val9, T10 val10) {
		super();
		this.val1 = val1;
		this.val2 = val2;
		this.val3 = val3;
		this.val4 = val4;
		this.val5 = val5;
		this.val6 = val6;
		this.val7 = val7;
		this.val8 = val8;
		this.val9 = val9;
		this.val10 = val10;
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

	public T6 getVal6() {
		return val6;
	}

	public T7 getVal7() {
		return val7;
	}

	public T8 getVal8() {
		return val8;
	}

	public T9 getVal9() {
		return val9;
	}

	public T10 getVal10() {
		return val10;
	}

	@Override
	public String toString() {
		return "(" + val1 + ", " + val2 + ", " + val3 + ", " + val4 + ", "
				+ val5 + ", " + val6 + ", " + val7 + ", " + val8 + ", " + val9
				+ ", " + val10 + ")";
	}

}
