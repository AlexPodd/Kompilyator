package org.example.semantic.CustomVisitorForChecking;

import org.example.MyLangParser1;
import org.example.MyLangParser1BaseVisitor;
import org.example.error.ErrorHandler;
import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;
import org.example.semantic.TypeName;
import org.example.semantic.Utilz;

import java.util.ArrayList;

public class CustomVisitorExpr extends MyLangParser1BaseVisitor<Void> implements CustomVisitorForCheckingInterface{

    private ArrayList<TypeName> Term;
    private ArrayList<TypeName> Term_coppy;
    private SymbolTable table;
    private final Utilz utilz = Utilz.getUtilz();
    private final ErrorHandler errorHandler = ErrorHandler.getErrorHandler();

    private SymbolTable global;

    @Override
    public ArrayList<TypeName> VisitExpression(MyLangParser1.ExpressionContext context, SymbolTable table) {
        Term = new ArrayList<>();
        this.table = table;
        Term_coppy = new ArrayList<>();
        visit(context);
        return Term;
    }


    @Override
    public Void visitFactor(MyLangParser1.FactorContext ctx) {
        if (ctx.array_index() != null){
            SymbolInfo info = table.find(ctx.IDENTIFIER().getText());
            if(info == null || info.getArrayType() == null){
                errorHandler.ErrorSemIDNotFound(ctx, "Переменная "+ctx.IDENTIFIER().getText()+" не массив");
                return null;
            }
            Term.add(table.find(ctx.IDENTIFIER().getText()).getArrayType());
            return null;
        }

        if(ctx.IDENTIFIER() != null){
            Term.add(table.findReturnType(ctx.IDENTIFIER().getText()));
            return null;
        }

        return super.visitFactor(ctx);
    }


    @Override
    public Void visitFactor_logical(MyLangParser1.Factor_logicalContext ctx) {
        if (ctx.array_index() != null){
            SymbolInfo info = table.find(ctx.IDENTIFIER().getText());
            if(info == null || info.getArrayType() == null){
                errorHandler.ErrorSemIDNotFound(ctx, "Переменная "+ctx.IDENTIFIER().getText()+" не массив");
                return null;
            }
            Term.add(table.find(ctx.IDENTIFIER().getText()).getArrayType());
            return null;
        }

        if(ctx.BOOLEAN_LITERAL() != null){
            Term.add(TypeName.BOOLEAN);
            return null;
        }

        if(!ctx.arithmetic_expression().isEmpty()){
            Term_coppy.addAll(Term);
            if (!checkArithmeticIsFloat(ctx.arithmetic_expression(0)) || !checkArithmeticIsFloat(ctx.arithmetic_expression(1))) {
                Term_coppy.add(TypeName.ERROR);
                return null;
            }
            else {
                Term_coppy.add(TypeName.BOOLEAN);
            }

            Term.clear();
            Term.addAll(Term_coppy);
            Term_coppy.clear();
            return null;
        }

        if(ctx.IDENTIFIER() != null){
            Term.add(table.findReturnType(ctx.IDENTIFIER().getText()));
            return null;
        }

        return super.visitFactor_logical(ctx);
    }


    @Override
    public Void visitString_term(MyLangParser1.String_termContext ctx) {
        if(ctx.STRING_LITERAL() != null){
            table.declareConstant(ctx.STRING_LITERAL().getText(), TypeName.STRING);
            table.getGlobal().declateConstToData(ctx.STRING_LITERAL().getText(), TypeName.STRING);
            Term.add(TypeName.STRING);
            return null;
        }



        if (ctx.array_index() != null){
            SymbolInfo info = table.find(ctx.IDENTIFIER().getText());
            if(info == null || info.getArrayType() == null){
                errorHandler.ErrorSemIDNotFound(ctx, "Переменная "+ctx.IDENTIFIER().getText()+" не массив");
                return null;
            }
            Term.add(table.find(ctx.IDENTIFIER().getText()).getArrayType());
            return null;
        }

        if(ctx.IDENTIFIER() != null){
            Term.add(table.findReturnType(ctx.IDENTIFIER().getText()));
            return null;
        }

        return super.visitString_term(ctx);
    }


