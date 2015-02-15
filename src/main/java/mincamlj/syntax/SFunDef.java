package mincamlj.syntax;

import java.util.List;

import mincamlj.type.Type;
import mincamlj.util.Pair;

public class SFunDef {

	private Pair<String, Type> name;
	private List<Pair<String, Type>> params;
	private SyntaxExpr body;

	public SFunDef(Pair<String, Type> name, List<Pair<String, Type>> params,
			SyntaxExpr body) {
		super();
		this.name = name;
		this.params = params;
		this.body = body;
	}

	public Pair<String, Type> getName() {
		return name;
	}

	public List<Pair<String, Type>> getParams() {
		return params;
	}

	public SyntaxExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "SFunDef [name=" + name + ", params=" + params + ", body="
				+ body + "]";
	}

}
