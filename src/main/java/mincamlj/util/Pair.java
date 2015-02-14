package mincamlj.util;

public class Pair<L, R> {

	private L left;
	private R right;

	public Pair(L left, R right) {
		super();
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}

	public R getRight() {
		return right;
	}

	@Override
	public String toString() {
		return "Pair [left=" + left + ", right=" + right + "]";
	}

}
