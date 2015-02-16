package mincamlj.closure;

import mincamlj.type.Type;
import mincamlj.util.Pair;

public class CLet implements ClosureExpr {

	private Pair<String, Type> var;
	private ClosureExpr value;
	private ClosureExpr body;

	public CLet(Pair<String, Type> var, ClosureExpr value, ClosureExpr body) {
		super();
		this.var = var;
		this.value = value;
		this.body = body;
	}

	public Pair<String, Type> getVar() {
		return var;
	}

	public ClosureExpr getValue() {
		return value;
	}

	public ClosureExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "CLet [var=" + var + ", value=" + value + ", body=" + body + "]";
	}

}
