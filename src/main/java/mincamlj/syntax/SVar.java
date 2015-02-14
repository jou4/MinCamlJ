package mincamlj.syntax;

public class SVar implements SyntaxExpr {

	private String name;

	public SVar(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "SVar [name=" + name + "]";
	}

}
