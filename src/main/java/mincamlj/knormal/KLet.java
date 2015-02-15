package mincamlj.knormal;

import mincamlj.type.Type;
import mincamlj.util.Pair;

public class KLet implements KNormalExpr {

	private Pair<String, Type> var;
	private KNormalExpr value;
	private KNormalExpr body;

	public KLet(Pair<String, Type> var, KNormalExpr value, KNormalExpr body) {
		super();
		this.var = var;
		this.value = value;
		this.body = body;
	}

	public Pair<String, Type> getVar() {
		return var;
	}

	public KNormalExpr getValue() {
		return value;
	}

	public KNormalExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "KLet [var=" + var + ", value=" + value + ", body=" + body + "]";
	}

}
