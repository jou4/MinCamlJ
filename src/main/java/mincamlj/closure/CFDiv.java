package mincamlj.closure;

public class CFDiv implements ClosureExpr {

	private float left;
	private float right;

	public CFDiv(float left, float right) {
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
		return "CFDiv [left=" + left + ", right=" + right + "]";
	}

}
