package org.example.codeGen;

import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public abstract class AbstractCodeGen implements CodeGen{
    protected final ArrayList<String> command;
    protected SymbolTable table;

    protected final SymbolTable globalTable;
    protected final RegisterAllocator registerAllocator;

    protected final StackManager stackManager;

    public AbstractCodeGen(ArrayList<String> command, SymbolTable globalTable, RegisterAllocator registerAllocator, StackManager stackManager) {
        this.command = command;
        this.globalTable = globalTable;
        this.registerAllocator = registerAllocator;
        this.stackManager = stackManager;
    }

    public void setTable(SymbolTable table) {
        this.table = table;
    }

}