    @Override
    public Void visitLiteral(MyLangParser1.LiteralContext ctx) {
        if(ctx.INTEGER_LITERAL() != null){
            if(ctx.SUBTRACT()!= null){
                table.declareConstant("-"+ctx.INTEGER_LITERAL().getText(), TypeName.INTEGER);
                table.getGlobal().declateConstToData("-"+ctx.INTEGER_LITERAL().getText(), TypeName.FLOAT);
            }
            else {
                table.declareConstant(ctx.INTEGER_LITERAL().getText(), TypeName.INTEGER);
                table.getGlobal().declateConstToData(ctx.INTEGER_LITERAL().getText(), TypeName.FLOAT);
            }

            Term.add(TypeName.INTEGER);
        }
        if(ctx.NULL_LITERAL() != null){
            table.declareConstant(ctx.NULL_LITERAL().getText(), TypeName.NULL);
            Term.add(TypeName.NULL);
        }
        if(ctx.FLOAT_LITERAL() != null){
            if(ctx.SUBTRACT()!= null){
                table.declareConstant("-"+ctx.FLOAT_LITERAL().getText(), TypeName.FLOAT);
                table.getGlobal().declateConstToData("-"+ctx.FLOAT_LITERAL().getText(), TypeName.FLOAT);
            }
            else {
                table.declareConstant(ctx.FLOAT_LITERAL().getText(), TypeName.FLOAT);
                table.getGlobal().declateConstToData(ctx.FLOAT_LITERAL().getText(), TypeName.FLOAT);
            }

            Term.add(TypeName.FLOAT);
        }
    
        if(ctx.BOOLEAN_LITERAL() != null){
            table.declareConstant(ctx.BOOLEAN_LITERAL().getText(), TypeName.BOOLEAN);
            Term.add(TypeName.BOOLEAN);
        }
        return null;
    }


    @Override
    public Void visitFunction_call(MyLangParser1.Function_callContext ctx) {
        SymbolInfo funcInfo = table.find(ctx.IDENTIFIER().getText());

        if (funcInfo == null) {
            if(!table.getName().equals(ctx.IDENTIFIER().getText())){
                errorHandler.ErrorSemIDNotFound(ctx, "Функция " + ctx.IDENTIFIER().getText() + " не объявлена");
                return null;
            }
            funcInfo = table.getGlobal().find(ctx.IDENTIFIER().getText());
        }

        if (ctx.arguments() == null && funcInfo.getParams().isEmpty()) {
            Term.add(funcInfo.getTypeReturnValue());
            return null;
        }

        if (ctx.arguments() == null && !funcInfo.getParams().isEmpty()) {
            errorHandler.ErrorSemParamIsNedostatochno(ctx, "Ожидались аргументы, но их нет");
            return null;
        }

        if (ctx.arguments() != null && funcInfo.getParams().isEmpty()) {
            errorHandler.ErrorSemParamIsLishniy(ctx, "Функция не принимает аргументы, но они переданы");
            return null;
        }

        if (ctx.arguments().expression().size() != funcInfo.getParams().size()) {
            errorHandler.ErrorSemParamIsNedostatochno(ctx, "Ожидалось " + funcInfo.getParams().size() +
                    " аргументов, но получено " + ctx.arguments().expression().size());
            return null;
        }

        for (int i = 0; i < ctx.arguments().expression().size(); i++) {
            TypeName expectedType = funcInfo.getParams().get(i);
            MyLangParser1.ExpressionContext argument = ctx.arguments().expression(i);
            utilz.checkTypes(expectedType, null, argument, table);
        }

        Term.add(funcInfo.getTypeReturnValue());
        return null;
    }



    private boolean checkArithmeticIsFloat(MyLangParser1.Arithmetic_expressionContext context) {
        Term.clear();
        visit(context);

        for (TypeName res : Term) {
            if (res == null || !(res.equals(TypeName.FLOAT)) && !(res.equals(TypeName.INTEGER))) {
                return false;
            }
        }
        return true;
    }



}
