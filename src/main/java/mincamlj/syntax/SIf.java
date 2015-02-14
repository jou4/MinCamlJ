package mincamlj.syntax;

public class SIf implements SyntaxExpr {

	private SyntaxExpr pred;
	private SyntaxExpr trueExpr;
	private SyntaxExpr falseExpr;

	public SIf(SyntaxExpr pred, SyntaxExpr trueExpr, SyntaxExpr falseExpr) {
		super();
		this.pred = pred;
		this.trueExpr = trueExpr;
		this.falseExpr = falseExpr;
	}

	public SyntaxExpr getPred() {
		return pred;
	}

	public SyntaxExpr getTrueExpr() {
		return trueExpr;
	}

	public SyntaxExpr getFalseExpr() {
		return falseExpr;
	}

	@Override
	public String toString() {
		return "SIf [pred=" + pred + ", trueExpr=" + trueExpr + ", falseExpr="
				+ falseExpr + "]";
	}

}
