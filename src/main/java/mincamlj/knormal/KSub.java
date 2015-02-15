package mincamlj.knormal;

public class KSub implements KNormalExpr {

	private String left;
	private String right;

	public KSub(String left, String right) {
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
		return "KSub [left=" + left + ", right=" + right + "]";
	}

}
