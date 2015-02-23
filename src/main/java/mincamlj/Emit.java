package mincamlj;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
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
import mincamlj.type.ArrayType;
import mincamlj.type.BoolType;
import mincamlj.type.FloatType;
import mincamlj.type.FunType;
import mincamlj.type.IntType;
import mincamlj.type.TupleType;
import mincamlj.type.Type;
import mincamlj.type.UnitType;
import mincamlj.util.Pair;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Emit implements Opcodes {

	private BiConsumer<EmitState, Type> defaultCont = (st, t) -> {
		if (t instanceof UnitType) {
			st.getMv().visitInsn(RETURN);
		} else if (t instanceof BoolType) {
			st.getMv().visitInsn(IRETURN);
		} else if (t instanceof IntType) {
			st.getMv().visitInsn(IRETURN);
		} else if (t instanceof FloatType) {
			st.getMv().visitInsn(DRETURN);
		} else {
			st.getMv().visitInsn(ARETURN);
		}
	};

	private Class<?> t2c(Type t) {
		if (t instanceof UnitType) {
			return void.class;
		} else if (t instanceof BoolType) {
			return int.class;
		} else if (t instanceof IntType) {
			return int.class;
		} else if (t instanceof FloatType) {
			return double.class;
		} else if (t instanceof ArrayType) {
			ArrayType at = (ArrayType) t;
			if (at.getInner() instanceof FloatType) {
				return double[].class;
			} else {
				return int[].class;
			}
		}
		return Object.class;
	}

	private String tupleName(int size) {
		return "mincamlj.runtime.Tuple" + size;
	}

	private String tupleInitSig(int size) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < size; i++) {
			sb.append("Ljava/lang/Object;");
		}
		sb.append(")V");
		return sb.toString();
	}

	private static class EmitState {
		private MethodVisitor mv;
		private int localVarId = 0;
		private int maxStack = 0;
		private int curStack = 0;

		public EmitState() {
			super();
		}

		public EmitState(MethodVisitor mv) {
			super();
			this.mv = mv;
		}

		public void setMv(MethodVisitor mv) {
			this.mv = mv;
		}

		public MethodVisitor getMv() {
			return mv;
		}

		public int newLocalVarId() {
			return localVarId++;
		}

		public void pushStack() {
			curStack++;
			maxStack = Math.max(maxStack, curStack);
		}

		public void consumeStack(int consume, int ret) {
			curStack = curStack - consume + ret;
		}

		public int getMaxLocals() {
			return localVarId;
		}

		public int getMaxStack() {
			return maxStack;
		}
	}

	public void emitExpr(ClosureExpr e, EmitState st,
			Map<String, Pair<Type, Integer>> env,
			BiConsumer<EmitState, Type> cont) {
		if (e instanceof CUnit) {
			cont.accept(st, UnitType.getInstance());
		} else if (e instanceof CInt) {
			CInt e1 = (CInt) e;
			st.getMv().visitLdcInsn(new Integer(e1.getValue()));
			st.pushStack();
			cont.accept(st, IntType.getInstance());
		} else if (e instanceof CFloat) {
			CFloat e1 = (CFloat) e;
			st.getMv().visitLdcInsn(new Double(e1.getValue()));
			st.pushStack();
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CNeg) {
			CNeg e1 = (CNeg) e;
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getInner()).getRight());
			st.pushStack();
			st.getMv().visitInsn(INEG);
			st.consumeStack(1, 1);
			cont.accept(st, IntType.getInstance());
		} else if (e instanceof CAdd) {
			CAdd e1 = (CAdd) e;
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getLeft()).getRight());
			st.pushStack();
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getRight()).getRight());
			st.pushStack();
			st.getMv().visitInsn(IADD);
			st.consumeStack(2, 1);
			cont.accept(st, IntType.getInstance());
		} else if (e instanceof CSub) {
			CSub e1 = (CSub) e;
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getLeft()).getRight());
			st.pushStack();
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getRight()).getRight());
			st.pushStack();
			st.getMv().visitInsn(ISUB);
			st.consumeStack(2, 1);
			cont.accept(st, IntType.getInstance());
		} else if (e instanceof CFNeg) {
			CFNeg e1 = (CFNeg) e;
			st.getMv().visitVarInsn(DLOAD, env.get(e1.getInner()).getRight());
			st.pushStack();
			st.getMv().visitInsn(DNEG);
			st.consumeStack(1, 1);
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CFAdd) {
			CFAdd e1 = (CFAdd) e;
			st.getMv().visitVarInsn(DLOAD, env.get(e1.getLeft()).getRight());
			st.pushStack();
			st.getMv().visitVarInsn(DLOAD, env.get(e1.getRight()).getRight());
			st.pushStack();
			st.getMv().visitInsn(DADD);
			st.consumeStack(2, 1);
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CFSub) {
			CFSub e1 = (CFSub) e;
			st.getMv().visitVarInsn(DLOAD, env.get(e1.getLeft()).getRight());
			st.pushStack();
			st.getMv().visitVarInsn(DLOAD, env.get(e1.getRight()).getRight());
			st.pushStack();
			st.getMv().visitInsn(DSUB);
			st.consumeStack(2, 1);
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CFMul) {
			CFMul e1 = (CFMul) e;
			st.getMv().visitVarInsn(DLOAD, env.get(e1.getLeft()).getRight());
			st.pushStack();
			st.getMv().visitVarInsn(DLOAD, env.get(e1.getRight()).getRight());
			st.pushStack();
			st.getMv().visitInsn(DMUL);
			st.consumeStack(2, 1);
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CFDiv) {
			CFDiv e1 = (CFDiv) e;
			st.getMv().visitVarInsn(DLOAD, env.get(e1.getLeft()).getRight());
			st.pushStack();
			st.getMv().visitVarInsn(DLOAD, env.get(e1.getRight()).getRight());
			st.pushStack();
			st.getMv().visitInsn(DDIV);
			st.consumeStack(2, 1);
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CIfEq) {
			CIfEq e1 = (CIfEq) e;
			Type t1 = env.get(e1.getLeft()).getLeft();
			Label branch = new Label();
			Label end = new Label();
			if (t1 instanceof BoolType || t1 instanceof IntType) {
				st.getMv()
						.visitVarInsn(ILOAD, env.get(e1.getLeft()).getRight());
				st.pushStack();
				st.getMv().visitVarInsn(ILOAD,
						env.get(e1.getRight()).getRight());
				st.pushStack();
				st.getMv().visitInsn(ISUB);
				st.consumeStack(2, 1);
				st.getMv().visitJumpInsn(IFEQ, branch);
				st.consumeStack(1, 0);
				// false
				emitExpr(e1.getFalseExpr(), st, env, cont);
				// true
				st.getMv().visitLabel(branch);

				if (cont == defaultCont) {
					emitExpr(e1.getTrueExpr(), st, env, cont);
				} else {
					emitExpr(e1.getTrueExpr(), st, env, (s, t) -> {
						cont.accept(s, t);
						s.getMv().visitJumpInsn(GOTO, end);
					});
				}
			} else if (t1 instanceof FloatType) {
				st.getMv()
						.visitVarInsn(DLOAD, env.get(e1.getLeft()).getRight());
				st.pushStack();
				st.getMv().visitVarInsn(DLOAD,
						env.get(e1.getRight()).getRight());
				st.pushStack();
				st.getMv().visitInsn(DSUB);
				st.consumeStack(2, 1);
				st.getMv().visitJumpInsn(IFEQ, branch);
				st.consumeStack(1, 0);
				// false
				emitExpr(e1.getFalseExpr(), st, env, cont);
				// true
				st.getMv().visitLabel(branch);
				if (cont == defaultCont) {
					emitExpr(e1.getTrueExpr(), st, env, cont);
				} else {
					emitExpr(e1.getTrueExpr(), st, env, (s, t) -> {
						cont.accept(s, t);
						s.getMv().visitJumpInsn(GOTO, end);
					});
				}
			} else {
				throw new RuntimeException(
						"equality supported only for bool, int, and float");
			}
			st.getMv().visitLabel(end);
		} else if (e instanceof CIfLe) {
			CIfLe e1 = (CIfLe) e;
			Type t1 = env.get(e1.getLeft()).getLeft();
			Label branch = new Label();
			Label end = new Label();
			if (t1 instanceof BoolType || t1 instanceof IntType) {
				st.getMv()
						.visitVarInsn(ILOAD, env.get(e1.getLeft()).getRight());
				st.pushStack();
				st.getMv().visitVarInsn(ILOAD,
						env.get(e1.getRight()).getRight());
				st.pushStack();
				st.getMv().visitInsn(ISUB);
				st.consumeStack(2, 1);
				st.getMv().visitJumpInsn(IFLE, branch);
				st.consumeStack(1, 0);
				// false
				if (cont == defaultCont) {
					emitExpr(e1.getFalseExpr(), st, env, cont);
				} else {
					emitExpr(e1.getFalseExpr(), st, env, (s, t) -> {
						cont.accept(s, t);
						s.getMv().visitJumpInsn(GOTO, end);
					});
				}
				// true
				st.getMv().visitLabel(branch);
				emitExpr(e1.getTrueExpr(), st, env, cont);
				st.getMv().visitLabel(end);
			} else if (t1 instanceof FloatType) {
				st.getMv()
						.visitVarInsn(DLOAD, env.get(e1.getLeft()).getRight());
				st.pushStack();
				st.getMv().visitVarInsn(DLOAD,
						env.get(e1.getRight()).getRight());
				st.pushStack();
				st.getMv().visitInsn(DSUB);
				st.consumeStack(2, 1);
				st.getMv().visitJumpInsn(IFEQ, branch);
				st.consumeStack(1, 0);
				// false
				if (cont == defaultCont) {
					emitExpr(e1.getFalseExpr(), st, env, cont);
				} else {
					emitExpr(e1.getFalseExpr(), st, env, (s, t) -> {
						cont.accept(s, t);
						s.getMv().visitJumpInsn(GOTO, end);
					});
				}
				emitExpr(e1.getFalseExpr(), st, env, cont);
				// true
				st.getMv().visitLabel(branch);
				emitExpr(e1.getTrueExpr(), st, env, cont);
				st.getMv().visitLabel(end);
			} else {
				throw new RuntimeException(
						"equality supported only for bool, int, and float");
			}
			st.getMv().visitLabel(end);
		} else if (e instanceof CVar) {
			CVar e1 = (CVar) e;
			Type t = env.get(e1.getName()).getLeft();
			int varId = env.get(e1.getName()).getRight();
			if (t instanceof IntType) {
				st.getMv().visitVarInsn(ILOAD, varId);
				st.pushStack();
			} else if (t instanceof FloatType) {
				st.getMv().visitVarInsn(DLOAD, varId);
				st.pushStack();
			} else {
				st.getMv().visitVarInsn(ALOAD, varId);
				st.pushStack();
			}
			cont.accept(st, t);
		} else if (e instanceof CLet) {
			CLet e1 = (CLet) e;
			int varId = st.newLocalVarId();
			emitExpr(e1.getValue(), st, env, (s, t) -> {
				if (t instanceof UnitType) {
					// do nothing
					return;
				} else if (t instanceof IntType) {
					s.getMv().visitVarInsn(ISTORE, varId);
				} else if (t instanceof FloatType) {
					s.getMv().visitVarInsn(DSTORE, varId);
				} else {
					s.getMv().visitVarInsn(ASTORE, varId);
				}
				s.consumeStack(1, 0);
			});
			Map<String, Pair<Type, Integer>> newEnv = new HashMap<>(env);
			newEnv.put(e1.getVar().getLeft(), new Pair<Type, Integer>(e1
					.getVar().getRight(), varId));
			emitExpr(e1.getBody(), st, newEnv, cont);
		} else if (e instanceof CTuple) {
			CTuple e1 = (CTuple) e;
			st.getMv().visitTypeInsn(NEW,
					tupleName(e1.getValues().size()).replace('.', '/'));
			st.pushStack();
			st.getMv().visitInsn(DUP);
			st.pushStack();
			e1.getValues().forEach(
					v -> {
						Type t = env.get(v).getLeft();
						int varId = env.get(v).getRight();
						if (t instanceof BoolType || t instanceof IntType) {
							st.getMv().visitVarInsn(ILOAD, varId);
							st.getMv().visitMethodInsn(INVOKESTATIC,
									"java/lang/Integer", "valueOf",
									"(I)Ljava/lang/Integer;", false);
							st.pushStack();
						} else if (t instanceof FloatType) {
							st.getMv().visitVarInsn(DLOAD, varId);
							st.getMv().visitMethodInsn(INVOKESTATIC,
									"java/lang/Double", "valueOf",
									"(D)Ljava/lang/Double;", false);
							st.pushStack();
						} else {
							st.getMv().visitVarInsn(ALOAD, varId);
							st.pushStack();
						}
					});
			// make tuple
			st.getMv().visitMethodInsn(INVOKESPECIAL,
					tupleName(e1.getValues().size()).replace('.', '/'),
					"<init>", tupleInitSig(e1.getValues().size()), false);
			st.consumeStack(e1.getValues().size(), 1);
			cont.accept(
					st,
					new TupleType(e1.getValues().stream()
							.map(v -> env.get(v).getLeft())
							.collect(Collectors.toList())));
		} else if (e instanceof CLetTuple) {
			CLetTuple e1 = (CLetTuple) e;
			Map<String, Pair<Type, Integer>> newEnv = new HashMap<>(env);
			int tupleId = env.get(e1.getValue()).getRight();
			for (int i = 0; i < e1.getVars().size(); i++) {
				Pair<String, Type> var = e1.getVars().get(i);
				int varId = st.newLocalVarId();
				Type t = var.getRight();
				st.getMv().visitVarInsn(ALOAD, tupleId);
				st.pushStack();
				st.getMv().visitTypeInsn(CHECKCAST,
						tupleName(e1.getVars().size()).replace('.', '/'));
				st.consumeStack(1, 1);
				st.getMv().visitMethodInsn(INVOKEVIRTUAL,
						tupleName(e1.getVars().size()).replace('.', '/'),
						"getVal" + (i + 1), "()Ljava/lang/Object;", false);
				st.consumeStack(1, 1);
				if (t instanceof BoolType || t instanceof IntType) {
					st.getMv().visitTypeInsn(CHECKCAST, "java/lang/Integer");
					st.getMv().visitMethodInsn(INVOKEVIRTUAL,
							"java/lang/Integer", "intValue", "()I", false);
					st.consumeStack(1, 1);
					st.getMv().visitVarInsn(ISTORE, varId);
					st.consumeStack(1, 0);
				} else if (t instanceof FloatType) {
					st.getMv().visitTypeInsn(CHECKCAST, "java/lang/Double");
					st.getMv().visitMethodInsn(INVOKEVIRTUAL,
							"java/lang/Double", "doubleValue", "()D", false);
					st.consumeStack(1, 1);
					st.getMv().visitVarInsn(DSTORE, varId);
					st.consumeStack(1, 0);
				} else {
					st.getMv().visitVarInsn(ASTORE, varId);
					st.consumeStack(1, 0);
				}

				newEnv.put(var.getLeft(), new Pair<>(t, varId));
			}

			emitExpr(e1.getBody(), st, newEnv, cont);
		} else if (e instanceof CAppDir) {
			CAppDir e1 = (CAppDir) e;
			String fn = e1.getFunc().getName();
			String cls;
			FunType ft;
			String prefix = "min_caml_";
			if (fn.startsWith(prefix)) {
				cls = "mincamlj.runtime.Prelude";
				ft = (FunType) Typing.extEnv.get(fn.substring(prefix.length()));
			} else {
				cls = this.className;
				ft = (FunType) funcEnv.get(fn);
			}
			MethodType mt = MethodType.methodType(
					t2c(ft.getReturned()),
					ft.getParams().stream().map(t -> t2c(t))
							.collect(Collectors.toList()));

			for (String a : e1.getArgs()) {
				Type t = env.get(a).getLeft();
				int varId = env.get(a).getRight();
				if (t instanceof IntType) {
					st.getMv().visitVarInsn(ILOAD, varId);
					st.pushStack();
				} else if (t instanceof FloatType) {
					st.getMv().visitVarInsn(DLOAD, varId);
					st.pushStack();
				} else {
					st.getMv().visitVarInsn(ALOAD, varId);
					st.pushStack();
				}
			}

			st.getMv().visitMethodInsn(INVOKESTATIC, cls.replace('.', '/'), fn,
					mt.toMethodDescriptorString(), false);
			st.consumeStack(ft.getParams().size(), 1);
			cont.accept(st, ft.getReturned());
		} else if (e instanceof CAppCls) {
			CAppCls e1 = (CAppCls) e;
			FunType ftype = (FunType) env.get(e1.getFunc()).getLeft();
			int fvarId = env.get(e1.getFunc()).getRight();
			List<Type> ptypes = e1.getArgs().stream()
					.map(a -> env.get(a).getLeft())
					.collect(Collectors.toList());

			// closureをロード
			st.getMv().visitVarInsn(load(ftype), fvarId);
			st.pushStack();

			// キャスト
			Class<?> clsType = functionType(ptypes, ftype.getReturned());
			st.getMv().visitTypeInsn(Opcodes.CHECKCAST,
					clsType.getName().replace('.', '/'));

			for (int i = 0; i < e1.getArgs().size(); i++) {
				String arg = e1.getArgs().get(i);
				Type atype = env.get(arg).getLeft();
				int avarId = env.get(arg).getRight();
				Class<?> lambdaType = functionType(
						ptypes.subList(i, ptypes.size()), ftype.getReturned());
				Class<?> rtype = functionType(
						ptypes.subList(i + 1, ptypes.size()),
						ftype.getReturned());
				String apply = (i < e1.getArgs().size() - 1) ? "apply"
						: applyName(ftype.getReturned());

				st.getMv().visitVarInsn(load(atype), avarId);
				st.pushStack();
				if (i < e1.getArgs().size() - 1) {
					st.getMv().visitMethodInsn(Opcodes.INVOKEINTERFACE,
							lambdaType.getName().replace('.', '/'), apply,
							"(I)Ljava/lang/Object;", true);
					st.consumeStack(2, 1);
					st.getMv().visitTypeInsn(Opcodes.CHECKCAST,
							rtype.getName().replace('.', '/'));
				} else {
					st.getMv().visitMethodInsn(
							Opcodes.INVOKEINTERFACE,
							lambdaType.getName().replace('.', '/'),
							apply,
							MethodType.methodType(t2c(ftype.getReturned()),
									t2c(atype)).toMethodDescriptorString(),
							true);
					st.consumeStack(2, 1);
				}
			}
			cont.accept(st, ftype.getReturned());

		} else if (e instanceof CMakeCls) {
			CMakeCls e1 = (CMakeCls) e;
			String fname = e1.getEntry().getName();
			FunType ftype = (FunType) e1.getName().getRight();
			Function<Type, Class<?>> getRetType = t -> {
				if (t instanceof BoolType || t instanceof IntType) {
					return IntFunction.class;
				} else if (t instanceof FloatType) {
					return DoubleFunction.class;
				} else {
					return Function.class;
				}
			};

			// 部分適用開始用の関数の参照を取り出す
			Class<?> rtype;
			if (e1.getFreeVars().isEmpty()) {
				rtype = functionType(ftype.getParams(), ftype.getReturned());
			} else {
				rtype = getRetType.apply(env.get(e1.getFreeVars().get(0))
						.getLeft());
			}
			st.getMv().visitMethodInsn(Opcodes.INVOKESTATIC,
					className.replace('.', '/'), fname + "$",
					MethodType.methodType(rtype).toMethodDescriptorString(),
					false);
			// 自由変数を順に適用
			// プリミティブな値が戻ってこないのでFunction, IntFunction, DoubleFunctionのいずれか
			for (int i = 0; i < e1.getFreeVars().size(); i++) {
				String fv = e1.getFreeVars().get(i);
				Type fvtype = env.get(fv).getLeft();
				int varId = env.get(fv).getRight();

				st.getMv().visitVarInsn(load(fvtype), varId);
				st.pushStack();

				// 適用
				st.getMv().visitMethodInsn(
						Opcodes.INVOKEINTERFACE,
						rtype.getName().replace('.', '/'),
						"apply",
						MethodType.methodType(Object.class, t2c(fvtype))
								.toMethodDescriptorString(), true);
				st.consumeStack(2, 1);

				if (i < e1.getFreeVars().size() - 1) {
					// 戻り値の型
					rtype = getRetType.apply(env.get(
							e1.getFreeVars().get(i + 1)).getLeft());
					// キャスト
					st.getMv().visitTypeInsn(Opcodes.CHECKCAST,
							rtype.getName().replace('.', '/'));
				}
			}

			int clsvarId = st.newLocalVarId();
			st.getMv().visitVarInsn(ASTORE, clsvarId);
			st.consumeStack(1, 0);

			// closureをenvに登録
			Map<String, Pair<Type, Integer>> newEnv = new HashMap<>(env);
			newEnv.put(e1.getName().getLeft(), new Pair<Type, Integer>(e1
					.getName().getRight(), clsvarId));

			// 後続を変換
			emitExpr(e1.getBody(), st, newEnv, cont);

		} else if (e instanceof CGet) {
			CGet e1 = (CGet) e;
			Type t = ((ArrayType) env.get(e1.getArray()).getLeft()).getInner();
			int varId = env.get(e1.getArray()).getRight();
			st.getMv().visitVarInsn(ALOAD, varId);
			st.pushStack();
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getIndex()).getRight());
			st.pushStack();
			if (t instanceof BoolType || t instanceof IntType) {
				st.getMv().visitInsn(IALOAD);
				st.consumeStack(2, 1);
			} else if (t instanceof FloatType) {
				st.getMv().visitInsn(DALOAD);
				st.consumeStack(2, 1);
			} else {
				throw new RuntimeException();
			}
			cont.accept(st, t);
		} else if (e instanceof CPut) {
			CPut e1 = (CPut) e;
			Type t = ((ArrayType) env.get(e1.getArray()).getLeft()).getInner();
			int varId = env.get(e1.getArray()).getRight();
			st.getMv().visitVarInsn(ALOAD, varId);
			st.pushStack();
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getIndex()).getRight());
			st.pushStack();
			if (t instanceof BoolType || t instanceof IntType) {
				st.getMv().visitVarInsn(ILOAD,
						env.get(e1.getValue()).getRight());
				st.pushStack();
				st.getMv().visitInsn(IASTORE);
				st.consumeStack(3, 0);
			} else if (t instanceof FloatType) {
				st.getMv().visitVarInsn(DLOAD,
						env.get(e1.getValue()).getRight());
				st.pushStack();
				st.getMv().visitInsn(DASTORE);
				st.consumeStack(3, 0);
			} else {
				throw new RuntimeException();
			}
		} else if (e instanceof CExtArray) {
			// TODO
		}
	}

	public List<Pair<String, Type>> funDefParams(CFunDef funDef) {
		List<Pair<String, Type>> plist = new ArrayList<>();
		plist.addAll(funDef.getFreeVars());
		plist.addAll(funDef.getParams());
		return plist;
	}

	public void emitFunDef(CFunDef funDef, ClassWriter cw) {
		String fname = funDef.getName().getLeft().getName();
		FunType t = (FunType) funDef.getName().getRight();
		Class<?> rtype = t2c(t.getReturned());
		List<Class<?>> ptypes = new ArrayList<Class<?>>();
		EmitState st = new EmitState();
		Map<String, Pair<Type, Integer>> env = new HashMap<>();
		List<Pair<String, Type>> plist = funDefParams(funDef);
		plist.forEach(p -> {
			ptypes.add(t2c(p.getRight()));
			env.put(p.getLeft(), new Pair<>(p.getRight(), st.newLocalVarId()));
		});

		// function
		MethodType ftype = MethodType.methodType(rtype, ptypes);
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, fname,
				ftype.toMethodDescriptorString(), null, null);
		st.setMv(mv);
		mv.visitCode();
		st.newLocalVarId();
		emitExpr(funDef.getBody(), st, env, defaultCont);
		mv.visitMaxs(st.getMaxStack(), st.getMaxLocals());
		mv.visitEnd();

		// partial
		emitPartial(fname, ftype, 0, plist.stream().map(p -> p.getRight())
				.collect(Collectors.toList()), t.getReturned(), cw);
	}

	private int load(Type t) {
		if (t instanceof BoolType || t instanceof IntType) {
			return ILOAD;
		} else if (t instanceof FloatType) {
			return DLOAD;
		} else {
			return ALOAD;
		}
	}

	private String repeat(String s, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++)
			sb.append(s);
		return sb.toString();
	}

	/**
	 * <pre>
	 * {@code
	 * f : int -> int -> float -> int
	 * f$ : () -> IntFunction
	 * f$$ : int -> IntFunction
	 * f$$$ : int -> DoubleToIntFunction
	 * }
	 * </pre>
	 */
	public void emitPartial(String originName, MethodType originType, int n,
			List<Type> ptypes, Type rtype, ClassWriter cw) {
		boolean isTerminate = n >= (ptypes.size() - 1);
		String f1name = originName + repeat("$", n + 1);
		MethodType f1type;
		if (n == 0) {
			f1type = MethodType.methodType(functionType(ptypes, rtype));
		} else {
			f1type = MethodType.methodType(
					functionType(ptypes.subList(n, ptypes.size()), rtype),
					ptypes.subList(0, n).stream().map(p -> t2c(p))
							.collect(Collectors.toList()));
		}

		String f2name = isTerminate ? originName : originName
				+ repeat("$", n + 2);
		MethodType f2type = isTerminate ? originType : MethodType.methodType(
				functionType(ptypes.subList(n + 1, ptypes.size()), rtype),
				ptypes.subList(0, n + 1).stream().map(p -> t2c(p))
						.collect(Collectors.toList()));

		MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC
				+ Opcodes.ACC_STATIC, f1name,
				f1type.toMethodDescriptorString(), null, null);
		mv.visitCode();

		Handle bootstrap = new Handle(Opcodes.H_INVOKESTATIC,
				"java/lang/invoke/LambdaMetafactory", "metafactory", MethodType
						.methodType(CallSite.class, MethodHandles.Lookup.class,
								String.class, MethodType.class,
								MethodType.class, MethodHandle.class,
								MethodType.class).toMethodDescriptorString());

		MethodType instantiatedMethodType = MethodType.methodType(
				functionType(ptypes.subList(n + 1, ptypes.size()), rtype),
				t2c(ptypes.get(n)));
		MethodType samMethodType = MethodType.methodType(
				genericType(instantiatedMethodType.returnType()),
				t2c(ptypes.get(n)));

		Handle nextFnHandle = new Handle(Opcodes.H_INVOKESTATIC,
				className.replace('.', '/'), f2name,
				f2type.toMethodDescriptorString());

		// Functionを得るためのメソッドを動的に生成し、実行する
		for (int i = 0; i < n; i++) {
			mv.visitVarInsn(load(ptypes.get(n - 1)), i);
		}

		// Functionを得るためのメソッドを動的に生成し、実行する
		mv.visitInvokeDynamicInsn(
		// Functionを実行するためのメソッド名
				isTerminate ? applyName(rtype) : "apply",
				// 動的に生成したメソッド(Functionを得るためのメソッド)の型
				// invokeDynamicで実行されるメソッドの型
				f1type.toMethodDescriptorString(),
				// ブートストラップオブジェクト(ここではLambdaMetafactory.metafactory)
				bootstrap,
				// applyの型(genericな型)
				org.objectweb.asm.Type.getType(samMethodType
						.toMethodDescriptorString()),
				// apply実行時に実行するメソッド
				// InvokeDynamic前に積まれた引数とapplyに渡された引数が全て引数として渡される
				nextFnHandle,
				// applyの型(specializedされた型)
				org.objectweb.asm.Type.getType(instantiatedMethodType
						.toMethodDescriptorString()));

		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(n, n);
		mv.visitEnd();

		if (!isTerminate) {
			emitPartial(originName, originType, n + 1, ptypes, rtype, cw);
		}
	}

	private String applyName(Type rtype) {
		if (rtype instanceof BoolType || rtype instanceof IntType) {
			return "applyAsInt";
		} else if (rtype instanceof FloatType) {
			return "applyAsDouble";
		} else {
			return "apply";
		}
	}

	private Class<?> genericType(Class<?> klass) {
		if (klass.isPrimitive()) {
			return klass;
		} else {
			return Object.class;
		}
	}

	private Class<?> functionType(List<Type> ptypes, Type rtype) {
		if (ptypes.size() > 1) {
			Type ptype = ptypes.get(0);
			if (ptype instanceof BoolType || ptype instanceof IntType) {
				return IntFunction.class;
			} else if (ptype instanceof FloatType) {
				return DoubleFunction.class;
			} else {
				return Function.class;
			}
		} else if (ptypes.size() == 1) {
			Type ptype = ptypes.get(0);
			if (ptype instanceof BoolType || ptype instanceof IntType) {
				if (rtype instanceof BoolType || rtype instanceof IntType) {
					return IntUnaryOperator.class;
				} else if (rtype instanceof FloatType) {
					return IntToDoubleFunction.class;
				} else {
					return IntFunction.class;
				}
			} else if (ptype instanceof FloatType) {
				if (rtype instanceof BoolType || rtype instanceof IntType) {
					return DoubleToIntFunction.class;
				} else if (rtype instanceof FloatType) {
					return DoubleUnaryOperator.class;
				} else {
					return DoubleFunction.class;
				}
			} else {
				if (rtype instanceof BoolType || rtype instanceof IntType) {
					return ToIntFunction.class;
				} else if (rtype instanceof FloatType) {
					return ToDoubleFunction.class;
				} else {
					return Function.class;
				}
			}
		} else {
			if (rtype instanceof BoolType || rtype instanceof IntType) {
				return int.class;
			} else if (rtype instanceof FloatType) {
				return double.class;
			} else {
				return Object.class;
			}
		}
	}

	public void emitMain(ClosureExpr e, ClassWriter cw) {
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
				"([Ljava/lang/String;)V", null, null);
		mv.visitCode();

		EmitState st = new EmitState(mv);
		st.newLocalVarId();

		emitExpr(e, st, new HashMap<>(), defaultCont);

		mv.visitMaxs(st.getMaxStack(), st.getMaxLocals());
		mv.visitEnd();

		// -------------
		// mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
		// "Ljava/io/PrintStream;");
		//
		// mv.visitMethodInsn(Opcodes.INVOKESTATIC, className, "add.5$",
		// "()Ljava/util/function/IntFunction;", false);
		//
		// mv.visitIntInsn(Opcodes.BIPUSH, 12);
		// mv.visitMethodInsn(Opcodes.INVOKEINTERFACE,
		// "java/util/function/IntFunction", "apply",
		// "(I)Ljava/lang/Object;", true);
		// mv.visitTypeInsn(Opcodes.CHECKCAST,
		// "java/util/function/IntFunction");
		//
		// mv.visitIntInsn(Opcodes.BIPUSH, 34);
		// mv.visitMethodInsn(Opcodes.INVOKEINTERFACE,
		// "java/util/function/IntFunction", "apply",
		// "(I)Ljava/lang/Object;", true);
		// mv.visitTypeInsn(Opcodes.CHECKCAST,
		// "java/util/function/IntUnaryOperator");
		//
		// mv.visitIntInsn(Opcodes.BIPUSH, 56);
		// mv.visitMethodInsn(Opcodes.INVOKEINTERFACE,
		// "java/util/function/IntUnaryOperator", "applyAsInt", "(I)I",
		// true);
		//
		// mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer",
		// "valueOf", "(I)Ljava/lang/Integer;", false);
		//
		// mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
		// "println", "(Ljava/lang/Object;)V", false);
		//
		// mv.visitInsn(Opcodes.RETURN);
		//
		// mv.visitMaxs(2, 1);
		// mv.visitEnd();
		// -------------

	}

	private String className;
	private Map<String, CFunDef> funDefs;
	private Map<String, Type> funcEnv;

	public Emit(String className) {
		super();
		this.className = className;
	}

	public byte[] emit(CProg prog) {
		funDefs = new HashMap<>();
		funcEnv = new HashMap<>();
		prog.getFunDefs().forEach(
				f -> {
					funDefs.put(f.getName().getLeft().getName(), f);
					funcEnv.put(f.getName().getLeft().getName(), f.getName()
							.getRight());
				});

		// ClassWriter cw = new ClassWriter(0);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, className.replace('.', '/'),
				null, "java/lang/Object", null);

		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null,
				null);
		mv.visitCode();
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",
				false);
		mv.visitInsn(RETURN);
		mv.visitMaxs(1, 1);
		mv.visitEnd();

		prog.getFunDefs().forEach(f -> emitFunDef(f, cw));
		emitMain(prog.getExpr(), cw);

		return cw.toByteArray();
	}
}
