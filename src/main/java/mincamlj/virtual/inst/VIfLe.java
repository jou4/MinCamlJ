package mincamlj.virtual.inst;

import mincamlj.virtual.VirtualExpr;

public class VIfLe implements VirtualInst {

	private String left;
	private String right;
	private VirtualExpr trueExpr;
	private VirtualExpr falseExpr;

	public VIfLe(String left, String right, VirtualExpr trueExpr,
			VirtualExpr falseExpr) {
		super();
		this.left = left;
		this.right = right;
		this.trueExpr = trueExpr;
		this.falseExpr = falseExpr;
	}

	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}

	public VirtualExpr getTrueExpr() {
		return trueExpr;
	}

	public VirtualExpr getFalseExpr() {
		return falseExpr;
	}

	@Override
	public String toString() {
		return "VIfLe [left=" + left + ", right=" + right + ", trueExpr="
				+ trueExpr + ", falseExpr=" + falseExpr + "]";
	}

}
