package mincamlj.type;

import java.util.List;

public class FunType implements Type {

	private List<Type> params;
	private Type returned;

	public FunType(List<Type> params, Type returned) {
		super();
		this.params = params;
		this.returned = returned;
	}

	public List<Type> getParams() {
		return params;
	}

	public Type getReturned() {
		return returned;
	}

	@Override
	public String toString() {
		return "FunType [params=" + params + ", returned=" + returned + "]";
	}

}
