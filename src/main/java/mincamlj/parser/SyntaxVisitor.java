package mincamlj.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mincamlj.Id;
import mincamlj.parser.grammer.MinCamlBaseVisitor;
import mincamlj.parser.grammer.MinCamlParser.AddSubContext;
import mincamlj.parser.grammer.MinCamlParser.AppContext;
import mincamlj.parser.grammer.MinCamlParser.ArrayCreateContext;
import mincamlj.parser.grammer.MinCamlParser.ArrayGetContext;
import mincamlj.parser.grammer.MinCamlParser.ArrayPutContext;
import mincamlj.parser.grammer.MinCamlParser.BlockContext;
import mincamlj.parser.grammer.MinCamlParser.BoolContext;
import mincamlj.parser.grammer.MinCamlParser.EqualContext;
import mincamlj.parser.grammer.MinCamlParser.ExpContext;
import mincamlj.parser.grammer.MinCamlParser.FloatContext;
import mincamlj.parser.grammer.MinCamlParser.GreaterContext;
import mincamlj.parser.grammer.MinCamlParser.GreaterEqualContext;
import mincamlj.parser.grammer.MinCamlParser.IdentContext;
import mincamlj.parser.grammer.MinCamlParser.IfContext;
import mincamlj.parser.grammer.MinCamlParser.IntContext;
import mincamlj.parser.grammer.MinCamlParser.LessContext;
import mincamlj.parser.grammer.MinCamlParser.LessEqualContext;
import mincamlj.parser.grammer.MinCamlParser.LessGreaterContext;
import mincamlj.parser.grammer.MinCamlParser.LetContext;
import mincamlj.parser.grammer.MinCamlParser.LetRecContext;
import mincamlj.parser.grammer.MinCamlParser.LetTupleContext;
import mincamlj.parser.grammer.MinCamlParser.MulDivContext;
import mincamlj.parser.grammer.MinCamlParser.NegContext;
import mincamlj.parser.grammer.MinCamlParser.NotExpContext;
import mincamlj.parser.grammer.MinCamlParser.ParenExpContext;
import mincamlj.parser.grammer.MinCamlParser.TupleContext;
import mincamlj.parser.grammer.MinCamlParser.UnitContext;
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
import mincamlj.type.Type;
import mincamlj.type.UnitType;
import mincamlj.type.VarType;
import mincamlj.util.Pair;

public class SyntaxVisitor extends MinCamlBaseVisitor<SyntaxExpr> {

	private String genIdent(String ident) {
		if ("_".equals(ident)) {
			return Id.genTmp(UnitType.getInstance());
		}
		return ident;
	}

	@Override
	public SyntaxExpr visitFloat(FloatContext ctx) {
		return new SFloat(Float.valueOf(ctx.FLOAT().getText()));
	}

	@Override
	public SyntaxExpr visitIdent(IdentContext ctx) {
		return new SVar(genIdent(ctx.IDENT().getText()));
	}

	@Override
	public SyntaxExpr visitBool(BoolContext ctx) {
		return new SBool("true".equals(ctx.BOOL().getText()) ? true : false);
	}

	@Override
	public SyntaxExpr visitParenExp(ParenExpContext ctx) {
		return ctx.exp().accept(this);
	}

	@Override
	public SyntaxExpr visitArrayGet(ArrayGetContext ctx) {
		return new SGet(ctx.simple_exp().accept(this), ctx.exp().accept(this));
	}

	@Override
	public SyntaxExpr visitUnit(UnitContext ctx) {
		return SUnit.getInstance();
	}

	@Override
	public SyntaxExpr visitTuple(TupleContext ctx) {
		List<SyntaxExpr> values = new ArrayList<>();
		for (ExpContext exp : ctx.exp()) {
			values.add(exp.accept(this));
		}
		return new STuple(values);
	}

	@Override
	public SyntaxExpr visitInt(IntContext ctx) {
		return new SInt(Integer.valueOf(ctx.INT().getText()));
	}

	@Override
	public SyntaxExpr visitLetRec(LetRecContext ctx) {
		List<Pair<String, Type>> params = ctx
				.IDENT()
				.subList(1, ctx.IDENT().size())
				.stream()
				.map(ident -> new Pair<String, Type>(genIdent(ident.getText()),
						VarType.genType())).collect(Collectors.toList());
		SFunDef funDef = new SFunDef(new Pair<String, Type>(genIdent(ctx.IDENT(
				0).getText()), VarType.genType()), params, ctx.exp(0).accept(
				this));
		return new SLetRec(funDef, ctx.exp(1).accept(this));
	}

	@Override
	public SyntaxExpr visitApp(AppContext ctx) {
		List<SyntaxExpr> args = ctx.simple_exp().stream()
				.map(e -> e.accept(this)).collect(Collectors.toList());
		return new SApp(ctx.exp().accept(this), args);
	}

