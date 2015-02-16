package mincamlj.closure;

public class CFNeg implements ClosureExpr {

	private String inner;

	public CFNeg(String inner) {
		super();
		this.inner = inner;
	}

	public String getInner() {
		return inner;
	}

	@Override
	public String toString() {
		return "CFNeg [inner=" + inner + "]";
	}

}
