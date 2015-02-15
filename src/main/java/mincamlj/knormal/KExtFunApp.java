package mincamlj.knormal;

import java.util.List;

public class KExtFunApp implements KNormalExpr {

	private String func;
	private List<String> args;

	public KExtFunApp(String func, List<String> args) {
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
		return "KExtFunApp [func=" + func + ", args=" + args + "]";
	}

}
