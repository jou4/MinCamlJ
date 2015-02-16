package mincamlj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mincamlj.closure.CFunDef;
import mincamlj.closure.CProg;
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

public class Closure {

	private List<CFunDef> toplevel;

	public Set<String> freeVars(ClosureExpr e) {
		// TODO
		return null;
	}

	public ClosureExpr transformExpr(KNormalExpr e, Map<String, Type> env,
			Set<String> known) {
		if (e instanceof KUnit) {

		} else if (e instanceof KInt) {
			KInt e1 = (KInt) e;
		} else if (e instanceof KFloat) {
			KFloat e1 = (KFloat) e;
		} else if (e instanceof KNeg) {
			KNeg e1 = (KNeg) e;
		} else if (e instanceof KAdd) {
			KAdd e1 = (KAdd) e;
		} else if (e instanceof KSub) {
			KSub e1 = (KSub) e;
		} else if (e instanceof KFNeg) {
			KFNeg e1 = (KFNeg) e;
		} else if (e instanceof KFAdd) {
			KFAdd e1 = (KFAdd) e;
		} else if (e instanceof KFSub) {
			KFSub e1 = (KFSub) e;
		} else if (e instanceof KFMul) {
			KFMul e1 = (KFMul) e;
		} else if (e instanceof KFDiv) {
			KFDiv e1 = (KFDiv) e;
		} else if (e instanceof KIfEq) {
			KIfEq e1 = (KIfEq) e;
		} else if (e instanceof KIfLe) {
			KIfLe e1 = (KIfLe) e;
		} else if (e instanceof KLet) {
			KLet e1 = (KLet) e;
		} else if (e instanceof KVar) {
			KVar e1 = (KVar) e;
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
		} else if (e instanceof KApp) {
			KApp e1 = (KApp) e;
		} else if (e instanceof KTuple) {
			KTuple e1 = (KTuple) e;
		} else if (e instanceof KLetTuple) {
			KLetTuple e1 = (KLetTuple) e;
		} else if (e instanceof KGet) {
			KGet e1 = (KGet) e;
		} else if (e instanceof KPut) {
			KPut e1 = (KPut) e;
		} else if (e instanceof KExtArray) {
			KExtArray e1 = (KExtArray) e;
		} else if (e instanceof KExtFunApp) {
			KExtFunApp e1 = (KExtFunApp) e;

		}

		throw new RuntimeException("unknown expression: " + e);
	}

	public CProg transform(KNormalExpr e) {
		toplevel = new ArrayList<CFunDef>();
		ClosureExpr e1 = transformExpr(e, new HashMap<>(), new HashSet<>());
		return new CProg(toplevel, e1);
	}

}
