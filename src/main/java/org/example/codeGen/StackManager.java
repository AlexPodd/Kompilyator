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

    public void initFunc(int sizeOfLocalVariable, SymbolTable table){
        stackTop = sizeOfLocalVariable;
        generator.addCommand("    sub rsp,"+Math.abs(stackTop));
        tempStackOffset = new HashMap<>();
        localStackOffset = new HashMap<>();
        paramStackOffset = new HashMap<>();
        stackInit(table);
    }
    public void newBlockLocalVariable(int sizeOfLocalVariable, SymbolTable table){
        if (stackTop < sizeOfLocalVariable) {
            generator.addCommand("    sub rsp, " + (sizeOfLocalVariable - stackTop) + " ; резерв под локальные");
        }

        stackTop=sizeOfLocalVariable;
        stackInit(table);
    }
    private void stackInit(SymbolTable table){
        if(table != null){
            for (Map.Entry<String, SymbolInfo> entry : table.getTable().entrySet()) {
                String key = entry.getKey();
                SymbolInfo value = entry.getValue();
                if (value.isConst()){
                    continue;
                }
                if(localStackOffset.get(key) != null){
                    continue;
                }
                localStackOffset.put(key, value.getOffset());
                
                
              //  generator.addCommand("LOCAL "+ key+" "+value.getOffset());
                switch (value.getType()){
                    case INTEGER -> {
                        Register register = generator.findInRegParamINT(key);
                        if(register != null){
                            generator.addCommand("    mov "+sizeOfReg(value.getSize())+"[rbp" + offsetToText(value.getOffset()) + "], " + register.getNameLoad(4));
                        }else {
                            if(value.getValue() != null){
                                    generator.addCommand("    mov "+sizeOfReg(value.getSize())+"[rbp" + offsetToText(value.getOffset()) + "], " + value.getValue());
                            }else{
                                    generator.addCommand("    mov "+sizeOfReg(value.getSize())+"[rbp" + offsetToText(value.getOffset()) + "], " + "0");
                            }
                        }
                    }
                    case FLOAT -> {
                        Register register = generator.findInRegParamFLOAT(key);
                        Register empty =  generator.getRegisterAllocatorXMM().getEmptyXMMReg();
                        if(register != null){
                            generator.addCommand("    movsd "+"[rbp" + offsetToText(value.getOffset()) + "], " + register.getName());
                        }else {
                            if(value.getValue() != null){
                                                            generator.addCommand("    movsd "+empty.getName()+", ["+valueToText(value.getValue())+"]");
                            generator.addCommand("    movsd "+"[rbp" + offsetToText(value.getOffset()) + "], " + empty.getName());
                            }
                            else{
                                    generator.addCommand("    pxor "+empty.getName()+", "+empty.getName());
                            generator.addCommand("    movsd "+"[rbp" + offsetToText(value.getOffset()) + "], "+empty.getName());
                            }
                        }
                    }
                }


            }
        }
    }
    private String valueToText(Object val) {
        double num = Double.parseDouble((String) val);
        if (num < 0) {
            return "_minus" + Math.abs(num);
        } else {
            return "_"+num;
        }
    }


    public void addNewParametr(String name, int offset){
        paramStackOffset.put(name, offset);
    }
    public int getParametrOffset(String name){
        return paramStackOffset.get(name);
    }

    public int alignStackCall() {
        int usedBytes = Math.abs(stackTop);
        int totalAfterCall = usedBytes + 8;
        int padding = (16 - (totalAfterCall % 16)) % 16;
        return padding;
    }
    public int addTempToStack(String var){
        if(tempStackOffset.get(var) != null){
            return tempStackOffset.get(var);
        }
        stackTop -= 8;
        generator.addCommand("    sub rsp, "+8);
        generator.addCommand("    mov "+sizeOfReg(8)+"[rbp" + stackTop + "], " + "0");
        //     generator.addCommand("TEMP "+ var+" "+stackTop);
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
        if (offset == null) {
            offset = addTempToStack(var);
        }
        if (regName.startsWith("xmm")) {
            return "    movsd " + regName + ", [rbp" + offsetToText(offset) + "]";
        } else {
            return "    mov " + regName + ", [rbp" + offsetToText(offset) + "]";
        }
    }
    
    public String storeToStackTemp(String var, String regName) {
        Integer offset = tempStackOffset.get(var);
        if (offset == null) {
            offset = addTempToStack(var);
        }
        if (regName.startsWith("xmm")) {
            return "    movsd [rbp" + offsetToText(offset) + "], " + regName;
        } else {
            return "    mov [rbp" + offsetToText(offset) + "], " + regName;
        }
    }

    


    public String loadLocalFromStack(String name,SymbolInfo info, Register reg) {
        localStackOffset.computeIfAbsent(name, k -> info.getOffset());
        return "    mov " + reg.getNameLoad(info.getSize()) + ", "+sizeOfReg(info.getSize())+ " [rbp" + offsetToText(localStackOffset.get(name)) + "]";
    }
    public String storeLocalToStack(String name,SymbolInfo info, String regName) {
        localStackOffset.computeIfAbsent(name, k -> info.getOffset());
        return "    mov "+sizeOfReg(info.getSize())+"[rbp" + offsetToText(localStackOffset.get(name)) + "], " + regName;
    }

    public String loadLocalXMMFromStack(String name, SymbolInfo info, Register reg) {
        localStackOffset.computeIfAbsent(name, k -> info.getOffset());
        return "    movsd " + reg.getName() + ", [rbp" + offsetToText(localStackOffset.get(name)) + "]";
    }

    public String storeLocalXMMStack(String name, SymbolInfo info, String regName) {
        localStackOffset.computeIfAbsent(name, k -> info.getOffset());
        return "    movsd [rbp" + offsetToText(localStackOffset.get(name)) + "], " + regName;
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