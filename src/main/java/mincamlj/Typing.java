package mincamlj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import mincamlj.syntax.SAdd;
import mincamlj.syntax.SApp;
import mincamlj.syntax.SArray;
import mincamlj.syntax.SBool;
import mincamlj.syntax.SEq;
import mincamlj.syntax.SFAdd;
import mincamlj.syntax.SFDiv;
import mincamlj.syntax.SFMul;
import mincamlj.syntax.SFNeg;
import mincamlj.syntax.SFSub;
import mincamlj.syntax.SFloat;
import mincamlj.syntax.SFunDef;
import mincamlj.syntax.SGet;
import mincamlj.syntax.SIf;
import mincamlj.syntax.SInt;
import mincamlj.syntax.SLe;
import mincamlj.syntax.SLet;
import mincamlj.syntax.SLetRec;
import mincamlj.syntax.SLetTuple;
import mincamlj.syntax.SNeg;
import mincamlj.syntax.SNot;
import mincamlj.syntax.SPut;
import mincamlj.syntax.SSub;
import mincamlj.syntax.STuple;
import mincamlj.syntax.SUnit;
import mincamlj.syntax.SVar;
import mincamlj.syntax.SyntaxExpr;
import mincamlj.type.ArrayType;
import mincamlj.type.BoolType;
import mincamlj.type.FloatType;
import mincamlj.type.FunType;
import mincamlj.type.IntType;
import mincamlj.type.TupleType;
import mincamlj.type.Type;
import mincamlj.type.UnitType;
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
			return occur(t1, t2_.getInner());
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

	public void unify(Type t1, Type t2) throws UnifyError {
		if (t1 instanceof UnitType && t2 instanceof UnitType) {
			return;
		}
		if (t1 instanceof BoolType && t2 instanceof BoolType) {
			return;
		}
		if (t1 instanceof IntType && t2 instanceof IntType) {
			return;
		}
		if (t1 instanceof FloatType && t2 instanceof FloatType) {
			return;
		}

		if (t1 instanceof FunType && t2 instanceof FunType) {
			FunType t1_ = (FunType) t1;
			FunType t2_ = (FunType) t2;

			if (t1_.getParams().size() != t2_.getParams().size()) {
				throw new UnifyError(new Pair<Type, Type>(t1, t2));
			}

			for (int i = 0; i < t1_.getParams().size(); i++) {
				unify(t1_.getParams().get(i), t2_.getParams().get(i));
			}

			unify(t1_.getReturned(), t2_.getReturned());
			return;
		}
		if (t1 instanceof TupleType && t2 instanceof TupleType) {
			TupleType t1_ = (TupleType) t1;
			TupleType t2_ = (TupleType) t2;

			if (t1_.getInners().size() != t2_.getInners().size()) {
				throw new UnifyError(new Pair<Type, Type>(t1, t2));
			}

			for (int i = 0; i < t1_.getInners().size(); i++) {
				unify(t1_.getInners().get(i), t2_.getInners().get(i));
			}

			return;
		}
		if (t1 instanceof ArrayType && t2 instanceof ArrayType) {
			ArrayType t1_ = (ArrayType) t1;
			ArrayType t2_ = (ArrayType) t2;
			unify(t1_.getInner(), t2_.getInner());
			return;
		}
		if (t1 instanceof VarType && t2 instanceof VarType) {
			VarType t1_ = (VarType) t1;
			VarType t2_ = (VarType) t2;
			if (t1_.getInner() == t2_.getInner()) {
				return;
			}
		}
		if (t1 instanceof VarType) {
			VarType t1_ = (VarType) t1;
			if (t1_.isNone()) {
				if (occur(t1_.getInner(), t2)) {
					throw new UnifyError(new Pair<Type, Type>(t1, t2));
				} else {
					t1_.setInner(t2);
					return;
				}
			} else {
				unify(t1_.getInner(), t2);
				return;
			}
		}
		if (t2 instanceof VarType) {
			VarType t2_ = (VarType) t2;
			if (t2_.isNone()) {
				if (occur(t2_.getInner(), t1)) {
					throw new UnifyError(new Pair<Type, Type>(t1, t2));
				} else {
					t2_.setInner(t1);
					return;
				}
			} else {
				unify(t1, t2_.getInner());
				return;
			}
		}

		throw new UnifyError(new Pair<Type, Type>(t1, t2));
	}

	public Type infer(SyntaxExpr e, Map<String, Type> env) throws TypingError {
		try {
			if (e instanceof SUnit) {
				return UnitType.getInstance();
			} else if (e instanceof SBool) {
				return BoolType.getInstance();
			} else if (e instanceof SInt) {
				return IntType.getInstance();
			} else if (e instanceof SFloat) {
				return FloatType.getInstance();
			} else if (e instanceof SNot) {
				SNot e1 = (SNot) e;
				unify(BoolType.getInstance(), infer(e1.getExpr(), env));
				return BoolType.getInstance();
			} else if (e instanceof SNeg) {
				SNeg e1 = (SNeg) e;
				unify(IntType.getInstance(), infer(e1.getExpr(), env));
				return IntType.getInstance();
			} else if (e instanceof SAdd) {
				SAdd e1 = (SAdd) e;
				unify(IntType.getInstance(), infer(e1.getLeft(), env));
				unify(IntType.getInstance(), infer(e1.getRight(), env));
				return IntType.getInstance();
			} else if (e instanceof SSub) {
				SSub e1 = (SSub) e;
				unify(IntType.getInstance(), infer(e1.getLeft(), env));
				unify(IntType.getInstance(), infer(e1.getRight(), env));
				return IntType.getInstance();
			} else if (e instanceof SFNeg) {
				SFNeg e1 = (SFNeg) e;
				unify(FloatType.getInstance(), infer(e1.getExpr(), env));
				return FloatType.getInstance();
			} else if (e instanceof SFAdd) {
				SFAdd e1 = (SFAdd) e;
				unify(FloatType.getInstance(), infer(e1.getLeft(), env));
				unify(FloatType.getInstance(), infer(e1.getRight(), env));
				return FloatType.getInstance();
			} else if (e instanceof SFSub) {
				SFSub e1 = (SFSub) e;
				unify(FloatType.getInstance(), infer(e1.getLeft(), env));
				unify(FloatType.getInstance(), infer(e1.getRight(), env));
				return FloatType.getInstance();
			} else if (e instanceof SFMul) {
				SFMul e1 = (SFMul) e;
				unify(FloatType.getInstance(), infer(e1.getLeft(), env));
				unify(FloatType.getInstance(), infer(e1.getRight(), env));
				return FloatType.getInstance();
			} else if (e instanceof SFDiv) {
				SFDiv e1 = (SFDiv) e;
				unify(FloatType.getInstance(), infer(e1.getLeft(), env));
				unify(FloatType.getInstance(), infer(e1.getRight(), env));
				return FloatType.getInstance();
			} else if (e instanceof SEq) {
				SEq e1 = (SEq) e;
				unify(infer(e1.getLeft(), env), infer(e1.getRight(), env));
				return BoolType.getInstance();
			} else if (e instanceof SLe) {
				SLe e1 = (SLe) e;
				unify(infer(e1.getLeft(), env), infer(e1.getRight(), env));
				return BoolType.getInstance();
			} else if (e instanceof SIf) {
				SIf e1 = (SIf) e;
				unify(infer(e1.getPred(), env), BoolType.getInstance());
				Type tt = infer(e1.getTrueExpr(), env);
				Type ft = infer(e1.getFalseExpr(), env);
				unify(tt, ft);
				return tt;
			} else if (e instanceof SLet) {
				SLet e1 = (SLet) e;
				unify(e1.getVar().getRight(), infer(e1.getValue(), env));
				Map<String, Type> newEnv = new HashMap<>(env);
				newEnv.put(e1.getVar().getLeft(), e1.getVar().getRight());
				return infer(e1.getBody(), newEnv);
			} else if (e instanceof SVar) {
				SVar e1 = (SVar) e;
				if (env.containsKey(e1.getName())) {
					return env.get(e1.getName());
				} else if (extEnv.containsKey(e1.getName())) {
					return extEnv.get(e1.getName());
				} else {
					Log.getLogger().info(
							String.format(
									"free variable %s assumed as external@.",
									e1.getName()));
					VarType t = VarType.genType();
					extEnv.put(e1.getName(), t);
					return t;
				}
			} else if (e instanceof SLetRec) {
				SLetRec e1 = (SLetRec) e;
				SFunDef funDef = e1.getFunDef();
				Map<String, Type> newEnv = new HashMap<>(env);
				newEnv.put(funDef.getName().getLeft(), funDef.getName()
						.getRight());
				Map<String, Type> newEnv2 = new HashMap<>(newEnv);
				funDef.getParams().forEach(
						p -> newEnv2.put(p.getLeft(), p.getRight()));
				unify(funDef.getName().getRight(),
						new FunType(funDef.getParams().stream()
								.map(p -> p.getRight())
								.collect(Collectors.toList()), infer(
								funDef.getBody(), newEnv2)));
				return infer(e1.getBody(), newEnv);
			} else if (e instanceof SApp) {
				SApp e1 = (SApp) e;
				VarType t = VarType.genType();

				List<Type> argTypes = new ArrayList<>();
				for (SyntaxExpr a : e1.getArgs()) {
					argTypes.add(infer(a, env));
				}
				unify(infer(e1.getFunc(), env), new FunType(argTypes, t));

				return t;
			} else if (e instanceof STuple) {
				STuple e1 = (STuple) e;
				List<Type> inners = new ArrayList<>();
				for (SyntaxExpr v : e1.getValues()) {
					inners.add(infer(v, env));
				}
				return new TupleType(inners);
			} else if (e instanceof SLetTuple) {
				SLetTuple e1 = (SLetTuple) e;
				unify(new TupleType(e1.getVars().stream()
						.map(v -> v.getRight()).collect(Collectors.toList())),
						infer(e1.getValue(), env));
				Map<String, Type> newEnv = new HashMap<>(env);
				e1.getVars()
						.forEach(v -> newEnv.put(v.getLeft(), v.getRight()));
				return infer(e1.getBody(), newEnv);
			} else if (e instanceof SArray) {
				SArray e1 = (SArray) e;
				unify(infer(e1.getLength(), env), IntType.getInstance());
				return new ArrayType(infer(e1.getInitial(), env));
			} else if (e instanceof SGet) {
				SGet e1 = (SGet) e;
				VarType t = VarType.genType();
				unify(new ArrayType(t), infer(e1.getArray(), env));
				unify(IntType.getInstance(), infer(e1.getIndex(), env));
				return t;
			} else if (e instanceof SPut) {
				SPut e1 = (SPut) e;
				Type t = infer(e1.getValue(), env);
				unify(new ArrayType(t), infer(e1.getArray(), env));
				unify(IntType.getInstance(), infer(e1.getIndex(), env));
				return t;
			}
		} catch (UnifyError err) {
			Pair<Type, Type> errorTypes = err.getErrorTypes();
			throw new TypingError(derefTerm(e), new Pair<Type, Type>(
					derefType(errorTypes.getLeft()),
					derefType(errorTypes.getRight())));
		}

		throw new RuntimeException("unknown expression: " + e);
	}

	public static Map<String, Type> extEnv;

	public SyntaxExpr typing(SyntaxExpr e) {
		Map<String, Type> env = new HashMap<>();
		try {
			unify(UnitType.getInstance(), infer(e, env));
		} catch (UnifyError err) {
			Log.getLogger().severe("top level does not have type unit");
			throw new RuntimeException("top level does not have type unit");
		} catch (TypingError err) {
			Log.getLogger().severe(
					"typing error. expression: " + err.getExpr()
							+ " ; expected: " + err.getErrorTypes().getLeft()
							+ " ; actual: " + err.getErrorTypes().getRight());
			throw new RuntimeException(err);
		}
		Map<String, Type> tmpEnv = new HashMap<>();
		for (String key : extEnv.keySet()) {
			tmpEnv.put(key, derefType(extEnv.get(key)));
		}
		extEnv = tmpEnv;
		Log.getLogger().info("external env: " + extEnv);
		return derefTerm(e);
	}
}
