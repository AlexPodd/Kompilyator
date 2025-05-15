package org.example.codeGen;

import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public class Register {
    private final String name;
    private final ArrayList<String> contains;

    private boolean hasValue;

    private boolean isUsed = false;

    private String x32, x16, x8;

    public Register(String name, String x32, String x16, String x8){
        this.name = name;
        contains = new ArrayList<>();

        this.x32 = x32;
        this.x16 = x16;
        this.x8 = x8;
    }


    public boolean isHasValue() {
        return hasValue;
    }

    public void setHasValue(boolean hasValue) {
        this.hasValue = hasValue;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void addVar(String var){
        if(contains.contains(var)){
            return;
        }
        contains.add(var);
    }

    public void addVarVirtual(String var){
        if(contains.contains(var)){
            return;
        }
        contains.add(var);
    }

    public void deleteFromReg(String var){
        contains.remove(var);
    }
    public boolean contain(String var){
        return contains.contains(var);
    }
    public boolean isEmpty(){
        return contains.isEmpty();
    }


    public ArrayList<String> getContains() {
        return contains;
    }

    public String getName() {
        return name;
    }

    public String getNameLoad(int size){
        return switch (size) {
            case 1 -> x8;
            case 2 -> x16;
            case 4 -> x32;
            default -> name;
        };
    }

    public void clear(){
        contains.clear();
        hasValue = false;
    }


}
