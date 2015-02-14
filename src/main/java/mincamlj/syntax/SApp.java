package mincamlj.syntax;

import java.util.List;

public class SApp implements SyntaxExpr {

	private SyntaxExpr func;
	private List<SyntaxExpr> args;

	public SApp(SyntaxExpr func, List<SyntaxExpr> args) {
		super();
		this.func = func;
		this.args = args;
	}

	public SyntaxExpr getFunc() {
		return func;
	}

	public List<SyntaxExpr> getArgs() {
		return args;
	}

	@Override
	public String toString() {
		return "SApp [func=" + func + ", args=" + args + "]";
	}

}
