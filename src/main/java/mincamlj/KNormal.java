package mincamlj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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
import mincamlj.type.FloatType;
import mincamlj.type.FunType;
import mincamlj.type.IntType;
import mincamlj.type.TupleType;
import mincamlj.type.Type;
import mincamlj.type.UnitType;
import mincamlj.util.Pair;

public class KNormal {

	public static Set<String> freeVars(KNormalExpr e) {
		if (e instanceof KUnit) {
			return new HashSet<>();
		} else if (e instanceof KInt) {
			return new HashSet<>();
		} else if (e instanceof KFloat) {
			return new HashSet<>();
		} else if (e instanceof KNeg) {
			KNeg e1 = (KNeg) e;
			return new HashSet<>(Arrays.asList(e1.getInner()));
		} else if (e instanceof KAdd) {
			KAdd e1 = (KAdd) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof KSub) {
			KSub e1 = (KSub) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof KFNeg) {
			KFNeg e1 = (KFNeg) e;
			return new HashSet<>(Arrays.asList(e1.getInner()));
		} else if (e instanceof KFAdd) {
			KFAdd e1 = (KFAdd) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof KFSub) {
			KFSub e1 = (KFSub) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof KFMul) {
			KFMul e1 = (KFMul) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof KFDiv) {
			KFDiv e1 = (KFDiv) e;
			return new HashSet<>(Arrays.asList(e1.getLeft(), e1.getRight()));
		} else if (e instanceof KIfEq) {
			KIfEq e1 = (KIfEq) e;
			Set<String> fvs = new HashSet<>(Arrays.asList(e1.getLeft(),
					e1.getRight()));
			fvs.addAll(freeVars(e1.getTrueExpr()));
			fvs.addAll(freeVars(e1.getFalseExpr()));
			return fvs;
		} else if (e instanceof KIfLe) {
			KIfLe e1 = (KIfLe) e;
			Set<String> fvs = new HashSet<>(Arrays.asList(e1.getLeft(),
					e1.getRight()));
			fvs.addAll(freeVars(e1.getTrueExpr()));
			fvs.addAll(freeVars(e1.getFalseExpr()));
			return fvs;
		} else if (e instanceof KLet) {
			KLet e1 = (KLet) e;
			Set<String> fvs = new HashSet<>();
			fvs.addAll(freeVars(e1.getBody()));
			fvs.remove(e1.getVar().getLeft());
			fvs.addAll(freeVars(e1.getValue()));
			return fvs;
		} else if (e instanceof KVar) {
			KVar e1 = (KVar) e;
			return new HashSet<>(Arrays.asList(e1.getName()));
		} else if (e instanceof KLetRec) {
			KLetRec e1 = (KLetRec) e;
			KFunDef funDef = e1.getFunDef();
			Set<String> fvs = freeVars(funDef.getBody());
			Set<String> boundVars = funDef.getParams().stream()
					.map(p -> p.getLeft()).collect(Collectors.toSet());
			fvs.removeAll(boundVars);
			fvs.addAll(freeVars(e1.getBody()));
			fvs.remove(funDef.getName().getLeft());
			return fvs;
		} else if (e instanceof KApp) {
			KApp e1 = (KApp) e;
			Set<String> fvs = new HashSet<>(Arrays.asList(e1.getFunc()));
			fvs.addAll(e1.getArgs());
			return fvs;
		} else if (e instanceof KTuple) {
			KTuple e1 = (KTuple) e;
			return new HashSet<>(e1.getValues());
		} else if (e instanceof KLetTuple) {
			KLetTuple e1 = (KLetTuple) e;
			Set<String> fvs = freeVars(e1.getBody());
			Set<String> boundVars = e1.getVars().stream().map(p -> p.getLeft())
					.collect(Collectors.toSet());
			fvs.removeAll(boundVars);
			fvs.add(e1.getValue());
			return fvs;
		} else if (e instanceof KGet) {
			KGet e1 = (KGet) e;
			return new HashSet<>(Arrays.asList(e1.getArray(), e1.getIndex()));
		} else if (e instanceof KPut) {
			KPut e1 = (KPut) e;
			return new HashSet<>(Arrays.asList(e1.getArray(), e1.getIndex(),
					e1.getValue()));
		} else if (e instanceof KExtArray) {
			return new HashSet<>();
		} else if (e instanceof KExtFunApp) {
			KExtFunApp e1 = (KExtFunApp) e;
			return new HashSet<>(e1.getArgs());
		}
		return new HashSet<>();
	}

