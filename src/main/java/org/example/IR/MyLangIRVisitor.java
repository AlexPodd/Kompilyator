package org.example.IR;

import org.example.MyLangParser1;
import org.example.MyLangParser1BaseVisitor;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;


public class MyLangIRVisitor extends MyLangParser1BaseVisitor<String> {
    private static int tempVarCount = 0;

    private static int tempLabelCount = 0;

    private ArrayList<Label> needLabel;

    private ArrayList<Instructions> instructions;
    private SymbolTable table;
    private SymbolTable globalTable;
    public MyLangIRVisitor(SymbolTable globalTable){
        instructions = new ArrayList<>();
        needLabel = new ArrayList<>();
        this.table = globalTable;
        this.globalTable = globalTable;
    }

    private String newTemp() {
        return "t" + (tempVarCount++);
    }

    private String newLabel() {
        return "L" + (tempLabelCount++);
    }

    @Override
    public String visitArithmetic_expression(MyLangParser1.Arithmetic_expressionContext ctx) {
        if (ctx.term().isEmpty()) {
            throw new IllegalStateException("Empty arithmetic expression");
        }

        String result = visitTerm(ctx.term(0));
        if (result == null) {
            throw new IllegalStateException("Null result from first term");
        }

        for (int i = 1; i < ctx.term().size(); i++) {
            String operator = ctx.getChild(2 * i - 1).getText();
            String rightOperand = visitTerm(ctx.term(i));
            if (rightOperand == null) {
                throw new IllegalStateException("Null right operand for operator: " + operator);
            }

            String temp = newTemp();
            instructions.add(new Instructions(operator, result, rightOperand, temp, table));
            result = temp;
        }
        return result;
    }

    @Override
    public String visitTerm(MyLangParser1.TermContext ctx) {
        if (ctx.factor().isEmpty()) {
            throw new IllegalStateException("Empty term");
        }

        String result = visitFactor(ctx.factor(0));
        if (result == null) {
            throw new IllegalStateException("Null result from first factor");
        }

        for (int i = 1; i < ctx.factor().size(); i++) {
            String operator = ctx.getChild(2 * i - 1).getText();
            String rightOperand = visitFactor(ctx.factor(i));
            if (rightOperand == null) {
                throw new IllegalStateException("Null right operand for operator: " + operator);
            }

            String temp = newTemp();
            instructions.add(new Instructions(operator, result, rightOperand, temp, table));
            result = temp;
        }
        return result;
    }

    @Override
    public String visitFactor(MyLangParser1.FactorContext ctx) {
        // Обработка идентификаторов
        if (ctx.IDENTIFIER() != null) {
            String prefix = ctx.SUBTRACT() != null ? "-" : "";
            if (ctx.array_index() != null) {
                String index = visitArray_index(ctx.array_index());
                return prefix + ctx.IDENTIFIER().getText() + "[" + index + "]";
            }
            return prefix + ctx.IDENTIFIER().getText();
        }

        // Обработка литералов
        if (ctx.literal() != null) {
            String literal = ctx.literal().getText();
            if (ctx.SUBTRACT() != null) {
                return "-" + literal;
            }
            return literal;
        }

        // Обработка выражений в скобках
        if (ctx.LEFT_PAREN() != null && ctx.RIGHT_PAREN() != null) {
            String expr = visitArithmetic_expression(ctx.arithmetic_expression());
            if (ctx.SUBTRACT() != null) {
                return "-(" + expr + ")";
            }
            return expr;
        }

        // Обработка вызовов функций
        if (ctx.function_call() != null) {
            String call = visitFunction_call(ctx.function_call());
            if (ctx.SUBTRACT() != null) {
                return "-" + call;
            }
            return call;
        }

        throw new IllegalStateException("Unsupported factor: " + ctx.getText());
    }