	@Override
	public SyntaxExpr visitNotExp(NotExpContext ctx) {
		return new SNot(ctx.exp().accept(this));
	}

	// @Override
	// public SyntaxExpr visitFDiv(FDivContext ctx) {
	// return new SFDiv(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
	// }

	@Override
	public SyntaxExpr visitLessGreater(LessGreaterContext ctx) {
		return new SNot(new SEq(ctx.exp(0).accept(this), ctx.exp(1)
				.accept(this)));
	}

	// @Override
	// public SyntaxExpr visitFNeg(FNegContext ctx) {
	// return new SFNeg(ctx.exp().accept(this));
	// }

	// @Override
	// public SyntaxExpr visitNeg(NegContext ctx) {
	// return new SNeg(ctx.exp().accept(this));
	// }

	@Override
	public SyntaxExpr visitLessEqual(LessEqualContext ctx) {
		return new SLe(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
	}

	// @Override
	// public SyntaxExpr visitFAdd(FAddContext ctx) {
	// return new SFAdd(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
	// }

	// @Override
	// public SyntaxExpr visitSub(SubContext ctx) {
	// return new SSub(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
	// }

	@Override
	public SyntaxExpr visitGreaterEqual(GreaterEqualContext ctx) {
		return new SLe(ctx.exp(1).accept(this), ctx.exp(0).accept(this));
	}

	// @Override
	// public SyntaxExpr visitFMul(FMulContext ctx) {
	// return new SFMul(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
	// }

	@Override
	public SyntaxExpr visitEqual(EqualContext ctx) {
		return new SEq(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
	}

	@Override
	public SyntaxExpr visitArrayCreate(ArrayCreateContext ctx) {
		return new SArray(ctx.simple_exp(0).accept(this), ctx.simple_exp(1)
				.accept(this));
	}

	@Override
	public SyntaxExpr visitBlock(BlockContext ctx) {
		return new SLet(new Pair<String, Type>(
				Id.genTmp(UnitType.getInstance()), UnitType.getInstance()), ctx
				.exp(0).accept(this), ctx.exp(1).accept(this));
	}

	@Override
	public SyntaxExpr visitLet(LetContext ctx) {
		return new SLet(new Pair<String, Type>(genIdent(ctx.IDENT().getText()),
				VarType.genType()), ctx.exp(0).accept(this), ctx.exp(1).accept(
				this));
	}

	@Override
	public SyntaxExpr visitLetTuple(LetTupleContext ctx) {
		List<Pair<String, Type>> vars = ctx
				.IDENT()
				.stream()
				.map(ident -> new Pair<String, Type>(genIdent(ident.getText()),
						VarType.genType())).collect(Collectors.toList());
		return new SLetTuple(vars, ctx.exp(0).accept(this), ctx.exp(1).accept(
				this));
	}

	@Override
	public SyntaxExpr visitGreater(GreaterContext ctx) {
		return new SNot(new SLe(ctx.exp(0).accept(this), ctx.exp(1)
				.accept(this)));
	}

	// @Override
	// public SyntaxExpr visitFSub(FSubContext ctx) {
	// return new SFSub(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
	// }

	@Override
	public SyntaxExpr visitArrayPut(ArrayPutContext ctx) {
		return new SPut(ctx.simple_exp().accept(this), ctx.exp(0).accept(this),
				ctx.exp(1).accept(this));
	}

	// @Override
	// public SyntaxExpr visitAdd(AddContext ctx) {
	// return new SAdd(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
	// }

	@Override
	public SyntaxExpr visitIf(IfContext ctx) {
		return new SIf(ctx.exp(0).accept(this), ctx.exp(1).accept(this), ctx
				.exp(2).accept(this));
	}

	@Override
	public SyntaxExpr visitLess(LessContext ctx) {
		return new SNot(new SLe(ctx.exp(1).accept(this), ctx.exp(0)
				.accept(this)));
	}

	@Override
	public SyntaxExpr visitNeg(NegContext ctx) {
		if (ctx.MINUS() != null) {
			return new SNeg(ctx.exp().accept(this));
		} else {
			return new SFNeg(ctx.exp().accept(this));
		}
	}

	@Override
	public SyntaxExpr visitMulDiv(MulDivContext ctx) {
		if (ctx.AST_DOT() != null) {
			return new SFMul(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
		} else {
			return new SFDiv(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
		}
	}

	@Override
	public SyntaxExpr visitAddSub(AddSubContext ctx) {
		if (ctx.PLUS() != null) {
			return new SAdd(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
		} else if (ctx.MINUS() != null) {
			return new SSub(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
		} else if (ctx.PLUS_DOT() != null) {
			return new SFAdd(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
		} else {
			return new SFSub(ctx.exp(0).accept(this), ctx.exp(1).accept(this));
		}
	}

}
