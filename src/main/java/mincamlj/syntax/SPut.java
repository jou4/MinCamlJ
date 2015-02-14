package mincamlj.syntax;

public class SPut implements SyntaxExpr {

	private SyntaxExpr array;
	private SyntaxExpr index;
	private SyntaxExpr value;

	public SPut(SyntaxExpr array, SyntaxExpr index, SyntaxExpr value) {
		super();
		this.array = array;
		this.index = index;
		this.value = value;
	}

	public SyntaxExpr getArray() {
		return array;
	}

	public SyntaxExpr getIndex() {
		return index;
	}

	public SyntaxExpr getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "SPut [array=" + array + ", index=" + index + ", value=" + value
				+ "]";
	}

}
