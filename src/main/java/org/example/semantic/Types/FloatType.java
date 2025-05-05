package org.example.semantic.Types;

import org.example.MyLangParser1;
import org.example.semantic.TypeName;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public class FloatType extends AbstractType{


    public FloatType(TypeName myType, ArrayList<TypeName> legalTypes) {
        super(myType, legalTypes);
    }


    @Override
    public boolean CheckTypeID(String ID, SymbolTable table) {
        TypeName type = table.findReturnType(ID);
        if(type == null){
            return false;
        }

        return (type.equals(myType) || type.equals(TypeName.INTEGER));
    }
    @Override
    public boolean SingleExpressionChecker(MyLangParser1.SingleExpressionContext context, SymbolTable table) {
        if(context.literal() != null){
            return context.literal().INTEGER_LITERAL() != null || context.literal().NULL_LITERAL() != null || context.literal().FLOAT_LITERAL() != null;
        }


        return super.SingleExpressionChecker(context, table);
    }
}
