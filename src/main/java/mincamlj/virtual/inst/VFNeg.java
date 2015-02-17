package mincamlj.virtual.inst;

public class VFNeg implements VirtualInst {

	private String inner;

	public VFNeg(String inner) {
		super();
		this.inner = inner;
	}

	public String getInner() {
		return inner;
	}

	@Override
	public String toString() {
		return "VFNeg [inner=" + inner + "]";
	}

}
