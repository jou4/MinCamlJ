package mincamlj.syntax;

import mincamlj.type.Type;
import mincamlj.util.Pair;

public class SLet implements SyntaxExpr {

	private Pair<String, Type> var;
	private SyntaxExpr value;
	private SyntaxExpr body;

	public SLet(String varName, Type varType, SyntaxExpr value, SyntaxExpr body) {
		super();
		this.var = new Pair<String, Type>(varName, varType);
		this.value = value;
		this.body = body;
	}

	public String getVarName() {
		return var.getLeft();
	}

	public Type getVarType() {
		return var.getRight();
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
