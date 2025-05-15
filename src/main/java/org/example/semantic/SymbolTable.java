package org.example.semantic;

import java.util.*;

public class SymbolTable {

    private int offset;
    private final HashMap<String, SymbolInfo> table = new HashMap<>();
    private final SymbolTable parent;

    private static int tempTableCount = 0;
    private static int tempTableCountIR = 0;

    private final String name;
    private final boolean isGlobal;

private static ArrayList<SymbolTable> tables = new ArrayList<>();


    public SymbolTable(SymbolTable parent, String name, int offset, boolean isGlobal) {
        this.parent = parent;
        this.name = name;
        this.offset = offset;
        this.isGlobal = isGlobal;
        tables.add(this);
        boolConst();
    }
    private void boolConst(){
        SymbolInfo infoTrue = new SymbolInfo(TypeName.BOOLEAN,1);
        SymbolInfo infoFalse = new SymbolInfo(TypeName.BOOLEAN,0);
        infoTrue.setConst(true);
        infoFalse.setConst(true);

        declaration("правда", infoTrue);
        declaration("ложь", infoFalse);
    }



    public void declaration(String ID, SymbolInfo symbolInfo) {
       /* if(symbolInfo.getType().equals(TypeName.TEMP)){
            table.put(ID, symbolInfo);
            return;
        }
        */

        if(isGlobal){
            symbolInfo.addPlace("global");
            symbolInfo.setGlobal(true);
            table.put(ID, symbolInfo);
            return;
        }

        if (Objects.requireNonNull(symbolInfo.getType()) == TypeName.ARRAY) {
            symbolInfo.setOffset(offset);
            offset -= offsetSize(symbolInfo.getArrayType()) * symbolInfo.getArraySize();
        } else {
            symbolInfo.setOffset(offset);
            offset -= offsetSize(symbolInfo.getType());
        }

        symbolInfo.addPlace("stack");
        table.put(ID, symbolInfo);
    }
    public void declareConstant(String IDValue, TypeName typeName){
        if(find(IDValue) != null){
            return;
        }
        SymbolInfo info = new SymbolInfo(typeName, IDValue);
        info.setConst(true);
        table.put(IDValue, info);

    }
    private int offsetSize(TypeName type) {
        switch (type){
            default -> {
                return 8;
            }
            case INTEGER -> {
                return 4;
            }
            case BOOLEAN -> {
                return 1;
            }
        }
    }

    public SymbolInfo find(String ID) {
        if (table.containsKey(ID)) {
            return table.get(ID);
        } else if (parent != null) {
            return parent.find(ID);
        }
        return null;
    }

    public List<String> allDeclarated(){
        return table.keySet().stream().toList();
    }

    public List<SymbolInfo> allDeclaratedInfo(){
        return table.values().stream().toList();
    }


    public TypeName findReturnType(String ID) {
        SymbolInfo info = find(ID);
        if (info == null) {
            return null;
        }

        /*if(info.getType().equals(TypeName.ARRAY)){
            return info.getArrayType();
        }*/
        if(info.getType().equals(TypeName.FUNCTION)){
            return info.getTypeReturnValue();
        }
        return info.getType();
    }

    public static ArrayList<SymbolTable> getTables() {
        return tables;
    }

    public SymbolTable enterScope() {
        return new SymbolTable(this, newBlock(), offset, false);
    }

    public SymbolTable enterScopeReturn() {
        return new SymbolTable(this, "ret", 0, false);
    }

    public SymbolTable enterScopeIR() {
        String block = newBlockIR();
        SymbolTable needTable = null;
        for(SymbolTable table1: tables){
            if(table1.name.equals(block)){
                needTable = table1;
                break;
            }
        }
        return needTable;
    }

    public String getName() {
        return name;
    }


    public int getOffset() {
        return offset;
    }

    private String newBlockIR() {
        return "b" + (tempTableCountIR++);
    }
    private String newBlock() {
        return "b" + (tempTableCount++);
    }
    public SymbolTable exitScope() {
        return parent;
    }


}