    @Override
    public String visitConditional_statement(MyLangParser1.Conditional_statementContext ctx) {
        String endLabel = newLabel();

        for (int i = 0; i < ctx.expression().size(); i++) {
            if(ctx.expression().size() == 1 && ctx.ELSE() == null){
                instructions.add(new Instructions("ifFalse",visitExpression(ctx.expression(i)),null,endLabel, table));
                table = table.enterScopeIR();
                visitBlock(ctx.block(i));
                table = table.exitScope();
                needLabel.add(new Label(endLabel, instructions.size()));
                return endLabel;
            }

            String nextLabel = newLabel();
            instructions.add(new Instructions("ifFalse",visitExpression(ctx.expression(i)),null,nextLabel, table));
            table = table.enterScopeIR();
            visitBlock(ctx.block(i));
            table = table.exitScope();
            instructions.add(new Instructions("goto", null, null, endLabel, table));
            needLabel.add(new Label(nextLabel, instructions.size()));
        }
            if (ctx.ELSE() != null) {
                table = table.enterScopeIR();
                visitBlock(ctx.block(ctx.block().size() - 1));
                table = table.exitScope();
            }


        needLabel.add(new Label(endLabel, instructions.size()));

        return endLabel;
    }


    @Override
    public String visitAssignment(MyLangParser1.AssignmentContext ctx) {
        instructions.add(new Instructions("assign", visitExpression(ctx.expression()), null, ctx.IDENTIFIER().getText(), table));
        return null;
    }

    @Override
    public String visitSingleExpression(MyLangParser1.SingleExpressionContext ctx) {
        if (ctx.IDENTIFIER() != null){
            return ctx.IDENTIFIER().getText();
        }
        if(ctx.literal() != null){
            return ctx.literal().getText();
        }
        return super.visitSingleExpression(ctx);
    }

    @Override
    public String visitLogical_expression(MyLangParser1.Logical_expressionContext ctx) {

        Instructions.setIsLogical(true);

        if (ctx.term_logical().size() == 1) {

            return visitTerm_logical(ctx.term_logical(0));
        }

        String result = newTemp();
        String endLabel = newLabel();

        for (int i = 0; i < ctx.term_logical().size(); i++) {

            String termRes = visitTerm_logical(ctx.term_logical(i));
            String nextLabel = newLabel();

            instructions.add(new Instructions("ifTrue", termRes, null, endLabel, table));
            needLabel.add(new Label(nextLabel, instructions.size()));
        }


        instructions.add(new Instructions("assign", "0", null, result, table));
        instructions.add(new Instructions("goto", null, null, endLabel, table));
        needLabel.add(new Label(endLabel, instructions.size()));
        instructions.add(new Instructions("assign", "1", null, result, table));

        Instructions.setIsLogical(false);

        return result;
    }

    @Override
    public String visitTerm_logical(MyLangParser1.Term_logicalContext ctx) {

        if (ctx.factor_logical().size() == 1) {

            return visitFactor_logical(ctx.factor_logical(0));
        }

        String result = newTemp();
        String endLabel = newLabel();
        String trueLabel = newLabel();

        for (int i = 0; i < ctx.factor_logical().size(); i++) {

            String factorRes = visitFactor_logical(ctx.factor_logical(i));
            String nextLabel = newLabel();

            instructions.add(new Instructions("ifFalse", factorRes, null, endLabel, table));
            needLabel.add(new Label(nextLabel, instructions.size()));
        }


        instructions.add(new Instructions("assign", "1", null, result, table));
        instructions.add(new Instructions("goto", null, null, trueLabel, table));
        needLabel.add(new Label(endLabel, instructions.size()));
        instructions.add(new Instructions("assign", "0", null, result, table));
        needLabel.add(new Label(trueLabel, instructions.size()));

        Instructions.setIsLogical(false);

        return result;
    }

