package mincamlj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mincamlj.knormal.KAdd;
import mincamlj.knormal.KFAdd;
import mincamlj.knormal.KFDiv;
import mincamlj.knormal.KFMul;
import mincamlj.knormal.KFNeg;
import mincamlj.knormal.KFSub;
import mincamlj.knormal.KFloat;
import mincamlj.knormal.KFunDef;
import mincamlj.knormal.KIfEq;
import mincamlj.knormal.KIfLe;
import mincamlj.knormal.KInt;
import mincamlj.knormal.KLet;
import mincamlj.knormal.KLetRec;
import mincamlj.knormal.KLetTuple;
import mincamlj.knormal.KNeg;
import mincamlj.knormal.KNormalExpr;
import mincamlj.knormal.KSub;
import mincamlj.knormal.KTuple;
import mincamlj.knormal.KVar;
import mincamlj.type.Type;
import mincamlj.util.Pair;

public class ConstFold {

	public boolean memi(String name, Map<String, KNormalExpr> env) {
		if (env.containsKey(name)) {
			KNormalExpr e = env.get(name);
			if (e instanceof KInt) {
				return true;
			}
		}
		return false;
	}

	public boolean memf(String name, Map<String, KNormalExpr> env) {
		if (env.containsKey(name)) {
			KNormalExpr e = env.get(name);
			if (e instanceof KFloat) {
				return true;
			}
		}
		return false;
	}

	public boolean memt(String name, Map<String, KNormalExpr> env) {
		if (env.containsKey(name)) {
			KNormalExpr e = env.get(name);
			if (e instanceof KTuple) {
				return true;
			}
		}
		return false;
	}

	public int findi(String name, Map<String, KNormalExpr> env) {
		if (env.containsKey(name)) {
			KNormalExpr e = env.get(name);
			if (e instanceof KInt) {
				return ((KInt) e).getValue();
			}
		}
		throw new RuntimeException("not found: " + name);
	}

	public float findf(String name, Map<String, KNormalExpr> env) {
		if (env.containsKey(name)) {
			KNormalExpr e = env.get(name);
			if (e instanceof KFloat) {
				return ((KFloat) e).getValue();
			}
		}
		throw new RuntimeException("not found: " + name);
	}

	public List<String> findt(String name, Map<String, KNormalExpr> env) {
		if (env.containsKey(name)) {
			KNormalExpr e = env.get(name);
			if (e instanceof KTuple) {
				return ((KTuple) e).getValues();
			}
		}
		throw new RuntimeException("not found: " + name);
	}

