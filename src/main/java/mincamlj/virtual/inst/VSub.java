package mincamlj.virtual.inst;

public class VSub implements VirtualInst {

	private String left;
	private String right;

	public VSub(String left, String right) {
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
		return "VSub [left=" + left + ", right=" + right + "]";
	}

}
