package mincamlj;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import mincamlj.knormal.KApp;
import mincamlj.knormal.KExtFunApp;
import mincamlj.knormal.KFunDef;
import mincamlj.knormal.KIfEq;
import mincamlj.knormal.KIfLe;
import mincamlj.knormal.KLet;
import mincamlj.knormal.KLetRec;
import mincamlj.knormal.KLetTuple;
import mincamlj.knormal.KNormalExpr;
import mincamlj.knormal.KPut;

public class Elim {

	public boolean sideEffect(KNormalExpr e) {
		if (e instanceof KLet) {
			KLet e1 = (KLet) e;
			return sideEffect(e1.getValue()) || sideEffect(e1.getBody());
		} else if (e instanceof KIfEq) {
			KIfEq e1 = (KIfEq) e;
			return sideEffect(e1.getTrueExpr())
					|| sideEffect(e1.getFalseExpr());
		} else if (e instanceof KIfLe) {
			KIfLe e1 = (KIfLe) e;
			return sideEffect(e1.getTrueExpr())
					|| sideEffect(e1.getFalseExpr());
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
			return sideEffect(e1.getBody());
		} else if (e instanceof KLetTuple) {
			KLetTuple e1 = (KLetTuple) e;
			return sideEffect(e1.getBody());
		} else if (e instanceof KApp) {
			return true;
		} else if (e instanceof KPut) {
			return true;
		} else if (e instanceof KExtFunApp) {
			return true;
		}
		return false;
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
			KNormalExpr value = transform(e1.getValue());
			KNormalExpr body = transform(e1.getBody());
			if (sideEffect(value)
					|| KNormal.freeVars(body).contains(e1.getVar().getLeft())) {
				return new KLet(e1.getVar(), value, body);
			} else {
				Log.getLogger().info(
						String.format("eliminating variable %s@.", e1.getVar()
								.getLeft()));
				return body;
			}
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
			KFunDef funDef = e1.getFunDef();
			KNormalExpr body = transform(e1.getBody());
			if (KNormal.freeVars(body).contains(funDef.getName().getLeft())) {
				return new KLetRec(new KFunDef(funDef.getName(),
						funDef.getParams(), transform(funDef.getBody())), body);
			} else {
				Log.getLogger().info(
						String.format("eliminating function %s@.", funDef
								.getName().getLeft()));
				return body;
			}
		} else if (e instanceof KLetTuple) {
			KLetTuple e1 = (KLetTuple) e;
			List<String> vars = e1.getVars().stream().map(v -> v.getLeft())
					.collect(Collectors.toList());
			KNormalExpr body = transform(e1.getBody());
			Set<String> fvs = KNormal.freeVars(body);
			boolean used = vars.stream().anyMatch(v -> fvs.contains(v));
			if (used) {
				return new KLetTuple(e1.getVars(), e1.getValue(), body);
			} else {
				Log.getLogger().info(
						String.format("eliminating variables %s@.", vars
								.stream().collect(Collectors.joining(", "))));
				return body;
			}
		}
		return e;
	}
}
