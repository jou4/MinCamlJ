package mincamlj.syntax;

import java.util.List;

import mincamlj.type.Type;
import mincamlj.util.Pair;

public class SLetTuple implements SyntaxExpr {

	private List<Pair<String, Type>> vars;
	private SyntaxExpr value;
	private SyntaxExpr body;

	public SLetTuple(List<Pair<String, Type>> vars, SyntaxExpr value,
			SyntaxExpr body) {
		super();
		this.vars = vars;
		this.value = value;
		this.body = body;
	}

	public List<Pair<String, Type>> getVars() {
		return vars;
	}

	public SyntaxExpr getValue() {
		return value;
	}

	public SyntaxExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "SLetTuple [vars=" + vars + ", value=" + value + ", body="
				+ body + "]";
	}

}
