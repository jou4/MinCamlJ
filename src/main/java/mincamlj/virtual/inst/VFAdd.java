package mincamlj.virtual.inst;

public class VFAdd implements VirtualInst {

	private String left;
	private String right;

	public VFAdd(String left, String right) {
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
		return "VFAdd [left=" + left + ", right=" + right + "]";
	}

}