	public KNormalExpr transform(KNormalExpr e, Map<String, KNormalExpr> env) {
		if (e instanceof KVar) {
			KVar e1 = (KVar) e;
			if (memi(e1.getName(), env)) {
				return new KInt(findi(e1.getName(), env));
			}
		} else if (e instanceof KNeg) {
			KNeg e1 = (KNeg) e;
			if (memi(e1.getInner(), env)) {
				return new KInt(-findi(e1.getInner(), env));
			}
		} else if (e instanceof KAdd) {
			KAdd e1 = (KAdd) e;
			if (memi(e1.getLeft(), env) && memi(e1.getRight(), env)) {
				return new KInt(findi(e1.getLeft(), env)
						+ findi(e1.getRight(), env));
			}
		} else if (e instanceof KSub) {
			KSub e1 = (KSub) e;
			if (memi(e1.getLeft(), env) && memi(e1.getRight(), env)) {
				return new KInt(findi(e1.getLeft(), env)
						- findi(e1.getRight(), env));
			}
		} else if (e instanceof KFNeg) {
			KFNeg e1 = (KFNeg) e;
			if (memf(e1.getInner(), env)) {
				return new KFloat(-findf(e1.getInner(), env));
			}
		} else if (e instanceof KFAdd) {
			KFAdd e1 = (KFAdd) e;
			if (memf(e1.getLeft(), env) && memf(e1.getRight(), env)) {
				return new KFloat(findf(e1.getLeft(), env)
						+ findf(e1.getRight(), env));
			}
		} else if (e instanceof KFSub) {
			KFSub e1 = (KFSub) e;
			if (memf(e1.getLeft(), env) && memf(e1.getRight(), env)) {
				return new KFloat(findf(e1.getLeft(), env)
						- findf(e1.getRight(), env));
			}
		} else if (e instanceof KFMul) {
			KFMul e1 = (KFMul) e;
			if (memf(e1.getLeft(), env) && memf(e1.getRight(), env)) {
				return new KFloat(findf(e1.getLeft(), env)
						* findf(e1.getRight(), env));
			}
		} else if (e instanceof KFDiv) {
			KFDiv e1 = (KFDiv) e;
			if (memf(e1.getLeft(), env) && memf(e1.getRight(), env)) {
				return new KFloat(findf(e1.getLeft(), env)
						/ findf(e1.getRight(), env));
			}
		} else if (e instanceof KIfEq) {
			KIfEq e1 = (KIfEq) e;
			if (memi(e1.getLeft(), env) && memi(e1.getRight(), env)) {
				if (findi(e1.getLeft(), env) == findi(e1.getRight(), env)) {
					return transform(e1.getTrueExpr(), env);
				} else {
					return transform(e1.getFalseExpr(), env);
				}
			}
			if (memf(e1.getLeft(), env) && memf(e1.getRight(), env)) {
				if (findf(e1.getLeft(), env) == findf(e1.getRight(), env)) {
					return transform(e1.getTrueExpr(), env);
				} else {
					return transform(e1.getFalseExpr(), env);
				}
			}
			return new KIfEq(e1.getLeft(), e1.getRight(), transform(
					e1.getTrueExpr(), env), transform(e1.getFalseExpr(), env));
		} else if (e instanceof KIfLe) {
			KIfLe e1 = (KIfLe) e;
			if (memi(e1.getLeft(), env) && memi(e1.getRight(), env)) {
				if (findi(e1.getLeft(), env) <= findi(e1.getRight(), env)) {
					return transform(e1.getTrueExpr(), env);
				} else {
					return transform(e1.getFalseExpr(), env);
				}
			}
			if (memf(e1.getLeft(), env) && memf(e1.getRight(), env)) {
				if (findf(e1.getLeft(), env) <= findf(e1.getRight(), env)) {
					return transform(e1.getTrueExpr(), env);
				} else {
					return transform(e1.getFalseExpr(), env);
				}
			}
			return new KIfLe(e1.getLeft(), e1.getRight(), transform(
					e1.getTrueExpr(), env), transform(e1.getFalseExpr(), env));
		} else if (e instanceof KLet) {
			KLet e1 = (KLet) e;
			KNormalExpr value = transform(e1.getValue(), env);
			Map<String, KNormalExpr> newEnv = new HashMap<>(env);
			env.put(e1.getVar().getLeft(), value);
			KNormalExpr body = transform(e1.getBody(), newEnv);
			return new KLet(e1.getVar(), value, body);
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
			KFunDef funDef = e1.getFunDef();
			return new KLetRec(new KFunDef(funDef.getName(),
					funDef.getParams(), transform(funDef.getBody(), env)),
					transform(e1.getBody(), env));
		} else if (e instanceof KLetTuple) {
			KLetTuple e1 = (KLetTuple) e;
			if (memt(e1.getValue(), env)) {
				List<Pair<String, Type>> vars = e1.getVars();
				List<String> values = findt(e1.getValue(), env);
				KNormalExpr body = transform(e1.getBody(), env);
				KNormalExpr curr = body;
				for (int i = vars.size() - 1; i >= 0; i--) {
					curr = new KLet(vars.get(i), new KVar(values.get(i)), curr);
				}
				return curr;
			} else {
				return new KLetTuple(e1.getVars(), e1.getValue(), transform(
						e1.getBody(), env));
			}
		}
		return e;
	}

	public KNormalExpr transform(KNormalExpr e) {
		return transform(e, new HashMap<String, KNormalExpr>());
	}

}
