package mincamlj.closure;

public class CFDiv implements ClosureExpr {

	private String left;
	private String right;

	public CFDiv(String left, String right) {
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
		return "CFDiv [left=" + left + ", right=" + right + "]";
	}

}
