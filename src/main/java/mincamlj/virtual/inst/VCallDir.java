package mincamlj.virtual.inst;

import java.util.List;

import mincamlj.Id;

public class VCallDir implements VirtualInst {

	private Id.Label func;
	private List<String> args;

	public VCallDir(Id.Label func, List<String> args) {
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
		return "VCallDir [func=" + func + ", args=" + args + "]";
	}

}
