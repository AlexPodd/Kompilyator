package org.example.semantic.Types;

import org.example.MyLangParser1;
import org.example.semantic.TypeName;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public class IntegerType extends AbstractType{
    public IntegerType(TypeName myType, ArrayList<TypeName> legalTypes) {
        super(myType, legalTypes);
    }

    @Override
    public boolean SingleExpressionChecker(MyLangParser1.SingleExpressionContext context, SymbolTable table) {
        if(context.literal() != null){
            if(context.literal().INTEGER_LITERAL() != null){
                table.declareConstant(context.getText(), TypeName.INTEGER);
                return true;
            }
            if(context.literal().NULL_LITERAL() != null){
                return true;
            }
        }



        return super.SingleExpressionChecker(context, table);
    }
}
