package mincamlj.syntax;

public class SNeg implements SyntaxExpr {

	private SyntaxExpr expr;

	public SNeg(SyntaxExpr expr) {
		super();
		this.expr = expr;
	}

	public SyntaxExpr getExpr() {
		return expr;
	}

	@Override
	public String toString() {
		return "SNeg [expr=" + expr + "]";
	}

}
