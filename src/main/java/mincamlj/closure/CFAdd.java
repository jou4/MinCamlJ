package mincamlj.closure;

public class CFAdd implements ClosureExpr {

	private String left;
	private String right;

	public CFAdd(String left, String right) {
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
		return "CFAdd [left=" + left + ", right=" + right + "]";
	}

}