	public Pair<KNormalExpr, Type> insertLet(Pair<KNormalExpr, Type> et,
			Function<String, Pair<KNormalExpr, Type>> k) {
		KNormalExpr e = et.getLeft();
		Type t = et.getRight();
		if (e instanceof KVar) {
			KVar e1 = (KVar) e;
			return k.apply(e1.getName());
		}
		String x = Id.genTmp(t);
		Pair<KNormalExpr, Type> newEt = k.apply(x);
		return new Pair<KNormalExpr, Type>(new KLet(
				new Pair<String, Type>(x, t), e, newEt.getLeft()),
				newEt.getRight());
	}

	public Pair<KNormalExpr, Type> normalize(SyntaxExpr e, Map<String, Type> env) {
		if (e instanceof SUnit) {
			return new Pair<KNormalExpr, Type>(KUnit.getInstance(),
					UnitType.getInstance());
		} else if (e instanceof SBool) {
			SBool e1 = (SBool) e;
			if (e1.isValue()) {
				return new Pair<KNormalExpr, Type>(new KInt(1),
						IntType.getInstance());
			} else {
				return new Pair<KNormalExpr, Type>(new KInt(0),
						IntType.getInstance());
			}
		} else if (e instanceof SInt) {
			SInt e1 = (SInt) e;
			return new Pair<KNormalExpr, Type>(new KInt(e1.getValue()),
					IntType.getInstance());
		} else if (e instanceof SFloat) {
			SFloat e1 = (SFloat) e;
			return new Pair<KNormalExpr, Type>(new KFloat(e1.getValue()),
					FloatType.getInstance());
		} else if (e instanceof SNot) {
			SNot e1 = (SNot) e;
			return normalize(new SIf(e1.getExpr(), new SBool(false), new SBool(
					true)), env);
		} else if (e instanceof SNeg) {
			SNeg e1 = (SNeg) e;
			return insertLet(normalize(e1.getExpr(), env), x -> new Pair<>(
					new KNeg(x), IntType.getInstance()));
		} else if (e instanceof SAdd) {
			SAdd e1 = (SAdd) e;
			return insertLet(
					normalize(e1.getLeft(), env),
					x -> insertLet(
							normalize(e1.getRight(), env),
							y -> new Pair<>(new KAdd(x, y), IntType
									.getInstance())));
		} else if (e instanceof SSub) {
			SSub e1 = (SSub) e;
			return insertLet(
					normalize(e1.getLeft(), env),
					x -> insertLet(
							normalize(e1.getRight(), env),
							y -> new Pair<>(new KSub(x, y), IntType
									.getInstance())));
		} else if (e instanceof SFNeg) {
			SFNeg e1 = (SFNeg) e;
			return insertLet(normalize(e1.getExpr(), env), x -> new Pair<>(
					new KFNeg(x), FloatType.getInstance()));
		} else if (e instanceof SFAdd) {
			SFAdd e1 = (SFAdd) e;
			return insertLet(
					normalize(e1.getLeft(), env),
					x -> insertLet(
							normalize(e1.getRight(), env),
							y -> new Pair<>(new KFAdd(x, y), FloatType
									.getInstance())));
		} else if (e instanceof SFSub) {
			SFSub e1 = (SFSub) e;
			return insertLet(
					normalize(e1.getLeft(), env),
					x -> insertLet(
							normalize(e1.getRight(), env),
							y -> new Pair<>(new KFSub(x, y), FloatType
									.getInstance())));
		} else if (e instanceof SFMul) {
			SFMul e1 = (SFMul) e;
			return insertLet(
					normalize(e1.getLeft(), env),
					x -> insertLet(
							normalize(e1.getRight(), env),
							y -> new Pair<>(new KFMul(x, y), FloatType
									.getInstance())));
		} else if (e instanceof SFDiv) {
			SFDiv e1 = (SFDiv) e;
			return insertLet(
					normalize(e1.getLeft(), env),
					x -> insertLet(
							normalize(e1.getRight(), env),
							y -> new Pair<>(new KFDiv(x, y), FloatType
									.getInstance())));
		} else if (e instanceof SEq) {
			SEq e1 = (SEq) e;
			return normalize(new SIf(e1, new SBool(true), new SBool(false)),
					env);
		} else if (e instanceof SLe) {
			SLe e1 = (SLe) e;
			return normalize(new SIf(e1, new SBool(true), new SBool(false)),
					env);
		} else if (e instanceof SIf) {
			SIf e1 = (SIf) e;
			if (e1.getPred() instanceof SNot) {
				SNot e2 = (SNot) e1.getPred();
				return normalize(
						new SIf(e2.getExpr(), e1.getFalseExpr(),
								e1.getTrueExpr()), env);
			} else if (e1.getPred() instanceof SEq) {
				SEq e2 = (SEq) e1.getPred();
				return insertLet(
						normalize(e2.getLeft(), env),
						x -> insertLet(
								normalize(e2.getRight(), env),
								y -> {
									Pair<KNormalExpr, Type> tPair = normalize(
											e1.getTrueExpr(), env);
									Pair<KNormalExpr, Type> fPair = normalize(
											e1.getFalseExpr(), env);
									return new Pair<KNormalExpr, Type>(
											new KIfEq(x, y, tPair.getLeft(),
													fPair.getLeft()), tPair
													.getRight());
								}));
			} else if (e1.getPred() instanceof SLe) {
				SLe e2 = (SLe) e1.getPred();
				return insertLet(
						normalize(e2.getLeft(), env),
						x -> insertLet(
								normalize(e2.getRight(), env),
								y -> {
									Pair<KNormalExpr, Type> tPair = normalize(
											e1.getTrueExpr(), env);
									Pair<KNormalExpr, Type> fPair = normalize(
											e1.getFalseExpr(), env);
									return new Pair<KNormalExpr, Type>(
											new KIfLe(x, y, tPair.getLeft(),
													fPair.getLeft()), tPair
													.getRight());
								}));
			} else {
				return normalize(
						new SIf(new SEq(e1.getPred(), new SBool(false)),
								e1.getFalseExpr(), e1.getTrueExpr()), env);
			}

		} else if (e instanceof SLet) {
			SLet e1 = (SLet) e;
			Pair<KNormalExpr, Type> vPair = normalize(e1.getValue(), env);
			Map<String, Type> newEnv = new HashMap<String, Type>(env);
			newEnv.put(e1.getVar().getLeft(), e1.getVar().getRight());
			Pair<KNormalExpr, Type> bPair = normalize(e1.getBody(), newEnv);
			return new Pair<KNormalExpr, Type>(new KLet(e1.getVar(),
					vPair.getLeft(), bPair.getLeft()), bPair.getRight());
		} else if (e instanceof SVar) {
			SVar e1 = (SVar) e;
			if (env.containsKey(e1.getName())) {
				return new Pair<KNormalExpr, Type>(new KVar(e1.getName()),
						env.get(e1.getName()));
			} else {
				Type t = Typing.extEnv.get(e1.getName());
				if (t instanceof ArrayType) {
					return new Pair<KNormalExpr, Type>(new KExtArray(
							e1.getName()), t);
				} else {
					throw new RuntimeException(String.format(
							"external variable %s does not have an array type",
							e1.getName()));
				}
			}
		} else if (e instanceof SLetRec) {
			SLetRec e1 = (SLetRec) e;
			SFunDef funDef = e1.getFunDef();
			Map<String, Type> newEnv = new HashMap<String, Type>(env);
			newEnv.put(funDef.getName().getLeft(), funDef.getName().getRight());
			Pair<KNormalExpr, Type> bPair = normalize(e1.getBody(), newEnv);
			Map<String, Type> newEnv2 = new HashMap<String, Type>(newEnv);
			funDef.getParams().forEach(
					p -> newEnv2.put(p.getLeft(), p.getRight()));
			Pair<KNormalExpr, Type> fPair = normalize(funDef.getBody(), newEnv2);
			return new Pair<KNormalExpr, Type>(new KLetRec(new KFunDef(
					funDef.getName(), funDef.getParams(), fPair.getLeft()),
					bPair.getLeft()), bPair.getRight());
		} else if (e instanceof SApp) {
			SApp e1 = (SApp) e;
			if (e1.getFunc() instanceof SVar) {
				SVar e2 = (SVar) e1.getFunc();
				if (!env.containsKey(e2.getName())) {
					Type t = Typing.extEnv.get(e2.getName());
					if (t instanceof FunType) {
						FunType ft = (FunType) t;
						return bindExtFunApp(ft.getReturned(), e2.getName(),
								e1.getArgs(), new ArrayList<String>(), env);
					} else {
						throw new RuntimeException();
					}
				}
			}

			Pair<KNormalExpr, Type> fPair = normalize(e1.getFunc(), env);
			if (fPair.getRight() instanceof FunType) {
				FunType ft = (FunType) fPair.getRight();
				return insertLet(
						fPair,
						f -> bindApp(ft.getReturned(), f, e1.getArgs(),
								new ArrayList<>(), env));
			} else {
				throw new RuntimeException();
			}
		} else if (e instanceof STuple) {
			STuple e1 = (STuple) e;
			return bindTuple(e1.getValues(), new ArrayList<>(),
					new ArrayList<>(), env);
		} else if (e instanceof SLetTuple) {
			SLetTuple e1 = (SLetTuple) e;
			return insertLet(
					normalize(e1.getValue(), env),
					x -> {
						Map<String, Type> newEnv = new HashMap<>(env);
						e1.getVars().forEach(
								v -> newEnv.put(v.getLeft(), v.getRight()));
						Pair<KNormalExpr, Type> bPair = normalize(e1.getBody(),
								newEnv);
						return new Pair<KNormalExpr, Type>(new KLetTuple(e1
								.getVars(), x, bPair.getLeft()), bPair
								.getRight());
					});
		} else if (e instanceof SArray) {
			SArray e1 = (SArray) e;
			return insertLet(
					normalize(e1.getLength(), env),
					x -> {
						Pair<KNormalExpr, Type> iPair = normalize(
								e1.getInitial(), env);
						return insertLet(
								iPair,
								y -> {
									String label = (iPair.getRight() instanceof FloatType) ? "create_float_array"
											: "create_array";
									return new Pair<KNormalExpr, Type>(
											new KExtFunApp(label,
													new ArrayList<>(Arrays
															.asList(x, y))),
											new ArrayType(iPair.getRight()));
								});
					});
		} else if (e instanceof SGet) {
			SGet e1 = (SGet) e;
			Pair<KNormalExpr, Type> aPair = normalize(e1.getArray(), env);
			if (aPair.getRight() instanceof ArrayType) {
				ArrayType at = (ArrayType) aPair.getRight();
				return insertLet(
						aPair,
						x -> insertLet(normalize(e1.getIndex(), env),
								y -> new Pair<>(new KGet(x, y), at.getInner())));
			} else {
				throw new RuntimeException();
			}
		} else if (e instanceof SPut) {
			SPut e1 = (SPut) e;
			return insertLet(
					normalize(e1.getArray(), env),
					x -> insertLet(
							normalize(e1.getIndex(), env),
							y -> insertLet(
									normalize(e1.getValue(), env),
									z -> new Pair<>(new KPut(x, y, z), UnitType
											.getInstance()))));
		}

		throw new RuntimeException("unknown expression: " + e);
	}

