package org.example.semantic.Types;

import org.example.MyLangParser1;
import org.example.semantic.TypeName;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public class StringType extends AbstractType{
    public StringType(TypeName myType, ArrayList<TypeName> legalTypes) {
        super(myType, legalTypes);
    }

    @Override
    public boolean SingleExpressionChecker(MyLangParser1.SingleExpressionContext context, SymbolTable table) {
        if(context.literal() != null){
            return context.literal().NULL_LITERAL() != null;
        }


        return super.SingleExpressionChecker(context, table);
    }
}
