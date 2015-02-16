package mincamlj.closure;

public class CNeg implements ClosureExpr {

	private String inner;

	public CNeg(String inner) {
		super();
		this.inner = inner;
	}

	public String getInner() {
		return inner;
	}

	@Override
	public String toString() {
		return "CNeg [inner=" + inner + "]";
	}

}
