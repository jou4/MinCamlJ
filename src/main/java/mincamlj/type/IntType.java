package mincamlj.type;

public class IntType implements Type {

	private static final IntType t = new IntType();

	public static IntType getInstance() {
		return t;
	}

	private IntType() {
	}

	@Override
	public String toString() {
		return "IntType []";
	}

}
