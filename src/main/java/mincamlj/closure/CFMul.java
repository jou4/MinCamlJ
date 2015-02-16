package mincamlj.closure;

public class CFMul implements ClosureExpr {

	private float left;
	private float right;

	public CFMul(float left, float right) {
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
		return "CFMul [left=" + left + ", right=" + right + "]";
	}

}
