package mincamlj.knormal;

public class KAdd implements KNormalExpr {

	private String left;
	private String right;

	public KAdd(String left, String right) {
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
		return "KAdd [left=" + left + ", right=" + right + "]";
	}

}
