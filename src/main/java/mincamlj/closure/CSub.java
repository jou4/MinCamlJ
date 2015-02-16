package mincamlj.closure;

public class CSub implements ClosureExpr {

	private String left;
	private String right;

	public CSub(String left, String right) {
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
		return "CSub [left=" + left + ", right=" + right + "]";
	}

}
