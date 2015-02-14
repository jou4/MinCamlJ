package mincamlj.syntax;

public class SAdd implements SyntaxExpr {
	private SyntaxExpr left;
	private SyntaxExpr right;

	public SAdd(SyntaxExpr left, SyntaxExpr right) {
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
		return "SAdd [left=" + left + ", right=" + right + "]";
	}

}
