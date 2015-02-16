package mincamlj.closure;

import java.util.List;

public class CTuple implements ClosureExpr {

	private List<String> values;

	public CTuple(List<String> values) {
		super();
		this.values = values;
	}

	public List<String> getValues() {
		return values;
	}

	@Override
	public String toString() {
		return "CTuple [values=" + values + "]";
	}

}
