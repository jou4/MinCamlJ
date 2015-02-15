package mincamlj.knormal;

import java.util.List;

import mincamlj.type.Type;
import mincamlj.util.Pair;

public class KLetTuple implements KNormalExpr {

	private List<Pair<String, Type>> vars;
	private String value;
	private KNormalExpr body;

	public KLetTuple(List<Pair<String, Type>> vars, String value,
			KNormalExpr body) {
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

	public KNormalExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "KLetTuple [vars=" + vars + ", value=" + value + ", body="
				+ body + "]";
	}

}
