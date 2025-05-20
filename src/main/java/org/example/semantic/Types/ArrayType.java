package org.example.semantic.Types;

import org.example.MyLangParser1;
import org.example.semantic.CustomVisitorForChecking.CustomVisitorExpr;
import org.example.semantic.TypeName;
import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public class ArrayType extends AbstractType{

    protected int arraySize;
    public ArrayType(TypeName myType, ArrayList<TypeName> legalTypes) {
        super(myType, legalTypes);
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }

    @Override
    public boolean CheckExpression(MyLangParser1.ExpressionContext context, SymbolTable table) {
        CustomVisitorExpr visitor = new CustomVisitorExpr();
        ArrayList<TypeName> Expression = visitor.VisitExpression(context, table);

        if(arraySize+1 != Expression.size()){
            errorHandler.ErrorSemArraySize(context, "");
            return false;
        }

        for(TypeName type: Expression){
            if(!legalTypes.contains(type)){
                errorHandler.ErrorSemTypeError(context, "Нельзя привести " +type+" к "+myType);
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean SingleExpressionChecker(MyLangParser1.SingleExpressionContext context, SymbolTable table) {
        if(context.literal() != null){
            switch (legalTypes.get(0)){
                case INTEGER -> {
                    return context.literal().INTEGER_LITERAL() != null || context.literal().NULL_LITERAL() != null;
                }
                case FLOAT -> {
                    return context.literal().INTEGER_LITERAL() != null || context.literal().NULL_LITERAL() != null || context.literal().FLOAT_LITERAL() != null;
                }
                case BOOLEAN -> {
                    return context.literal().BOOLEAN_LITERAL() != null;
                }
                default -> {
                    return context.literal().NULL_LITERAL() != null;
                }
            }
        }
        return super.SingleExpressionChecker(context, table);
    }


    @Override
    public void declare(MyLangParser1.DeclarationContext context, SymbolTable table) {
        arraySize = Integer.parseInt(context.type().array().INTEGER_LITERAL().getText());

        if(context.expression() == null){
            table.declaration(context.IDENTIFIER().getText(), new SymbolInfo(myType,legalTypes.get(0), arraySize), true);
            return;
        }
        if(!CheckExpression(context.expression(), table)){
            return;
        }

        table.declaration(context.IDENTIFIER().getText(), new SymbolInfo(myType,legalTypes.get(0),arraySize, context.expression().getText()), true);

    }
}
