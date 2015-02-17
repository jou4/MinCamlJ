package mincamlj.virtual.inst;

public class VNeg implements VirtualInst {

	private String inner;

	public VNeg(String inner) {
		super();
		this.inner = inner;
	}

	public String getInner() {
		return inner;
	}

	@Override
	public String toString() {
		return "VNeg [inner=" + inner + "]";
	}

}
