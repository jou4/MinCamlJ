package mincamlj.closure;

import java.util.List;

import mincamlj.Id;
import mincamlj.Id.Label;
import mincamlj.type.Type;
import mincamlj.util.Pair;

public class CFunDef {

	private Pair<Id.Label, Type> name;
	private List<Pair<String, Type>> params;
	private List<Pair<String, Type>> freeVars;
	private ClosureExpr body;

	public CFunDef(Pair<Label, Type> name, List<Pair<String, Type>> params,
			List<Pair<String, Type>> freeVars, ClosureExpr body) {
		super();
		this.name = name;
		this.params = params;
		this.freeVars = freeVars;
		this.body = body;
	}

	public Pair<Id.Label, Type> getName() {
		return name;
	}

	public List<Pair<String, Type>> getParams() {
		return params;
	}

	public List<Pair<String, Type>> getFreeVars() {
		return freeVars;
	}

	public ClosureExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "CFunDef [name=" + name + ", params=" + params + ", freeVars="
				+ freeVars + ", body=" + body + "]";
	}

}
