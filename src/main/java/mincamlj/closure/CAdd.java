package mincamlj.closure;

public class CAdd implements ClosureExpr {

	private String left;
	private String right;

	public CAdd(String left, String right) {
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
		return "CAdd [left=" + left + ", right=" + right + "]";
	}

}
