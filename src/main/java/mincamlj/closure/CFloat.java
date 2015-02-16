package mincamlj.closure;

public class CFloat implements ClosureExpr {

	private float value;

	public CFloat(float value) {
		super();
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "CFloat [value=" + value + "]";
	}

}
