package mincamlj.virtual.inst;

public class VFConst implements VirtualInst {

	private float value;

	public VFConst(float value) {
		super();
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "VFConst [value=" + value + "]";
	}

}
