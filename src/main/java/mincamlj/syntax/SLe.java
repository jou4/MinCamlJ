package mincamlj.syntax;

public class SLe implements SyntaxExpr {

	private SyntaxExpr left;
	private SyntaxExpr right;

	public SLe(SyntaxExpr left, SyntaxExpr right) {
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
		return "SLe [left=" + left + ", right=" + right + "]";
	}

}
