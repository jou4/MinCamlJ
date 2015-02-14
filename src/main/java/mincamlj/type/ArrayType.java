package mincamlj.type;

public class ArrayType implements Type {

	private Type inner;

	public ArrayType(Type inner) {
		super();
		this.inner = inner;
	}

	public Type getInner() {
		return inner;
	}

	@Override
	public String toString() {
		return "ArrayType [inner=" + inner + "]";
	}

}
