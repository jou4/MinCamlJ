package mincamlj;

import mincamlj.knormal.KFunDef;
import mincamlj.knormal.KIfEq;
import mincamlj.knormal.KIfLe;
import mincamlj.knormal.KLet;
import mincamlj.knormal.KLetRec;
import mincamlj.knormal.KLetTuple;
import mincamlj.knormal.KNormalExpr;

public class Assoc {

	public KNormalExpr insert(KLet let) {
		KNormalExpr e = transform(let.getValue());
		if (e instanceof KLet) {
			KLet e1 = (KLet) e;
			return new KLet(e1.getVar(), e1.getValue(), insert(new KLet(
					let.getVar(), e1.getBody(), let.getBody())));
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
			return new KLetRec(e1.getFunDef(), insert(new KLet(let.getVar(),
					e1.getBody(), let.getBody())));
		} else if (e instanceof KLetTuple) {
			KLetTuple e1 = (KLetTuple) e;
			return new KLetTuple(e1.getVars(), e1.getValue(), insert(new KLet(
					let.getVar(), e1.getBody(), let.getBody())));
		} else {
			return new KLet(let.getVar(), e, transform(let.getBody()));
		}
	}

	public KNormalExpr transform(KNormalExpr e) {
		if (e instanceof KIfEq) {
			KIfEq e1 = (KIfEq) e;
			return new KIfEq(e1.getLeft(), e1.getRight(),
					transform(e1.getTrueExpr()), transform(e1.getFalseExpr()));
		} else if (e instanceof KIfLe) {
			KIfLe e1 = (KIfLe) e;
			return new KIfLe(e1.getLeft(), e1.getRight(),
					transform(e1.getTrueExpr()), transform(e1.getFalseExpr()));
		} else if (e instanceof KLet) {
			KLet e1 = (KLet) e;
			return insert(e1);
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
			KFunDef funDef = e1.getFunDef();
			return new KLetRec(new KFunDef(funDef.getName(),
					funDef.getParams(), transform(funDef.getBody())),
					transform(e1.getBody()));
		} else if (e instanceof KLetTuple) {
			KLetTuple e1 = (KLetTuple) e;
			return new KLetTuple(e1.getVars(), e1.getValue(),
					transform(e1.getBody()));
		}
		return e;
	}
}
