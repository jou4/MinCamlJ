package mincamlj.syntax;

public class SFMul implements SyntaxExpr {

	private SyntaxExpr left;
	private SyntaxExpr right;

	public SFMul(SyntaxExpr left, SyntaxExpr right) {
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
		return "SFMul [left=" + left + ", right=" + right + "]";
	}

}
