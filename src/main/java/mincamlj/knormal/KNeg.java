package mincamlj.knormal;

public class KNeg implements KNormalExpr {

	private String inner;

	public KNeg(String inner) {
		super();
		this.inner = inner;
	}

	public String getInner() {
		return inner;
	}

	@Override
	public String toString() {
		return "KNeg [inner=" + inner + "]";
	}

}
