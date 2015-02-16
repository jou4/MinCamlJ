package mincamlj.closure;

import java.util.List;

public class CProg {

	private List<CFunDef> funDefs;
	private ClosureExpr expr;

	public CProg(List<CFunDef> funDefs, ClosureExpr expr) {
		super();
		this.funDefs = funDefs;
		this.expr = expr;
	}

	public List<CFunDef> getFunDefs() {
		return funDefs;
	}

	public ClosureExpr getExpr() {
		return expr;
	}

	@Override
	public String toString() {
		return "CProg [funDefs=" + funDefs + ", expr=" + expr + "]";
	}

}
