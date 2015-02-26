package mincamlj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import mincamlj.closure.CAdd;
import mincamlj.closure.CAppCls;
import mincamlj.closure.CAppDir;
import mincamlj.closure.CExtArray;
import mincamlj.closure.CFAdd;
import mincamlj.closure.CFDiv;
import mincamlj.closure.CFMul;
import mincamlj.closure.CFNeg;
import mincamlj.closure.CFSub;
import mincamlj.closure.CFloat;
import mincamlj.closure.CFunDef;
import mincamlj.closure.CGet;
import mincamlj.closure.CIfEq;
import mincamlj.closure.CIfLe;
import mincamlj.closure.CInt;
import mincamlj.closure.CLet;
import mincamlj.closure.CLetTuple;
import mincamlj.closure.CMakeCls;
import mincamlj.closure.CNeg;
import mincamlj.closure.CProg;
import mincamlj.closure.CPut;
import mincamlj.closure.CSub;
import mincamlj.closure.CTuple;
import mincamlj.closure.CUnit;
import mincamlj.closure.CVar;
import mincamlj.closure.ClosureExpr;
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

public class Closure {

	private List<CFunDef> toplevel;

	public Set<String> freeVars(ClosureExpr e) {
		if (e instanceof CUnit) {
			return new HashSet<>();
		} else if (e instanceof CInt) {
			return new HashSet<>();
		} else if (e instanceof CFloat) {
			return new HashSet<>();
		} else if (e instanceof CExtArray) {
			return new HashSet<>();
		} else if (e instanceof CNeg) {
			CNeg e1 = (CNeg) e;
			return new HashSet<>(Arrays.asList(e1.getInner()));
		} else if (e instanceof CFNeg) {
			CFNeg e1 = (CFNeg) e;
			return new HashSet<>(Arrays.asList(e1.getInner()));
		} else if (e instanceof CAdd) {
			CAdd e1 = (CAdd) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CSub) {
			CSub e1 = (CSub) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CFAdd) {
			CFAdd e1 = (CFAdd) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CFSub) {
			CFSub e1 = (CFSub) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CFMul) {
			CFMul e1 = (CFMul) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CFDiv) {
			CFDiv e1 = (CFDiv) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CGet) {
			CGet e1 = (CGet) e;
			return new HashSet<>(Arrays.asList(e1.getArray(), e1.getIndex()));
		} else if (e instanceof CLet) {
			CLet e1 = (CLet) e;
			Set<String> fvs = new HashSet<>();
			fvs.addAll(freeVars(e1.getBody()));
			fvs.remove(e1.getVar().getLeft());
			fvs.addAll(freeVars(e1.getValue()));
			return fvs;
		} else if (e instanceof CVar) {
			CVar e1 = (CVar) e;
			return new HashSet<>(Arrays.asList(e1.getName()));
		} else if (e instanceof CMakeCls) {
			CMakeCls e1 = (CMakeCls) e;
			Set<String> fvs = new HashSet<>(e1.getFreeVars());
			fvs.addAll(freeVars(e1.getBody()));
			fvs.remove(e1.getName().getLeft());
			return fvs;
		} else if (e instanceof CAppCls) {
			CAppCls e1 = (CAppCls) e;
			Set<String> fvs = new HashSet<>(Arrays.asList(e1.getFunc()));
			fvs.addAll(e1.getArgs());
			return fvs;
		} else if (e instanceof CAppDir) {
			CAppDir e1 = (CAppDir) e;
			return new HashSet<>(e1.getArgs());
		} else if (e instanceof CTuple) {
			CTuple e1 = (CTuple) e;
			return new HashSet<>(e1.getValues());
		} else if (e instanceof CLetTuple) {
			CLetTuple e1 = (CLetTuple) e;
			Set<String> fvs = freeVars(e1.getBody());
			fvs.removeAll(e1.getVars().stream().map(v -> v.getLeft())
					.collect(Collectors.toList()));
			fvs.add(e1.getValue());
			return fvs;
		} else if (e instanceof CPut) {
			CPut e1 = (CPut) e;
			return new HashSet<>(Arrays.asList(e1.getArray(), e1.getIndex(),
					e1.getValue()));
		}
		return new HashSet<>();
	}

