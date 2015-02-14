package mincamlj.syntax;

public class SFNeg implements SyntaxExpr {

	private SyntaxExpr expr;

	public SFNeg(SyntaxExpr expr) {
		super();
		this.expr = expr;
	}

	public SyntaxExpr getExpr() {
		return expr;
	}

	@Override
	public String toString() {
		return "SFNeg [expr=" + expr + "]";
	}

}
