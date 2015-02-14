package mincamlj.syntax;

import java.util.List;

public class STuple implements SyntaxExpr {

	private List<SyntaxExpr> values;

	public STuple(List<SyntaxExpr> values) {
		super();
		this.values = values;
	}

	public List<SyntaxExpr> getValues() {
		return values;
	}

	@Override
	public String toString() {
		return "STuple [values=" + values + "]";
	}

}
