package mincamlj.type;

public class FloatType implements Type {

	private static final FloatType t = new FloatType();

	public static FloatType getInstance() {
		return t;
	}

	private FloatType() {
	}

	@Override
	public String toString() {
		return "FloatType []";
	}

}
