package org.example.semantic;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.example.error.ErrorHandler;
import org.example.MyLangParser1;
import org.example.MyLangParser1BaseListener;
import org.example.semantic.Types.TypeFactoryInterface;
import org.example.semantic.Types.TypeInterface;

import java.util.*;

public class MyLangReturnCheckListener extends MyLangParser1BaseListener {

    private TypeName funcType;
    private final ErrorHandler errorHandler = ErrorHandler.getErrorHandler();

    private final Utilz utilz = Utilz.getUtilz();

    private final LinkedList<Boolean> blocks;
    private final TypeFactoryInterface factory;
    private final SymbolTable table;
    public MyLangReturnCheckListener(TypeFactoryInterface factory, SymbolTable table){
        this.factory = factory;
        this.table = table;
        blocks = new LinkedList<>();
    }

    @Override
    public void enterBlock(MyLangParser1.BlockContext ctx) {
        table.enterScopeReturn();
        blocks.add(false);
        super.enterBlock(ctx);
    }

    @Override
    public void exitBlock(MyLangParser1.BlockContext ctx) {
        if(blocks.getLast()){
            blocks.pollLast();
        }else {
            errorHandler.ErrorSemReturn(ctx, "отсутствует блок возврата в функции");

        }
        table.exitScope();
        super.exitBlock(ctx);
    }

    @Override
    public void enterDeclaration(MyLangParser1.DeclarationContext ctx) {
        TypeInterface type = utilz.createType(ctx.type());
        type.declare(ctx, table);
        super.enterDeclaration(ctx);
    }

    @Override
    public void enterFunction_declaration(MyLangParser1.Function_declarationContext ctx) {
        funcType = utilz.TypeToText(ctx.return_type().type());
    }

    @Override
    public void exitFunction_declaration(MyLangParser1.Function_declarationContext ctx) {
        if (!funcType.equals(TypeName.NULL) && !blocks.isEmpty()) {
            errorHandler.ErrorSemReturn(ctx, "Отстуствует возвращаемое значение функции");

        }
    }

    @Override
    public void enterReturn_statement(MyLangParser1.Return_statementContext ctx) {
        TypeInterface type = null;
        type = factory.createType(funcType, new ArrayList<>(), null);
            if(!type.CheckTypeValue(ctx.expression(), table)){
                errorHandler.ErrorSemTypeError(ctx, "Неверный тип возвращаемого значения");
            }
         if(blocks.getLast()){
             errorHandler.ErrorSemReturn(ctx, "Лишний возврат из функции ");
         }
         blocks.set(blocks.size()-1, true);
    }

    public void checkReturns(ParseTree tree) {
        ParseTreeWalker.DEFAULT.walk(this, tree);
    }



}
