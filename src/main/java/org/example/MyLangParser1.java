// Generated from C:/Users/user/IdeaProjects/Kompilyator/src/main/java/org/example/MyLangParser1.g4 by ANTLR 4.13.2
package org.example;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MyLangParser1 extends MyLangParserBase {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INDENT=1, DEDENT=2, NEWLINE=3, WHITESPACE=4, BOOLEAN=5, BREAK=6, CONTINUE=7, 
		FLOAT=8, INTEGER=9, IF=10, ELSE_IF=11, ELSE=12, THEN=13, FOR=14, ARRAY=15, 
		STRING=16, PROCEDURE=17, RETURN=18, PROCEDURE_END=19, ERROR=20, INTEGER_LITERAL=21, 
		FLOAT_LITERAL=22, NULL_LITERAL=23, CHAR_LITERAL=24, STRING_LITERAL=25, 
		BLOCK_TEXT=26, BOOLEAN_LITERAL=27, LEFT_PAREN=28, RIGHT_PAREN=29, LEFT_BRACKET=30, 
		RIGHT_BRACKET=31, SEMICOLON=32, COMMA=33, DOT=34, ASSIGN=35, GREATER=36, 
		LESS=37, NOT=38, COLON=39, EQUAL=40, LESS_EQUAL=41, GREATER_EQUAL=42, 
		NOT_EQUAL=43, AND=44, OR=45, ADD=46, SUBTRACT=47, MULTIPLY=48, DIVIDE=49, 
		MODULO=50, ADD_ASSIGN=51, SUBTRACT_ASSIGN=52, MULTIPLY_ASSIGN=53, DIVIDE_ASSIGN=54, 
		MODULO_ASSIGN=55, PRINT=56, INPUT=57, COMMENT=58, LINE_COMMENT=59, IDENTIFIER=60;
	public static final int
		RULE_program = 0, RULE_statements = 1, RULE_statement = 2, RULE_simple_statements = 3, 
		RULE_function_declarations = 4, RULE_simple_stmt = 5, RULE_print = 6, 
		RULE_input = 7, RULE_declaration = 8, RULE_compound_statement = 9, RULE_assignment = 10, 
		RULE_compound_operator = 11, RULE_return_statement = 12, RULE_conditional_statement = 13, 
		RULE_for = 14, RULE_function_declaration = 15, RULE_return_type = 16, 
		RULE_parameters = 17, RULE_parameter = 18, RULE_type = 19, RULE_array = 20, 
		RULE_function_call = 21, RULE_arguments = 22, RULE_block = 23, RULE_expression = 24, 
		RULE_string_expression = 25, RULE_string_term = 26, RULE_singleExpression = 27, 
		RULE_logical_expression = 28, RULE_term_logical = 29, RULE_factor_logical = 30, 
		RULE_arithmetic_expression = 31, RULE_term = 32, RULE_factor = 33, RULE_array_index = 34, 
		RULE_comparison_operator = 35, RULE_array_literal = 36, RULE_literal = 37;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statements", "statement", "simple_statements", "function_declarations", 
			"simple_stmt", "print", "input", "declaration", "compound_statement", 
			"assignment", "compound_operator", "return_statement", "conditional_statement", 
			"for", "function_declaration", "return_type", "parameters", "parameter", 
			"type", "array", "function_call", "arguments", "block", "expression", 
			"string_expression", "string_term", "singleExpression", "logical_expression", 
			"term_logical", "factor_logical", "arithmetic_expression", "term", "factor", 
			"array_index", "comparison_operator", "array_literal", "literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'\\u0420\\u00BB\\u0420\\u0455\\u0420\\u0456\\u0420\\u0451\\u0421\\u2021\\u0420\\u00B5\\u0421\\u0403\\u0420\\u0454\\u0420\\u00B0\\u0421\\u040F'", 
			"'\\u0421\\u0403\\u0421\\u201A\\u0420\\u0455\\u0420\\u0457'", "'\\u0420\\u0457\\u0421\\u0402\\u0420\\u0455\\u0420\\u0457\\u0421\\u0453\\u0421\\u0403\\u0421\\u201A\\u0420\\u0451\\u0421\\u201A\\u0421\\u040A'", 
			"'\\u0420\\u0406\\u0420\\u00B5\\u0421\\u2030\\u0420\\u00B5\\u0421\\u0403\\u0421\\u201A\\u0420\\u0406\\u0420\\u00B5\\u0420\\u0405\\u0420\\u0405\\u0421\\u2039\\u0420\\u2116'", 
			"'\\u0421\\u2020\\u0420\\u00B5\\u0420\\u00BB\\u0420\\u0455\\u0420\\u00B5'", 
			"'\\u0420\\u00B5\\u0421\\u0403\\u0420\\u00BB\\u0420\\u0451'", "'\\u0420\\u0451\\u0420\\u0405\\u0420\\u00B0\\u0421\\u2021\\u0420\\u00B5_\\u0420\\u00B5\\u0421\\u0403\\u0420\\u00BB\\u0420\\u0451'", 
			"'\\u0420\\u0451\\u0420\\u0405\\u0420\\u00B0\\u0421\\u2021\\u0420\\u00B5'", 
			"'\\u0421\\u201A\\u0420\\u0455\\u0420\\u0456\\u0420\\u0491\\u0420\\u00B0'", 
			"'\\u0421\\u2020\\u0420\\u0451\\u0420\\u0454\\u0420\\u00BB'", "'\\u0420\\u0458\\u0420\\u00B0\\u0421\\u0403\\u0421\\u0403\\u0420\\u0451\\u0420\\u0406'", 
			"'\\u0421\\u0403\\u0421\\u201A\\u0421\\u0402\\u0420\\u0455\\u0420\\u0454\\u0420\\u00B0'", 
			"'\\u0420\\u0457\\u0421\\u0402\\u0420\\u0455\\u0421\\u2020\\u0420\\u00B5\\u0420\\u0491\\u0421\\u0453\\u0421\\u0402\\u0420\\u00B0'", 
			"'\\u0420\\u0406\\u0420\\u00B5\\u0421\\u0402\\u0420\\u0405\\u0421\\u0453\\u0421\\u201A\\u0421\\u040A'", 
			"'\\u0420\\u0454\\u0420\\u0455\\u0420\\u0405\\u0420\\u00B5\\u0421\\u2020_\\u0420\\u0457\\u0421\\u0402\\u0420\\u0455\\u0421\\u2020\\u0420\\u00B5\\u0420\\u0491\\u0421\\u0453\\u0421\\u0402\\u0421\\u2039'", 
			"'ERROR'", null, null, "'\\u0420\\u0405\\u0420\\u0451\\u0421\\u2021\\u0420\\u00B5\\u0420\\u0456\\u0420\\u0455'", 
			null, null, null, null, "'('", "')'", "'['", "']'", "';'", "','", "'.'", 
			"'='", "'>'", "'<'", "'!'", "':'", "'=='", "'<='", "'>='", "'!='", "'&&'", 
			"'||'", "'+'", "'-'", "'*'", "'/'", "'%'", "'+='", "'-='", "'*='", "'/='", 
			"'%='", "'\\u0420\\u0406\\u0421\\u2039\\u0420\\u0406\\u0420\\u0455\\u0420\\u0491'", 
			"'\\u0420\\u0406\\u0420\\u0406\\u0420\\u0455\\u0420\\u0491'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INDENT", "DEDENT", "NEWLINE", "WHITESPACE", "BOOLEAN", "BREAK", 
			"CONTINUE", "FLOAT", "INTEGER", "IF", "ELSE_IF", "ELSE", "THEN", "FOR", 
			"ARRAY", "STRING", "PROCEDURE", "RETURN", "PROCEDURE_END", "ERROR", "INTEGER_LITERAL", 
			"FLOAT_LITERAL", "NULL_LITERAL", "CHAR_LITERAL", "STRING_LITERAL", "BLOCK_TEXT", 
			"BOOLEAN_LITERAL", "LEFT_PAREN", "RIGHT_PAREN", "LEFT_BRACKET", "RIGHT_BRACKET", 
			"SEMICOLON", "COMMA", "DOT", "ASSIGN", "GREATER", "LESS", "NOT", "COLON", 
			"EQUAL", "LESS_EQUAL", "GREATER_EQUAL", "NOT_EQUAL", "AND", "OR", "ADD", 
			"SUBTRACT", "MULTIPLY", "DIVIDE", "MODULO", "ADD_ASSIGN", "SUBTRACT_ASSIGN", 
			"MULTIPLY_ASSIGN", "DIVIDE_ASSIGN", "MODULO_ASSIGN", "PRINT", "INPUT", 
			"COMMENT", "LINE_COMMENT", "IDENTIFIER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "MyLangParser1.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MyLangParser1(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MyLangParser1.EOF, 0); }
		public Function_declarationsContext function_declarations() {
			return getRuleContext(Function_declarationsContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PROCEDURE) {
				{
				setState(76);
				function_declarations();
				}
			}

			setState(79);
			statements();
			setState(80);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(82);
				statement();
				}
				}
				setState(85); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1369094286721009640L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public Simple_statementsContext simple_statements() {
			return getRuleContext(Simple_statementsContext.class,0);
		}
		public Compound_statementContext compound_statement() {
			return getRuleContext(Compound_statementContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(MyLangParser1.NEWLINE, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BREAK:
			case CONTINUE:
			case FLOAT:
			case INTEGER:
			case ARRAY:
			case STRING:
			case RETURN:
			case PRINT:
			case INPUT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				simple_statements();
				}
				break;
			case IF:
			case FOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				compound_statement();
				setState(90);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(89);
					match(NEWLINE);
					}
					break;
				}
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				match(NEWLINE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_statementsContext extends ParserRuleContext {
		public List<Simple_stmtContext> simple_stmt() {
			return getRuleContexts(Simple_stmtContext.class);
		}
		public Simple_stmtContext simple_stmt(int i) {
			return getRuleContext(Simple_stmtContext.class,i);
		}
		public TerminalNode NEWLINE() { return getToken(MyLangParser1.NEWLINE, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(MyLangParser1.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MyLangParser1.SEMICOLON, i);
		}
		public Simple_statementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterSimple_statements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitSimple_statements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitSimple_statements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_statementsContext simple_statements() throws RecognitionException {
		Simple_statementsContext _localctx = new Simple_statementsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_simple_statements);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			simple_stmt();
			setState(100);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(96);
					match(SEMICOLON);
					setState(97);
					simple_stmt();
					}
					} 
				}
				setState(102);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(103);
				match(SEMICOLON);
				}
			}

			setState(106);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_declarationsContext extends ParserRuleContext {
		public List<Function_declarationContext> function_declaration() {
			return getRuleContexts(Function_declarationContext.class);
		}
		public Function_declarationContext function_declaration(int i) {
			return getRuleContext(Function_declarationContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MyLangParser1.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MyLangParser1.NEWLINE, i);
		}
		public Function_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterFunction_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitFunction_declarations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitFunction_declarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_declarationsContext function_declarations() throws RecognitionException {
		Function_declarationsContext _localctx = new Function_declarationsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_function_declarations);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			function_declaration();
			setState(113);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(109);
					match(NEWLINE);
					setState(110);
					function_declaration();
					}
					} 
				}
				setState(115);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_stmtContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public Return_statementContext return_statement() {
			return getRuleContext(Return_statementContext.class,0);
		}
		public TerminalNode BREAK() { return getToken(MyLangParser1.BREAK, 0); }
		public TerminalNode CONTINUE() { return getToken(MyLangParser1.CONTINUE, 0); }
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public InputContext input() {
			return getRuleContext(InputContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MyLangParser1.SEMICOLON, 0); }
		public Simple_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterSimple_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitSimple_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitSimple_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_stmtContext simple_stmt() throws RecognitionException {
		Simple_stmtContext _localctx = new Simple_stmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_simple_stmt);
		try {
			setState(127);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				assignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				return_statement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(119);
				match(BREAK);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(120);
				match(CONTINUE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(121);
				print();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(122);
				input();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(123);
				function_call();
				setState(125);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(124);
					match(SEMICOLON);
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrintContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(MyLangParser1.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(MyLangParser1.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(MyLangParser1.LEFT_PAREN, 0); }
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			match(PRINT);
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(130);
				match(LEFT_PAREN);
				}
				break;
			}
			setState(133);
			expression();
			setState(134);
			match(RIGHT_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InputContext extends ParserRuleContext {
		public TerminalNode INPUT() { return getToken(MyLangParser1.INPUT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser1.IDENTIFIER, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(MyLangParser1.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(MyLangParser1.LEFT_PAREN, 0); }
		public InputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitInput(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputContext input() throws RecognitionException {
		InputContext _localctx = new InputContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(INPUT);
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LEFT_PAREN) {
				{
				setState(137);
				match(LEFT_PAREN);
				}
			}

			setState(140);
			match(IDENTIFIER);
			setState(141);
			match(RIGHT_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser1.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(MyLangParser1.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			type();
			setState(144);
			match(IDENTIFIER);
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(145);
				match(ASSIGN);
				setState(146);
				expression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_statementContext extends ParserRuleContext {
		public Conditional_statementContext conditional_statement() {
			return getRuleContext(Conditional_statementContext.class,0);
		}
		public ForContext for_() {
			return getRuleContext(ForContext.class,0);
		}
		public Compound_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterCompound_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitCompound_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitCompound_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compound_statementContext compound_statement() throws RecognitionException {
		Compound_statementContext _localctx = new Compound_statementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_compound_statement);
		try {
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				conditional_statement();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
				for_();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser1.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(MyLangParser1.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Compound_operatorContext compound_operator() {
			return getRuleContext(Compound_operatorContext.class,0);
		}
		public Array_indexContext array_index() {
			return getRuleContext(Array_indexContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assignment);
		try {
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				match(IDENTIFIER);
				setState(154);
				match(ASSIGN);
				setState(155);
				expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(156);
				match(IDENTIFIER);
				setState(159);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ASSIGN:
					{
					setState(157);
					match(ASSIGN);
					}
					break;
				case ADD_ASSIGN:
				case SUBTRACT_ASSIGN:
				case MULTIPLY_ASSIGN:
				case DIVIDE_ASSIGN:
				case MODULO_ASSIGN:
					{
					setState(158);
					compound_operator();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(161);
				expression();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(162);
				match(IDENTIFIER);
				setState(163);
				array_index();
				setState(164);
				match(ASSIGN);
				setState(165);
				expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_operatorContext extends ParserRuleContext {
		public TerminalNode ADD_ASSIGN() { return getToken(MyLangParser1.ADD_ASSIGN, 0); }
		public TerminalNode SUBTRACT_ASSIGN() { return getToken(MyLangParser1.SUBTRACT_ASSIGN, 0); }
		public TerminalNode MULTIPLY_ASSIGN() { return getToken(MyLangParser1.MULTIPLY_ASSIGN, 0); }
		public TerminalNode DIVIDE_ASSIGN() { return getToken(MyLangParser1.DIVIDE_ASSIGN, 0); }
		public TerminalNode MODULO_ASSIGN() { return getToken(MyLangParser1.MODULO_ASSIGN, 0); }
		public Compound_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterCompound_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitCompound_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitCompound_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compound_operatorContext compound_operator() throws RecognitionException {
		Compound_operatorContext _localctx = new Compound_operatorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_compound_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 69805794224242688L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Return_statementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(MyLangParser1.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Return_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterReturn_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitReturn_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitReturn_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_statementContext return_statement() throws RecognitionException {
		Return_statementContext _localctx = new Return_statementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_return_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(RETURN);
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1153079216256843776L) != 0)) {
				{
				setState(172);
				expression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Conditional_statementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MyLangParser1.IF, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> THEN() { return getTokens(MyLangParser1.THEN); }
		public TerminalNode THEN(int i) {
			return getToken(MyLangParser1.THEN, i);
		}
		public List<TerminalNode> COLON() { return getTokens(MyLangParser1.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(MyLangParser1.COLON, i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> ELSE_IF() { return getTokens(MyLangParser1.ELSE_IF); }
		public TerminalNode ELSE_IF(int i) {
			return getToken(MyLangParser1.ELSE_IF, i);
		}
		public TerminalNode ELSE() { return getToken(MyLangParser1.ELSE, 0); }
		public Conditional_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterConditional_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitConditional_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitConditional_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Conditional_statementContext conditional_statement() throws RecognitionException {
		Conditional_statementContext _localctx = new Conditional_statementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_conditional_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(IF);
			setState(176);
			expression();
			setState(177);
			match(THEN);
			setState(178);
			match(COLON);
			setState(179);
			block();
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSE_IF) {
				{
				{
				setState(180);
				match(ELSE_IF);
				setState(181);
				expression();
				setState(182);
				match(THEN);
				setState(183);
				match(COLON);
				setState(184);
				block();
				}
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(191);
				match(ELSE);
				setState(192);
				match(COLON);
				setState(193);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(MyLangParser1.FOR, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(MyLangParser1.LEFT_PAREN, 0); }
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(MyLangParser1.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MyLangParser1.SEMICOLON, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(MyLangParser1.RIGHT_PAREN, 0); }
		public TerminalNode COLON() { return getToken(MyLangParser1.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitFor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForContext for_() throws RecognitionException {
		ForContext _localctx = new ForContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_for);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(FOR);
			setState(197);
			match(LEFT_PAREN);
			setState(198);
			declaration();
			setState(199);
			match(SEMICOLON);
			setState(200);
			expression();
			setState(201);
			match(SEMICOLON);
			setState(202);
			assignment();
			setState(203);
			match(RIGHT_PAREN);
			setState(204);
			match(COLON);
			setState(205);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_declarationContext extends ParserRuleContext {
		public TerminalNode PROCEDURE() { return getToken(MyLangParser1.PROCEDURE, 0); }
		public Return_typeContext return_type() {
			return getRuleContext(Return_typeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser1.IDENTIFIER, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(MyLangParser1.LEFT_PAREN, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(MyLangParser1.RIGHT_PAREN, 0); }
		public TerminalNode COLON() { return getToken(MyLangParser1.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode PROCEDURE_END() { return getToken(MyLangParser1.PROCEDURE_END, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public Function_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterFunction_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitFunction_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitFunction_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_declarationContext function_declaration() throws RecognitionException {
		Function_declarationContext _localctx = new Function_declarationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_function_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(PROCEDURE);
			setState(208);
			return_type();
			setState(209);
			match(IDENTIFIER);
			setState(210);
			match(LEFT_PAREN);
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 99104L) != 0)) {
				{
				setState(211);
				parameters();
				}
			}

			setState(214);
			match(RIGHT_PAREN);
			setState(215);
			match(COLON);
			setState(216);
			block();
			setState(217);
			match(PROCEDURE_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Return_typeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Return_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterReturn_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitReturn_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitReturn_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_typeContext return_type() throws RecognitionException {
		Return_typeContext _localctx = new Return_typeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_return_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParametersContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MyLangParser1.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MyLangParser1.COMMA, i);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			parameter();
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(222);
				match(COMMA);
				setState(223);
				parameter();
				}
				}
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser1.IDENTIFIER, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			type();
			setState(230);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(MyLangParser1.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(MyLangParser1.FLOAT, 0); }
		public TerminalNode BOOLEAN() { return getToken(MyLangParser1.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(MyLangParser1.STRING, 0); }
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_type);
		try {
			setState(237);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(232);
				match(INTEGER);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				match(FLOAT);
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(234);
				match(BOOLEAN);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(235);
				match(STRING);
				}
				break;
			case ARRAY:
				enterOuterAlt(_localctx, 5);
				{
				setState(236);
				array();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode ARRAY() { return getToken(MyLangParser1.ARRAY, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(MyLangParser1.LEFT_BRACKET, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(MyLangParser1.INTEGER_LITERAL, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(MyLangParser1.RIGHT_BRACKET, 0); }
		public TerminalNode INTEGER() { return getToken(MyLangParser1.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(MyLangParser1.FLOAT, 0); }
		public TerminalNode BOOLEAN() { return getToken(MyLangParser1.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(MyLangParser1.STRING, 0); }
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(ARRAY);
			setState(240);
			match(LEFT_BRACKET);
			setState(241);
			match(INTEGER_LITERAL);
			setState(242);
			match(RIGHT_BRACKET);
			setState(243);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 66336L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_callContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser1.IDENTIFIER, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(MyLangParser1.LEFT_PAREN, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(MyLangParser1.RIGHT_PAREN, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitFunction_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitFunction_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(IDENTIFIER);
			setState(246);
			match(LEFT_PAREN);
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1153079216256843776L) != 0)) {
				{
				setState(247);
				arguments();
				}
			}

			setState(250);
			match(RIGHT_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MyLangParser1.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MyLangParser1.COMMA, i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			expression();
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(253);
				match(COMMA);
				setState(254);
				expression();
				}
				}
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(MyLangParser1.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(MyLangParser1.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(MyLangParser1.DEDENT, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(NEWLINE);
			setState(261);
			match(INDENT);
			setState(263); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(262);
				statement();
				}
				}
				setState(265); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1369094286721009640L) != 0) );
			setState(267);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public Logical_expressionContext logical_expression() {
			return getRuleContext(Logical_expressionContext.class,0);
		}
		public Arithmetic_expressionContext arithmetic_expression() {
			return getRuleContext(Arithmetic_expressionContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public String_expressionContext string_expression() {
			return getRuleContext(String_expressionContext.class,0);
		}
		public Array_literalContext array_literal() {
			return getRuleContext(Array_literalContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_expression);
		try {
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				singleExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
				logical_expression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(271);
				arithmetic_expression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(272);
				function_call();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(273);
				string_expression();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(274);
				array_literal();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class String_expressionContext extends ParserRuleContext {
		public List<String_termContext> string_term() {
			return getRuleContexts(String_termContext.class);
		}
		public String_termContext string_term(int i) {
			return getRuleContext(String_termContext.class,i);
		}
		public List<TerminalNode> ADD() { return getTokens(MyLangParser1.ADD); }
		public TerminalNode ADD(int i) {
			return getToken(MyLangParser1.ADD, i);
		}
		public String_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterString_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitString_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitString_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_expressionContext string_expression() throws RecognitionException {
		String_expressionContext _localctx = new String_expressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_string_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			string_term();
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD) {
				{
				{
				setState(278);
				match(ADD);
				setState(279);
				string_term();
				}
				}
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class String_termContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(MyLangParser1.STRING_LITERAL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser1.IDENTIFIER, 0); }
		public Array_indexContext array_index() {
			return getRuleContext(Array_indexContext.class,0);
		}
		public String_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterString_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitString_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitString_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_termContext string_term() throws RecognitionException {
		String_termContext _localctx = new String_termContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_string_term);
		try {
			setState(289);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(285);
				match(STRING_LITERAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(286);
				match(IDENTIFIER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(287);
				match(IDENTIFIER);
				setState(288);
				array_index();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SingleExpressionContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser1.IDENTIFIER, 0); }
		public TerminalNode SUBTRACT() { return getToken(MyLangParser1.SUBTRACT, 0); }
		public SingleExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterSingleExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitSingleExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitSingleExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleExpressionContext singleExpression() throws RecognitionException {
		SingleExpressionContext _localctx = new SingleExpressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_singleExpression);
		int _la;
		try {
			setState(296);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUBTRACT) {
					{
					setState(292);
					match(SUBTRACT);
					}
				}

				setState(295);
				match(IDENTIFIER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Logical_expressionContext extends ParserRuleContext {
		public List<Term_logicalContext> term_logical() {
			return getRuleContexts(Term_logicalContext.class);
		}
		public Term_logicalContext term_logical(int i) {
			return getRuleContext(Term_logicalContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(MyLangParser1.OR); }
		public TerminalNode OR(int i) {
			return getToken(MyLangParser1.OR, i);
		}
		public Logical_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterLogical_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitLogical_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitLogical_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logical_expressionContext logical_expression() throws RecognitionException {
		Logical_expressionContext _localctx = new Logical_expressionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_logical_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			term_logical();
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(299);
				match(OR);
				setState(300);
				term_logical();
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Term_logicalContext extends ParserRuleContext {
		public List<Factor_logicalContext> factor_logical() {
			return getRuleContexts(Factor_logicalContext.class);
		}
		public Factor_logicalContext factor_logical(int i) {
			return getRuleContext(Factor_logicalContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(MyLangParser1.AND); }
		public TerminalNode AND(int i) {
			return getToken(MyLangParser1.AND, i);
		}
		public Term_logicalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term_logical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterTerm_logical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitTerm_logical(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitTerm_logical(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term_logicalContext term_logical() throws RecognitionException {
		Term_logicalContext _localctx = new Term_logicalContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_term_logical);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			factor_logical();
			setState(311);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(307);
				match(AND);
				setState(308);
				factor_logical();
				}
				}
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Factor_logicalContext extends ParserRuleContext {
		public TerminalNode BOOLEAN_LITERAL() { return getToken(MyLangParser1.BOOLEAN_LITERAL, 0); }
		public TerminalNode NOT() { return getToken(MyLangParser1.NOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser1.IDENTIFIER, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(MyLangParser1.LEFT_PAREN, 0); }
		public Logical_expressionContext logical_expression() {
			return getRuleContext(Logical_expressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(MyLangParser1.RIGHT_PAREN, 0); }
		public Comparison_operatorContext comparison_operator() {
			return getRuleContext(Comparison_operatorContext.class,0);
		}
		public List<Arithmetic_expressionContext> arithmetic_expression() {
			return getRuleContexts(Arithmetic_expressionContext.class);
		}
		public Arithmetic_expressionContext arithmetic_expression(int i) {
			return getRuleContext(Arithmetic_expressionContext.class,i);
		}
		public Array_indexContext array_index() {
			return getRuleContext(Array_indexContext.class,0);
		}
		public Factor_logicalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor_logical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterFactor_logical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitFactor_logical(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitFactor_logical(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Factor_logicalContext factor_logical() throws RecognitionException {
		Factor_logicalContext _localctx = new Factor_logicalContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_factor_logical);
		int _la;
		try {
			setState(339);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(314);
					match(NOT);
					}
				}

				setState(317);
				match(BOOLEAN_LITERAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(319);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(318);
					match(NOT);
					}
				}

				setState(321);
				match(IDENTIFIER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(322);
					match(NOT);
					}
				}

				setState(325);
				match(LEFT_PAREN);
				setState(326);
				logical_expression();
				setState(327);
				match(RIGHT_PAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(329);
				comparison_operator();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(330);
				arithmetic_expression();
				setState(331);
				comparison_operator();
				setState(332);
				arithmetic_expression();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(335);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(334);
					match(NOT);
					}
				}

				setState(337);
				match(IDENTIFIER);
				setState(338);
				array_index();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Arithmetic_expressionContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> ADD() { return getTokens(MyLangParser1.ADD); }
		public TerminalNode ADD(int i) {
			return getToken(MyLangParser1.ADD, i);
		}
		public List<TerminalNode> SUBTRACT() { return getTokens(MyLangParser1.SUBTRACT); }
		public TerminalNode SUBTRACT(int i) {
			return getToken(MyLangParser1.SUBTRACT, i);
		}
		public Arithmetic_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterArithmetic_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitArithmetic_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitArithmetic_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arithmetic_expressionContext arithmetic_expression() throws RecognitionException {
		Arithmetic_expressionContext _localctx = new Arithmetic_expressionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_arithmetic_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			term();
			setState(346);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD || _la==SUBTRACT) {
				{
				{
				setState(342);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUBTRACT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(343);
				term();
				}
				}
				setState(348);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> DIVIDE() { return getTokens(MyLangParser1.DIVIDE); }
		public TerminalNode DIVIDE(int i) {
			return getToken(MyLangParser1.DIVIDE, i);
		}
		public List<TerminalNode> MULTIPLY() { return getTokens(MyLangParser1.MULTIPLY); }
		public TerminalNode MULTIPLY(int i) {
			return getToken(MyLangParser1.MULTIPLY, i);
		}
		public List<TerminalNode> MODULO() { return getTokens(MyLangParser1.MODULO); }
		public TerminalNode MODULO(int i) {
			return getToken(MyLangParser1.MODULO, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			factor();
			setState(354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1970324836974592L) != 0)) {
				{
				{
				setState(350);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1970324836974592L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(351);
				factor();
				}
				}
				setState(356);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser1.IDENTIFIER, 0); }
		public TerminalNode SUBTRACT() { return getToken(MyLangParser1.SUBTRACT, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(MyLangParser1.LEFT_PAREN, 0); }
		public Arithmetic_expressionContext arithmetic_expression() {
			return getRuleContext(Arithmetic_expressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(MyLangParser1.RIGHT_PAREN, 0); }
		public Array_indexContext array_index() {
			return getRuleContext(Array_indexContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_factor);
		int _la;
		try {
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(357);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(359);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUBTRACT) {
					{
					setState(358);
					match(SUBTRACT);
					}
				}

				setState(361);
				match(IDENTIFIER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(362);
				match(LEFT_PAREN);
				setState(363);
				arithmetic_expression();
				setState(364);
				match(RIGHT_PAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(367);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUBTRACT) {
					{
					setState(366);
					match(SUBTRACT);
					}
				}

				setState(369);
				match(IDENTIFIER);
				setState(370);
				array_index();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(371);
				function_call();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Array_indexContext extends ParserRuleContext {
		public TerminalNode LEFT_BRACKET() { return getToken(MyLangParser1.LEFT_BRACKET, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(MyLangParser1.RIGHT_BRACKET, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(MyLangParser1.INTEGER_LITERAL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser1.IDENTIFIER, 0); }
		public Array_indexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterArray_index(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitArray_index(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitArray_index(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_indexContext array_index() throws RecognitionException {
		Array_indexContext _localctx = new Array_indexContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_array_index);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			match(LEFT_BRACKET);
			setState(375);
			_la = _input.LA(1);
			if ( !(_la==INTEGER_LITERAL || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(376);
			match(RIGHT_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Comparison_operatorContext extends ParserRuleContext {
		public TerminalNode EQUAL() { return getToken(MyLangParser1.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(MyLangParser1.NOT_EQUAL, 0); }
		public TerminalNode GREATER() { return getToken(MyLangParser1.GREATER, 0); }
		public TerminalNode LESS() { return getToken(MyLangParser1.LESS, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(MyLangParser1.GREATER_EQUAL, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(MyLangParser1.LESS_EQUAL, 0); }
		public Comparison_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterComparison_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitComparison_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitComparison_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comparison_operatorContext comparison_operator() throws RecognitionException {
		Comparison_operatorContext _localctx = new Comparison_operatorContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16698832846848L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Array_literalContext extends ParserRuleContext {
		public TerminalNode LEFT_PAREN() { return getToken(MyLangParser1.LEFT_PAREN, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(MyLangParser1.RIGHT_PAREN, 0); }
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public List<TerminalNode> STRING_LITERAL() { return getTokens(MyLangParser1.STRING_LITERAL); }
		public TerminalNode STRING_LITERAL(int i) {
			return getToken(MyLangParser1.STRING_LITERAL, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MyLangParser1.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MyLangParser1.COMMA, i);
		}
		public Array_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterArray_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitArray_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitArray_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_literalContext array_literal() throws RecognitionException {
		Array_literalContext _localctx = new Array_literalContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_array_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			match(LEFT_PAREN);
			setState(395);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 140737670807552L) != 0)) {
				{
				setState(383);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case INTEGER_LITERAL:
				case FLOAT_LITERAL:
				case NULL_LITERAL:
				case BOOLEAN_LITERAL:
				case SUBTRACT:
					{
					setState(381);
					literal();
					}
					break;
				case STRING_LITERAL:
					{
					setState(382);
					match(STRING_LITERAL);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(392);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(385);
					match(COMMA);
					setState(388);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case INTEGER_LITERAL:
					case FLOAT_LITERAL:
					case NULL_LITERAL:
					case BOOLEAN_LITERAL:
					case SUBTRACT:
						{
						setState(386);
						literal();
						}
						break;
					case STRING_LITERAL:
						{
						setState(387);
						match(STRING_LITERAL);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					}
					setState(394);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(397);
			match(RIGHT_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(MyLangParser1.INTEGER_LITERAL, 0); }
		public TerminalNode SUBTRACT() { return getToken(MyLangParser1.SUBTRACT, 0); }
		public TerminalNode FLOAT_LITERAL() { return getToken(MyLangParser1.FLOAT_LITERAL, 0); }
		public TerminalNode BOOLEAN_LITERAL() { return getToken(MyLangParser1.BOOLEAN_LITERAL, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(MyLangParser1.NULL_LITERAL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangParser1Listener ) ((MyLangParser1Listener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyLangParser1Visitor ) return ((MyLangParser1Visitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_literal);
		int _la;
		try {
			setState(409);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(400);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUBTRACT) {
					{
					setState(399);
					match(SUBTRACT);
					}
				}

				setState(402);
				match(INTEGER_LITERAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(404);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUBTRACT) {
					{
					setState(403);
					match(SUBTRACT);
					}
				}

				setState(406);
				match(FLOAT_LITERAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(407);
				match(BOOLEAN_LITERAL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(408);
				match(NULL_LITERAL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001<\u019c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0001\u0000\u0003\u0000N\b\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0004\u0001T\b\u0001"+
		"\u000b\u0001\f\u0001U\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"[\b\u0002\u0001\u0002\u0003\u0002^\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0005\u0003c\b\u0003\n\u0003\f\u0003f\t\u0003\u0001\u0003\u0003"+
		"\u0003i\b\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0005\u0004p\b\u0004\n\u0004\f\u0004s\t\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005~\b\u0005\u0003\u0005\u0080\b\u0005\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u0084\b\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0003\u0007\u008b\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0094\b\b\u0001"+
		"\t\u0001\t\u0003\t\u0098\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u00a0\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u00a8\b\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0003\f\u00ae\b\f"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0005\r\u00bb\b\r\n\r\f\r\u00be\t\r\u0001\r\u0001\r"+
		"\u0001\r\u0003\r\u00c3\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u00d5\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0005\u0011\u00e1\b\u0011\n\u0011\f\u0011\u00e4\t\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0003\u0013\u00ee\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0003"+
		"\u0015\u00f9\b\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0005\u0016\u0100\b\u0016\n\u0016\f\u0016\u0103\t\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0004\u0017\u0108\b\u0017\u000b\u0017\f\u0017"+
		"\u0109\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0114\b\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0005\u0019\u0119\b\u0019\n\u0019\f\u0019\u011c\t\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0122\b\u001a"+
		"\u0001\u001b\u0001\u001b\u0003\u001b\u0126\b\u001b\u0001\u001b\u0003\u001b"+
		"\u0129\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0005\u001c\u012e\b"+
		"\u001c\n\u001c\f\u001c\u0131\t\u001c\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0005\u001d\u0136\b\u001d\n\u001d\f\u001d\u0139\t\u001d\u0001\u001e\u0003"+
		"\u001e\u013c\b\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0140\b\u001e"+
		"\u0001\u001e\u0001\u001e\u0003\u001e\u0144\b\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0003\u001e\u0150\b\u001e\u0001\u001e\u0001\u001e"+
		"\u0003\u001e\u0154\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0005\u001f"+
		"\u0159\b\u001f\n\u001f\f\u001f\u015c\t\u001f\u0001 \u0001 \u0001 \u0005"+
		" \u0161\b \n \f \u0164\t \u0001!\u0001!\u0003!\u0168\b!\u0001!\u0001!"+
		"\u0001!\u0001!\u0001!\u0001!\u0003!\u0170\b!\u0001!\u0001!\u0001!\u0003"+
		"!\u0175\b!\u0001\"\u0001\"\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001"+
		"$\u0001$\u0003$\u0180\b$\u0001$\u0001$\u0001$\u0003$\u0185\b$\u0005$\u0187"+
		"\b$\n$\f$\u018a\t$\u0003$\u018c\b$\u0001$\u0001$\u0001%\u0003%\u0191\b"+
		"%\u0001%\u0001%\u0003%\u0195\b%\u0001%\u0001%\u0001%\u0003%\u019a\b%\u0001"+
		"%\u0000\u0000&\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJ\u0000\u0006\u0001\u0000"+
		"37\u0003\u0000\u0005\u0005\b\t\u0010\u0010\u0001\u0000./\u0001\u00000"+
		"2\u0002\u0000\u0015\u0015<<\u0002\u0000$%(+\u01be\u0000M\u0001\u0000\u0000"+
		"\u0000\u0002S\u0001\u0000\u0000\u0000\u0004]\u0001\u0000\u0000\u0000\u0006"+
		"_\u0001\u0000\u0000\u0000\bl\u0001\u0000\u0000\u0000\n\u007f\u0001\u0000"+
		"\u0000\u0000\f\u0081\u0001\u0000\u0000\u0000\u000e\u0088\u0001\u0000\u0000"+
		"\u0000\u0010\u008f\u0001\u0000\u0000\u0000\u0012\u0097\u0001\u0000\u0000"+
		"\u0000\u0014\u00a7\u0001\u0000\u0000\u0000\u0016\u00a9\u0001\u0000\u0000"+
		"\u0000\u0018\u00ab\u0001\u0000\u0000\u0000\u001a\u00af\u0001\u0000\u0000"+
		"\u0000\u001c\u00c4\u0001\u0000\u0000\u0000\u001e\u00cf\u0001\u0000\u0000"+
		"\u0000 \u00db\u0001\u0000\u0000\u0000\"\u00dd\u0001\u0000\u0000\u0000"+
		"$\u00e5\u0001\u0000\u0000\u0000&\u00ed\u0001\u0000\u0000\u0000(\u00ef"+
		"\u0001\u0000\u0000\u0000*\u00f5\u0001\u0000\u0000\u0000,\u00fc\u0001\u0000"+
		"\u0000\u0000.\u0104\u0001\u0000\u0000\u00000\u0113\u0001\u0000\u0000\u0000"+
		"2\u0115\u0001\u0000\u0000\u00004\u0121\u0001\u0000\u0000\u00006\u0128"+
		"\u0001\u0000\u0000\u00008\u012a\u0001\u0000\u0000\u0000:\u0132\u0001\u0000"+
		"\u0000\u0000<\u0153\u0001\u0000\u0000\u0000>\u0155\u0001\u0000\u0000\u0000"+
		"@\u015d\u0001\u0000\u0000\u0000B\u0174\u0001\u0000\u0000\u0000D\u0176"+
		"\u0001\u0000\u0000\u0000F\u017a\u0001\u0000\u0000\u0000H\u017c\u0001\u0000"+
		"\u0000\u0000J\u0199\u0001\u0000\u0000\u0000LN\u0003\b\u0004\u0000ML\u0001"+
		"\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000"+
		"OP\u0003\u0002\u0001\u0000PQ\u0005\u0000\u0000\u0001Q\u0001\u0001\u0000"+
		"\u0000\u0000RT\u0003\u0004\u0002\u0000SR\u0001\u0000\u0000\u0000TU\u0001"+
		"\u0000\u0000\u0000US\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000"+
		"V\u0003\u0001\u0000\u0000\u0000W^\u0003\u0006\u0003\u0000XZ\u0003\u0012"+
		"\t\u0000Y[\u0005\u0003\u0000\u0000ZY\u0001\u0000\u0000\u0000Z[\u0001\u0000"+
		"\u0000\u0000[^\u0001\u0000\u0000\u0000\\^\u0005\u0003\u0000\u0000]W\u0001"+
		"\u0000\u0000\u0000]X\u0001\u0000\u0000\u0000]\\\u0001\u0000\u0000\u0000"+
		"^\u0005\u0001\u0000\u0000\u0000_d\u0003\n\u0005\u0000`a\u0005 \u0000\u0000"+
		"ac\u0003\n\u0005\u0000b`\u0001\u0000\u0000\u0000cf\u0001\u0000\u0000\u0000"+
		"db\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000eh\u0001\u0000\u0000"+
		"\u0000fd\u0001\u0000\u0000\u0000gi\u0005 \u0000\u0000hg\u0001\u0000\u0000"+
		"\u0000hi\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jk\u0005\u0003"+
		"\u0000\u0000k\u0007\u0001\u0000\u0000\u0000lq\u0003\u001e\u000f\u0000"+
		"mn\u0005\u0003\u0000\u0000np\u0003\u001e\u000f\u0000om\u0001\u0000\u0000"+
		"\u0000ps\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000qr\u0001\u0000"+
		"\u0000\u0000r\t\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000t\u0080"+
		"\u0003\u0010\b\u0000u\u0080\u0003\u0014\n\u0000v\u0080\u0003\u0018\f\u0000"+
		"w\u0080\u0005\u0006\u0000\u0000x\u0080\u0005\u0007\u0000\u0000y\u0080"+
		"\u0003\f\u0006\u0000z\u0080\u0003\u000e\u0007\u0000{}\u0003*\u0015\u0000"+
		"|~\u0005 \u0000\u0000}|\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000"+
		"~\u0080\u0001\u0000\u0000\u0000\u007ft\u0001\u0000\u0000\u0000\u007fu"+
		"\u0001\u0000\u0000\u0000\u007fv\u0001\u0000\u0000\u0000\u007fw\u0001\u0000"+
		"\u0000\u0000\u007fx\u0001\u0000\u0000\u0000\u007fy\u0001\u0000\u0000\u0000"+
		"\u007fz\u0001\u0000\u0000\u0000\u007f{\u0001\u0000\u0000\u0000\u0080\u000b"+
		"\u0001\u0000\u0000\u0000\u0081\u0083\u00058\u0000\u0000\u0082\u0084\u0005"+
		"\u001c\u0000\u0000\u0083\u0082\u0001\u0000\u0000\u0000\u0083\u0084\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0086\u0003"+
		"0\u0018\u0000\u0086\u0087\u0005\u001d\u0000\u0000\u0087\r\u0001\u0000"+
		"\u0000\u0000\u0088\u008a\u00059\u0000\u0000\u0089\u008b\u0005\u001c\u0000"+
		"\u0000\u008a\u0089\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000"+
		"\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008d\u0005<\u0000\u0000"+
		"\u008d\u008e\u0005\u001d\u0000\u0000\u008e\u000f\u0001\u0000\u0000\u0000"+
		"\u008f\u0090\u0003&\u0013\u0000\u0090\u0093\u0005<\u0000\u0000\u0091\u0092"+
		"\u0005#\u0000\u0000\u0092\u0094\u00030\u0018\u0000\u0093\u0091\u0001\u0000"+
		"\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u0011\u0001\u0000"+
		"\u0000\u0000\u0095\u0098\u0003\u001a\r\u0000\u0096\u0098\u0003\u001c\u000e"+
		"\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0097\u0096\u0001\u0000\u0000"+
		"\u0000\u0098\u0013\u0001\u0000\u0000\u0000\u0099\u009a\u0005<\u0000\u0000"+
		"\u009a\u009b\u0005#\u0000\u0000\u009b\u00a8\u00030\u0018\u0000\u009c\u009f"+
		"\u0005<\u0000\u0000\u009d\u00a0\u0005#\u0000\u0000\u009e\u00a0\u0003\u0016"+
		"\u000b\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u009f\u009e\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a8\u00030\u0018"+
		"\u0000\u00a2\u00a3\u0005<\u0000\u0000\u00a3\u00a4\u0003D\"\u0000\u00a4"+
		"\u00a5\u0005#\u0000\u0000\u00a5\u00a6\u00030\u0018\u0000\u00a6\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a7\u0099\u0001\u0000\u0000\u0000\u00a7\u009c\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a2\u0001\u0000\u0000\u0000\u00a8\u0015\u0001"+
		"\u0000\u0000\u0000\u00a9\u00aa\u0007\u0000\u0000\u0000\u00aa\u0017\u0001"+
		"\u0000\u0000\u0000\u00ab\u00ad\u0005\u0012\u0000\u0000\u00ac\u00ae\u0003"+
		"0\u0018\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000"+
		"\u0000\u0000\u00ae\u0019\u0001\u0000\u0000\u0000\u00af\u00b0\u0005\n\u0000"+
		"\u0000\u00b0\u00b1\u00030\u0018\u0000\u00b1\u00b2\u0005\r\u0000\u0000"+
		"\u00b2\u00b3\u0005\'\u0000\u0000\u00b3\u00bc\u0003.\u0017\u0000\u00b4"+
		"\u00b5\u0005\u000b\u0000\u0000\u00b5\u00b6\u00030\u0018\u0000\u00b6\u00b7"+
		"\u0005\r\u0000\u0000\u00b7\u00b8\u0005\'\u0000\u0000\u00b8\u00b9\u0003"+
		".\u0017\u0000\u00b9\u00bb\u0001\u0000\u0000\u0000\u00ba\u00b4\u0001\u0000"+
		"\u0000\u0000\u00bb\u00be\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000"+
		"\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u00c2\u0001\u0000"+
		"\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005\f\u0000"+
		"\u0000\u00c0\u00c1\u0005\'\u0000\u0000\u00c1\u00c3\u0003.\u0017\u0000"+
		"\u00c2\u00bf\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c3\u001b\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005\u000e\u0000\u0000"+
		"\u00c5\u00c6\u0005\u001c\u0000\u0000\u00c6\u00c7\u0003\u0010\b\u0000\u00c7"+
		"\u00c8\u0005 \u0000\u0000\u00c8\u00c9\u00030\u0018\u0000\u00c9\u00ca\u0005"+
		" \u0000\u0000\u00ca\u00cb\u0003\u0014\n\u0000\u00cb\u00cc\u0005\u001d"+
		"\u0000\u0000\u00cc\u00cd\u0005\'\u0000\u0000\u00cd\u00ce\u0003.\u0017"+
		"\u0000\u00ce\u001d\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005\u0011\u0000"+
		"\u0000\u00d0\u00d1\u0003 \u0010\u0000\u00d1\u00d2\u0005<\u0000\u0000\u00d2"+
		"\u00d4\u0005\u001c\u0000\u0000\u00d3\u00d5\u0003\"\u0011\u0000\u00d4\u00d3"+
		"\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d6"+
		"\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005\u001d\u0000\u0000\u00d7\u00d8"+
		"\u0005\'\u0000\u0000\u00d8\u00d9\u0003.\u0017\u0000\u00d9\u00da\u0005"+
		"\u0013\u0000\u0000\u00da\u001f\u0001\u0000\u0000\u0000\u00db\u00dc\u0003"+
		"&\u0013\u0000\u00dc!\u0001\u0000\u0000\u0000\u00dd\u00e2\u0003$\u0012"+
		"\u0000\u00de\u00df\u0005!\u0000\u0000\u00df\u00e1\u0003$\u0012\u0000\u00e0"+
		"\u00de\u0001\u0000\u0000\u0000\u00e1\u00e4\u0001\u0000\u0000\u0000\u00e2"+
		"\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3"+
		"#\u0001\u0000\u0000\u0000\u00e4\u00e2\u0001\u0000\u0000\u0000\u00e5\u00e6"+
		"\u0003&\u0013\u0000\u00e6\u00e7\u0005<\u0000\u0000\u00e7%\u0001\u0000"+
		"\u0000\u0000\u00e8\u00ee\u0005\t\u0000\u0000\u00e9\u00ee\u0005\b\u0000"+
		"\u0000\u00ea\u00ee\u0005\u0005\u0000\u0000\u00eb\u00ee\u0005\u0010\u0000"+
		"\u0000\u00ec\u00ee\u0003(\u0014\u0000\u00ed\u00e8\u0001\u0000\u0000\u0000"+
		"\u00ed\u00e9\u0001\u0000\u0000\u0000\u00ed\u00ea\u0001\u0000\u0000\u0000"+
		"\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ed\u00ec\u0001\u0000\u0000\u0000"+
		"\u00ee\'\u0001\u0000\u0000\u0000\u00ef\u00f0\u0005\u000f\u0000\u0000\u00f0"+
		"\u00f1\u0005\u001e\u0000\u0000\u00f1\u00f2\u0005\u0015\u0000\u0000\u00f2"+
		"\u00f3\u0005\u001f\u0000\u0000\u00f3\u00f4\u0007\u0001\u0000\u0000\u00f4"+
		")\u0001\u0000\u0000\u0000\u00f5\u00f6\u0005<\u0000\u0000\u00f6\u00f8\u0005"+
		"\u001c\u0000\u0000\u00f7\u00f9\u0003,\u0016\u0000\u00f8\u00f7\u0001\u0000"+
		"\u0000\u0000\u00f8\u00f9\u0001\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000"+
		"\u0000\u0000\u00fa\u00fb\u0005\u001d\u0000\u0000\u00fb+\u0001\u0000\u0000"+
		"\u0000\u00fc\u0101\u00030\u0018\u0000\u00fd\u00fe\u0005!\u0000\u0000\u00fe"+
		"\u0100\u00030\u0018\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u0100\u0103"+
		"\u0001\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101\u0102"+
		"\u0001\u0000\u0000\u0000\u0102-\u0001\u0000\u0000\u0000\u0103\u0101\u0001"+
		"\u0000\u0000\u0000\u0104\u0105\u0005\u0003\u0000\u0000\u0105\u0107\u0005"+
		"\u0001\u0000\u0000\u0106\u0108\u0003\u0004\u0002\u0000\u0107\u0106\u0001"+
		"\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000\u0000\u0109\u0107\u0001"+
		"\u0000\u0000\u0000\u0109\u010a\u0001\u0000\u0000\u0000\u010a\u010b\u0001"+
		"\u0000\u0000\u0000\u010b\u010c\u0005\u0002\u0000\u0000\u010c/\u0001\u0000"+
		"\u0000\u0000\u010d\u0114\u00036\u001b\u0000\u010e\u0114\u00038\u001c\u0000"+
		"\u010f\u0114\u0003>\u001f\u0000\u0110\u0114\u0003*\u0015\u0000\u0111\u0114"+
		"\u00032\u0019\u0000\u0112\u0114\u0003H$\u0000\u0113\u010d\u0001\u0000"+
		"\u0000\u0000\u0113\u010e\u0001\u0000\u0000\u0000\u0113\u010f\u0001\u0000"+
		"\u0000\u0000\u0113\u0110\u0001\u0000\u0000\u0000\u0113\u0111\u0001\u0000"+
		"\u0000\u0000\u0113\u0112\u0001\u0000\u0000\u0000\u01141\u0001\u0000\u0000"+
		"\u0000\u0115\u011a\u00034\u001a\u0000\u0116\u0117\u0005.\u0000\u0000\u0117"+
		"\u0119\u00034\u001a\u0000\u0118\u0116\u0001\u0000\u0000\u0000\u0119\u011c"+
		"\u0001\u0000\u0000\u0000\u011a\u0118\u0001\u0000\u0000\u0000\u011a\u011b"+
		"\u0001\u0000\u0000\u0000\u011b3\u0001\u0000\u0000\u0000\u011c\u011a\u0001"+
		"\u0000\u0000\u0000\u011d\u0122\u0005\u0019\u0000\u0000\u011e\u0122\u0005"+
		"<\u0000\u0000\u011f\u0120\u0005<\u0000\u0000\u0120\u0122\u0003D\"\u0000"+
		"\u0121\u011d\u0001\u0000\u0000\u0000\u0121\u011e\u0001\u0000\u0000\u0000"+
		"\u0121\u011f\u0001\u0000\u0000\u0000\u01225\u0001\u0000\u0000\u0000\u0123"+
		"\u0129\u0003J%\u0000\u0124\u0126\u0005/\u0000\u0000\u0125\u0124\u0001"+
		"\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0127\u0001"+
		"\u0000\u0000\u0000\u0127\u0129\u0005<\u0000\u0000\u0128\u0123\u0001\u0000"+
		"\u0000\u0000\u0128\u0125\u0001\u0000\u0000\u0000\u01297\u0001\u0000\u0000"+
		"\u0000\u012a\u012f\u0003:\u001d\u0000\u012b\u012c\u0005-\u0000\u0000\u012c"+
		"\u012e\u0003:\u001d\u0000\u012d\u012b\u0001\u0000\u0000\u0000\u012e\u0131"+
		"\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u012f\u0130"+
		"\u0001\u0000\u0000\u0000\u01309\u0001\u0000\u0000\u0000\u0131\u012f\u0001"+
		"\u0000\u0000\u0000\u0132\u0137\u0003<\u001e\u0000\u0133\u0134\u0005,\u0000"+
		"\u0000\u0134\u0136\u0003<\u001e\u0000\u0135\u0133\u0001\u0000\u0000\u0000"+
		"\u0136\u0139\u0001\u0000\u0000\u0000\u0137\u0135\u0001\u0000\u0000\u0000"+
		"\u0137\u0138\u0001\u0000\u0000\u0000\u0138;\u0001\u0000\u0000\u0000\u0139"+
		"\u0137\u0001\u0000\u0000\u0000\u013a\u013c\u0005&\u0000\u0000\u013b\u013a"+
		"\u0001\u0000\u0000\u0000\u013b\u013c\u0001\u0000\u0000\u0000\u013c\u013d"+
		"\u0001\u0000\u0000\u0000\u013d\u0154\u0005\u001b\u0000\u0000\u013e\u0140"+
		"\u0005&\u0000\u0000\u013f\u013e\u0001\u0000\u0000\u0000\u013f\u0140\u0001"+
		"\u0000\u0000\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0154\u0005"+
		"<\u0000\u0000\u0142\u0144\u0005&\u0000\u0000\u0143\u0142\u0001\u0000\u0000"+
		"\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144\u0145\u0001\u0000\u0000"+
		"\u0000\u0145\u0146\u0005\u001c\u0000\u0000\u0146\u0147\u00038\u001c\u0000"+
		"\u0147\u0148\u0005\u001d\u0000\u0000\u0148\u0154\u0001\u0000\u0000\u0000"+
		"\u0149\u0154\u0003F#\u0000\u014a\u014b\u0003>\u001f\u0000\u014b\u014c"+
		"\u0003F#\u0000\u014c\u014d\u0003>\u001f\u0000\u014d\u0154\u0001\u0000"+
		"\u0000\u0000\u014e\u0150\u0005&\u0000\u0000\u014f\u014e\u0001\u0000\u0000"+
		"\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0151\u0001\u0000\u0000"+
		"\u0000\u0151\u0152\u0005<\u0000\u0000\u0152\u0154\u0003D\"\u0000\u0153"+
		"\u013b\u0001\u0000\u0000\u0000\u0153\u013f\u0001\u0000\u0000\u0000\u0153"+
		"\u0143\u0001\u0000\u0000\u0000\u0153\u0149\u0001\u0000\u0000\u0000\u0153"+
		"\u014a\u0001\u0000\u0000\u0000\u0153\u014f\u0001\u0000\u0000\u0000\u0154"+
		"=\u0001\u0000\u0000\u0000\u0155\u015a\u0003@ \u0000\u0156\u0157\u0007"+
		"\u0002\u0000\u0000\u0157\u0159\u0003@ \u0000\u0158\u0156\u0001\u0000\u0000"+
		"\u0000\u0159\u015c\u0001\u0000\u0000\u0000\u015a\u0158\u0001\u0000\u0000"+
		"\u0000\u015a\u015b\u0001\u0000\u0000\u0000\u015b?\u0001\u0000\u0000\u0000"+
		"\u015c\u015a\u0001\u0000\u0000\u0000\u015d\u0162\u0003B!\u0000\u015e\u015f"+
		"\u0007\u0003\u0000\u0000\u015f\u0161\u0003B!\u0000\u0160\u015e\u0001\u0000"+
		"\u0000\u0000\u0161\u0164\u0001\u0000\u0000\u0000\u0162\u0160\u0001\u0000"+
		"\u0000\u0000\u0162\u0163\u0001\u0000\u0000\u0000\u0163A\u0001\u0000\u0000"+
		"\u0000\u0164\u0162\u0001\u0000\u0000\u0000\u0165\u0175\u0003J%\u0000\u0166"+
		"\u0168\u0005/\u0000\u0000\u0167\u0166\u0001\u0000\u0000\u0000\u0167\u0168"+
		"\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000\u0000\u0000\u0169\u0175"+
		"\u0005<\u0000\u0000\u016a\u016b\u0005\u001c\u0000\u0000\u016b\u016c\u0003"+
		">\u001f\u0000\u016c\u016d\u0005\u001d\u0000\u0000\u016d\u0175\u0001\u0000"+
		"\u0000\u0000\u016e\u0170\u0005/\u0000\u0000\u016f\u016e\u0001\u0000\u0000"+
		"\u0000\u016f\u0170\u0001\u0000\u0000\u0000\u0170\u0171\u0001\u0000\u0000"+
		"\u0000\u0171\u0172\u0005<\u0000\u0000\u0172\u0175\u0003D\"\u0000\u0173"+
		"\u0175\u0003*\u0015\u0000\u0174\u0165\u0001\u0000\u0000\u0000\u0174\u0167"+
		"\u0001\u0000\u0000\u0000\u0174\u016a\u0001\u0000\u0000\u0000\u0174\u016f"+
		"\u0001\u0000\u0000\u0000\u0174\u0173\u0001\u0000\u0000\u0000\u0175C\u0001"+
		"\u0000\u0000\u0000\u0176\u0177\u0005\u001e\u0000\u0000\u0177\u0178\u0007"+
		"\u0004\u0000\u0000\u0178\u0179\u0005\u001f\u0000\u0000\u0179E\u0001\u0000"+
		"\u0000\u0000\u017a\u017b\u0007\u0005\u0000\u0000\u017bG\u0001\u0000\u0000"+
		"\u0000\u017c\u018b\u0005\u001c\u0000\u0000\u017d\u0180\u0003J%\u0000\u017e"+
		"\u0180\u0005\u0019\u0000\u0000\u017f\u017d\u0001\u0000\u0000\u0000\u017f"+
		"\u017e\u0001\u0000\u0000\u0000\u0180\u0188\u0001\u0000\u0000\u0000\u0181"+
		"\u0184\u0005!\u0000\u0000\u0182\u0185\u0003J%\u0000\u0183\u0185\u0005"+
		"\u0019\u0000\u0000\u0184\u0182\u0001\u0000\u0000\u0000\u0184\u0183\u0001"+
		"\u0000\u0000\u0000\u0185\u0187\u0001\u0000\u0000\u0000\u0186\u0181\u0001"+
		"\u0000\u0000\u0000\u0187\u018a\u0001\u0000\u0000\u0000\u0188\u0186\u0001"+
		"\u0000\u0000\u0000\u0188\u0189\u0001\u0000\u0000\u0000\u0189\u018c\u0001"+
		"\u0000\u0000\u0000\u018a\u0188\u0001\u0000\u0000\u0000\u018b\u017f\u0001"+
		"\u0000\u0000\u0000\u018b\u018c\u0001\u0000\u0000\u0000\u018c\u018d\u0001"+
		"\u0000\u0000\u0000\u018d\u018e\u0005\u001d\u0000\u0000\u018eI\u0001\u0000"+
		"\u0000\u0000\u018f\u0191\u0005/\u0000\u0000\u0190\u018f\u0001\u0000\u0000"+
		"\u0000\u0190\u0191\u0001\u0000\u0000\u0000\u0191\u0192\u0001\u0000\u0000"+
		"\u0000\u0192\u019a\u0005\u0015\u0000\u0000\u0193\u0195\u0005/\u0000\u0000"+
		"\u0194\u0193\u0001\u0000\u0000\u0000\u0194\u0195\u0001\u0000\u0000\u0000"+
		"\u0195\u0196\u0001\u0000\u0000\u0000\u0196\u019a\u0005\u0016\u0000\u0000"+
		"\u0197\u019a\u0005\u001b\u0000\u0000\u0198\u019a\u0005\u0017\u0000\u0000"+
		"\u0199\u0190\u0001\u0000\u0000\u0000\u0199\u0194\u0001\u0000\u0000\u0000"+
		"\u0199\u0197\u0001\u0000\u0000\u0000\u0199\u0198\u0001\u0000\u0000\u0000"+
		"\u019aK\u0001\u0000\u0000\u00000MUZ]dhq}\u007f\u0083\u008a\u0093\u0097"+
		"\u009f\u00a7\u00ad\u00bc\u00c2\u00d4\u00e2\u00ed\u00f8\u0101\u0109\u0113"+
		"\u011a\u0121\u0125\u0128\u012f\u0137\u013b\u013f\u0143\u014f\u0153\u015a"+
		"\u0162\u0167\u016f\u0174\u017f\u0184\u0188\u018b\u0190\u0194\u0199";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}