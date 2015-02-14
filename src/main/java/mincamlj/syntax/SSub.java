package mincamlj.syntax;

public class SSub implements SyntaxExpr {

	private SyntaxExpr left;
	private SyntaxExpr right;

	public SSub(SyntaxExpr left, SyntaxExpr right) {
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
		return "SSub [left=" + left + ", right=" + right + "]";
	}

}
