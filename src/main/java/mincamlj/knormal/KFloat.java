package mincamlj.knormal;

public class KFloat implements KNormalExpr {

	private float value;

	public KFloat(float value) {
		super();
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "KFloat [value=" + value + "]";
	}

}
