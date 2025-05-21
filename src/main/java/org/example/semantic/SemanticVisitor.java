package org.example.semantic;


import org.example.error.ErrorHandler;
import org.example.MyLangParser1;
import org.example.MyLangParser1BaseVisitor;
import org.example.error.SemanticError;
import org.example.semantic.CustomVisitorForChecking.CustomVisitorExpr;
import org.example.semantic.Types.TypeFactoryInterface;
import org.example.semantic.Types.TypeInterface;

import java.util.ArrayList;
import java.util.List;

public class SemanticVisitor extends MyLangParser1BaseVisitor {

    private static SymbolTable symbolTable;
    private final TypeFactoryInterface typeFactory;
    private static final ErrorHandler errorHandler = ErrorHandler.getErrorHandler();
    private static final Utilz utilz = Utilz.getUtilz();
    private boolean inLoop = false;

    public SemanticVisitor(TypeFactoryInterface typeFactory, SymbolTable table) {
        this.typeFactory = typeFactory;
        symbolTable = table;
    }

    @Override
    public Object visitDeclaration(MyLangParser1.DeclarationContext ctx) {
        if (symbolTable.find(ctx.IDENTIFIER().getText()) != null){
            errorHandler.ErrorSemIDIsUsed(ctx, ctx.IDENTIFIER().getText());
            return null;
        }
        TypeInterface type = utilz.createType(ctx.type());
        type.declare(ctx, symbolTable);
        return null;
    }

    @Override
    public Object visitReturn_statement(MyLangParser1.Return_statementContext ctx) {
        CustomVisitorExpr visitor = new CustomVisitorExpr();
        ArrayList<TypeName> Expression = visitor.VisitExpression(ctx.expression(), symbolTable);
        return super.visitReturn_statement(ctx);
    }

    @Override
    public Object visitAssignment(MyLangParser1.AssignmentContext ctx) {
        if (ctx.IDENTIFIER() == null){
            return null;
        }

        SymbolInfo info = symbolTable.find(ctx.IDENTIFIER().getText());
        if(info == null){
            errorHandler.ErrorSemIDNotFound(ctx, ctx.IDENTIFIER().getText());
            return null;
        }
        TypeInterface type;
        if(info.getType().equals(TypeName.ARRAY)){
            type = utilz.createType(info.getArrayType(), info.getArrayType());
        }else {
            type = utilz.createType(info.getType(), info.getArrayType());
        }

        if(ctx.array_index() != null){
            if(!info.getType().equals(TypeName.ARRAY)){
                errorHandler.ErrorSemTypeError(ctx, "Переменная не является массивом "+ctx.IDENTIFIER().getText());
                return null;
            }
            if(CheckArrayType(ctx.array_index(), info)){
                   type.CheckTypeValue(ctx.expression(), symbolTable);
                    return null;
            }
            else {
                return null;
            }
        }

        if(info.getType().equals(TypeName.ARRAY)){
            errorHandler.ErrorSemTypeError(ctx, "");
            return null;
        }

        if(type.CheckTypeValue(ctx.expression(), symbolTable)){

            return null;
        }
        return super.visitAssignment(ctx);
    }

    @Override
    public Object visitFunction_declaration(MyLangParser1.Function_declarationContext ctx) {
        if( symbolTable.find(ctx.IDENTIFIER().getText()) != null){
            errorHandler.ErrorSemIDIsUsed(ctx, "Имя функции занято");
            return super.visitFunction_declaration(ctx);
        }
        SymbolTable functionTable = new SymbolTable(null, ctx.IDENTIFIER().getText(), 0 , false);
        ArrayList<TypeName> params= new ArrayList<>();
        ArrayList<String> parameters= new ArrayList<>();
        if (ctx.parameters()!=null) {
            for (int i = 0; i < ctx.parameters().parameter().size(); i++) {
                if (functionTable.find(ctx.parameters().parameter(i).IDENTIFIER().getText()) != null) {
                    errorHandler.ErrorSemIDIsUsed(ctx, "Повторное объявление переменной в сигнатуре функции");
                    return super.visitFunction_declaration(ctx);
                }

                params.add(utilz.TypeToText(ctx.parameters().parameter(i).type()));
                parameters.add(ctx.parameters().parameter(i).IDENTIFIER().getText());
                functionTable.declaration(ctx.parameters().parameter(i).IDENTIFIER().getText(), new SymbolInfo(utilz.TypeToText(ctx.parameters().parameter(i).type())), true);
            }
        }
        symbolTable.declaration(ctx.IDENTIFIER().getText(), new SymbolInfo(TypeName.FUNCTION,utilz.TypeToText(ctx.return_type().type()), functionTable, params, parameters), false);
        SymbolTable globalTable = symbolTable;
        symbolTable = functionTable;
        visit(ctx.block());
        MyLangReturnCheckListener listener = new MyLangReturnCheckListener(typeFactory, symbolTable);
        listener.checkReturns(ctx);
        symbolTable = globalTable;
        return null;
    }


