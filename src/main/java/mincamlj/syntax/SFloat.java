package mincamlj.syntax;

public class SFloat implements SyntaxExpr {

	private float value;

	public SFloat(float value) {
		super();
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "SFloat [value=" + value + "]";
	}

}
