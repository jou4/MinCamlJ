package mincamlj.virtual.inst;

public class VAdd implements VirtualInst {

	private String left;
	private String right;

	public VAdd(String left, String right) {
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
		return "VAdd [left=" + left + ", right=" + right + "]";
	}

}