	public ClosureExpr transformExpr(KNormalExpr e, Map<String, Type> env,
			Set<String> known) {
		if (e instanceof KUnit) {
			return CUnit.getInstance();
		} else if (e instanceof KInt) {
			KInt e1 = (KInt) e;
			return new CInt(e1.getValue());
		} else if (e instanceof KFloat) {
			KFloat e1 = (KFloat) e;
			return new CFloat(e1.getValue());
		} else if (e instanceof KNeg) {
			KNeg e1 = (KNeg) e;
			return new CNeg(e1.getInner());
		} else if (e instanceof KAdd) {
			KAdd e1 = (KAdd) e;
			return new CAdd(e1.getLeft(), e1.getRight());
		} else if (e instanceof KSub) {
			KSub e1 = (KSub) e;
			return new CSub(e1.getLeft(), e1.getRight());
		} else if (e instanceof KFNeg) {
			KFNeg e1 = (KFNeg) e;
			return new CFNeg(e1.getInner());
		} else if (e instanceof KFAdd) {
			KFAdd e1 = (KFAdd) e;
			return new CFAdd(e1.getLeft(), e1.getRight());
		} else if (e instanceof KFSub) {
			KFSub e1 = (KFSub) e;
			return new CFSub(e1.getLeft(), e1.getRight());
		} else if (e instanceof KFMul) {
			KFMul e1 = (KFMul) e;
			return new CFMul(e1.getLeft(), e1.getRight());
		} else if (e instanceof KFDiv) {
			KFDiv e1 = (KFDiv) e;
			return new CFDiv(e1.getLeft(), e1.getRight());
		} else if (e instanceof KIfEq) {
			KIfEq e1 = (KIfEq) e;
			return new CIfEq(e1.getLeft(), e1.getRight(), transformExpr(
					e1.getTrueExpr(), env, known), transformExpr(
					e1.getFalseExpr(), env, known));
		} else if (e instanceof KIfLe) {
			KIfLe e1 = (KIfLe) e;
			return new CIfLe(e1.getLeft(), e1.getRight(), transformExpr(
					e1.getTrueExpr(), env, known), transformExpr(
					e1.getFalseExpr(), env, known));
		} else if (e instanceof KLet) {
			KLet e1 = (KLet) e;
			Map<String, Type> newEnv = new HashMap<>(env);
			newEnv.put(e1.getVar().getLeft(), e1.getVar().getRight());
			return new CLet(e1.getVar(), transformExpr(e1.getValue(), env,
					known), transformExpr(e1.getBody(), newEnv, known));
		} else if (e instanceof KVar) {
			KVar e1 = (KVar) e;
			return new CVar(e1.getName());
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
			KFunDef funDef = e1.getFunDef();
			List<CFunDef> toplevelBackup = new ArrayList<>(toplevel);
			Map<String, Type> newEnv = new HashMap<>(env);
			newEnv.put(funDef.getName().getLeft(), funDef.getName().getRight());
			Set<String> newKnown = new HashSet<>(known);
			newKnown.add(funDef.getName().getLeft());
			Map<String, Type> newEnv2 = new HashMap<>(newEnv);
			funDef.getParams().forEach(
					p -> newEnv2.put(p.getLeft(), p.getRight()));
			ClosureExpr funDefBody = transformExpr(funDef.getBody(), newEnv2,
					newKnown);
			Set<String> fvs = freeVars(funDefBody);
			fvs.removeAll(funDef.getParams().stream().map(p -> p.getLeft())
					.collect(Collectors.toSet()));
			if (!fvs.isEmpty()) {
				Log.getLogger().info(
						String.format(
								"free variable(s) %s found in function %s@.",
								fvs.stream().collect(Collectors.joining(", ")),
										funDef.getName().getLeft()));
				Log.getLogger()
						.info(String
								.format("function %s cannot be directly applied in fact@.",
										funDef.getName().getLeft()));
				toplevel = toplevelBackup;
				funDefBody = transformExpr(funDef.getBody(), newEnv2, known);
				newKnown = known;
			}
			Set<String> fvs2 = freeVars(funDefBody);
			fvs2.remove(funDef.getName().getLeft());
			fvs2.removeAll(funDef.getParams().stream().map(p -> p.getLeft())
					.collect(Collectors.toSet()));
			List<Pair<String, Type>> fvPairs = fvs2.stream()
					.map(fv -> new Pair<String, Type>(fv, newEnv.get(fv)))
					.collect(Collectors.toList());
			toplevel.add(new CFunDef(new Pair<Id.Label, Type>(new Id.Label(
					funDef.getName().getLeft()), funDef.getName().getRight()),
					funDef.getParams(), fvPairs, funDefBody));
			ClosureExpr body = transformExpr(e1.getBody(), newEnv, newKnown);
			if (freeVars(body).contains(funDef.getName().getLeft())) {
				return new CMakeCls(funDef.getName(), new Id.Label(funDef
						.getName().getLeft()), fvPairs.stream()
						.map(p -> p.getLeft()).collect(Collectors.toList()),
						body);
			} else {
				Log.getLogger().info(
						String.format("eliminating closure(s) %s@.", funDef
								.getName().getLeft()));
				return body;
			}

		} else if (e instanceof KApp) {
			KApp e1 = (KApp) e;
			if (known.contains(e1.getFunc())) {
				Log.getLogger().info(
						String.format("directly applying %s@.", e1.getFunc()));
				return new CAppDir(new Id.Label(e1.getFunc()), e1.getArgs());
			} else {
				return new CAppCls(e1.getFunc(), e1.getArgs());
			}
		} else if (e instanceof KTuple) {
			KTuple e1 = (KTuple) e;
			return new CTuple(e1.getValues());
		} else if (e instanceof KLetTuple) {
			KLetTuple e1 = (KLetTuple) e;
			Map<String, Type> newEnv = new HashMap<>(env);
			e1.getVars().forEach(v -> newEnv.put(v.getLeft(), v.getRight()));
			return new CLetTuple(e1.getVars(), e1.getValue(), transformExpr(
					e1.getBody(), newEnv, known));
		} else if (e instanceof KGet) {
			KGet e1 = (KGet) e;
			return new CGet(e1.getArray(), e1.getIndex());
		} else if (e instanceof KPut) {
			KPut e1 = (KPut) e;
			return new CPut(e1.getArray(), e1.getIndex(), e1.getValue());
		} else if (e instanceof KExtArray) {
			KExtArray e1 = (KExtArray) e;
			return new CExtArray(new Id.Label(e1.getArray()));
		} else if (e instanceof KExtFunApp) {
			KExtFunApp e1 = (KExtFunApp) e;
			return new CAppDir(new Id.Label("min_caml_" + e1.getFunc()),
					e1.getArgs());
		}
		throw new RuntimeException("unknown expression: " + e);
	}

	public CProg transform(KNormalExpr e) {
		toplevel = new ArrayList<CFunDef>();
		ClosureExpr e1 = transformExpr(e, new HashMap<>(), new HashSet<>());
		return new CProg(toplevel, e1);
	}

}
