package mincamlj.closure;

public class CGet implements ClosureExpr {

	private String array;
	private String index;

	public CGet(String array, String index) {
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
		return "CGet [array=" + array + ", index=" + index + "]";
	}

}
