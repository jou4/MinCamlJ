package mincamlj.closure;

public class CPut implements ClosureExpr {

	private String array;
	private String index;
	private String value;

	public CPut(String array, String index, String value) {
		super();
		this.array = array;
		this.index = index;
		this.value = value;
	}

	public String getArray() {
		return array;
	}

	public String getIndex() {
		return index;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "CPut [array=" + array + ", index=" + index + ", value=" + value
				+ "]";
	}

}
