// Generated from D:\m122023\code\workspaces\mincamlj\mincamlj\src\main\resources\MinCaml.g4 by ANTLR 4.5
package mincamlj.parser.grammer;

import java.util.List;

import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class MinCamlParser extends Parser {
	static {
		RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int IF = 1, THEN = 2, ELSE = 3, LET = 4, IN = 5,
			REC = 6, NOT = 7, BOOL = 8, LPAREN = 9, RPAREN = 10, PLUS = 11,
			MINUS = 12, PLUS_DOT = 13, MINUS_DOT = 14, AST_DOT = 15,
			SLASH_DOT = 16, EQUAL = 17, LESS_GREATER = 18, LESS_EQUAL = 19,
			GREATER_EQUAL = 20, LESS = 21, GREATER = 22, COMMA = 23, DOT = 24,
			LESS_MINUS = 25, SEMICOLON = 26, ARRAY_CREATE = 27, INT = 28,
			FLOAT = 29, IDENT = 30, WHITESPACE = 31;
	public static final int RULE_simple_exp = 0, RULE_exp = 1;
	public static final String[] ruleNames = { "simple_exp", "exp" };

	private static final String[] _LITERAL_NAMES = { null, "'if'", "'then'",
			"'else'", "'let'", "'in'", "'rec'", "'not'", null, "'('", "')'",
			"'+'", "'-'", "'+.'", "'-.'", "'*.'", "'/.'", "'='", "'<>'",
			"'<='", "'>='", "'<'", "'>'", "','", "'.'", "'<-'", "';'",
			"'Array.make'" };
	private static final String[] _SYMBOLIC_NAMES = { null, "IF", "THEN",
			"ELSE", "LET", "IN", "REC", "NOT", "BOOL", "LPAREN", "RPAREN",
			"PLUS", "MINUS", "PLUS_DOT", "MINUS_DOT", "AST_DOT", "SLASH_DOT",
			"EQUAL", "LESS_GREATER", "LESS_EQUAL", "GREATER_EQUAL", "LESS",
			"GREATER", "COMMA", "DOT", "LESS_MINUS", "SEMICOLON",
			"ARRAY_CREATE", "INT", "FLOAT", "IDENT", "WHITESPACE" };
	public static final Vocabulary VOCABULARY = new VocabularyImpl(
			_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() {
		return "MinCaml.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public MinCamlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA,
				_sharedContextCache);
	}

	public static class Simple_expContext extends ParserRuleContext {
		public Simple_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_simple_exp;
		}

		public Simple_expContext() {
		}

		public void copyFrom(Simple_expContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class FloatContext extends Simple_expContext {
		public TerminalNode FLOAT() {
			return getToken(MinCamlParser.FLOAT, 0);
		}

		public FloatContext(Simple_expContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitFloat(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class IdentContext extends Simple_expContext {
		public TerminalNode IDENT() {
			return getToken(MinCamlParser.IDENT, 0);
		}

		public IdentContext(Simple_expContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitIdent(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class BoolContext extends Simple_expContext {
		public TerminalNode BOOL() {
			return getToken(MinCamlParser.BOOL, 0);
		}

		public BoolContext(Simple_expContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitBool(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ParenExpContext extends Simple_expContext {
		public TerminalNode LPAREN() {
			return getToken(MinCamlParser.LPAREN, 0);
		}

		public ExpContext exp() {
			return getRuleContext(ExpContext.class, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(MinCamlParser.RPAREN, 0);
		}

		public ParenExpContext(Simple_expContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitParenExp(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ArrayGetContext extends Simple_expContext {
		public Simple_expContext simple_exp() {
			return getRuleContext(Simple_expContext.class, 0);
		}

		public TerminalNode DOT() {
			return getToken(MinCamlParser.DOT, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(MinCamlParser.LPAREN, 0);
		}

		public ExpContext exp() {
			return getRuleContext(ExpContext.class, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(MinCamlParser.RPAREN, 0);
		}

		public ArrayGetContext(Simple_expContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitArrayGet(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class UnitContext extends Simple_expContext {
		public TerminalNode LPAREN() {
			return getToken(MinCamlParser.LPAREN, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(MinCamlParser.RPAREN, 0);
		}

		public UnitContext(Simple_expContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitUnit(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class TupleContext extends Simple_expContext {
		public TerminalNode LPAREN() {
			return getToken(MinCamlParser.LPAREN, 0);
		}

		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode RPAREN() {
			return getToken(MinCamlParser.RPAREN, 0);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(MinCamlParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(MinCamlParser.COMMA, i);
		}

		public TupleContext(Simple_expContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitTuple(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class IntContext extends Simple_expContext {
		public TerminalNode INT() {
			return getToken(MinCamlParser.INT, 0);
		}

		public IntContext(Simple_expContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitInt(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Simple_expContext simple_exp() throws RecognitionException {
		return simple_exp(0);
	}

	private Simple_expContext simple_exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Simple_expContext _localctx = new Simple_expContext(_ctx, _parentState);
		Simple_expContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_simple_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(25);
				switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
				case 1: {
					_localctx = new ParenExpContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;

					setState(5);
					match(LPAREN);
					setState(6);
					exp(0);
					setState(7);
					match(RPAREN);
				}
					break;
				case 2: {
					_localctx = new TupleContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(9);
					match(LPAREN);
					setState(10);
					exp(0);
					setState(13);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
							{
								setState(11);
								match(COMMA);
								setState(12);
								exp(0);
							}
						}
						setState(15);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while (_la == COMMA);
					setState(17);
					match(RPAREN);
				}
					break;
				case 3: {
					_localctx = new UnitContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(19);
					match(LPAREN);
					setState(20);
					match(RPAREN);
				}
					break;
				case 4: {
					_localctx = new BoolContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(21);
					match(BOOL);
				}
					break;
				case 5: {
					_localctx = new IntContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(22);
					match(INT);
				}
					break;
				case 6: {
					_localctx = new FloatContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(23);
					match(FLOAT);
				}
					break;
				case 7: {
					_localctx = new IdentContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(24);
					match(IDENT);
				}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(35);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
				while (_alt != 2
						&& _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null)
							triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							{
								_localctx = new ArrayGetContext(
										new Simple_expContext(_parentctx,
												_parentState));
								pushNewRecursionContext(_localctx, _startState,
										RULE_simple_exp);
								setState(27);
								if (!(precpred(_ctx, 1)))
									throw new FailedPredicateException(this,
											"precpred(_ctx, 1)");
								setState(28);
								match(DOT);
								setState(29);
								match(LPAREN);
								setState(30);
								exp(0);
								setState(31);
								match(RPAREN);
							}
						}
					}
					setState(37);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_exp;
		}

		public ExpContext() {
		}

		public void copyFrom(ExpContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class LetRecContext extends ExpContext {
		public TerminalNode LET() {
			return getToken(MinCamlParser.LET, 0);
		}

		public TerminalNode REC() {
			return getToken(MinCamlParser.REC, 0);
		}

		public List<TerminalNode> IDENT() {
			return getTokens(MinCamlParser.IDENT);
		}

		public TerminalNode IDENT(int i) {
			return getToken(MinCamlParser.IDENT, i);
		}

		public TerminalNode EQUAL() {
			return getToken(MinCamlParser.EQUAL, 0);
		}

		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode IN() {
			return getToken(MinCamlParser.IN, 0);
		}

		public LetRecContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitLetRec(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class AppContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class, 0);
		}

		public List<Simple_expContext> simple_exp() {
			return getRuleContexts(Simple_expContext.class);
		}

		public Simple_expContext simple_exp(int i) {
			return getRuleContext(Simple_expContext.class, i);
		}

		public AppContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitApp(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class NotExpContext extends ExpContext {
		public TerminalNode NOT() {
			return getToken(MinCamlParser.NOT, 0);
		}

		public ExpContext exp() {
			return getRuleContext(ExpContext.class, 0);
		}

		public NotExpContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitNotExp(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LessGreaterContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode LESS_GREATER() {
			return getToken(MinCamlParser.LESS_GREATER, 0);
		}

		public LessGreaterContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitLessGreater(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class MulDivContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode AST_DOT() {
			return getToken(MinCamlParser.AST_DOT, 0);
		}

		public TerminalNode SLASH_DOT() {
			return getToken(MinCamlParser.SLASH_DOT, 0);
		}

		public MulDivContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitMulDiv(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class AddSubContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode PLUS() {
			return getToken(MinCamlParser.PLUS, 0);
		}

		public TerminalNode MINUS() {
			return getToken(MinCamlParser.MINUS, 0);
		}

		public TerminalNode PLUS_DOT() {
			return getToken(MinCamlParser.PLUS_DOT, 0);
		}

		public TerminalNode MINUS_DOT() {
			return getToken(MinCamlParser.MINUS_DOT, 0);
		}

		public AddSubContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitAddSub(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LessEqualContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode LESS_EQUAL() {
			return getToken(MinCamlParser.LESS_EQUAL, 0);
		}

		public LessEqualContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitLessEqual(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class NegContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class, 0);
		}

		public TerminalNode MINUS() {
			return getToken(MinCamlParser.MINUS, 0);
		}

		public TerminalNode MINUS_DOT() {
			return getToken(MinCamlParser.MINUS_DOT, 0);
		}

		public NegContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitNeg(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class GreaterEqualContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode GREATER_EQUAL() {
			return getToken(MinCamlParser.GREATER_EQUAL, 0);
		}

		public GreaterEqualContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitGreaterEqual(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class EqualContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode EQUAL() {
			return getToken(MinCamlParser.EQUAL, 0);
		}

		public EqualContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitEqual(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ArrayCreateContext extends ExpContext {
		public TerminalNode ARRAY_CREATE() {
			return getToken(MinCamlParser.ARRAY_CREATE, 0);
		}

		public List<Simple_expContext> simple_exp() {
			return getRuleContexts(Simple_expContext.class);
		}

		public Simple_expContext simple_exp(int i) {
			return getRuleContext(Simple_expContext.class, i);
		}

		public ArrayCreateContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitArrayCreate(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class SimpleExpContext extends ExpContext {
		public Simple_expContext simple_exp() {
			return getRuleContext(Simple_expContext.class, 0);
		}

		public SimpleExpContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitSimpleExp(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class BlockContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode SEMICOLON() {
			return getToken(MinCamlParser.SEMICOLON, 0);
		}

		public BlockContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitBlock(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LetContext extends ExpContext {
		public TerminalNode LET() {
			return getToken(MinCamlParser.LET, 0);
		}

		public TerminalNode IDENT() {
			return getToken(MinCamlParser.IDENT, 0);
		}

		public TerminalNode EQUAL() {
			return getToken(MinCamlParser.EQUAL, 0);
		}

		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode IN() {
			return getToken(MinCamlParser.IN, 0);
		}

		public LetContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitLet(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LetTupleContext extends ExpContext {
		public TerminalNode LET() {
			return getToken(MinCamlParser.LET, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(MinCamlParser.LPAREN, 0);
		}

		public List<TerminalNode> IDENT() {
			return getTokens(MinCamlParser.IDENT);
		}

		public TerminalNode IDENT(int i) {
			return getToken(MinCamlParser.IDENT, i);
		}

		public TerminalNode RPAREN() {
			return getToken(MinCamlParser.RPAREN, 0);
		}

		public TerminalNode EQUAL() {
			return getToken(MinCamlParser.EQUAL, 0);
		}

		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode IN() {
			return getToken(MinCamlParser.IN, 0);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(MinCamlParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(MinCamlParser.COMMA, i);
		}

		public LetTupleContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitLetTuple(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class GreaterContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode GREATER() {
			return getToken(MinCamlParser.GREATER, 0);
		}

		public GreaterContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitGreater(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ArrayPutContext extends ExpContext {
		public Simple_expContext simple_exp() {
			return getRuleContext(Simple_expContext.class, 0);
		}

		public TerminalNode DOT() {
			return getToken(MinCamlParser.DOT, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(MinCamlParser.LPAREN, 0);
		}

		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode RPAREN() {
			return getToken(MinCamlParser.RPAREN, 0);
		}

		public TerminalNode LESS_MINUS() {
			return getToken(MinCamlParser.LESS_MINUS, 0);
		}

		public ArrayPutContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor)
						.visitArrayPut(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class IfContext extends ExpContext {
		public TerminalNode IF() {
			return getToken(MinCamlParser.IF, 0);
		}

		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode THEN() {
			return getToken(MinCamlParser.THEN, 0);
		}

		public TerminalNode ELSE() {
			return getToken(MinCamlParser.ELSE, 0);
		}

		public IfContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitIf(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LessContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class, i);
		}

		public TerminalNode LESS() {
			return getToken(MinCamlParser.LESS, 0);
		}

		public LessContext(ExpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof MinCamlVisitor)
				return ((MinCamlVisitor<? extends T>) visitor).visitLess(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(98);
				switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
				case 1: {
					_localctx = new NotExpContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;

					setState(39);
					match(NOT);
					setState(40);
					exp(18);
				}
					break;
				case 2: {
					_localctx = new NegContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(41);
					_la = _input.LA(1);
					if (!(_la == MINUS || _la == MINUS_DOT)) {
						_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(42);
					exp(17);
				}
					break;
				case 3: {
					_localctx = new SimpleExpContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(43);
					simple_exp(0);
				}
					break;
				case 4: {
					_localctx = new IfContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(44);
					match(IF);
					setState(45);
					exp(0);
					setState(46);
					match(THEN);
					setState(47);
					exp(0);
					setState(48);
					match(ELSE);
					setState(49);
					exp(0);
				}
					break;
				case 5: {
					_localctx = new LetContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(51);
					match(LET);
					setState(52);
					match(IDENT);
					setState(53);
					match(EQUAL);
					setState(54);
					exp(0);
					setState(55);
					match(IN);
					setState(56);
					exp(0);
				}
					break;
				case 6: {
					_localctx = new LetRecContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(58);
					match(LET);
					setState(59);
					match(REC);
					setState(60);
					match(IDENT);
					setState(62);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
							{
								setState(61);
								match(IDENT);
							}
						}
						setState(64);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while (_la == IDENT);
					setState(66);
					match(EQUAL);
					setState(67);
					exp(0);
					setState(68);
					match(IN);
					setState(69);
					exp(0);
				}
					break;
				case 7: {
					_localctx = new LetTupleContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(71);
					match(LET);
					setState(72);
					match(LPAREN);
					setState(73);
					match(IDENT);
					setState(76);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
							{
								setState(74);
								match(COMMA);
								setState(75);
								match(IDENT);
							}
						}
						setState(78);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while (_la == COMMA);
					setState(80);
					match(RPAREN);
					setState(81);
					match(EQUAL);
					setState(82);
					exp(0);
					setState(83);
					match(IN);
					setState(84);
					exp(0);
				}
					break;
				case 8: {
					_localctx = new ArrayPutContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(86);
					simple_exp(0);
					setState(87);
					match(DOT);
					setState(88);
					match(LPAREN);
					setState(89);
					exp(0);
					setState(90);
					match(RPAREN);
					setState(91);
					match(LESS_MINUS);
					setState(92);
					exp(0);
				}
					break;
				case 9: {
					_localctx = new ArrayCreateContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(94);
					match(ARRAY_CREATE);
					setState(95);
					simple_exp(0);
					setState(96);
					simple_exp(0);
				}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(135);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 8, _ctx);
				while (_alt != 2
						&& _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null)
							triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(133);
							switch (getInterpreter().adaptivePredict(_input, 7,
									_ctx)) {
							case 1: {
								_localctx = new EqualContext(new ExpContext(
										_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState,
										RULE_exp);
								setState(100);
								if (!(precpred(_ctx, 16)))
									throw new FailedPredicateException(this,
											"precpred(_ctx, 16)");
								setState(101);
								match(EQUAL);
								setState(102);
								exp(17);
							}
								break;
							case 2: {
								_localctx = new LessGreaterContext(
										new ExpContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState,
										RULE_exp);
								setState(103);
								if (!(precpred(_ctx, 15)))
									throw new FailedPredicateException(this,
											"precpred(_ctx, 15)");
								setState(104);
								match(LESS_GREATER);
								setState(105);
								exp(16);
							}
								break;
							case 3: {
								_localctx = new LessContext(new ExpContext(
										_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState,
										RULE_exp);
								setState(106);
								if (!(precpred(_ctx, 14)))
									throw new FailedPredicateException(this,
											"precpred(_ctx, 14)");
								setState(107);
								match(LESS);
								setState(108);
								exp(15);
							}
								break;
							case 4: {
								_localctx = new GreaterContext(new ExpContext(
										_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState,
										RULE_exp);
								setState(109);
								if (!(precpred(_ctx, 13)))
									throw new FailedPredicateException(this,
											"precpred(_ctx, 13)");
								setState(110);
								match(GREATER);
								setState(111);
								exp(14);
							}
								break;
							case 5: {
								_localctx = new LessEqualContext(
										new ExpContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState,
										RULE_exp);
								setState(112);
								if (!(precpred(_ctx, 12)))
									throw new FailedPredicateException(this,
											"precpred(_ctx, 12)");
								setState(113);
								match(LESS_EQUAL);
								setState(114);
								exp(13);
							}
								break;
							case 6: {
								_localctx = new GreaterEqualContext(
										new ExpContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState,
										RULE_exp);
								setState(115);
								if (!(precpred(_ctx, 11)))
									throw new FailedPredicateException(this,
											"precpred(_ctx, 11)");
								setState(116);
								match(GREATER_EQUAL);
								setState(117);
								exp(12);
							}
								break;
							case 7: {
								_localctx = new BlockContext(new ExpContext(
										_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState,
										RULE_exp);
								setState(118);
								if (!(precpred(_ctx, 4)))
									throw new FailedPredicateException(this,
											"precpred(_ctx, 4)");
								setState(119);
								match(SEMICOLON);
								setState(120);
								exp(5);
							}
								break;
							case 8: {
								_localctx = new MulDivContext(new ExpContext(
										_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState,
										RULE_exp);
								setState(121);
								if (!(precpred(_ctx, 2)))
									throw new FailedPredicateException(this,
											"precpred(_ctx, 2)");
								setState(122);
								_la = _input.LA(1);
								if (!(_la == AST_DOT || _la == SLASH_DOT)) {
									_errHandler.recoverInline(this);
								} else {
									consume();
								}
								setState(123);
								exp(3);
							}
								break;
							case 9: {
								_localctx = new AddSubContext(new ExpContext(
										_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState,
										RULE_exp);
								setState(124);
								if (!(precpred(_ctx, 1)))
									throw new FailedPredicateException(this,
											"precpred(_ctx, 1)");
								setState(125);
								_la = _input.LA(1);
								if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS)
										| (1L << MINUS) | (1L << PLUS_DOT) | (1L << MINUS_DOT))) != 0))) {
									_errHandler.recoverInline(this);
								} else {
									consume();
								}
								setState(126);
								exp(2);
							}
								break;
							case 10: {
								_localctx = new AppContext(new ExpContext(
										_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState,
										RULE_exp);
								setState(127);
								if (!(precpred(_ctx, 7)))
									throw new FailedPredicateException(this,
											"precpred(_ctx, 7)");
								setState(129);
								_errHandler.sync(this);
								_alt = 1;
								do {
									switch (_alt) {
									case 1: {
										{
											setState(128);
											simple_exp(0);
										}
									}
										break;
									default:
										throw new NoViableAltException(this);
									}
									setState(131);
									_errHandler.sync(this);
									_alt = getInterpreter().adaptivePredict(
											_input, 6, _ctx);
								} while (_alt != 2
										&& _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
							}
								break;
							}
						}
					}
					setState(137);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 8, _ctx);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return simple_exp_sempred((Simple_expContext) _localctx, predIndex);
		case 1:
			return exp_sempred((ExpContext) _localctx, predIndex);
		}
		return true;
	}

	private boolean simple_exp_sempred(Simple_expContext _localctx,
			int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 16);
		case 2:
			return precpred(_ctx, 15);
		case 3:
			return precpred(_ctx, 14);
		case 4:
			return precpred(_ctx, 13);
		case 5:
			return precpred(_ctx, 12);
		case 6:
			return precpred(_ctx, 11);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 2);
		case 9:
			return precpred(_ctx, 1);
		case 10:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u008d\4\2\t\2\4"
			+ "\3\t\3\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\6\2\20\n\2\r\2\16\2\21\3\2"
			+ "\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\34\n\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2$\n"
			+ "\2\f\2\16\2\'\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"
			+ "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3A\n\3\r\3\16\3B\3\3\3"
			+ "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3O\n\3\r\3\16\3P\3\3\3\3\3\3\3\3"
			+ "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3e\n\3\3\3"
			+ "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"
			+ "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3\u0084\n\3\r\3\16\3\u0085"
			+ "\7\3\u0088\n\3\f\3\16\3\u008b\13\3\3\3\2\4\2\4\4\2\4\2\5\4\2\16\16\20"
			+ "\20\3\2\21\22\3\2\r\20\u00a7\2\33\3\2\2\2\4d\3\2\2\2\6\7\b\2\1\2\7\b\7"
			+ "\13\2\2\b\t\5\4\3\2\t\n\7\f\2\2\n\34\3\2\2\2\13\f\7\13\2\2\f\17\5\4\3"
			+ "\2\r\16\7\31\2\2\16\20\5\4\3\2\17\r\3\2\2\2\20\21\3\2\2\2\21\17\3\2\2"
			+ "\2\21\22\3\2\2\2\22\23\3\2\2\2\23\24\7\f\2\2\24\34\3\2\2\2\25\26\7\13"
			+ "\2\2\26\34\7\f\2\2\27\34\7\n\2\2\30\34\7\36\2\2\31\34\7\37\2\2\32\34\7"
			+ " \2\2\33\6\3\2\2\2\33\13\3\2\2\2\33\25\3\2\2\2\33\27\3\2\2\2\33\30\3\2"
			+ "\2\2\33\31\3\2\2\2\33\32\3\2\2\2\34%\3\2\2\2\35\36\f\3\2\2\36\37\7\32"
			+ "\2\2\37 \7\13\2\2 !\5\4\3\2!\"\7\f\2\2\"$\3\2\2\2#\35\3\2\2\2$\'\3\2\2"
			+ "\2%#\3\2\2\2%&\3\2\2\2&\3\3\2\2\2\'%\3\2\2\2()\b\3\1\2)*\7\t\2\2*e\5\4"
			+ "\3\24+,\t\2\2\2,e\5\4\3\23-e\5\2\2\2./\7\3\2\2/\60\5\4\3\2\60\61\7\4\2"
			+ "\2\61\62\5\4\3\2\62\63\7\5\2\2\63\64\5\4\3\2\64e\3\2\2\2\65\66\7\6\2\2"
			+ "\66\67\7 \2\2\678\7\23\2\289\5\4\3\29:\7\7\2\2:;\5\4\3\2;e\3\2\2\2<=\7"
			+ "\6\2\2=>\7\b\2\2>@\7 \2\2?A\7 \2\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2"
			+ "\2\2CD\3\2\2\2DE\7\23\2\2EF\5\4\3\2FG\7\7\2\2GH\5\4\3\2He\3\2\2\2IJ\7"
			+ "\6\2\2JK\7\13\2\2KN\7 \2\2LM\7\31\2\2MO\7 \2\2NL\3\2\2\2OP\3\2\2\2PN\3"
			+ "\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\7\f\2\2ST\7\23\2\2TU\5\4\3\2UV\7\7\2\2VW"
			+ "\5\4\3\2We\3\2\2\2XY\5\2\2\2YZ\7\32\2\2Z[\7\13\2\2[\\\5\4\3\2\\]\7\f\2"
			+ "\2]^\7\33\2\2^_\5\4\3\2_e\3\2\2\2`a\7\35\2\2ab\5\2\2\2bc\5\2\2\2ce\3\2"
			+ "\2\2d(\3\2\2\2d+\3\2\2\2d-\3\2\2\2d.\3\2\2\2d\65\3\2\2\2d<\3\2\2\2dI\3"
			+ "\2\2\2dX\3\2\2\2d`\3\2\2\2e\u0089\3\2\2\2fg\f\22\2\2gh\7\23\2\2h\u0088"
			+ "\5\4\3\23ij\f\21\2\2jk\7\24\2\2k\u0088\5\4\3\22lm\f\20\2\2mn\7\27\2\2"
			+ "n\u0088\5\4\3\21op\f\17\2\2pq\7\30\2\2q\u0088\5\4\3\20rs\f\16\2\2st\7"
			+ "\25\2\2t\u0088\5\4\3\17uv\f\r\2\2vw\7\26\2\2w\u0088\5\4\3\16xy\f\6\2\2"
			+ "yz\7\34\2\2z\u0088\5\4\3\7{|\f\4\2\2|}\t\3\2\2}\u0088\5\4\3\5~\177\f\3"
			+ "\2\2\177\u0080\t\4\2\2\u0080\u0088\5\4\3\4\u0081\u0083\f\t\2\2\u0082\u0084"
			+ "\5\2\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085"
			+ "\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087f\3\2\2\2\u0087i\3\2\2\2\u0087"
			+ "l\3\2\2\2\u0087o\3\2\2\2\u0087r\3\2\2\2\u0087u\3\2\2\2\u0087x\3\2\2\2"
			+ "\u0087{\3\2\2\2\u0087~\3\2\2\2\u0087\u0081\3\2\2\2\u0088\u008b\3\2\2\2"
			+ "\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\5\3\2\2\2\u008b\u0089\3"
			+ "\2\2\2\13\21\33%BPd\u0085\u0087\u0089";
	public static final ATN _ATN = new ATNDeserializer()
			.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}