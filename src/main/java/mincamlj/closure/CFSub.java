package mincamlj.closure;

public class CFSub implements ClosureExpr {

	private String left;
	private String right;

	public CFSub(String left, String right) {
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
		return "CFSub [left=" + left + ", right=" + right + "]";
	}

}
