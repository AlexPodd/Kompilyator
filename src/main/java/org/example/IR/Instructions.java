package org.example.IR;


import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;
import org.example.semantic.TypeName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

//result - метка перехода
public class Instructions {
    private static int Number = 0;
    private static final HashMap<String, TypeName> tempType = new HashMap<>();
    private int myNumber;
    private Operator op;
    private String arg1;
    private String arg2;
    private String result;

    private final SymbolTable myTable;


    private boolean lifeArg1;
    private boolean lifeArg2;
    private boolean lifeResult;

    private int nextUseArg1;
    private int nextUseArg2;
    private int nextUseResult;
    private Operator compOp;

    private String label;
    private final boolean myInLoop;

    private boolean myLogical;

    private static boolean isLoop = false;

    private static boolean isLogical = false;

    private TypeName typeArg1;
    private TypeName typeArg2;
    private TypeName typeResult;

    public Instructions(String op, String arg1, String arg2, String result, SymbolTable table) {
        this.op = genOp(op);
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
        myNumber = Number;
        this.myTable = table;
        inc();
        myInLoop = isLoop;
        myLogical = isLogical;
        setType();
    }
    public Instructions(Operator op, String arg1, String arg2, String result, SymbolTable table) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
        this.myTable = table;
        myNumber = Number;
        inc();
        myInLoop = isLoop;
        myLogical = isLogical;
        setType();
    }

    public Instructions(String op, String arg1,String compOp, String arg2, String result, SymbolTable table) {
        this.op = genOp(op);
        this.compOp = genOp(compOp);
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
        myNumber = Number;
        this.myTable = table;
        inc();
        myInLoop = isLoop;
        myLogical = isLogical;
        setType();
    }

    public Instructions(Operator op, String arg1,Operator compOp, String arg2, String result, SymbolTable table) {
        this.op = op;
        this.compOp = compOp;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
        myNumber = Number;
        this.myTable = table;
        inc();
        myInLoop = isLoop;
        myLogical = isLogical;
        setType();
    }

    private void setType(){
        typeArg1 = findType(arg1);
        typeArg2 = findType(arg2);
        typeResult = findType(result);

        if(typeArg1 == null) typeArg1 = calcType(typeArg2, typeResult, arg1);
        if(typeArg2 == null) typeArg2 = calcType(typeArg1, typeResult, arg2);
        if(typeResult == null) typeResult = calcType(typeArg1, typeArg2, result);

    }
    private TypeName findType(String arg){
        SymbolInfo info = myTable.find(arg);
        if(info != null){
            return info.getType();
        }
        return tempType.get(arg);
    }
    private TypeName calcType(TypeName type1, TypeName type2, String result){
        if (TypeName.FLOAT.equals(type1) || TypeName.FLOAT.equals(type2)) {
            tempType.put(result, TypeName.FLOAT);
            return TypeName.FLOAT;
        }
        tempType.put(result, TypeName.INTEGER);
        return TypeName.INTEGER;
    }

    public TypeName getTypeArg1() {
        return typeArg1;
    }

    public TypeName getTypeArg2() {
        return typeArg2;
    }

    public TypeName getTypeResult() {
        return typeResult;
    }

    public static void setIsLogical(boolean isLogical) {
        Instructions.isLogical = isLogical;
    }

    public static boolean isIsLoop() {
        return isLoop;
    }

    public static boolean isIsLogical() {
        return isLogical;
    }

    public void setMyLogical(boolean myLogical) {
        this.myLogical = myLogical;
    }

    public boolean isMyLogical() {
        return myLogical;
    }

    public static void setLoop(boolean loop) {
        isLoop = loop;
    }

    public boolean myInLoop() {
        return myInLoop;
    }

    public Operator getCompOp() {
        return compOp;
    }

    private static void inc(){
        Number++;
    }

    private Operator genOp(String op){
        switch (op){
            case "+":
                return Operator.PLUS;
            case "print":
                return Operator.PRINT;
            case "-":
                return Operator.MINUS;
            case "*":
                return Operator.MULTIPLY;
            case "/":
                return Operator.DIVIDE;
            case "&&":
                return Operator.AND;
            case "||":
                return Operator.OR;
            case "minus":
                return Operator.minus;
            case "ifFalse":
                return Operator.IFFALSE;
            case "for":
                return Operator.FOR;
            case "goto":
                return Operator.GOTO;
            case "assign":
                return Operator.ASSIGN;
            case "==":
                return Operator.EQUAL;
            case "!=":
                return Operator.NOT_EQUAL;
            case ">":
                return Operator.MORE;
            case "<":
                return Operator.LESS;
            case "<=":
                return Operator.LESS_EQUAL;
            case ">=":
                return Operator.GREATER_EQUAL;
            case "!":
                return Operator.NOT;
            case "ifTrue":
                return Operator.IFTRUE;
            case "param":
                return Operator.PARAM;
            case "call":
                return Operator.CALL;
            case "end":
                return Operator.END;
            case "return":
                return Operator.RETURN;
            case "%":
                return Operator.MODULO;
            default:
                try {
                    throw new Exception("NE WORK");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }

    }

    public SymbolTable getMyTable() {
        return myTable;
    }

    public static int getNumber() {
        return Number;
    }

    public boolean isLifeArg1() {
        return lifeArg1;
    }

    public boolean isLifeArg2() {
        return lifeArg2;
    }

    public boolean isLifeResult() {
        return lifeResult;
    }

    public int getNextUseArg1() {
        return nextUseArg1;
    }

    public int getNextUseArg2() {
        return nextUseArg2;
    }

    public static void setNumber(int number) {
        Number = number;
    }

    public void setLifeArg1(boolean lifeArg1) {
        this.lifeArg1 = lifeArg1;
    }

    public void setLifeArg2(boolean lifeArg2) {
        this.lifeArg2 = lifeArg2;
    }

    public void setLifeResult(boolean lifeResult) {
        this.lifeResult = lifeResult;
    }

    public void setNextUseArg1(int nextUseArg1) {
        this.nextUseArg1 = nextUseArg1;
    }

    public void setNextUseArg2(int nextUseArg2) {
        this.nextUseArg2 = nextUseArg2;
    }

    public void setNextUseResult(int nextUseResult) {
        this.nextUseResult = nextUseResult;
    }

    public int getNextUseResult() {
        return nextUseResult;
    }


    public Operator getOp() {
        return op;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public String getResult() {
        return result;
    }

    public String getLabel() {
        return label;
    }

    public void setOp(Operator op) {
        this.op = op;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public int getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(int myNumber) {
        this.myNumber = myNumber;
    }

    @Override
    public String toString() {
        // Форматируем строку с фиксированными отступами
        String formattedLabel = label != null ? String.format("%-4s", label) : "    ";
        String formattedNumber = String.format("%-3d", myNumber);

        // Форматируем аргументы в зависимости от типа операции
        String operationLine;
        switch (op) {
            case ASSIGN:
                operationLine = String.format("%-5s = %-5s", result, arg1);
                break;
            case GOTO:
                operationLine = String.format("goto %-5s", result);
                break;
            case PARAM:
                operationLine = String.format("param %-5s", result);
                break;
            case IFFALSE:
            case IFTRUE:
                operationLine = String.format("%-7s %-5s, %-5s, %-5s",
                        op.name().toLowerCase(), arg1, arg2, result);
                break;
            case FOR:
            case AND:
            case OR:
            case PLUS:
            case MINUS:
            case MULTIPLY:
            case DIVIDE:
            case LESS:
            case MORE:
            case EQUAL:
            case NOT_EQUAL:
            case LESS_EQUAL:
            case GREATER_EQUAL:
                operationLine = String.format("%-5s = %-5s %-2s %-5s",
                        result, arg1, op.name(), arg2);
                break;
            case minus: // Унарный минус
                operationLine = String.format("%-5s = -%-5s", result, arg1);
                break;
            default:
                operationLine = String.format("%-5s %-5s %-5s %-5s",
                        op.name(), arg1, arg2, result);
        }

        return String.format("%s %s %s", formattedLabel, formattedNumber, operationLine);
    }
}

