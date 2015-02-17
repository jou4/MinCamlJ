package mincamlj.virtual.inst;

public class VFMul implements VirtualInst {

	private String left;
	private String right;

	public VFMul(String left, String right) {
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
		return "VFMul [left=" + left + ", right=" + right + "]";
	}

}
