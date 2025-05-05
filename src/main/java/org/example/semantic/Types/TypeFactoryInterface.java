package org.example.semantic.Types;

import org.example.semantic.TypeName;

import java.util.ArrayList;

public interface TypeFactoryInterface {
    TypeInterface createType(TypeName typeName, ArrayList<TypeName> legalType, TypeName arrayType);
}
