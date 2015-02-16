package mincamlj.closure;

import mincamlj.Id;
import mincamlj.Id.Label;

public class CExtArray implements ClosureExpr {

	private Id.Label array;

	public CExtArray(Label array) {
		super();
		this.array = array;
	}

	public Id.Label getArray() {
		return array;
	}

	@Override
	public String toString() {
		return "CExtArray [array=" + array + "]";
	}

}
