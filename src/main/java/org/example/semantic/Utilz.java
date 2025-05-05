package org.example.semantic;

import org.example.MyLangParser1;
import org.example.error.ErrorHandler;
import org.example.semantic.Types.TypeFactory;
import org.example.semantic.Types.TypeFactoryInterface;
import org.example.semantic.Types.TypeInterface;

import java.util.ArrayList;

public class Utilz {
    private static Utilz utilz;

    private static ErrorHandler errorHandler = ErrorHandler.getErrorHandler();
    private final TypeFactoryInterface typeFactory;

    public static synchronized Utilz getUtilz(){
        if(utilz == null){
            utilz = new Utilz();
        }
        return utilz;
    }

    private Utilz(){
        typeFactory = new TypeFactory();
    }

    public TypeName TypeToText(MyLangParser1.TypeContext context){
        if(context.array() != null){
            return TypeName.ARRAY;
        }
        switch (context.getText()){
            case ("целое"):
                return TypeName.INTEGER;
            case ("вещественный"):
                return TypeName.FLOAT;
            case ("строка"):
                return TypeName.STRING;
            case ("логическая"):
                return TypeName.BOOLEAN;
            case ("ничего"):
                return TypeName.NULL;
        }
        return TypeName.ERROR;
    }


    public TypeInterface createType(MyLangParser1.TypeContext ctx){
        TypeInterface type = null;
        ArrayList<TypeName> legalType = new ArrayList<>();
        if(ctx.array() != null){
            if(ctx.array().INTEGER() != null){
                type = typeFactory.createType(TypeToText(ctx), legalType, TypeName.INTEGER);
            }
            if(ctx.array().BOOLEAN() != null){
                type = typeFactory.createType(TypeToText(ctx), legalType, TypeName.BOOLEAN);
            }
            if(ctx.array().FLOAT() != null){
                type = typeFactory.createType(TypeToText(ctx), legalType, TypeName.FLOAT);
            }
            if(ctx.array().STRING() != null){
                type = typeFactory.createType(TypeToText(ctx), legalType, TypeName.STRING);
            }
        } else {
            type = typeFactory.createType(TypeToText(ctx), legalType, null);
        }
        return type;
    }


    public TypeInterface createType(TypeName typeName, TypeName typeArray){
        TypeInterface type = null;
        ArrayList<TypeName> legalType = new ArrayList<>();
        switch (typeName){
            case ARRAY -> {
                type = typeFactory.createType(typeName, legalType, typeArray);
            }
            default -> {
                type = typeFactory.createType(typeName, legalType, null);
            }
        }
        return type;
    }


    public void checkTypes(TypeName myType, TypeName arrayType, MyLangParser1.ExpressionContext context, SymbolTable table){
        TypeInterface type = createType(myType, arrayType);
        type.CheckTypeValue(context, table);
    }
}
