package mincamlj.knormal;

public class KIfEq implements KNormalExpr {

	private String left;
	private String right;
	private KNormalExpr trueExpr;
	private KNormalExpr falseExpr;

	public KIfEq(String left, String right, KNormalExpr trueExpr,
			KNormalExpr falseExpr) {
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

	public KNormalExpr getTrueExpr() {
		return trueExpr;
	}

	public KNormalExpr getFalseExpr() {
		return falseExpr;
	}

	@Override
	public String toString() {
		return "KIfEq [left=" + left + ", right=" + right + ", trueExpr="
				+ trueExpr + ", falseExpr=" + falseExpr + "]";
	}

}
