package mincamlj.knormal;

import java.util.List;

public class KTuple implements KNormalExpr {

	private List<String> values;

	public KTuple(List<String> values) {
		super();
		this.values = values;
	}

	public List<String> getValues() {
		return values;
	}

	@Override
	public String toString() {
		return "KTuple [values=" + values + "]";
	}

}
