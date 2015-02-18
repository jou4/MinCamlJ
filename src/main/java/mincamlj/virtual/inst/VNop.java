package mincamlj.virtual.inst;

public class VNop implements VirtualInst {

	private static final VNop i = new VNop();

	private VNop() {
	}

	public static VNop getInstance() {
		return i;
	}

}
