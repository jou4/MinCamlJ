package mincamlj.knormal;

public class KGet implements KNormalExpr {

	private String array;
	private String index;

	public KGet(String array, String index) {
		super();
		this.array = array;
		this.index = index;
	}

	public String getArray() {
		return array;
	}

	public String getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "KGet [array=" + array + ", index=" + index + "]";
	}

}
