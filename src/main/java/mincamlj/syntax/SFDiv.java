package mincamlj.syntax;

public class SFDiv implements SyntaxExpr {

	private SyntaxExpr left;
	private SyntaxExpr right;

	public SFDiv(SyntaxExpr left, SyntaxExpr right) {
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
		return "SFDiv [left=" + left + ", right=" + right + "]";
	}

}
