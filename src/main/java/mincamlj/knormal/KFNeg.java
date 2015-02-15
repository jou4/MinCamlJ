package mincamlj.knormal;

public class KFNeg implements KNormalExpr {

	private String inner;

	public KFNeg(String inner) {
		super();
		this.inner = inner;
	}

	public String getInner() {
		return inner;
	}

	@Override
	public String toString() {
		return "KFNeg [inner=" + inner + "]";
	}

}
