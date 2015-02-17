package mincamlj.virtual;

import java.util.List;

public class VProg {

	private List<VFunDef> funDefs;
	private VirtualExpr expr;

	public VProg(List<VFunDef> funDefs, VirtualExpr expr) {
		super();
		this.funDefs = funDefs;
		this.expr = expr;
	}

	public List<VFunDef> getFunDefs() {
		return funDefs;
	}

	public VirtualExpr getExpr() {
		return expr;
	}

	@Override
	public String toString() {
		return "VProg [funDefs=" + funDefs + ", expr=" + expr + "]";
	}

}
