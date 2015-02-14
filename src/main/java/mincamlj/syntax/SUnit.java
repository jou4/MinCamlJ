package mincamlj.syntax;

public class SUnit implements SyntaxExpr {

	private static final SUnit e = new SUnit();

	public static SUnit getInstance() {
		return e;
	}

	private SUnit() {
	}

	@Override
	public String toString() {
		return "SUnit []";
	}

}
