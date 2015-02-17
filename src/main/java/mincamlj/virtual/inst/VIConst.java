package mincamlj.virtual.inst;

public class VIConst implements VirtualInst {

	private int value;

	public VIConst(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "VIConst [value=" + value + "]";
	}

}
