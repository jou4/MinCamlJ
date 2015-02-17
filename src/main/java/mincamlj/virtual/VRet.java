package mincamlj.virtual;

import mincamlj.virtual.inst.VirtualInst;

public class VRet implements VirtualExpr {

	private VirtualInst inner;

	public VRet(VirtualInst inner) {
		super();
		this.inner = inner;
	}

	public VirtualInst getInner() {
		return inner;
	}

	@Override
	public String toString() {
		return "VRet [inner=" + inner + "]";
	}

}
