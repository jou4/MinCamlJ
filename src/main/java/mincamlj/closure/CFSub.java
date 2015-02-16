package mincamlj.closure;

public class CFSub implements ClosureExpr {

	private float left;
	private float right;

	public CFSub(float left, float right) {
		super();
		this.left = left;
		this.right = right;
	}

	public float getLeft() {
		return left;
	}

	public float getRight() {
		return right;
	}

	@Override
	public String toString() {
		return "CFSub [left=" + left + ", right=" + right + "]";
	}

}
