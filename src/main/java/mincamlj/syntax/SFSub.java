package mincamlj.syntax;

public class SFSub implements SyntaxExpr {

	private SyntaxExpr left;
	private SyntaxExpr right;

	public SFSub(SyntaxExpr left, SyntaxExpr right) {
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
		return "SFSub [left=" + left + ", right=" + right + "]";
	}

}
