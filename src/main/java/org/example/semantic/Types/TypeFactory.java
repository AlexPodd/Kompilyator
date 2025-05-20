package org.example.semantic.Types;

import org.example.semantic.TypeName;

import java.util.ArrayList;

public class TypeFactory implements TypeFactoryInterface{
    @Override
    public TypeInterface createType(TypeName typeName, ArrayList<TypeName> legalType, TypeName arrayType) {
        legalType.clear();
        switch (typeName){
            case BOOLEAN -> {
                legalType.add(TypeName.BOOLEAN);
                return new BooleanType(typeName, legalType);
            }
            case FLOAT -> {
                legalType.add(TypeName.FLOAT);
                legalType.add(TypeName.INTEGER);
                legalType.add(TypeName.NULL);
                return new FloatType(typeName, legalType);
            }
            case INTEGER -> {
                legalType.add(TypeName.INTEGER);
                legalType.add(TypeName.NULL);
                return new IntegerType(typeName, legalType);
            }
            case STRING -> {
                legalType.add(TypeName.STRING);
                legalType.add(TypeName.NULL);
                return new StringType(typeName, legalType);
            }
            case ARRAY -> {
                legalType.add(arrayType);
                return new ArrayType(typeName, legalType);
            }
            default -> {
                return null;
            }


        }
    }
}
