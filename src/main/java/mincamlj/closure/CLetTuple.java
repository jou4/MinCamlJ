package mincamlj.closure;

import java.util.List;

import mincamlj.type.Type;
import mincamlj.util.Pair;

public class CLetTuple implements ClosureExpr {

	private List<Pair<String, Type>> vars;
	private String value;
	private ClosureExpr body;

	public CLetTuple(List<Pair<String, Type>> vars, String value,
			ClosureExpr body) {
		super();
		this.vars = vars;
		this.value = value;
		this.body = body;
	}

	public List<Pair<String, Type>> getVars() {
		return vars;
	}

	public String getValue() {
		return value;
	}

	public ClosureExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "CLetTuple [vars=" + vars + ", value=" + value + ", body="
				+ body + "]";
	}

}
