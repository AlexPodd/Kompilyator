package org.example.semantic;

import org.example.IR.Instructions;

import java.util.ArrayList;
import java.util.Stack;

public class SymbolInfo {

    private int offset;

    private final TypeName type;

    private TypeName arrayType;
    private Object value;
    private TypeName TypeReturnValue;
    private int arraySize;
    private SymbolTable symbolTable;
    private boolean isGlobal = false;

    private ArrayList<TypeName> params;

    private ArrayList<String> parametrs;

    private boolean isLife = false;
    private int nextUse;

    private boolean isConst = false;

    private boolean isIterator = false;

    private int parametrsSize;


    private ArrayList<String> places = new ArrayList<>();
    //Переменные
    public SymbolInfo(TypeName type) {
        this.type = type;
    }

    //Переменные со значениями
    public SymbolInfo(TypeName type, Object value) {
        this.type = type;
        this.value = value;
    }

    //массивы
    public SymbolInfo(TypeName type,TypeName arrayType, int arraySize) {
        this.type = type;
        this.arraySize = arraySize;
        this.arrayType = arrayType;
    }

    //массивы со значением
    public SymbolInfo(TypeName type,TypeName arrayType, int arraySize, Object value) {
        this.type = type;
        this.value = value;
        this.arrayType = arrayType;
        this.arraySize = arraySize;
    }


    public boolean isIterator() {
        return isIterator;
    }

    public void setIterator(boolean iterator) {
        isIterator = iterator;
    }


    public boolean isConst() {
        return isConst;
    }

    public void setConst(boolean aConst) {
        isConst = aConst;
    }

    public TypeName getArrayType() {
        return arrayType;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    //функции
    public SymbolInfo(TypeName type, TypeName typeReturnValue, SymbolTable symbolTable, ArrayList<TypeName> params, ArrayList<String> parametrs) {
        this.type = type;
        TypeReturnValue = typeReturnValue;
        this.symbolTable = symbolTable;
        this.params = params;
        this.parametrs = parametrs;
        parametrsSize = parametrs.size();

        for(int i = 0; i<parametrs.size(); i++){
            symbolTable.find(parametrs.get(i)).setOffset((i+2)*8);
        }

    }

    public ArrayList<String> getParametrs() {
        return parametrs;
    }

    public int getParametrsSize() {
        return parametrsSize;
    }

    public TypeName getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public TypeName getTypeReturnValue() {
        return TypeReturnValue;
    }

    public int getArraySize() {
        return arraySize;
    }


    public ArrayList<TypeName> getParams() {
        return params;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isLife() {
        return isLife;
    }

    public void setLife(boolean life) {
        isLife = life;
    }

    public void addUses(int value){
        nextUse = value;
    }

    public int getNextUse() {
        return nextUse;
    }


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public void addPlace(String place){
        places.add(place);
    }

    public void changeValue(String place){
        places.clear();
        places.add(place);
    }

    public void removeFrom(String place){
        places.remove(place);
    }

    public ArrayList<String> getPlaces() {
        return places;
    }

    public boolean isOnlyInReg(String regname){
        if(places.size() == 1){
            if(places.contains(regname)){
                return true;
            }
        }
        return false;
    }

    public int getSize(){
        if(type!=TypeName.ARRAY){
            return getSize(type);
        }
        return arraySize*getSize(arrayType);
    }

    private int getSize(TypeName type){
        switch (type){
            case INTEGER -> {
                return 4;
            }
            case FLOAT -> {
                return 8;
            }
            case BOOLEAN -> {
                return 1;
            }
            case NULL -> {
                return 0;
            }
            case STRING -> {
                return 8;
            }
        }
        return 0;
    }


}
