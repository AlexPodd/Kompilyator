package org.example.semantic.Types;

import org.example.error.ErrorHandler;
import org.example.MyLangParser1;
import org.example.semantic.CustomVisitorForChecking.CustomVisitorExpr;
import org.example.semantic.TypeName;
import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public abstract class AbstractType implements TypeInterface {

    protected TypeName myType;
    protected ArrayList<TypeName> legalTypes;


    protected static final ErrorHandler errorHandler = ErrorHandler.getErrorHandler();


    public AbstractType(TypeName myType, ArrayList<TypeName> legalTypes){
        this.myType = myType;
        this.legalTypes = legalTypes;
    }
    @Override
    public boolean CheckExpression(MyLangParser1.ExpressionContext context, SymbolTable table) {
        CustomVisitorExpr visitor = new CustomVisitorExpr();
        ArrayList<TypeName> Expression = visitor.VisitExpression(context, table);

        for(TypeName type: Expression){
            if(!legalTypes.contains(type)){
                errorHandler.ErrorSemTypeError(context, "Нельзя привести " +type+" к "+myType);
                return false;
            }
        }

        return true;
    }



    @Override
    public boolean CheckTypeID(String ID, SymbolTable table) {
        TypeName type = table.findReturnType(ID);
        if(type == null){
            return false;
        }
        return type.equals(myType);
    }


    //Каждый класс будет реализовать свою логику с литералами
    @Override
    public boolean CheckTypeValue(MyLangParser1.ExpressionContext context, SymbolTable table) {
        if(context.singleExpression() != null){
            if(!SingleExpressionChecker(context.singleExpression(), table)){
                errorHandler.ErrorSemTypeError(context, "Нельзя преобразовать "+myType);
                return false;
            }
            else {
             return true;
            }
        }
        return CheckExpression(context, table);
    }

    @Override
    public boolean SingleExpressionChecker(MyLangParser1.SingleExpressionContext context, SymbolTable table) {
        if(context.IDENTIFIER() != null){
            if(!CheckTypeID(context.IDENTIFIER().getText(), table)){
                errorHandler.ErrorSemTypeError(context, "");
                return false;
            }
            else {
                return true;
            }
        }
        errorHandler.ErrorSemIDNotFound(context, context.IDENTIFIER().getText());
        return false;
    }

    @Override
    public void declare(MyLangParser1.DeclarationContext context, SymbolTable table) {
        if(context.expression() == null){
            table.declaration(context.IDENTIFIER().getText(), new SymbolInfo(myType));
            return;
        }
        if(!CheckTypeValue(context.expression(), table)){
            return;
        }
        table.declaration(context.IDENTIFIER().getText(), new SymbolInfo(myType));

    }
    public TypeName getMyType() {
        return myType;
    }


}
