package mincamlj.virtual;

import java.util.List;

import mincamlj.Id;
import mincamlj.Id.Label;
import mincamlj.type.Type;
import mincamlj.util.Pair;

public class VFunDef {

	private Pair<Id.Label, Type> name;
	private List<Pair<String, Type>> params;
	private VirtualExpr body;
	private Type returned;

	public VFunDef(Pair<Label, Type> name, List<Pair<String, Type>> params,
			VirtualExpr body, Type returned) {
		super();
		this.name = name;
		this.params = params;
		this.body = body;
		this.returned = returned;
	}

	public Pair<Id.Label, Type> getName() {
		return name;
	}

	public List<Pair<String, Type>> getParams() {
		return params;
	}

	public VirtualExpr getBody() {
		return body;
	}

	public Type getReturned() {
		return returned;
	}

	@Override
	public String toString() {
		return "VFunDef [name=" + name + ", params=" + params + ", body="
				+ body + ", returned=" + returned + "]";
	}

}
