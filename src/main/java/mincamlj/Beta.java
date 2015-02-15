package mincamlj;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import mincamlj.knormal.KAdd;
import mincamlj.knormal.KApp;
import mincamlj.knormal.KExtArray;
import mincamlj.knormal.KExtFunApp;
import mincamlj.knormal.KFAdd;
import mincamlj.knormal.KFDiv;
import mincamlj.knormal.KFMul;
import mincamlj.knormal.KFNeg;
import mincamlj.knormal.KFSub;
import mincamlj.knormal.KFloat;
import mincamlj.knormal.KFunDef;
import mincamlj.knormal.KGet;
import mincamlj.knormal.KIfEq;
import mincamlj.knormal.KIfLe;
import mincamlj.knormal.KInt;
import mincamlj.knormal.KLet;
import mincamlj.knormal.KLetRec;
import mincamlj.knormal.KLetTuple;
import mincamlj.knormal.KNeg;
import mincamlj.knormal.KNormalExpr;
import mincamlj.knormal.KPut;
import mincamlj.knormal.KSub;
import mincamlj.knormal.KTuple;
import mincamlj.knormal.KUnit;
import mincamlj.knormal.KVar;

public class Beta {

	public String find(String name, Map<String, String> env) {
		return (env.containsKey(name)) ? env.get(name) : name;
	}

	public KNormalExpr transform(KNormalExpr e, Map<String, String> env) {
		if (e instanceof KUnit) {
			return e;
		} else if (e instanceof KInt) {
			return e;
		} else if (e instanceof KFloat) {
			return e;
		} else if (e instanceof KNeg) {
			KNeg e1 = (KNeg) e;
			return new KNeg(find(e1.getInner(), env));
		} else if (e instanceof KAdd) {
			KAdd e1 = (KAdd) e;
			return new KAdd(find(e1.getLeft(), env), find(e1.getRight(), env));
		} else if (e instanceof KSub) {
			KSub e1 = (KSub) e;
			return new KSub(find(e1.getLeft(), env), find(e1.getRight(), env));
		} else if (e instanceof KFNeg) {
			KFNeg e1 = (KFNeg) e;
			return new KFNeg(find(e1.getInner(), env));
		} else if (e instanceof KFAdd) {
			KFAdd e1 = (KFAdd) e;
			return new KFAdd(find(e1.getLeft(), env), find(e1.getRight(), env));
		} else if (e instanceof KFSub) {
			KFSub e1 = (KFSub) e;
			return new KFSub(find(e1.getLeft(), env), find(e1.getRight(), env));
		} else if (e instanceof KFMul) {
			KFMul e1 = (KFMul) e;
			return new KFMul(find(e1.getLeft(), env), find(e1.getRight(), env));
		} else if (e instanceof KFDiv) {
			KFDiv e1 = (KFDiv) e;
			return new KFDiv(find(e1.getLeft(), env), find(e1.getRight(), env));
		} else if (e instanceof KIfEq) {
			KIfEq e1 = (KIfEq) e;
			return new KIfEq(find(e1.getLeft(), env), find(e1.getRight(), env),
					transform(e1.getTrueExpr(), env), transform(
							e1.getFalseExpr(), env));
		} else if (e instanceof KIfLe) {
			KIfLe e1 = (KIfLe) e;
			return new KIfLe(find(e1.getLeft(), env), find(e1.getRight(), env),
					transform(e1.getTrueExpr(), env), transform(
							e1.getFalseExpr(), env));
		} else if (e instanceof KLet) {
			KLet e1 = (KLet) e;
			KNormalExpr val = transform(e1.getValue(), env);
			if (val instanceof KVar) {
				KVar val_ = (KVar) val;
				Log.getLogger().info(
						String.format("beta-reducing %s = %s@.", e1.getVar()
								.getLeft(), val_.getName()));
				Map<String, String> newEnv = new HashMap<>(env);
				newEnv.put(e1.getVar().getLeft(), val_.getName());
				return transform(e1.getBody(), newEnv);
			} else {
				return new KLet(e1.getVar(), val, transform(e1.getBody(), env));
			}
		} else if (e instanceof KVar) {
			KVar e1 = (KVar) e;
			return new KVar(find(e1.getName(), env));
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
			KFunDef funDef = e1.getFunDef();
			return new KLetRec(new KFunDef(funDef.getName(),
					funDef.getParams(), transform(funDef.getBody(), env)),
					transform(e1.getBody(), env));
		} else if (e instanceof KApp) {
			KApp e1 = (KApp) e;
			return new KApp(find(e1.getFunc(), env), e1.getArgs().stream()
					.map(a -> find(a, env)).collect(Collectors.toList()));
		} else if (e instanceof KTuple) {
			KTuple e1 = (KTuple) e;
			return new KTuple(e1.getValues().stream().map(v -> find(v, env))
					.collect(Collectors.toList()));
		} else if (e instanceof KLetTuple) {
			KLetTuple e1 = (KLetTuple) e;
			return new KLetTuple(e1.getVars(), find(e1.getValue(), env),
					transform(e1.getBody(), env));
		} else if (e instanceof KGet) {
			KGet e1 = (KGet) e;
			return new KGet(find(e1.getArray(), env), find(e1.getIndex(), env));
		} else if (e instanceof KPut) {
			KPut e1 = (KPut) e;
			return new KPut(find(e1.getArray(), env), find(e1.getIndex(), env),
					find(e1.getValue(), env));
		} else if (e instanceof KExtArray) {
			return e;
		} else if (e instanceof KExtFunApp) {
			KExtFunApp e1 = (KExtFunApp) e;
			return new KExtFunApp(e1.getFunc(), e1.getArgs().stream()
					.map(a -> find(a, env)).collect(Collectors.toList()));
		}

		throw new RuntimeException("unknown expression: " + e);
	}

	public KNormalExpr transform(KNormalExpr e) {
		return transform(e, new HashMap<String, String>());
	}

}
