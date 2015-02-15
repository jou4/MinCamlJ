package mincamlj.knormal;

public class KLetRec implements KNormalExpr {

	private KFunDef funDef;
	private KNormalExpr body;

	public KLetRec(KFunDef funDef, KNormalExpr body) {
		super();
		this.funDef = funDef;
		this.body = body;
	}

	public KFunDef getFunDef() {
		return funDef;
	}

	public KNormalExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "KLetRec [funDef=" + funDef + ", body=" + body + "]";
	}

}