    @Override
    public String visitFactor_logical(MyLangParser1.Factor_logicalContext ctx) {


        // Обработка NOT (должна быть первой, так как NOT может сочетаться с другими случаями)
        if (ctx.NOT() != null) {

            String falseLabel = newLabel();
            String endLabel = newLabel();
            String temp = newTemp();
            String expr = null;

            if (ctx.BOOLEAN_LITERAL() != null) {
                expr = ctx.BOOLEAN_LITERAL().getText();

            }
            else if (ctx.IDENTIFIER() != null) {
                expr = ctx.IDENTIFIER().getText();
                if (ctx.array_index() != null) {
                    expr += "[" + visitArray_index(ctx.array_index()) + "]";
                }

            }
            else if (ctx.logical_expression() != null) {
                expr = visitLogical_expression(ctx.logical_expression());

            }

            if (expr == null) {

                throw new IllegalStateException("No expression for NOT operation");
            }


            instructions.add(new Instructions("ifFalse", expr, null, falseLabel, table));
            instructions.add(new Instructions("assign", "1", null, temp, table));
            instructions.add(new Instructions("goto", null, null, endLabel, table));
            needLabel.add(new Label(falseLabel, instructions.size()));
            instructions.add(new Instructions("assign", "0", null, temp, table));
            needLabel.add(new Label(endLabel, instructions.size()));

            Instructions.setIsLogical(false);

            return temp;
        }

        // Обработка выражений в скобках
        if (ctx.LEFT_PAREN() != null && ctx.RIGHT_PAREN() != null) {

            String result = visitLogical_expression(ctx.logical_expression());

            return result;
        }

        // Обработка литералов и идентификаторов
        if (ctx.BOOLEAN_LITERAL() != null) {

            return ctx.BOOLEAN_LITERAL().getText(); // Возвращаем как есть
        }

        if (ctx.IDENTIFIER() != null) {

            String identifier = ctx.IDENTIFIER().getText();
            if (ctx.array_index() != null) {
                identifier += "[" + visitArray_index(ctx.array_index()) + "]";
            }
            return identifier;
        }

        // Обработка операторов сравнения
        if (ctx.comparison_operator() != null && ctx.arithmetic_expression().size() == 2) {

            String left = visitArithmetic_expression(ctx.arithmetic_expression(0));
            String right = visitArithmetic_expression(ctx.arithmetic_expression(1));
            String temp = newTemp();
            String op = ctx.comparison_operator().getText();


            String trueLabel = newLabel();
            String endLabel = newLabel();


            instructions.add(new Instructions("ifFalse", left, op, right, trueLabel, table));
            instructions.add(new Instructions("assign", "0", null, temp, table));
            instructions.add(new Instructions("goto", null, null, endLabel, table));
            needLabel.add(new Label(trueLabel, instructions.size()));
            instructions.add(new Instructions("assign", "1", null, temp, table));
            needLabel.add(new Label(endLabel, instructions.size()));

            Instructions.setIsLogical(false);

            return temp;
        }


        Instructions.setIsLogical(false);
        throw new UnsupportedOperationException("Unsupported factor_logical case: " + ctx.getText());
    }
    @Override
    public String visitPrint(MyLangParser1.PrintContext ctx) {
        String temp = newTemp();
        temp = visitExpression(ctx.expression());
        instructions.add(new Instructions("print", null , null, temp, table));
        return null;
    }

    @Override
    public String visitFor(MyLangParser1.ForContext ctx) {
        String startLabel = newLabel();
        String endLabel = newLabel();
        String incrementLabel = newLabel();

        table = table.enterScopeIR();

        Instructions.setLoop(true);
        visitDeclaration(ctx.declaration());




        needLabel.add(new Label(startLabel, instructions.size()));
        String conditionResult = visitExpression(ctx.expression());
        instructions.add(new Instructions("ifFalse", conditionResult, null, endLabel, table));
        Instructions.setLoop(false);


        for(MyLangParser1.StatementContext blockctx: ctx.block().statement()){
            if (blockctx.simple_statements() != null){
                for(MyLangParser1.Simple_stmtContext simpleStatementsContext: blockctx.simple_statements().simple_stmt()){
                    if(simpleStatementsContext.BREAK()!=null){
                        instructions.add(new Instructions("goto", null, null, endLabel, table));
                        continue;
                    }
                    if(simpleStatementsContext.CONTINUE()!=null){
                        instructions.add(new Instructions("goto", null, null, incrementLabel, table));
                        continue;
                    }
                    visit(simpleStatementsContext);
                }
            }
            else {
                visit(blockctx);
            }
        }

        needLabel.add(new Label(incrementLabel, instructions.size()));

        Instructions.setLoop(true);
        visitAssignment(ctx.assignment());
        Instructions.setLoop(false);

        instructions.add(new Instructions("goto", null, null, startLabel, table));


        needLabel.add(new Label(endLabel, instructions.size()));
        table = table.exitScope();
        return null;
    }


