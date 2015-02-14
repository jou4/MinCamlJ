// Generated from D:\m122023\code\workspaces\mincamlj\mincamlj\src\main\resources\MinCaml.g4 by ANTLR 4.5
package mincamlj.parser.grammer;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MinCamlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MinCamlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Float}
	 * labeled alternative in {@link MinCamlParser#simple_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(MinCamlParser.FloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ident}
	 * labeled alternative in {@link MinCamlParser#simple_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(MinCamlParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Bool}
	 * labeled alternative in {@link MinCamlParser#simple_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(MinCamlParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExp}
	 * labeled alternative in {@link MinCamlParser#simple_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExp(MinCamlParser.ParenExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayGet}
	 * labeled alternative in {@link MinCamlParser#simple_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayGet(MinCamlParser.ArrayGetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Unit}
	 * labeled alternative in {@link MinCamlParser#simple_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnit(MinCamlParser.UnitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Tuple}
	 * labeled alternative in {@link MinCamlParser#simple_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuple(MinCamlParser.TupleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link MinCamlParser#simple_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(MinCamlParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LetRec}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetRec(MinCamlParser.LetRecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Add}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(MinCamlParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code App}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApp(MinCamlParser.AppContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExp}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExp(MinCamlParser.NotExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Sub}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(MinCamlParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessGreater}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessGreater(MinCamlParser.LessGreaterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessEqual}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessEqual(MinCamlParser.LessEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FNeg}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFNeg(MinCamlParser.FNegContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FDiv}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFDiv(MinCamlParser.FDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Neg}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNeg(MinCamlParser.NegContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterEqual}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterEqual(MinCamlParser.GreaterEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equal}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqual(MinCamlParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayCreate}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreate(MinCamlParser.ArrayCreateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleExp}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExp(MinCamlParser.SimpleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Block}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MinCamlParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Let}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet(MinCamlParser.LetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LetTuple}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetTuple(MinCamlParser.LetTupleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Greater}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreater(MinCamlParser.GreaterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FAdd}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFAdd(MinCamlParser.FAddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FSub}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFSub(MinCamlParser.FSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayPut}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayPut(MinCamlParser.ArrayPutContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(MinCamlParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Less}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLess(MinCamlParser.LessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FMul}
	 * labeled alternative in {@link MinCamlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFMul(MinCamlParser.FMulContext ctx);
}