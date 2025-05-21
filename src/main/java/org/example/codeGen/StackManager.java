package org.example.codeGen;

import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;

import java.util.HashMap;
import java.util.Map;

public class StackManager {
    private int stackTop;
    private Map<String, Integer> tempStackOffset = new HashMap<>();
    private Map<String, Integer> localStackOffset = new HashMap<>();
    private Map<String, Integer> paramStackOffset = new HashMap<>();

    private final CodeGenerator generator;
    public StackManager(CodeGenerator generator){
        this.generator = generator;
    }

    public void initFunc(int sizeOfLocalVariable){
        stackTop = sizeOfLocalVariable;
        generator.addCommand("    sub rsp,"+Math.abs(stackTop));
        tempStackOffset = new HashMap<>();
        localStackOffset = new HashMap<>();
        paramStackOffset = new HashMap<>();
    }
    public void newBlockLocalVariable(int sizeOfLocalVariable){
        stackTop+=sizeOfLocalVariable;
        generator.addCommand("    sub rsp,"+Math.abs(sizeOfLocalVariable));
    }

    public void addNewParametr(String name, int offset){
        paramStackOffset.put(name, offset);
    }
    public int getParametrOffset(String name){
        return paramStackOffset.get(name);
    }

    private int addTempToStack(String var){
        stackTop -= 8;
        generator.addCommand("    sub rsp, "+8);
        tempStackOffset.put(var, stackTop);
        return stackTop;
    }
    private void addLocalToStack(String var, SymbolInfo info){
        localStackOffset.put(var, info.getOffset());
    }

    public int getLocalOffset(String name){
        return localStackOffset.get(name);
    }



    public String loadFromStackTemp(String var, String regName) {
        Integer offset = tempStackOffset.get(var);
        return "    mov " + regName + ", [rbp" + offsetToText(offset) + "]";
    }

    public String storeToStackTemp(String var, String regName) {
        Integer offset = tempStackOffset.get(var);
        if (offset == null) {
            offset = addTempToStack(var);
        }
        return "    mov [rbp" + offsetToText(offset) + "], " + regName;
    }


    public String loadLocalFromStack(String name,SymbolInfo info, Register reg) {
        localStackOffset.computeIfAbsent(name, k -> info.getOffset());
        return "    mov " + reg.getNameLoad(info.getSize()) + ", "+sizeOfReg(info.getSize())+ " [rbp" + localStackOffset.get(name) + "]";
    }
    public String storeLocalToStack(String name,SymbolInfo info, String regName) {
        localStackOffset.computeIfAbsent(name, k -> info.getOffset());
        return "    mov "+sizeOfReg(info.getSize())+"[rbp" + localStackOffset.get(name) + "], " + regName;
    }

    private String sizeOfReg(int size){
        switch (size){
            case 1:
                return "byte";
            case 2:
                return "word";
            case 4:
                return "dword";
            default:
                return "qword";
        }
    }


    private String offsetToText(int offset) {
        return offset >= 0 ? "+" + offset : String.valueOf(offset);
    }

}