    @Override
    public String visitDeclaration(MyLangParser1.DeclarationContext ctx) {

        if(ctx.expression() == null){
            return null;
        }
        if(Instructions.isIsLoop()){
            Instructions instruction = new Instructions("assign", visitExpression(ctx.expression()), null, ctx.IDENTIFIER().getText(), table);
            table.find(ctx.IDENTIFIER().getText()).setIterator(true);
            instructions.add(instruction);
            return null;
        }





        instructions.add(new Instructions("assign", visitExpression(ctx.expression()), null, ctx.IDENTIFIER().getText(), table));
        return null;
    }
    @Override
    public String visitFunction_call(MyLangParser1.Function_callContext ctx) {
        String temp;
        String result = newTemp();
        if(ctx.arguments() != null) {
            for (int i = 0; i < ctx.arguments().expression().size(); i++) {
                temp = visit(ctx.arguments().expression(i));
                instructions.add(new Instructions("param", null, null, temp, table));
            }
            instructions.add(new Instructions("call", ctx.IDENTIFIER().getText(), String.valueOf(ctx.arguments().expression().size()), result, table));
        }
        else {
            instructions.add(new Instructions("call", ctx.IDENTIFIER().getText(), String.valueOf(0), result, table));
        }

        return result;
    }

    @Override
    public String visitString_expression(MyLangParser1.String_expressionContext ctx) {
        String result = visitString_term(ctx.string_term(0));

        for (int i = 1; i < ctx.string_term().size(); i++) {
            String operator = ctx.getChild(2 * i - 1).getText();
            String rightOperand = visitString_term(ctx.string_term(i));
            String temp = newTemp();
            instructions.add(new Instructions(operator, result, rightOperand, temp, table));
            result = temp;
        }
        return result;
    }

    @Override
    public String visitString_term(MyLangParser1.String_termContext ctx) {
        if(ctx.IDENTIFIER() != null){
            return ctx.IDENTIFIER().getText();
        }
        return ctx.STRING_LITERAL().getText();
    }

    @Override
    public String visitReturn_statement(MyLangParser1.Return_statementContext ctx) {
        String t = visitExpression(ctx.expression());
        instructions.add(new Instructions("return", null, null, t, table));
        return t;
    }



    @Override
    public String visitProgram(MyLangParser1.ProgramContext ctx) {
        SymbolTable globalTable = table;
        if(ctx.function_declarations() != null){
            for (MyLangParser1.Function_declarationContext context: ctx.function_declarations().function_declaration()){
                table = globalTable.find(context.IDENTIFIER().getText()).getSymbolTable();
                visit(context);
            }
        }
        table =globalTable;
        needLabel.add(new Label("_start", instructions.size()));
        visit(ctx.statements());
        return null;
    }

    @Override
    public String visitFunction_declaration(MyLangParser1.Function_declarationContext ctx) {
        needLabel.add(new Label(ctx.IDENTIFIER().getText(), instructions.size()));

        return super.visitFunction_declaration(ctx);
    }

    public void instructionsToText(){
        for(Instructions instructions1: instructions){
            System.out.println(instructions1.toString());
        }
    }

    public void setLabel(){
        instructions.add(new Instructions("end", null, null, null, table));
        for(Label label: needLabel){
            instructions.get(label.getLine()).setLabel(label.getName());
        }

    }

    public ArrayList<Instructions> getInstructions() {
        return instructions;
    }
}
class Label{
    private String name;
    private int line;

    public Label(String name, int line) {
        this.name = name;
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }
}