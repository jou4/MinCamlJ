package mincamlj;

import java.util.stream.Collectors;

import mincamlj.syntax.SAdd;
import mincamlj.syntax.SApp;
import mincamlj.syntax.SArray;
import mincamlj.syntax.SEq;
import mincamlj.syntax.SFAdd;
import mincamlj.syntax.SFDiv;
import mincamlj.syntax.SFMul;
import mincamlj.syntax.SFNeg;
import mincamlj.syntax.SFSub;
import mincamlj.syntax.SFunDef;
import mincamlj.syntax.SGet;
import mincamlj.syntax.SIf;
import mincamlj.syntax.SLe;
import mincamlj.syntax.SLet;
import mincamlj.syntax.SLetRec;
import mincamlj.syntax.SLetTuple;
import mincamlj.syntax.SNeg;
import mincamlj.syntax.SNot;
import mincamlj.syntax.SPut;
import mincamlj.syntax.SSub;
import mincamlj.syntax.STuple;
import mincamlj.syntax.SyntaxExpr;
import mincamlj.type.ArrayType;
import mincamlj.type.FunType;
import mincamlj.type.IntType;
import mincamlj.type.TupleType;
import mincamlj.type.Type;
import mincamlj.type.VarType;
import mincamlj.util.Pair;

public class Typing {

	public Type derefType(Type t) {
		if (t instanceof FunType) {
			FunType t1 = (FunType) t;
			return new FunType(t1.getParams().stream().map(t2 -> derefType(t2))
					.collect(Collectors.toList()), derefType(t1.getReturned()));
		} else if (t instanceof TupleType) {
			TupleType t1 = (TupleType) t;
			return new TupleType(t1.getInners().stream()
					.map(t2 -> derefType(t2)).collect(Collectors.toList()));
		} else if (t instanceof ArrayType) {
			ArrayType t1 = (ArrayType) t;
			return new ArrayType(derefType(t1.getInner()));
		} else if (t instanceof VarType) {
			VarType t1 = (VarType) t;
			if (t1.isNone()) {
				Log.getLogger()
						.info("uninstantiated type variable detected; assuming int@.");
				t1.setInner(IntType.getInstance());
				return t1.getInner();
			} else {
				t1.setInner(derefType(t1.getInner()));
				return t1.getInner();
			}
		}
		return t;
	}

	public Pair<String, Type> derefIdType(Pair<String, Type> p) {
		return new Pair<>(p.getLeft(), derefType(p.getRight()));
	}

