package mincamlj.type;

import java.util.List;

public class TupleType implements Type {

	private List<Type> inners;

	public TupleType(List<Type> inners) {
		super();
		this.inners = inners;
	}

	public List<Type> getInners() {
		return inners;
	}

	@Override
	public String toString() {
		return "TupleType [inners=" + inners + "]";
	}

}
