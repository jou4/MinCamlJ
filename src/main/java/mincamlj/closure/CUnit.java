package mincamlj.closure;

public class CUnit implements ClosureExpr {

	private static final CUnit u = new CUnit();

	private CUnit() {
	}

	public static CUnit getInstance() {
		return u;
	}

	@Override
	public String toString() {
		return "CUnit []";
	}

}
