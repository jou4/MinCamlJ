// Generated from D:\m122023\code\workspaces\mincamlj\mincamlj\src\main\resources\MinCaml.g4 by ANTLR 4.5
package mincamlj.parser.grammer;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MinCamlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, THEN=2, ELSE=3, LET=4, IN=5, REC=6, NOT=7, BOOL=8, LPAREN=9, RPAREN=10, 
		PLUS=11, MINUS=12, PLUS_DOT=13, MINUS_DOT=14, AST_DOT=15, SLASH_DOT=16, 
		EQUAL=17, LESS_GREATER=18, LESS_EQUAL=19, GREATER_EQUAL=20, LESS=21, GREATER=22, 
		COMMA=23, DOT=24, LESS_MINUS=25, SEMICOLON=26, ARRAY_CREATE=27, INT=28, 
		FLOAT=29, IDENT=30, WHITESPACE=31;
	public static final int
		RULE_simple_exp = 0, RULE_exp = 1;
	public static final String[] ruleNames = {
		"simple_exp", "exp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'if'", "'then'", "'else'", "'let'", "'in'", "'rec'", "'not'", null, 
		"'('", "')'", "'+'", "'-'", "'+.'", "'-.'", "'*.'", "'/.'", "'='", "'<>'", 
		"'<='", "'>='", "'<'", "'>'", "','", "'.'", "'<-'", "';'", "'Array.make'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "IF", "THEN", "ELSE", "LET", "IN", "REC", "NOT", "BOOL", "LPAREN", 
		"RPAREN", "PLUS", "MINUS", "PLUS_DOT", "MINUS_DOT", "AST_DOT", "SLASH_DOT", 
		"EQUAL", "LESS_GREATER", "LESS_EQUAL", "GREATER_EQUAL", "LESS", "GREATER", 
		"COMMA", "DOT", "LESS_MINUS", "SEMICOLON", "ARRAY_CREATE", "INT", "FLOAT", 
		"IDENT", "WHITESPACE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

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
	public String getGrammarFileName() { return "MinCaml.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MinCamlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Simple_expContext extends ParserRuleContext {
		public Simple_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_exp; }
	 
		public Simple_expContext() { }
		public void copyFrom(Simple_expContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FloatContext extends Simple_expContext {
		public TerminalNode FLOAT() { return getToken(MinCamlParser.FLOAT, 0); }
		public FloatContext(Simple_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentContext extends Simple_expContext {
		public TerminalNode IDENT() { return getToken(MinCamlParser.IDENT, 0); }
		public IdentContext(Simple_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolContext extends Simple_expContext {
		public TerminalNode BOOL() { return getToken(MinCamlParser.BOOL, 0); }
		public BoolContext(Simple_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExpContext extends Simple_expContext {
		public TerminalNode LPAREN() { return getToken(MinCamlParser.LPAREN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MinCamlParser.RPAREN, 0); }
		public ParenExpContext(Simple_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitParenExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayGetContext extends Simple_expContext {
		public Simple_expContext simple_exp() {
			return getRuleContext(Simple_expContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MinCamlParser.DOT, 0); }
		public TerminalNode LPAREN() { return getToken(MinCamlParser.LPAREN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MinCamlParser.RPAREN, 0); }
		public ArrayGetContext(Simple_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitArrayGet(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnitContext extends Simple_expContext {
		public TerminalNode LPAREN() { return getToken(MinCamlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MinCamlParser.RPAREN, 0); }
		public UnitContext(Simple_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitUnit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TupleContext extends Simple_expContext {
		public TerminalNode LPAREN() { return getToken(MinCamlParser.LPAREN, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(MinCamlParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MinCamlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MinCamlParser.COMMA, i);
		}
		public TupleContext(Simple_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitTuple(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntContext extends Simple_expContext {
		public TerminalNode INT() { return getToken(MinCamlParser.INT, 0); }
		public IntContext(Simple_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
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
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
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
			case 2:
				{
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
				} while ( _la==COMMA );
				setState(17);
				match(RPAREN);
				}
				break;
			case 3:
				{
				_localctx = new UnitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(19);
				match(LPAREN);
				setState(20);
				match(RPAREN);
				}
				break;
			case 4:
				{
				_localctx = new BoolContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(21);
				match(BOOL);
				}
				break;
			case 5:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(22);
				match(INT);
				}
				break;
			case 6:
				{
				_localctx = new FloatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(23);
				match(FLOAT);
				}
				break;
			case 7:
				{
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
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayGetContext(new Simple_expContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_simple_exp);
					setState(27);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
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
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	 
		public ExpContext() { }
		public void copyFrom(ExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LetRecContext extends ExpContext {
		public TerminalNode LET() { return getToken(MinCamlParser.LET, 0); }
		public TerminalNode REC() { return getToken(MinCamlParser.REC, 0); }
		public List<TerminalNode> IDENT() { return getTokens(MinCamlParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(MinCamlParser.IDENT, i);
		}
		public TerminalNode EQUAL() { return getToken(MinCamlParser.EQUAL, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode IN() { return getToken(MinCamlParser.IN, 0); }
		public LetRecContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitLetRec(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(MinCamlParser.PLUS, 0); }
		public AddContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AppContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public List<Simple_expContext> simple_exp() {
			return getRuleContexts(Simple_expContext.class);
		}
		public Simple_expContext simple_exp(int i) {
			return getRuleContext(Simple_expContext.class,i);
		}
		public AppContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitApp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExpContext extends ExpContext {
		public TerminalNode NOT() { return getToken(MinCamlParser.NOT, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public NotExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitNotExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode MINUS() { return getToken(MinCamlParser.MINUS, 0); }
		public SubContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessGreaterContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode LESS_GREATER() { return getToken(MinCamlParser.LESS_GREATER, 0); }
		public LessGreaterContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitLessGreater(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessEqualContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode LESS_EQUAL() { return getToken(MinCamlParser.LESS_EQUAL, 0); }
		public LessEqualContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitLessEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FNegContext extends ExpContext {
		public TerminalNode MINUS_DOT() { return getToken(MinCamlParser.MINUS_DOT, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public FNegContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitFNeg(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FDivContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode SLASH_DOT() { return getToken(MinCamlParser.SLASH_DOT, 0); }
		public FDivContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitFDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegContext extends ExpContext {
		public TerminalNode MINUS() { return getToken(MinCamlParser.MINUS, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public NegContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitNeg(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterEqualContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode GREATER_EQUAL() { return getToken(MinCamlParser.GREATER_EQUAL, 0); }
		public GreaterEqualContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitGreaterEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(MinCamlParser.EQUAL, 0); }
		public EqualContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayCreateContext extends ExpContext {
		public TerminalNode ARRAY_CREATE() { return getToken(MinCamlParser.ARRAY_CREATE, 0); }
		public List<Simple_expContext> simple_exp() {
			return getRuleContexts(Simple_expContext.class);
		}
		public Simple_expContext simple_exp(int i) {
			return getRuleContext(Simple_expContext.class,i);
		}
		public ArrayCreateContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitArrayCreate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SimpleExpContext extends ExpContext {
		public Simple_expContext simple_exp() {
			return getRuleContext(Simple_expContext.class,0);
		}
		public SimpleExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitSimpleExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(MinCamlParser.SEMICOLON, 0); }
		public BlockContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LetContext extends ExpContext {
		public TerminalNode LET() { return getToken(MinCamlParser.LET, 0); }
		public TerminalNode IDENT() { return getToken(MinCamlParser.IDENT, 0); }
		public TerminalNode EQUAL() { return getToken(MinCamlParser.EQUAL, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode IN() { return getToken(MinCamlParser.IN, 0); }
		public LetContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitLet(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LetTupleContext extends ExpContext {
		public TerminalNode LET() { return getToken(MinCamlParser.LET, 0); }
		public TerminalNode LPAREN() { return getToken(MinCamlParser.LPAREN, 0); }
		public List<TerminalNode> IDENT() { return getTokens(MinCamlParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(MinCamlParser.IDENT, i);
		}
		public TerminalNode RPAREN() { return getToken(MinCamlParser.RPAREN, 0); }
		public TerminalNode EQUAL() { return getToken(MinCamlParser.EQUAL, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode IN() { return getToken(MinCamlParser.IN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MinCamlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MinCamlParser.COMMA, i);
		}
		public LetTupleContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitLetTuple(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode GREATER() { return getToken(MinCamlParser.GREATER, 0); }
		public GreaterContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitGreater(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FAddContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode PLUS_DOT() { return getToken(MinCamlParser.PLUS_DOT, 0); }
		public FAddContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitFAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FSubContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode MINUS_DOT() { return getToken(MinCamlParser.MINUS_DOT, 0); }
		public FSubContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitFSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayPutContext extends ExpContext {
		public Simple_expContext simple_exp() {
			return getRuleContext(Simple_expContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MinCamlParser.DOT, 0); }
		public TerminalNode LPAREN() { return getToken(MinCamlParser.LPAREN, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(MinCamlParser.RPAREN, 0); }
		public TerminalNode LESS_MINUS() { return getToken(MinCamlParser.LESS_MINUS, 0); }
		public ArrayPutContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitArrayPut(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends ExpContext {
		public TerminalNode IF() { return getToken(MinCamlParser.IF, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode THEN() { return getToken(MinCamlParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(MinCamlParser.ELSE, 0); }
		public IfContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode LESS() { return getToken(MinCamlParser.LESS, 0); }
		public LessContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitLess(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FMulContext extends ExpContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode AST_DOT() { return getToken(MinCamlParser.AST_DOT, 0); }
		public FMulContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MinCamlVisitor ) return ((MinCamlVisitor<? extends T>)visitor).visitFMul(this);
			else return visitor.visitChildren(this);
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
			setState(100);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				_localctx = new NotExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(39);
				match(NOT);
				setState(40);
				exp(23);
				}
				break;
			case 2:
				{
				_localctx = new NegContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(41);
				match(MINUS);
				setState(42);
				exp(22);
				}
				break;
			case 3:
				{
				_localctx = new FNegContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(43);
				match(MINUS_DOT);
				setState(44);
				exp(12);
				}
				break;
			case 4:
				{
				_localctx = new SimpleExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(45);
				simple_exp(0);
				}
				break;
			case 5:
				{
				_localctx = new IfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(46);
				match(IF);
				setState(47);
				exp(0);
				setState(48);
				match(THEN);
				setState(49);
				exp(0);
				setState(50);
				match(ELSE);
				setState(51);
				exp(0);
				}
				break;
			case 6:
				{
				_localctx = new LetContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(53);
				match(LET);
				setState(54);
				match(IDENT);
				setState(55);
				match(EQUAL);
				setState(56);
				exp(0);
				setState(57);
				match(IN);
				setState(58);
				exp(0);
				}
				break;
			case 7:
				{
				_localctx = new LetRecContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(60);
				match(LET);
				setState(61);
				match(REC);
				setState(62);
				match(IDENT);
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(63);
					match(IDENT);
					}
					}
					setState(66); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENT );
				setState(68);
				match(EQUAL);
				setState(69);
				exp(0);
				setState(70);
				match(IN);
				setState(71);
				exp(0);
				}
				break;
			case 8:
				{
				_localctx = new LetTupleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(73);
				match(LET);
				setState(74);
				match(LPAREN);
				setState(75);
				match(IDENT);
				setState(78); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(76);
					match(COMMA);
					setState(77);
					match(IDENT);
					}
					}
					setState(80); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMA );
				setState(82);
				match(RPAREN);
				setState(83);
				match(EQUAL);
				setState(84);
				exp(0);
				setState(85);
				match(IN);
				setState(86);
				exp(0);
				}
				break;
			case 9:
				{
				_localctx = new ArrayPutContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88);
				simple_exp(0);
				setState(89);
				match(DOT);
				setState(90);
				match(LPAREN);
				setState(91);
				exp(0);
				setState(92);
				match(RPAREN);
				setState(93);
				match(LESS_MINUS);
				setState(94);
				exp(0);
				}
				break;
			case 10:
				{
				_localctx = new ArrayCreateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(96);
				match(ARRAY_CREATE);
				setState(97);
				simple_exp(0);
				setState(98);
				simple_exp(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(149);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(147);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new AddContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(102);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(103);
						match(PLUS);
						setState(104);
						exp(22);
						}
						break;
					case 2:
						{
						_localctx = new SubContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(105);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(106);
						match(MINUS);
						setState(107);
						exp(21);
						}
						break;
					case 3:
						{
						_localctx = new EqualContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(108);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(109);
						match(EQUAL);
						setState(110);
						exp(20);
						}
						break;
					case 4:
						{
						_localctx = new LessGreaterContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(111);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(112);
						match(LESS_GREATER);
						setState(113);
						exp(19);
						}
						break;
					case 5:
						{
						_localctx = new LessContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(114);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(115);
						match(LESS);
						setState(116);
						exp(18);
						}
						break;
					case 6:
						{
						_localctx = new GreaterContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(117);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(118);
						match(GREATER);
						setState(119);
						exp(17);
						}
						break;
					case 7:
						{
						_localctx = new LessEqualContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(120);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(121);
						match(LESS_EQUAL);
						setState(122);
						exp(16);
						}
						break;
					case 8:
						{
						_localctx = new GreaterEqualContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(123);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(124);
						match(GREATER_EQUAL);
						setState(125);
						exp(15);
						}
						break;
					case 9:
						{
						_localctx = new FAddContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(126);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(127);
						match(PLUS_DOT);
						setState(128);
						exp(12);
						}
						break;
					case 10:
						{
						_localctx = new FSubContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(129);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(130);
						match(MINUS_DOT);
						setState(131);
						exp(11);
						}
						break;
					case 11:
						{
						_localctx = new FMulContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(132);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(133);
						match(AST_DOT);
						setState(134);
						exp(10);
						}
						break;
					case 12:
						{
						_localctx = new FDivContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(135);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(136);
						match(SLASH_DOT);
						setState(137);
						exp(9);
						}
						break;
					case 13:
						{
						_localctx = new BlockContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(138);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(139);
						match(SEMICOLON);
						setState(140);
						exp(3);
						}
						break;
					case 14:
						{
						_localctx = new AppContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(141);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(143); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(142);
								simple_exp(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(145); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return simple_exp_sempred((Simple_expContext)_localctx, predIndex);
		case 1:
			return exp_sempred((ExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean simple_exp_sempred(Simple_expContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 21);
		case 2:
			return precpred(_ctx, 20);
		case 3:
			return precpred(_ctx, 19);
		case 4:
			return precpred(_ctx, 18);
		case 5:
			return precpred(_ctx, 17);
		case 6:
			return precpred(_ctx, 16);
		case 7:
			return precpred(_ctx, 15);
		case 8:
			return precpred(_ctx, 14);
		case 9:
			return precpred(_ctx, 11);
		case 10:
			return precpred(_ctx, 10);
		case 11:
			return precpred(_ctx, 9);
		case 12:
			return precpred(_ctx, 8);
		case 13:
			return precpred(_ctx, 2);
		case 14:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u009b\4\2\t\2\4"+
		"\3\t\3\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\6\2\20\n\2\r\2\16\2\21\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\34\n\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2$\n"+
		"\2\f\2\16\2\'\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3C\n\3\r\3\16\3"+
		"D\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3Q\n\3\r\3\16\3R\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3g\n"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3\u0092\n\3\r\3\16\3\u0093\7\3\u0096\n\3"+
		"\f\3\16\3\u0099\13\3\3\3\2\4\2\4\4\2\4\2\2\u00ba\2\33\3\2\2\2\4f\3\2\2"+
		"\2\6\7\b\2\1\2\7\b\7\13\2\2\b\t\5\4\3\2\t\n\7\f\2\2\n\34\3\2\2\2\13\f"+
		"\7\13\2\2\f\17\5\4\3\2\r\16\7\31\2\2\16\20\5\4\3\2\17\r\3\2\2\2\20\21"+
		"\3\2\2\2\21\17\3\2\2\2\21\22\3\2\2\2\22\23\3\2\2\2\23\24\7\f\2\2\24\34"+
		"\3\2\2\2\25\26\7\13\2\2\26\34\7\f\2\2\27\34\7\n\2\2\30\34\7\36\2\2\31"+
		"\34\7\37\2\2\32\34\7 \2\2\33\6\3\2\2\2\33\13\3\2\2\2\33\25\3\2\2\2\33"+
		"\27\3\2\2\2\33\30\3\2\2\2\33\31\3\2\2\2\33\32\3\2\2\2\34%\3\2\2\2\35\36"+
		"\f\3\2\2\36\37\7\32\2\2\37 \7\13\2\2 !\5\4\3\2!\"\7\f\2\2\"$\3\2\2\2#"+
		"\35\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\3\3\2\2\2\'%\3\2\2\2()\b\3"+
		"\1\2)*\7\t\2\2*g\5\4\3\31+,\7\16\2\2,g\5\4\3\30-.\7\20\2\2.g\5\4\3\16"+
		"/g\5\2\2\2\60\61\7\3\2\2\61\62\5\4\3\2\62\63\7\4\2\2\63\64\5\4\3\2\64"+
		"\65\7\5\2\2\65\66\5\4\3\2\66g\3\2\2\2\678\7\6\2\289\7 \2\29:\7\23\2\2"+
		":;\5\4\3\2;<\7\7\2\2<=\5\4\3\2=g\3\2\2\2>?\7\6\2\2?@\7\b\2\2@B\7 \2\2"+
		"AC\7 \2\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2EF\3\2\2\2FG\7\23\2\2"+
		"GH\5\4\3\2HI\7\7\2\2IJ\5\4\3\2Jg\3\2\2\2KL\7\6\2\2LM\7\13\2\2MP\7 \2\2"+
		"NO\7\31\2\2OQ\7 \2\2PN\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2ST\3\2\2\2"+
		"TU\7\f\2\2UV\7\23\2\2VW\5\4\3\2WX\7\7\2\2XY\5\4\3\2Yg\3\2\2\2Z[\5\2\2"+
		"\2[\\\7\32\2\2\\]\7\13\2\2]^\5\4\3\2^_\7\f\2\2_`\7\33\2\2`a\5\4\3\2ag"+
		"\3\2\2\2bc\7\35\2\2cd\5\2\2\2de\5\2\2\2eg\3\2\2\2f(\3\2\2\2f+\3\2\2\2"+
		"f-\3\2\2\2f/\3\2\2\2f\60\3\2\2\2f\67\3\2\2\2f>\3\2\2\2fK\3\2\2\2fZ\3\2"+
		"\2\2fb\3\2\2\2g\u0097\3\2\2\2hi\f\27\2\2ij\7\r\2\2j\u0096\5\4\3\30kl\f"+
		"\26\2\2lm\7\16\2\2m\u0096\5\4\3\27no\f\25\2\2op\7\23\2\2p\u0096\5\4\3"+
		"\26qr\f\24\2\2rs\7\24\2\2s\u0096\5\4\3\25tu\f\23\2\2uv\7\27\2\2v\u0096"+
		"\5\4\3\24wx\f\22\2\2xy\7\30\2\2y\u0096\5\4\3\23z{\f\21\2\2{|\7\25\2\2"+
		"|\u0096\5\4\3\22}~\f\20\2\2~\177\7\26\2\2\177\u0096\5\4\3\21\u0080\u0081"+
		"\f\r\2\2\u0081\u0082\7\17\2\2\u0082\u0096\5\4\3\16\u0083\u0084\f\f\2\2"+
		"\u0084\u0085\7\20\2\2\u0085\u0096\5\4\3\r\u0086\u0087\f\13\2\2\u0087\u0088"+
		"\7\21\2\2\u0088\u0096\5\4\3\f\u0089\u008a\f\n\2\2\u008a\u008b\7\22\2\2"+
		"\u008b\u0096\5\4\3\13\u008c\u008d\f\4\2\2\u008d\u008e\7\34\2\2\u008e\u0096"+
		"\5\4\3\5\u008f\u0091\f\7\2\2\u0090\u0092\5\2\2\2\u0091\u0090\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0096\3\2"+
		"\2\2\u0095h\3\2\2\2\u0095k\3\2\2\2\u0095n\3\2\2\2\u0095q\3\2\2\2\u0095"+
		"t\3\2\2\2\u0095w\3\2\2\2\u0095z\3\2\2\2\u0095}\3\2\2\2\u0095\u0080\3\2"+
		"\2\2\u0095\u0083\3\2\2\2\u0095\u0086\3\2\2\2\u0095\u0089\3\2\2\2\u0095"+
		"\u008c\3\2\2\2\u0095\u008f\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2"+
		"\2\2\u0097\u0098\3\2\2\2\u0098\5\3\2\2\2\u0099\u0097\3\2\2\2\13\21\33"+
		"%DRf\u0093\u0095\u0097";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}