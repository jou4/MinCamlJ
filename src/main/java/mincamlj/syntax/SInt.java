package mincamlj.syntax;

public class SInt implements SyntaxExpr {
	private int value;

	public SInt(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "SInt [value=" + value + "]";
	}

}
