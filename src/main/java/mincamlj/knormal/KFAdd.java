package mincamlj.knormal;

public class KFAdd implements KNormalExpr {

	private String left;
	private String right;

	public KFAdd(String left, String right) {
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
		return "KFAdd [left=" + left + ", right=" + right + "]";
	}

}
