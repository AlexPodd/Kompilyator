package org.example.semantic.Types;

import org.example.MyLangParser1;
import org.example.semantic.SymbolTable;
import org.example.semantic.TypeName;

public interface TypeInterface {
    boolean CheckExpression(MyLangParser1.ExpressionContext context, SymbolTable table);


    boolean CheckTypeID(String ID, SymbolTable table);
    boolean CheckTypeValue(MyLangParser1.ExpressionContext context, SymbolTable table);
    boolean SingleExpressionChecker(MyLangParser1.SingleExpressionContext context, SymbolTable table);

    void declare(MyLangParser1.DeclarationContext context, SymbolTable table);

    TypeName getMyType();
}
