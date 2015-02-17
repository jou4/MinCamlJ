package mincamlj.virtual.inst;

import java.util.List;

public class VCallCls implements VirtualInst {

	private String func;
	private List<String> args;

	public VCallCls(String func, List<String> args) {
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
		return "VCallCls [func=" + func + ", args=" + args + "]";
	}

}
