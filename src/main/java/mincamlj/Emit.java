package mincamlj;

import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
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
import mincamlj.type.BoolType;
import mincamlj.type.FloatType;
import mincamlj.type.FunType;
import mincamlj.type.IntType;
import mincamlj.type.TupleType;
import mincamlj.type.Type;
import mincamlj.type.UnitType;
import mincamlj.util.Pair;

import org.objectweb.asm.ClassWriter;
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
			st.getMv().visitInsn(FRETURN);
		} else {
			st.getMv().visitInsn(ARETURN);
		}
	};

	private Class<?> t2c(Type t) {
		if (t instanceof BoolType) {
			return int.class;
		} else if (t instanceof IntType) {
			return int.class;
		} else if (t instanceof FloatType) {
			return float.class;
		}
		return void.class;
	}

	private Object t2o(Type t) {
		if (t instanceof BoolType) {
			return INTEGER;
		} else if (t instanceof IntType) {
			return INTEGER;
		} else if (t instanceof FloatType) {
			return FLOAT;
		}
		return "java/lang/Object";
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

		private List<Object> locals;
		private List<Object> stack;

		public EmitState(MethodVisitor mv) {
			super();
			this.mv = mv;
			this.locals = new ArrayList<Object>();
			this.stack = new ArrayList<Object>();
		}

		public EmitState(MethodVisitor mv, int localVarId, int maxStack,
				List<Object> locals, List<Object> stack) {
			super();
			this.mv = mv;
			this.localVarId = localVarId;
			this.maxStack = maxStack;
			this.locals = new ArrayList<Object>(locals);
			this.stack = new ArrayList<Object>(stack);
		}

		public EmitState newState() {
			return new EmitState(mv, localVarId, maxStack, locals, stack);
		}

		public MethodVisitor getMv() {
			return mv;
		}

		public void setLocalVarId(int varId) {
			localVarId = varId;
		}

		public int getLocalVarId() {
			return localVarId;
		}

		public int newLocalVarId() {
			return localVarId++;
		}

		public void addLocals(Object o) {
			locals.add(o);
		}

		public void pushStack(Object o) {
			stack.add(o);
			maxStack = Math.max(maxStack, stack.size());
		}

		public void consumeStack(int num) {
			stack = stack.subList(0, stack.size() - num);
		}

		public int getMaxLocals() {
			return localVarId;
		}

		public void setMaxStackIfLarge(int num) {
			maxStack = Math.max(maxStack, num);
		}

		public int getMaxStack() {
			return maxStack;
		}

		public Object[] getLocals() {
			return locals.toArray();
		}

		public Object[] getStack() {
			return stack.toArray();
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
			st.pushStack(INTEGER);
			cont.accept(st, IntType.getInstance());
		} else if (e instanceof CFloat) {
			CFloat e1 = (CFloat) e;
			st.getMv().visitLdcInsn(new Float(e1.getValue()));
			st.pushStack(FLOAT);
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CNeg) {
			CNeg e1 = (CNeg) e;
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getInner()).getRight());
			st.pushStack(INTEGER);
			st.getMv().visitInsn(INEG);
			cont.accept(st, IntType.getInstance());
		} else if (e instanceof CAdd) {
			CAdd e1 = (CAdd) e;
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getLeft()).getRight());
			st.pushStack(INTEGER);
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getRight()).getRight());
			st.pushStack(INTEGER);
			st.getMv().visitInsn(IADD);
			st.consumeStack(1);
			cont.accept(st, IntType.getInstance());
		} else if (e instanceof CSub) {
			CSub e1 = (CSub) e;
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getLeft()).getRight());
			st.pushStack(INTEGER);
			st.getMv().visitVarInsn(ILOAD, env.get(e1.getRight()).getRight());
			st.pushStack(INTEGER);
			st.getMv().visitInsn(ISUB);
			st.consumeStack(1);
			cont.accept(st, IntType.getInstance());
		} else if (e instanceof CFNeg) {
			CFNeg e1 = (CFNeg) e;
			st.getMv().visitVarInsn(FLOAD, env.get(e1.getInner()).getRight());
			st.pushStack(FLOAT);
			st.getMv().visitInsn(FNEG);
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CFAdd) {
			CFAdd e1 = (CFAdd) e;
			st.getMv().visitVarInsn(FLOAD, env.get(e1.getLeft()).getRight());
			st.pushStack(FLOAT);
			st.getMv().visitVarInsn(FLOAD, env.get(e1.getRight()).getRight());
			st.pushStack(FLOAT);
			st.getMv().visitInsn(FADD);
			st.consumeStack(1);
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CFSub) {
			CFSub e1 = (CFSub) e;
			st.getMv().visitVarInsn(FLOAD, env.get(e1.getLeft()).getRight());
			st.pushStack(FLOAT);
			st.getMv().visitVarInsn(FLOAD, env.get(e1.getRight()).getRight());
			st.pushStack(FLOAT);
			st.getMv().visitInsn(FSUB);
			st.consumeStack(1);
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CFMul) {
			CFMul e1 = (CFMul) e;
			st.getMv().visitVarInsn(FLOAD, env.get(e1.getLeft()).getRight());
			st.pushStack(FLOAT);
			st.getMv().visitVarInsn(FLOAD, env.get(e1.getRight()).getRight());
			st.pushStack(FLOAT);
			st.getMv().visitInsn(FMUL);
			st.consumeStack(1);
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CFDiv) {
			CFDiv e1 = (CFDiv) e;
			st.getMv().visitVarInsn(FLOAD, env.get(e1.getLeft()).getRight());
			st.pushStack(FLOAT);
			st.getMv().visitVarInsn(FLOAD, env.get(e1.getRight()).getRight());
			st.pushStack(FLOAT);
			st.getMv().visitInsn(FDIV);
			st.consumeStack(1);
			cont.accept(st, FloatType.getInstance());
		} else if (e instanceof CIfEq) {
			CIfEq e1 = (CIfEq) e;
			Type t1 = env.get(e1.getLeft()).getLeft();
			Label branch = new Label();
			Label end = new Label();
			if (t1 instanceof BoolType || t1 instanceof IntType) {
				st.getMv()
						.visitVarInsn(ILOAD, env.get(e1.getLeft()).getRight());
				st.pushStack(INTEGER);
				st.getMv().visitVarInsn(ILOAD,
						env.get(e1.getRight()).getRight());
				st.pushStack(INTEGER);
				st.getMv().visitInsn(ISUB);
				st.consumeStack(1);
				st.getMv().visitJumpInsn(IFEQ, branch);
				st.consumeStack(1);
				EmitState fState = st.newState();
				EmitState tState = st.newState();
				// false
				emitExpr(e1.getFalseExpr(), fState, env, cont);
				// true
				tState.getMv().visitLabel(branch);
				tState.getMv().visitFrame(F_FULL, tState.getLocals().length,
						tState.getLocals(), tState.getStack().length,
						tState.getStack());

				if (cont == defaultCont) {
					emitExpr(e1.getTrueExpr(), tState, env, cont);
				} else {
					emitExpr(e1.getTrueExpr(), tState, env, (s, t) -> {
						cont.accept(s, t);
						s.getMv().visitJumpInsn(GOTO, end);
					});
				}

				st.setMaxStackIfLarge(Math.max(fState.getMaxStack(),
						tState.getMaxStack()));
				st.setLocalVarId(Math.max(fState.getLocalVarId(),
						tState.getLocalVarId()));
			} else if (t1 instanceof FloatType) {
				st.getMv()
						.visitVarInsn(FLOAD, env.get(e1.getLeft()).getRight());
				st.pushStack(FLOAT);
				st.getMv().visitVarInsn(FLOAD,
						env.get(e1.getRight()).getRight());
				st.pushStack(FLOAT);
				st.getMv().visitInsn(FSUB);
				st.consumeStack(1);
				st.getMv().visitJumpInsn(IFEQ, branch);
				st.consumeStack(1);
				EmitState fState = st.newState();
				EmitState tState = st.newState();
				// false
				emitExpr(e1.getFalseExpr(), fState, env, cont);
				// true
				tState.getMv().visitLabel(branch);
				tState.getMv().visitFrame(F_FULL, tState.getLocals().length,
						tState.getLocals(), tState.getStack().length,
						tState.getStack());
				if (cont == defaultCont) {
					emitExpr(e1.getTrueExpr(), tState, env, cont);
				} else {
					emitExpr(e1.getTrueExpr(), tState, env, (s, t) -> {
						cont.accept(s, t);
						s.getMv().visitJumpInsn(GOTO, end);
					});
				}

				st.setMaxStackIfLarge(Math.max(fState.getMaxStack(),
						tState.getMaxStack()));
				st.setLocalVarId(Math.max(fState.getLocalVarId(),
						tState.getLocalVarId()));
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
				st.pushStack(INTEGER);
				st.getMv().visitVarInsn(ILOAD,
						env.get(e1.getRight()).getRight());
				st.pushStack(INTEGER);
				st.getMv().visitInsn(ISUB);
				st.consumeStack(1);
				st.getMv().visitJumpInsn(IFLE, branch);
				st.consumeStack(1);
				EmitState fState = st.newState();
				EmitState tState = st.newState();
				// false
				emitExpr(e1.getFalseExpr(), fState, env, cont);
				// true
				tState.getMv().visitLabel(branch);
				tState.getMv().visitFrame(F_FULL, tState.getLocals().length,
						tState.getLocals(), tState.getStack().length,
						tState.getStack());

				if (cont == defaultCont) {
					emitExpr(e1.getTrueExpr(), tState, env, cont);
				} else {
					emitExpr(e1.getTrueExpr(), tState, env, (s, t) -> {
						cont.accept(s, t);
						s.getMv().visitJumpInsn(GOTO, end);
					});
				}

				st.setMaxStackIfLarge(Math.max(fState.getMaxStack(),
						tState.getMaxStack()));
				st.setLocalVarId(Math.max(fState.getLocalVarId(),
						tState.getLocalVarId()));
			} else if (t1 instanceof FloatType) {
				st.getMv()
						.visitVarInsn(FLOAD, env.get(e1.getLeft()).getRight());
				st.pushStack(FLOAT);
				st.getMv().visitVarInsn(FLOAD,
						env.get(e1.getRight()).getRight());
				st.pushStack(FLOAT);
				st.getMv().visitInsn(FSUB);
				st.consumeStack(1);
				st.getMv().visitJumpInsn(IFEQ, branch);
				st.consumeStack(1);
				EmitState fState = st.newState();
				EmitState tState = st.newState();
				// false
				emitExpr(e1.getFalseExpr(), fState, env, cont);
				// true
				tState.getMv().visitLabel(branch);
				tState.getMv().visitFrame(F_FULL, tState.getLocals().length,
						tState.getLocals(), tState.getStack().length,
						tState.getStack());
				if (cont == defaultCont) {
					emitExpr(e1.getTrueExpr(), tState, env, cont);
				} else {
					emitExpr(e1.getTrueExpr(), tState, env, (s, t) -> {
						cont.accept(s, t);
						s.getMv().visitJumpInsn(GOTO, end);
					});
				}

				st.setMaxStackIfLarge(Math.max(fState.getMaxStack(),
						tState.getMaxStack()));
				st.setLocalVarId(Math.max(fState.getLocalVarId(),
						tState.getLocalVarId()));
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
				st.pushStack(t2o(t));
			} else if (t instanceof FloatType) {
				st.getMv().visitVarInsn(FLOAD, varId);
				st.pushStack(t2o(t));
			} else {
				st.getMv().visitVarInsn(ALOAD, varId);
				st.pushStack(t2o(t));
			}
			cont.accept(st, t);
		} else if (e instanceof CLet) {
			CLet e1 = (CLet) e;
			int varId = st.newLocalVarId();
			emitExpr(e1.getValue(), st, env, (s, t) -> {
				if (t instanceof UnitType) {
					// do nothing
				} else if (t instanceof IntType) {
					s.getMv().visitVarInsn(ISTORE, varId);
					s.consumeStack(1);
				} else if (t instanceof FloatType) {
					s.getMv().visitVarInsn(FSTORE, varId);
					s.consumeStack(1);
				} else {
					s.getMv().visitVarInsn(ASTORE, varId);
					s.consumeStack(1);
				}
			});
			Map<String, Pair<Type, Integer>> newEnv = new HashMap<>(env);
			newEnv.put(e1.getVar().getLeft(), new Pair<Type, Integer>(e1
					.getVar().getRight(), varId));
			st.addLocals(t2o(e1.getVar().getRight()));
			emitExpr(e1.getBody(), st, newEnv, cont);
		} else if (e instanceof CTuple) {
			CTuple e1 = (CTuple) e;
			e1.getValues().forEach(
					v -> {
						Type t = env.get(v).getLeft();
						int varId = env.get(v).getRight();
						if (t instanceof BoolType || t instanceof IntType) {
							st.getMv().visitVarInsn(ILOAD, varId);
							st.getMv().visitMethodInsn(INVOKESTATIC,
									"java/lang/Integer", "valueOf",
									"(I)Ljava/lang/Integer;", false);
							st.pushStack("java/lang/Integer");
						} else if (t instanceof FloatType) {
							st.getMv().visitVarInsn(FLOAD, varId);
							st.getMv().visitMethodInsn(INVOKESTATIC,
									"java/lang/Float", "valueOf",
									"(F)Ljava/lang/Float;", false);
							st.pushStack("java/lang/Float");
						} else {
							st.getMv().visitVarInsn(ALOAD, varId);
							st.pushStack(t2o(t));
						}
					});

			st.getMv().visitMethodInsn(INVOKESPECIAL,
					tupleName(e1.getValues().size()), "<init>",
					tupleInitSig(e1.getValues().size()), false);
			st.consumeStack(e1.getValues().size() - 1);

			cont.accept(
					st,
					new TupleType(e1.getValues().stream()
							.map(v -> env.get(v).getLeft())
							.collect(Collectors.toList())));
		} else if (e instanceof CLetTuple) {
			CLetTuple e1 = (CLetTuple) e;
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
					st.pushStack(t2o(t));
				} else if (t instanceof FloatType) {
					st.getMv().visitVarInsn(FLOAD, varId);
					st.pushStack(t2o(t));
				} else {
					st.getMv().visitVarInsn(ALOAD, varId);
					st.pushStack(t2o(t));
				}
			}

			st.getMv().visitMethodInsn(INVOKESTATIC, cls.replace('.', '/'), fn,
					mt.toMethodDescriptorString(), false);
			st.consumeStack(ft.getParams().size() - 1);
			cont.accept(st, ft.getReturned());
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
	}

	public void emitFunDef(CFunDef funDef, ClassWriter cw) {

	}

	public void emitMain(ClosureExpr e, ClassWriter cw) {
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
				"([Ljava/lang/String;)V", null, null);
		mv.visitCode();

		EmitState st = new EmitState(mv);
		emitExpr(e, st, new HashMap<>(), defaultCont);

		mv.visitMaxs(st.getMaxStack(), st.getMaxLocals());
		mv.visitEnd();
	}

	private String className;
	private Map<String, Type> funcEnv;

	public Emit(String className) {
		super();
		this.className = className;
	}

	public byte[] emit(CProg prog) {
		funcEnv = new HashMap<>();
		prog.getFunDefs().forEach(
				f -> funcEnv.put(f.getName().getLeft().getName(), f.getName()
						.getRight()));

		ClassWriter cw = new ClassWriter(0);
		// ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, className, null,
				"java/lang/Object", null);

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
