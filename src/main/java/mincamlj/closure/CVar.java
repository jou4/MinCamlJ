package mincamlj.closure;

public class CVar implements ClosureExpr {

	private String name;

	public CVar(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "CVar [name=" + name + "]";
	}

}
