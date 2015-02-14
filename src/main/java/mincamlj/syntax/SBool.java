package mincamlj.syntax;

public class SBool implements SyntaxExpr {

	private boolean value;

	public SBool(boolean value) {
		super();
		this.value = value;
	}

	public boolean isValue() {
		return value;
	}

	@Override
	public String toString() {
		return "SBool [value=" + value + "]";
	}

}
