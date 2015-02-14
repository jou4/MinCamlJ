package mincamlj.syntax;

public class SLetRec implements SyntaxExpr {

	private SFunDef funDef;
	private SyntaxExpr body;

	public SLetRec(SFunDef funDef, SyntaxExpr body) {
		super();
		this.funDef = funDef;
		this.body = body;
	}

	public SFunDef getFunDef() {
		return funDef;
	}

	public SyntaxExpr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "SLetRec [funDef=" + funDef + ", body=" + body + "]";
	}

}
