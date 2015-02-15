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
import mincamlj.type.Type;
import mincamlj.util.Pair;

public class Alpha {

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
			String x = Id.genId(e1.getVar().getLeft());
			Map<String, String> newEnv = new HashMap<>(env);
			newEnv.put(e1.getVar().getLeft(), x);
			return new KLet(new Pair<String, Type>(x, e1.getVar().getRight()),
					transform(e1.getValue(), env), transform(e1.getBody(),
							newEnv));
		} else if (e instanceof KVar) {
			KVar e1 = (KVar) e;
			return new KVar(find(e1.getName(), env));
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
			KFunDef funDef = e1.getFunDef();
			String x = Id.genId(funDef.getName().getLeft());
			Map<String, String> newEnv = new HashMap<>(env);
			newEnv.put(funDef.getName().getLeft(), x);
			Map<String, String> newEnv2 = new HashMap<>(newEnv);
			funDef.getParams().forEach(
					p -> newEnv2.put(p.getLeft(), Id.genId(p.getLeft())));
			return new KLetRec(new KFunDef(new Pair<String, Type>(find(funDef
					.getName().getLeft(), env), funDef.getName().getRight()),
					funDef.getParams()
							.stream()
							.map(p -> new Pair<>(find(p.getLeft(), newEnv2), p
									.getRight())).collect(Collectors.toList()),
					transform(funDef.getBody(), newEnv2)), transform(
					e1.getBody(), newEnv));
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
			Map<String, String> newEnv = new HashMap<>(env);
			e1.getVars().forEach(
					v -> newEnv.put(v.getLeft(), Id.genId(v.getLeft())));
			return new KLetTuple(e1
					.getVars()
					.stream()
					.map(v -> new Pair<>(find(v.getLeft(), newEnv), v
							.getRight())).collect(Collectors.toList()), find(
					e1.getValue(), env), transform(e1.getBody(), newEnv));
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
