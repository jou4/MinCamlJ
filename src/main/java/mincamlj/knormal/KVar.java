package mincamlj.knormal;

public class KVar implements KNormalExpr {

	private String name;

	public KVar(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "KVar [name=" + name + "]";
	}

}
