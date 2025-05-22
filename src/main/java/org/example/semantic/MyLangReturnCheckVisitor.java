package org.example.semantic;

import org.antlr.v4.runtime.tree.ParseTree;
import org.example.MyLangParser1;
import org.example.MyLangParser1BaseVisitor;
import org.example.semantic.Types.TypeFactoryInterface;
import org.example.semantic.Types.TypeInterface;
import org.example.error.ErrorHandler;

import java.util.*;

public class MyLangReturnCheckVisitor extends MyLangParser1BaseVisitor<Boolean> {

    private final TypeFactoryInterface typeFactory;
    private final SymbolTable symbolTable;
    private final ErrorHandler errorHandler = ErrorHandler.getErrorHandler();

    private TypeName currentFunctionReturnType;

    public MyLangReturnCheckVisitor(TypeFactoryInterface factory, SymbolTable table) {
        this.typeFactory = factory;
        this.symbolTable = table;
    }

    public void check(MyLangParser1.Function_declarationContext ctx) {
        currentFunctionReturnType = Utilz.getUtilz().TypeToText(ctx.return_type().type());
        Boolean hasReturn = visit(ctx.block());
        if (!currentFunctionReturnType.equals(TypeName.NULL) && !Boolean.TRUE.equals(hasReturn)) {
            errorHandler.ErrorSemReturn(ctx, "Функция должна возвращать значение, но return отсутствует на всех путях.");
        }
    }

    @Override
    public Boolean visitBlock(MyLangParser1.BlockContext ctx) {
        boolean hasReturn = false;
        for (MyLangParser1.StatementContext stmt : ctx.statement()) {
            Boolean result = visit(stmt);
            if (Boolean.TRUE.equals(result)) {
                hasReturn = true;
                break;
            }
        }
        return hasReturn;
    }

    @Override
    public Boolean visitStatement(MyLangParser1.StatementContext ctx) {
        if (ctx.simple_statements() != null) {
            return visit(ctx.simple_statements());
        } else if (ctx.compound_statement() != null) {
            return visit(ctx.compound_statement());
        }
        return false;
    }

    @Override
    public Boolean visitSimple_statements(MyLangParser1.Simple_statementsContext ctx) {
        for (MyLangParser1.Simple_stmtContext stmt : ctx.simple_stmt()) {
            if (stmt.return_statement() != null) {
                return visitReturn_statement(stmt.return_statement());
            }
        }
        return false;
    }

    @Override
    public Boolean visitReturn_statement(MyLangParser1.Return_statementContext ctx) {
        if (!currentFunctionReturnType.equals(TypeName.NULL)) {
            if (ctx.expression() == null) {
                errorHandler.ErrorSemReturn(ctx, "Ожидалось возвращаемое значение.");
            } else {
                TypeInterface expectedType = typeFactory.createType(currentFunctionReturnType, new ArrayList<>(), null);
                if (!expectedType.CheckTypeValue(ctx.expression(), symbolTable)) {
                    errorHandler.ErrorSemTypeError(ctx, "Тип возвращаемого значения не соответствует ожидаемому типу: " + currentFunctionReturnType);
                }
            }
        } else if (ctx.expression() != null) {
            errorHandler.ErrorSemReturn(ctx, "Процедура не должна возвращать значение.");
        }
        return true;
    }

    @Override
    public Boolean visitConditional_statement(MyLangParser1.Conditional_statementContext ctx) {
        // Проверим return в каждой ветке
        boolean ifBranch = visit(ctx.block(0));
        boolean allElseIfReturn = true;
        for (int i = 1; i < ctx.block().size() - 1; i++) {
            if (!Boolean.TRUE.equals(visit(ctx.block(i)))) {
                allElseIfReturn = false;
            }
        }

        boolean elseBranch = ctx.ELSE() != null && visit(ctx.block(ctx.block().size() - 1));

        return ifBranch && allElseIfReturn && elseBranch;
    }

    @Override
    public Boolean visitFor(MyLangParser1.ForContext ctx) {
        visit(ctx.block());
        return false;
    }
}