package mincamlj.syntax;

import java.util.List;

import mincamlj.type.Type;
import mincamlj.util.Pair;

public class SFunDef {

	private String name;
	private Type type;
	private List<Pair<String, Type>> params;
	private SyntaxExpr body;

	public SFunDef(String name, Type type, List<Pair<String, Type>> params,
			SyntaxExpr body) {
		super();
		this.name = name;
		this.type = type;
		this.params = params;
		this.body = body;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public List<Pair<String, Type>> getParams() {
		return params;
	}

	public SyntaxExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "SFunDef [name=" + name + ", type=" + type + ", params="
				+ params + ", body=" + body + "]";
	}

}