    @Override
    public Object visitFor(MyLangParser1.ForContext ctx) {
        TypeInterface type = null;
        ArrayList<TypeName> legalType = new ArrayList<>();
        if (symbolTable.find(ctx.declaration().IDENTIFIER().getText()) != null){
            errorHandler.ErrorSemIDIsUsed(ctx, ctx.declaration().IDENTIFIER().getText());
            return null;
        }


        type = utilz.createType(ctx.declaration().type());

        if (!type.getMyType().equals(TypeName.INTEGER)){
            errorHandler.ErrorSemIterator(ctx, ctx.declaration().IDENTIFIER().getText());
        }
        symbolTable = symbolTable.enterScope();
        if(!type.CheckTypeValue(ctx.declaration().expression(), symbolTable)){
            errorHandler.ErrorSemIterator(ctx, ctx.declaration().IDENTIFIER().getText());
            return null;
        }

        symbolTable.declaration(ctx.declaration().IDENTIFIER().getText(), new SymbolInfo(TypeName.INTEGER, ctx.declaration().expression().getText()), true);
        type = typeFactory.createType(TypeName.BOOLEAN, legalType, null);
        //проверка что логическое выражение

        if(!type.CheckTypeValue(ctx.expression(), symbolTable)){
            symbolTable = symbolTable.exitScope();
            errorHandler.ErrorSemTypeError(ctx, "Не логическое выражение в теле цикла");
            return null;
        }




        type = typeFactory.createType(TypeName.INTEGER, legalType, null);

        if(!type.CheckTypeValue(ctx.assignment().expression(), symbolTable)){
            symbolTable = symbolTable.exitScope();
            errorHandler.ErrorSemTypeError(ctx, "Присваивание допускает только целые числа");
            return null;
        }
        inLoop = true;
        visit(ctx.block());
        inLoop = false;
        symbolTable = symbolTable.exitScope();
        return null;
    }


    @Override
    public Object visitConditional_statement(MyLangParser1.Conditional_statementContext ctx) {
        TypeInterface type = null;
        ArrayList<TypeName> legalType = new ArrayList<>();
        type = typeFactory.createType(TypeName.BOOLEAN, legalType, null);
        for (int i = 0; i<ctx.expression().size(); i++){
            if(!type.CheckTypeValue(ctx.expression(i), symbolTable)){
                errorHandler.ErrorSemTypeError(ctx, "Не логический тип в теле условия");
                return null;
            }
        }
        for(int i = 0; i<ctx.block().size(); i++){
            symbolTable = symbolTable.enterScope();
            visitBlock(ctx.block(i));
            symbolTable = symbolTable.exitScope();
        }
        return null;
    }

    @Override
    public Object visitSimple_stmt(MyLangParser1.Simple_stmtContext ctx) {
        if(ctx.CONTINUE() != null || ctx.BREAK()!=null){
            if(!inLoop){
                errorHandler.ErrorSemBreakOrContinueOutLoop(ctx, "");
            }
        }

        return super.visitSimple_stmt(ctx);
    }


    private boolean CheckArrayType(MyLangParser1.Array_indexContext context, SymbolInfo info){
        if(context == null){
            errorHandler.ErrorSemTypeError(context, "");
            return false;
        }

        if(info.getArraySize()-Integer.parseInt(context.INTEGER_LITERAL().getText())<0){
            errorHandler.ErrorSemIndexOutOf(context, "");
            return false;
        }
        if(context.INTEGER_LITERAL() != null){
            return true;
        }



        if(symbolTable.findReturnType(context.IDENTIFIER().getText()) != TypeName.INTEGER){
            errorHandler.ErrorSemIDNotFound(context, context.IDENTIFIER().getText());
            return false;
        }
        return true;
    }
    public ArrayList<SemanticError> getErrors(){
        return errorHandler.getErrors();
    }
    public void clearError(){
        symbolTable = new SymbolTable(null, "global", 0, true);
        errorHandler.clearError();
    }


    public List<String> getAllDeclarated() {
        return symbolTable.allDeclarated();
    }
}
