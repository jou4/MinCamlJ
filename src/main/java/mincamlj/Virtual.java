package mincamlj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
import mincamlj.type.FloatType;
import mincamlj.type.Type;
import mincamlj.type.UnitType;
import mincamlj.virtual.VAns;
import mincamlj.virtual.VFunDef;
import mincamlj.virtual.VProg;
import mincamlj.virtual.VRet;
import mincamlj.virtual.VirtualExpr;
import mincamlj.virtual.inst.VAdd;
import mincamlj.virtual.inst.VFAdd;
import mincamlj.virtual.inst.VFConst;
import mincamlj.virtual.inst.VFDiv;
import mincamlj.virtual.inst.VFMul;
import mincamlj.virtual.inst.VFNeg;
import mincamlj.virtual.inst.VFSub;
import mincamlj.virtual.inst.VIConst;
import mincamlj.virtual.inst.VIfEq;
import mincamlj.virtual.inst.VIfLe;
import mincamlj.virtual.inst.VNeg;
import mincamlj.virtual.inst.VNop;
import mincamlj.virtual.inst.VSub;
import mincamlj.virtual.inst.VVar;
import mincamlj.virtual.inst.VirtualInst;

public class Virtual {

	public VirtualExpr transformExpr(ClosureExpr e) {
		return transformExpr(e, new HashMap<String, Type>(), i -> new VRet(i));
	}

	public VirtualExpr transformExpr(ClosureExpr e, Map<String, Type> env,
			Function<VirtualInst, VirtualExpr> cont) {
		if (e instanceof CUnit) {
			return cont.apply(VNop.getInstance());
		} else if (e instanceof CInt) {
			CInt e1 = (CInt) e;
			return cont.apply(new VIConst(e1.getValue()));
		} else if (e instanceof CFloat) {
			CFloat e1 = (CFloat) e;
			return cont.apply(new VFConst(e1.getValue()));
		} else if (e instanceof CNeg) {
			CNeg e1 = (CNeg) e;
			return cont.apply(new VNeg(e1.getInner()));
		} else if (e instanceof CAdd) {
			CAdd e1 = (CAdd) e;
			return cont.apply(new VAdd(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CSub) {
			CSub e1 = (CSub) e;
			return cont.apply(new VSub(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CFNeg) {
			CFNeg e1 = (CFNeg) e;
			return cont.apply(new VFNeg(e1.getInner()));
		} else if (e instanceof CFAdd) {
			CFAdd e1 = (CFAdd) e;
			return cont.apply(new VFAdd(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CFSub) {
			CFSub e1 = (CFSub) e;
			return cont.apply(new VFSub(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CFMul) {
			CFMul e1 = (CFMul) e;
			return cont.apply(new VFMul(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CFDiv) {
			CFDiv e1 = (CFDiv) e;
			return cont.apply(new VFDiv(e1.getLeft(), e1.getRight()));
		} else if (e instanceof CIfEq) {
			CIfEq e1 = (CIfEq) e;
			return new VAns(new VIfEq(e1.getLeft(), e1.getRight(),
					transformExpr(e1.getTrueExpr(), env, cont), transformExpr(
							e1.getFalseExpr(), env, cont)));
		} else if (e instanceof CIfLe) {
			CIfLe e1 = (CIfLe) e;
			return new VAns(new VIfLe(e1.getLeft(), e1.getRight(),
					transformExpr(e1.getTrueExpr(), env, cont), transformExpr(
							e1.getFalseExpr(), env, cont)));
		} else if (e instanceof CVar) {
			CVar e1 = (CVar) e;
			return cont.apply(new VVar(e1.getName()));
		} else if (e instanceof CLet) {
			CLet e1 = (CLet) e;

		} else if (e instanceof CTuple) {
			CTuple e1 = (CTuple) e;
			//return cont.apply(new VTu)
		} else if (e instanceof CLetTuple) {
			CLetTuple e1 = (CLetTuple) e;
		} else if (e instanceof CAppDir) {
			CAppDir e1 = (CAppDir) e;
		} else if (e instanceof CAppCls) {
			CAppCls e1 = (CAppCls) e;
		} else if (e instanceof CMakeCls) {
			CMakeCls e1 = (CMakeCls) e;
		} else if (e instanceof CGet) {
			CGet e1 = (CGet) e;
		} else if (e instanceof CPut) {
			CPut e1 = (CPut) e;
		} else if (e instanceof CExtArray) {
			CExtArray e1 = (CExtArray) e;
		}

		return null;
	}

	public List<VFunDef> transformFunDef(CFunDef funDef) {
		// TODO
		return null;
	}

	public VProg transform(CProg p) {
		List<VFunDef> funDefs = p.getFunDefs().stream()
				.flatMap(f -> transformFunDef(f).stream())
				.collect(Collectors.toList());
		VirtualExpr expr = transformExpr(p.getExpr());
		return new VProg(funDefs, expr);
	}

}
