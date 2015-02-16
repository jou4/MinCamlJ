package mincamlj.closure;

import java.util.List;

import mincamlj.Id;
import mincamlj.Id.Label;

public class CAppDir implements ClosureExpr {

	private Id.Label func;
	private List<String> args;

	public CAppDir(Label func, List<String> args) {
		super();
		this.func = func;
		this.args = args;
	}

	public Id.Label getFunc() {
		return func;
	}

	public List<String> getArgs() {
		return args;
	}

	@Override
	public String toString() {
		return "CAppDir [func=" + func + ", args=" + args + "]";
	}

}
