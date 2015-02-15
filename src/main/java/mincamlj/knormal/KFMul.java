package mincamlj.knormal;

public class KFMul implements KNormalExpr {

	private String left;
	private String right;

	public KFMul(String left, String right) {
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
		return "KFMul [left=" + left + ", right=" + right + "]";
	}

}
