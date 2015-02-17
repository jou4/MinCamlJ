package mincamlj.virtual.inst;

public class VFDiv implements VirtualInst {

	private String left;
	private String right;

	public VFDiv(String left, String right) {
		super();
		this.left = left;
		this.right = right;
	}

	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}

	@Override
	public String toString() {
		return "VFDiv [left=" + left + ", right=" + right + "]";
	}

}
