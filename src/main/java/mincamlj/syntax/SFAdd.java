package mincamlj.syntax;

public class SFAdd implements SyntaxExpr {

	private SyntaxExpr left;
	private SyntaxExpr right;

	public SFAdd(SyntaxExpr left, SyntaxExpr right) {
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
		return "SFAdd [left=" + left + ", right=" + right + "]";
	}

}
