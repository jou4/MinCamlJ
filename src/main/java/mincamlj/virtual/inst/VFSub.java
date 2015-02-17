package mincamlj.virtual.inst;

public class VFSub implements VirtualInst {

	private String left;
	private String right;

	public VFSub(String left, String right) {
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
		return "VFSub [left=" + left + ", right=" + right + "]";
	}

}
