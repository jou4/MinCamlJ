package mincamlj.syntax;

public class SNot implements SyntaxExpr {
	private SyntaxExpr expr;

	public SNot(SyntaxExpr expr) {
		super();
		this.expr = expr;
	}

	public SyntaxExpr getExpr() {
		return expr;
	}

	@Override
	public String toString() {
		return "SNot [expr=" + expr + "]";
	}

}
