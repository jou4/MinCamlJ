package mincamlj.knormal;

import java.util.List;

import mincamlj.type.Type;
import mincamlj.util.Pair;

public class KFunDef {

	private Pair<String, Type> name;
	private List<Pair<String, Type>> params;
	private KNormalExpr body;

	public KFunDef(Pair<String, Type> name, List<Pair<String, Type>> params,
			KNormalExpr body) {
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

	public KNormalExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "KFunDef [name=" + name + ", params=" + params + ", body="
				+ body + "]";
	}

}
