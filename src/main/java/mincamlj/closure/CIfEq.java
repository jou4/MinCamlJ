package mincamlj.closure;

public class CIfEq implements ClosureExpr {

	private String left;
	private String right;
	private ClosureExpr trueExpr;
	private ClosureExpr falseExpr;

	public CIfEq(String left, String right, ClosureExpr trueExpr,
			ClosureExpr falseExpr) {
		super();
		this.left = left;
		this.right = right;
		this.trueExpr = trueExpr;
		this.falseExpr = falseExpr;
	}

	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}

	public ClosureExpr getTrueExpr() {
		return trueExpr;
	}

	public ClosureExpr getFalseExpr() {
		return falseExpr;
	}

	@Override
	public String toString() {
		return "CIfEq [left=" + left + ", right=" + right + ", trueExpr="
				+ trueExpr + ", falseExpr=" + falseExpr + "]";
	}

}
