package mincamlj.knormal;

public class KInt implements KNormalExpr {

	private int value;

	public KInt(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "KInt [value=" + value + "]";
	}

}
