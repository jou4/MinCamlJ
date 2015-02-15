package mincamlj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mincamlj.knormal.KApp;
import mincamlj.knormal.KFunDef;
import mincamlj.knormal.KIfEq;
import mincamlj.knormal.KIfLe;
import mincamlj.knormal.KLet;
import mincamlj.knormal.KLetRec;
import mincamlj.knormal.KLetTuple;
import mincamlj.knormal.KNormalExpr;
import mincamlj.type.Type;
import mincamlj.util.Pair;

public class Inline {

	private int threshold = 10;

	public Inline() {
		super();
	}

	public Inline(int threshold) {
		super();
		this.threshold = threshold;
	}

	public int size(KNormalExpr e) {
		if (e instanceof KIfEq) {
			KIfEq e1 = (KIfEq) e;
			return 1 + size(e1.getTrueExpr()) + size(e1.getFalseExpr());
		} else if (e instanceof KIfLe) {
			KIfLe e1 = (KIfLe) e;
			return 1 + size(e1.getTrueExpr()) + size(e1.getFalseExpr());
		} else if (e instanceof KLet) {
			KLet e1 = (KLet) e;
			return 1 + size(e1.getValue()) + size(e1.getBody());
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
			return 1 + size(e1.getFunDef().getBody()) + size(e1.getBody());
		} else if (e instanceof KLetTuple) {
			KLetTuple e1 = (KLetTuple) e;
			return 1 + size(e1.getBody());
		}
		return 1;
	}

	public KNormalExpr transform(KNormalExpr e,
			Map<String, Pair<List<Pair<String, Type>>, KNormalExpr>> env) {
		if (e instanceof KIfEq) {
			KIfEq e1 = (KIfEq) e;
			return new KIfEq(e1.getLeft(), e1.getRight(), transform(
					e1.getTrueExpr(), env), transform(e1.getFalseExpr(), env));
		} else if (e instanceof KIfLe) {
			KIfLe e1 = (KIfLe) e;
			return new KIfLe(e1.getLeft(), e1.getRight(), transform(
					e1.getTrueExpr(), env), transform(e1.getFalseExpr(), env));
		} else if (e instanceof KLet) {
			KLet e1 = (KLet) e;
			return new KLet(e1.getVar(), transform(e1.getValue(), env),
					transform(e1.getBody(), env));
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
			KFunDef funDef = e1.getFunDef();
			Map<String, Pair<List<Pair<String, Type>>, KNormalExpr>> newEnv = new HashMap<>(
					env);
			if (size(funDef.getBody()) <= threshold) {
				newEnv.put(
						funDef.getName().getLeft(),
						new Pair<List<Pair<String, Type>>, KNormalExpr>(funDef
								.getParams(), funDef.getBody()));
			}
			return new KLetRec(new KFunDef(funDef.getName(),
					funDef.getParams(), transform(funDef.getBody(), newEnv)),
					transform(e1.getBody(), newEnv));
		} else if (e instanceof KApp) {
			KApp e1 = (KApp) e;
			if (env.containsKey(e1.getFunc())) {
				Log.getLogger().info(
						String.format("inlining %s@.", e1.getFunc()));
				Pair<List<Pair<String, Type>>, KNormalExpr> pair = env.get(e1
						.getFunc());
				Map<String, String> replaceEnv = new HashMap<>();
				for (int i = 0; i < pair.getLeft().size(); i++) {
					String param = pair.getLeft().get(i).getLeft();
					String arg = e1.getArgs().get(i);
					replaceEnv.put(param, arg);
				}
				return new Alpha().transform(pair.getRight(), replaceEnv);
			}
		} else if (e instanceof KLetTuple) {
			KLetTuple e1 = (KLetTuple) e;
			return new KLetTuple(e1.getVars(), e1.getValue(), transform(
					e1.getBody(), env));
		}
		return e;
	}

	public KNormalExpr transform(KNormalExpr e) {
		return transform(
				e,
				new HashMap<String, Pair<List<Pair<String, Type>>, KNormalExpr>>());
	}
}