	public Pair<KNormalExpr, Type> bindExtFunApp(Type returnType, String f,
			List<SyntaxExpr> args, List<String> newArgs, Map<String, Type> env) {
		if (args.isEmpty()) {
			return new Pair<KNormalExpr, Type>(new KExtFunApp(f, newArgs),
					returnType);
		} else {
			return insertLet(
					normalize(args.get(0), env),
					x -> {
						newArgs.add(x);
						return bindExtFunApp(returnType, f,
								args.subList(1, args.size()), newArgs, env);
					});
		}
	}

	public Pair<KNormalExpr, Type> bindApp(Type returnType, String f,
			List<SyntaxExpr> args, List<String> newArgs, Map<String, Type> env) {
		if (args.isEmpty()) {
			return new Pair<KNormalExpr, Type>(new KApp(f, newArgs), returnType);
		} else {
			return insertLet(
					normalize(args.get(0), env),
					x -> {
						newArgs.add(x);
						return bindApp(returnType, f,
								args.subList(1, args.size()), newArgs, env);
					});
		}
	}

	public Pair<KNormalExpr, Type> bindTuple(List<SyntaxExpr> values,
			List<String> newValues, List<Type> valueTypes, Map<String, Type> env) {
		if (values.isEmpty()) {
			return new Pair<KNormalExpr, Type>(new KTuple(newValues),
					new TupleType(valueTypes));
		} else {
			Pair<KNormalExpr, Type> vPair = normalize(values.get(0), env);
			return insertLet(
					vPair,
					x -> {
						newValues.add(x);
						valueTypes.add(vPair.getRight());
						return bindTuple(values.subList(1, values.size()),
								newValues, valueTypes, env);
					});
		}
	}

	public KNormalExpr transform(SyntaxExpr e) {
		Pair<KNormalExpr, Type> pair = normalize(e, new HashMap<>());
		return pair.getLeft();
	}
}
