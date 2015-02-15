package mincamlj.knormal;

public class KUnit implements KNormalExpr {

	private static final KUnit e = new KUnit();

	public static KUnit getInstance() {
		return e;
	}

	private KUnit() {
	}

	@Override
	public String toString() {
		return "KUnit []";
	}

}
