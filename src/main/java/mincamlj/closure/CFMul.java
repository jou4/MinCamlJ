package mincamlj.closure;

public class CFMul implements ClosureExpr {

	private String left;
	private String right;

	public CFMul(String left, String right) {
		super();
		this.left = left;
		this.right = right;
	}

	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}

	@Override
	public String toString() {
		return "CFMul [left=" + left + ", right=" + right + "]";
	}

}
