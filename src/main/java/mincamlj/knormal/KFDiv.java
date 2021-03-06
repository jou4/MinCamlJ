package mincamlj.knormal;

public class KFDiv implements KNormalExpr {

	private String left;
	private String right;

	public KFDiv(String left, String right) {
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
		return "KFDiv [left=" + left + ", right=" + right + "]";
	}

}
