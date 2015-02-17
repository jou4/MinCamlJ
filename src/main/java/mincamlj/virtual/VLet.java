package mincamlj.virtual;

import mincamlj.type.Type;
import mincamlj.util.Pair;
import mincamlj.virtual.inst.VirtualInst;

public class VLet implements VirtualExpr {

	private Pair<String, Type> var;
	private VirtualInst value;
	private VirtualExpr body;

	public VLet(Pair<String, Type> var, VirtualInst value, VirtualExpr body) {
		super();
		this.var = var;
		this.value = value;
		this.body = body;
	}

	public Pair<String, Type> getVar() {
		return var;
	}

	public VirtualInst getValue() {
		return value;
	}

	public VirtualExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "VLet [var=" + var + ", value=" + value + ", body=" + body + "]";
	}

}
