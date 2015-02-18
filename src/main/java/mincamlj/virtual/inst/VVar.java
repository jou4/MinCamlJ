package mincamlj.virtual.inst;

public class VVar implements VirtualInst {

	private String name;

	public VVar(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "VVar [name=" + name + "]";
	}

}
