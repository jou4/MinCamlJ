package mincamlj.closure;

import java.util.List;

public class CAppCls implements ClosureExpr {

	private String func;
	private List<String> args;

	public CAppCls(String func, List<String> args) {
		super();
		this.func = func;
		this.args = args;
	}

	public String getFunc() {
		return func;
	}

	public List<String> getArgs() {
		return args;
	}

	@Override
	public String toString() {
		return "CAppCls [func=" + func + ", args=" + args + "]";
	}

}
