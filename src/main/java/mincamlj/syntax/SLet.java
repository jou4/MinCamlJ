package mincamlj.syntax;

import mincamlj.type.Type;
import mincamlj.util.Pair;

public class SLet implements SyntaxExpr {

	private Pair<String, Type> var;
	private SyntaxExpr value;
	private SyntaxExpr body;

	public SLet(Pair<String, Type> var, SyntaxExpr value, SyntaxExpr body) {
		super();
		this.var = var;
		this.value = value;
		this.body = body;
	}

	public Pair<String, Type> getVar() {
		return var;
	}

	public SyntaxExpr getValue() {
		return value;
	}

	public SyntaxExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "SLet [var=" + var + ", value=" + value + ", body=" + body + "]";
	}

}
