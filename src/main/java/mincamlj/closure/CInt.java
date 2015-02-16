package mincamlj.closure;

public class CInt implements ClosureExpr {

	private int value;

	public CInt(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "CInt [value=" + value + "]";
	}

}
