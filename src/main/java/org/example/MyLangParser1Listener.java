// Generated from C:/Users/user/IdeaProjects/Kompilyator/src/main/java/org/example/MyLangParser1.g4 by ANTLR 4.13.2
package org.example;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MyLangParser1}.
 */
public interface MyLangParser1Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MyLangParser1.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MyLangParser1.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(MyLangParser1.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(MyLangParser1.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MyLangParser1.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MyLangParser1.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#simple_statements}.
	 * @param ctx the parse tree
	 */
	void enterSimple_statements(MyLangParser1.Simple_statementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#simple_statements}.
	 * @param ctx the parse tree
	 */
	void exitSimple_statements(MyLangParser1.Simple_statementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#function_declarations}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declarations(MyLangParser1.Function_declarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#function_declarations}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declarations(MyLangParser1.Function_declarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimple_stmt(MyLangParser1.Simple_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimple_stmt(MyLangParser1.Simple_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(MyLangParser1.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(MyLangParser1.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(MyLangParser1.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(MyLangParser1.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#compound_statement}.
	 * @param ctx the parse tree
	 */
	void enterCompound_statement(MyLangParser1.Compound_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#compound_statement}.
	 * @param ctx the parse tree
	 */
	void exitCompound_statement(MyLangParser1.Compound_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MyLangParser1.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MyLangParser1.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#compound_operator}.
	 * @param ctx the parse tree
	 */
	void enterCompound_operator(MyLangParser1.Compound_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#compound_operator}.
	 * @param ctx the parse tree
	 */
	void exitCompound_operator(MyLangParser1.Compound_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#return_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statement(MyLangParser1.Return_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#return_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statement(MyLangParser1.Return_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#conditional_statement}.
	 * @param ctx the parse tree
	 */
	void enterConditional_statement(MyLangParser1.Conditional_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#conditional_statement}.
	 * @param ctx the parse tree
	 */
	void exitConditional_statement(MyLangParser1.Conditional_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#for}.
	 * @param ctx the parse tree
	 */
	void enterFor(MyLangParser1.ForContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#for}.
	 * @param ctx the parse tree
	 */
	void exitFor(MyLangParser1.ForContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#function_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration(MyLangParser1.Function_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#function_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration(MyLangParser1.Function_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#return_type}.
	 * @param ctx the parse tree
	 */
	void enterReturn_type(MyLangParser1.Return_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#return_type}.
	 * @param ctx the parse tree
	 */
	void exitReturn_type(MyLangParser1.Return_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(MyLangParser1.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(MyLangParser1.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(MyLangParser1.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(MyLangParser1.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MyLangParser1.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MyLangParser1.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(MyLangParser1.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(MyLangParser1.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(MyLangParser1.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(MyLangParser1.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(MyLangParser1.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(MyLangParser1.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MyLangParser1.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MyLangParser1.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MyLangParser1.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MyLangParser1.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#string_expression}.
	 * @param ctx the parse tree
	 */
	void enterString_expression(MyLangParser1.String_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#string_expression}.
	 * @param ctx the parse tree
	 */
	void exitString_expression(MyLangParser1.String_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#string_term}.
	 * @param ctx the parse tree
	 */
	void enterString_term(MyLangParser1.String_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#string_term}.
	 * @param ctx the parse tree
	 */
	void exitString_term(MyLangParser1.String_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterSingleExpression(MyLangParser1.SingleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitSingleExpression(MyLangParser1.SingleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#logical_expression}.
	 * @param ctx the parse tree
	 */
	void enterLogical_expression(MyLangParser1.Logical_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#logical_expression}.
	 * @param ctx the parse tree
	 */
	void exitLogical_expression(MyLangParser1.Logical_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#term_logical}.
	 * @param ctx the parse tree
	 */
	void enterTerm_logical(MyLangParser1.Term_logicalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#term_logical}.
	 * @param ctx the parse tree
	 */
	void exitTerm_logical(MyLangParser1.Term_logicalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#factor_logical}.
	 * @param ctx the parse tree
	 */
	void enterFactor_logical(MyLangParser1.Factor_logicalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#factor_logical}.
	 * @param ctx the parse tree
	 */
	void exitFactor_logical(MyLangParser1.Factor_logicalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#arithmetic_expression}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_expression(MyLangParser1.Arithmetic_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#arithmetic_expression}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_expression(MyLangParser1.Arithmetic_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(MyLangParser1.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(MyLangParser1.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(MyLangParser1.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(MyLangParser1.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#array_index}.
	 * @param ctx the parse tree
	 */
	void enterArray_index(MyLangParser1.Array_indexContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#array_index}.
	 * @param ctx the parse tree
	 */
	void exitArray_index(MyLangParser1.Array_indexContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void enterComparison_operator(MyLangParser1.Comparison_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void exitComparison_operator(MyLangParser1.Comparison_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#array_literal}.
	 * @param ctx the parse tree
	 */
	void enterArray_literal(MyLangParser1.Array_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#array_literal}.
	 * @param ctx the parse tree
	 */
	void exitArray_literal(MyLangParser1.Array_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser1#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MyLangParser1.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser1#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MyLangParser1.LiteralContext ctx);
}