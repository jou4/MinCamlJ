package mincamlj.type;

public class UnitType implements Type {

	private static final UnitType t = new UnitType();

	public static UnitType getInstance() {
		return t;
	}

	private UnitType() {
	}

	@Override
	public String toString() {
		return "UnitType []";
	}

}
