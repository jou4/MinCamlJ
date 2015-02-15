package mincamlj.type;

public class VarType implements Type {

	// mutable
	private Type inner;

	public VarType(Type inner) {
		super();
		this.inner = inner;
	}

	public Type getInner() {
		return inner;
	}

	public void setInner(Type inner) {
		this.inner = inner;
	}

	public static VarType genType() {
		return new VarType(VarType.None.getInstance());
	}

	@Override
	public String toString() {
		return "VarType [inner=" + inner + "]";
	}

	public boolean isNone() {
		return inner == VarType.None.getInstance();
	}

	public static class None implements Type {

		private static final None t = new None();

		public static None getInstance() {
			return t;
		}

		@Override
		public String toString() {
			return "None []";
		}

	}

}
