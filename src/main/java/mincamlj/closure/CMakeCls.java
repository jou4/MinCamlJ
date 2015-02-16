package mincamlj.closure;

import java.util.List;

import mincamlj.Id;
import mincamlj.Id.Label;
import mincamlj.type.Type;
import mincamlj.util.Pair;

public class CMakeCls implements ClosureExpr {

	private Pair<String, Type> name;
	private Id.Label entry;
	private List<String> freeVars;
	private ClosureExpr body;

	public CMakeCls(Pair<String, Type> name, Label entry,
			List<String> freeVars, ClosureExpr body) {
		super();
		this.name = name;
		this.entry = entry;
		this.freeVars = freeVars;
		this.body = body;
	}

	public Pair<String, Type> getName() {
		return name;
	}

	public Id.Label getEntry() {
		return entry;
	}

	public List<String> getFreeVars() {
		return freeVars;
	}

	public ClosureExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "CMakeCls [name=" + name + ", entry=" + entry + ", freeVars="
				+ freeVars + ", body=" + body + "]";
	}

}
