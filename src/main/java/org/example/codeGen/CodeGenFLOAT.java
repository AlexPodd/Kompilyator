package org.example.codeGen;

import org.example.IR.Instructions;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public class CodeGenFLOAT extends AbstractCodeGen {


    public CodeGenFLOAT(ArrayList<String> command, SymbolTable globalTable, RegisterAllocator registerAllocator, StackManager stackManager) {
        super(command, globalTable, registerAllocator, stackManager);
    }

    @Override
    public void generateDiv(Register result, Register reg1, Register reg2) {

    }

    @Override
    public void generateMul(Register result, Register reg1, Register reg2) {

    }

    @Override
    public void generateAdd(Register result, Register reg1, Register reg2) {

    }

    @Override
    public void generateSub(Register result, Register reg1, Register reg2) {

    }

    @Override
    public void generateIfFalse(Register r1, Register r2, Instructions instruction) {

    }


    @Override
    public void generateAssign(Instructions instruction) {

    }

    @Override
    public void generateLoad(String var, Register reg) {

    }

    @Override
    public void generateReturn(Register reg, String var) {

    }


    @Override
    public void generatePrint(Register reg, String var) {

    }

    @Override
    public void generateStore(String var, Register reg) {

    }

    @Override
    public void generateModulo(Register result, Register reg1, Register reg2) {

    }
}
