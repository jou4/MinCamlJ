package mincamlj.closure;

public class CFAdd implements ClosureExpr {

	private float left;
	private float right;

	public CFAdd(float left, float right) {
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
		return "CFAdd [left=" + left + ", right=" + right + "]";
	}

}
