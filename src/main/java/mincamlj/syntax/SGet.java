package mincamlj.syntax;

public class SGet implements SyntaxExpr {

	private SyntaxExpr array;
	private SyntaxExpr index;

	public SGet(SyntaxExpr array, SyntaxExpr index) {
		super();
		this.array = array;
		this.index = index;
	}

	public SyntaxExpr getArray() {
		return array;
	}

	public SyntaxExpr getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "SGet [array=" + array + ", index=" + index + "]";
	}

}
