package mincamlj;

import mincamlj.syntax.SyntaxExpr;
import mincamlj.type.Type;
import mincamlj.util.Pair;

public class TypingError {

	private SyntaxExpr expr;
	private Pair<Type, Type> errorTypes;

	public TypingError(SyntaxExpr expr, Pair<Type, Type> errorTypes) {
		super();
		this.expr = expr;
		this.errorTypes = errorTypes;
	}

	public SyntaxExpr getExpr() {
		return expr;
	}

	public Pair<Type, Type> getErrorTypes() {
		return errorTypes;
	}

}
