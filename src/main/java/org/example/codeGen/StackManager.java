package org.example.codeGen;

import org.example.semantic.SymbolInfo;

import java.util.HashMap;
import java.util.Map;

public class StackManager {
    private int stackTop; // Текущий верх стека (отрицательный!)
    private final Map<String, Integer> tempStackOffset = new HashMap<>();
    private final CodeGenerator generator;
    public StackManager(CodeGenerator generator){
        this.generator = generator;
        stackTop = 0;
    }

    public void updateStackTop(int stackTop) {
        if(this.stackTop == 0){
           generator.addCommand("    sub rsp, "+Math.abs(stackTop));
            this.stackTop = stackTop;
           return;
        }
        if(this.stackTop > stackTop){
            generator.addCommand("    sub rsp, "+Math.abs(stackTop-this.stackTop));
        }

        if(this.stackTop < stackTop){
            generator.addCommand("    add rsp, "+Math.abs(stackTop-this.stackTop));
        }


        this.stackTop = stackTop;

    }

    public String loadFromStackTemp(String var, String regName) {
        Integer offset = tempStackOffset.get(var);
        if (offset == null) {
            throw new RuntimeException("Variable not allocated on stack: " + var);
        }
        return "    mov " + regName + ", [rbp" + offsetToText(offset) + "]";
    }

    public void setStackTop(int stackTop) {
        this.stackTop = stackTop;
    }

    public String storeToStackTemp(String var, String regName) {
        Integer offset = tempStackOffset.get(var);
        if (offset == null) {
            stackTop -= 8; // выделяем 8 байт
            generator.addCommand("    sub rsp, "+8);
            offset = stackTop;
            tempStackOffset.put(var, offset);
        }
        return "    mov [rbp" + offsetToText(offset) + "], " + regName;
    }

    public String loadFromStack(SymbolInfo info, Register reg) {
        return "    mov " + reg.getName() + ", [rbp" + offsetToText(info.getOffset()) + "]";
    }

    public String storeToStack(SymbolInfo info, String regName) {
        return "    mov [rbp" + offsetToText(info.getOffset()) + "], " + regName;
    }

    private String offsetToText(int offset) {
        return offset >= 0 ? "+" + offset : String.valueOf(offset);
    }

    public void clearTemp() {
        tempStackOffset.clear();
        // Ничего с rsp делать не нужно!
        // Так как мы только логически освобождаем темпы,
        // физически стек восстанавливается через leave/ret
    }
}