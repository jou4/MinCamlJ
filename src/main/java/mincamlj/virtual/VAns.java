package mincamlj.virtual;

import mincamlj.virtual.inst.VirtualInst;

public class VAns implements VirtualExpr {

	private VirtualInst inner;

	public VAns(VirtualInst inner) {
		super();
		this.inner = inner;
	}

	public VirtualInst getInner() {
		return inner;
	}

	@Override
	public String toString() {
		return "VAns [inner=" + inner + "]";
	}

}
