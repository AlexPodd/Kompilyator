// Generated from C:/Users/user/IdeaProjects/Kompilyator/src/main/java/org/example/MyLangParser1.g4 by ANTLR 4.13.2
package org.example;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MyLangParser1}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MyLangParser1Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MyLangParser1.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(MyLangParser1.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MyLangParser1.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#simple_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_statements(MyLangParser1.Simple_statementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#function_declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declarations(MyLangParser1.Function_declarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt(MyLangParser1.Simple_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(MyLangParser1.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInput(MyLangParser1.InputContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(MyLangParser1.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#compound_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_statement(MyLangParser1.Compound_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(MyLangParser1.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#compound_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_operator(MyLangParser1.Compound_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#return_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statement(MyLangParser1.Return_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#conditional_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional_statement(MyLangParser1.Conditional_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(MyLangParser1.ForContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#function_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declaration(MyLangParser1.Function_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#return_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_type(MyLangParser1.Return_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(MyLangParser1.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(MyLangParser1.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MyLangParser1.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(MyLangParser1.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(MyLangParser1.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(MyLangParser1.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MyLangParser1.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MyLangParser1.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#string_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_expression(MyLangParser1.String_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#string_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_term(MyLangParser1.String_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleExpression(MyLangParser1.SingleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#logical_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_expression(MyLangParser1.Logical_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#term_logical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm_logical(MyLangParser1.Term_logicalContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#factor_logical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor_logical(MyLangParser1.Factor_logicalContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#arithmetic_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic_expression(MyLangParser1.Arithmetic_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(MyLangParser1.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(MyLangParser1.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#array_index}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_index(MyLangParser1.Array_indexContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#comparison_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison_operator(MyLangParser1.Comparison_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#array_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_literal(MyLangParser1.Array_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser1#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(MyLangParser1.LiteralContext ctx);
}