	public SyntaxExpr derefTerm(SyntaxExpr e) {
		if (e instanceof SNot) {
			SNot e1 = (SNot) e;
			return new SNot(derefTerm(e1.getExpr()));
		} else if (e instanceof SNeg) {
			SNeg e1 = (SNeg) e;
			return new SNeg(derefTerm(e1.getExpr()));
		} else if (e instanceof SAdd) {
			SAdd e1 = (SAdd) e;
			return new SAdd(derefTerm(e1.getLeft()), derefTerm(e1.getRight()));
		} else if (e instanceof SSub) {
			SSub e1 = (SSub) e;
			return new SSub(derefTerm(e1.getLeft()), derefTerm(e1.getRight()));
		} else if (e instanceof SEq) {
			SEq e1 = (SEq) e;
			return new SEq(derefTerm(e1.getLeft()), derefTerm(e1.getRight()));
		} else if (e instanceof SLe) {
			SLe e1 = (SLe) e;
			return new SLe(derefTerm(e1.getLeft()), derefTerm(e1.getRight()));
		} else if (e instanceof SFNeg) {
			SFNeg e1 = (SFNeg) e;
			return new SFNeg(derefTerm(e1.getExpr()));
		} else if (e instanceof SFAdd) {
			SFAdd e1 = (SFAdd) e;
			return new SFAdd(derefTerm(e1.getLeft()), derefTerm(e1.getRight()));
		} else if (e instanceof SFSub) {
			SFSub e1 = (SFSub) e;
			return new SFSub(derefTerm(e1.getLeft()), derefTerm(e1.getRight()));
		} else if (e instanceof SFMul) {
			SFMul e1 = (SFMul) e;
			return new SFMul(derefTerm(e1.getLeft()), derefTerm(e1.getRight()));
		} else if (e instanceof SFDiv) {
			SFDiv e1 = (SFDiv) e;
			return new SFDiv(derefTerm(e1.getLeft()), derefTerm(e1.getRight()));
		} else if (e instanceof SIf) {
			SIf e1 = (SIf) e;
			return new SIf(derefTerm(e1.getPred()),
					derefTerm(e1.getTrueExpr()), derefTerm(e1.getFalseExpr()));
		} else if (e instanceof SLet) {
			SLet e1 = (SLet) e;
			return new SLet(derefIdType(e1.getVar()), derefTerm(e1.getValue()),
					derefTerm(e1.getBody()));
		} else if (e instanceof SLetRec) {
			SLetRec e1 = (SLetRec) e;
			SFunDef f = e1.getFunDef();
			return new SLetRec(new SFunDef(derefIdType(f.getName()), f
					.getParams().stream().map(p -> derefIdType(p))
					.collect(Collectors.toList()), derefTerm(f.getBody())),
					derefTerm(e1.getBody()));
		} else if (e instanceof SApp) {
			SApp e1 = (SApp) e;
			return new SApp(derefTerm(e1.getFunc()), e1.getArgs().stream()
					.map(e2 -> derefTerm(e2)).collect(Collectors.toList()));
		} else if (e instanceof STuple) {
			STuple e1 = (STuple) e;
			return new STuple(e1.getValues().stream().map(e2 -> derefTerm(e2))
					.collect(Collectors.toList()));
		} else if (e instanceof SLetTuple) {
			SLetTuple e1 = (SLetTuple) e;
			return new SLetTuple(e1.getVars().stream().map(p -> derefIdType(p))
					.collect(Collectors.toList()), derefTerm(e1.getValue()),
					derefTerm(e1.getBody()));
		} else if (e instanceof SArray) {
			SArray e1 = (SArray) e;
			return new SArray(derefTerm(e1.getLength()),
					derefTerm(e1.getInitial()));
		} else if (e instanceof SGet) {
			SGet e1 = (SGet) e;
			return new SGet(derefTerm(e1.getArray()), derefTerm(e1.getIndex()));
		} else if (e instanceof SPut) {
			SPut e1 = (SPut) e;
			return new SPut(derefTerm(e1.getArray()), derefTerm(e1.getIndex()),
					derefTerm(e1.getValue()));
		}
		return e;
	}

	public static class UnifyError extends Exception {

		private static final long serialVersionUID = 1L;
		private Pair<Type, Type> errorTypes;

		public UnifyError(Pair<Type, Type> errorTypes) {
			super();
			this.errorTypes = errorTypes;
		}

		public Pair<Type, Type> getErrorTypes() {
			return errorTypes;
		}

	}

	public boolean occur(Type t1, Type t2) {
		if (t2 instanceof FunType) {
			FunType t2_ = (FunType) t2;
			return t2_.getParams().stream().anyMatch(p -> occur(t1, p))
					|| occur(t1, t2_.getReturned());
		} else if (t2 instanceof TupleType) {
			TupleType t2_ = (TupleType) t2;
			return t2_.getInners().stream().anyMatch(i -> occur(t1, i));
		} else if (t2 instanceof ArrayType) {
			ArrayType t2_ = (ArrayType) t2;
			return occur(t1, t2_);
		} else if (t2 instanceof VarType) {
			VarType t2_ = (VarType) t2;
			if (t1 == t2_) {
				return true;
			}
			if (t2_.isNone()) {
				return false;
			} else {
				return occur(t1, t2_.getInner());
			}
		}
		return false;
	}

	public void unify(Type t1, Type t2) {

	}
}
