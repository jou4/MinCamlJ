package mincamlj.syntax;

public class SArray implements SyntaxExpr {

	private SyntaxExpr length;
	private SyntaxExpr initial;

	public SArray(SyntaxExpr length, SyntaxExpr initial) {
		super();
		this.length = length;
		this.initial = initial;
	}

	public SyntaxExpr getLength() {
		return length;
	}

	public SyntaxExpr getInitial() {
		return initial;
	}

	@Override
	public String toString() {
		return "SArray [length=" + length + ", initial=" + initial + "]";
	}

}
