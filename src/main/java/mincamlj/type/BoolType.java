package mincamlj.type;

public class BoolType implements Type {

	private static final BoolType t = new BoolType();

	public static BoolType getInstance() {
		return t;
	}

	private BoolType() {
	}

	@Override
	public String toString() {
		return "BoolType []";
	}

}
