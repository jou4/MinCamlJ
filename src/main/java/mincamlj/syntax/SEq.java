package mincamlj.syntax;

public class SEq implements SyntaxExpr {

	private SyntaxExpr left;
	private SyntaxExpr right;

	public SEq(SyntaxExpr left, SyntaxExpr right) {
		super();
		this.left = left;
		this.right = right;
	}

	public SyntaxExpr getLeft() {
		return left;
	}

	public SyntaxExpr getRight() {
		return right;
	}

	@Override
	public String toString() {
		return "SEq [left=" + left + ", right=" + right + "]";
	}

}
