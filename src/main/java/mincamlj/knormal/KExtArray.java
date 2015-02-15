package mincamlj.knormal;

public class KExtArray implements KNormalExpr {

	private String array;

	public KExtArray(String array) {
		super();
		this.array = array;
	}

	public String getArray() {
		return array;
	}

	@Override
	public String toString() {
		return "KExtArray [array=" + array + "]";
	}

}
