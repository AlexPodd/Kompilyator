package org.example.semantic.CustomVisitorForChecking;

import org.example.MyLangParser1;
import org.example.semantic.TypeName;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public interface CustomVisitorForCheckingInterface {
    ArrayList<TypeName> VisitExpression(MyLangParser1.ExpressionContext context, SymbolTable table);

}
