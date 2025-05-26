package org.example.codeGen;

import org.example.IR.Instructions;
import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;
import org.example.semantic.TypeName;

import java.util.ArrayList;

public class CodeGenINT extends AbstractCodeGen{

    private final Register rax;
    public CodeGenINT(ArrayList<String> command, SymbolTable globalTable, RegisterAllocator registerAllocator, StackManager stackManager, Register rax) {
        super(command, globalTable, registerAllocator, stackManager);
        this.rax = rax;
    }

    @Override
    public void generateDiv(Register result, Register reg1, Register reg2) {
        boolean saveRAX = !reg1.getName().equals("RAX") && !reg2.getName().equals("RAX") && !result.getName().equals("RAX");
        boolean saveRDX = !reg1.getName().equals("RDX") && !reg2.getName().equals("RDX");

        if (saveRAX) command.add("    push RAX");
        if (saveRDX) command.add("    push RDX");

        if (!reg1.getName().equals("RAX")) {

            if (reg2.getName().equals("RAX")) {
                command.add("    push " + reg1.getName());
                command.add("    push " + reg2.getName());

                command.add("    pop " + reg1.getName());
                command.add("    pop " + reg2.getName());

                command.add("    mov RAX, " + reg1.getName());
                command.add("    cqo");
                command.add("    idiv " + reg2.getName());

                if (!result.getName().equals("RAX")) {
                    command.add("    mov " + result.getName() + ", RAX");
                }

                if (saveRDX) command.add("    pop RDX");
                if (saveRAX) command.add("    pop RAX");

                result.setHasValue(true);
                return;
            }
            command.add("    mov RAX, " + reg1.getName());
        }

        command.add("    cqo");                         // sign extend RAX into RDX:RAX
        command.add("    idiv " + reg2.getName());     // divide RDX:RAX by reg2

        if (!result.getName().equals("RAX")) {
            command.add("    mov " + result.getName() + ", RAX");
        }

        if (saveRDX) command.add("    pop RDX");
        if (saveRAX) command.add("    pop RAX");

        result.setHasValue(true);
    }

    @Override
        //при умножении сохраняем значения регистров RDX и RAX
    public void generateMul(Register result, Register reg1, Register reg2) {
        boolean saveRAX = !result.getName().equals("RAX") && !reg1.getName().equals("RAX") && !reg2.getName().equals("RAX");
        boolean saveRDX = !result.getName().equals("RDX") && !reg1.getName().equals("RDX") && !reg2.getName().equals("RDX");

        if (saveRAX) command.add("    push RAX");
        if (saveRDX) command.add("    push RDX");

        if (!reg1.getName().equals("RAX")) {
            if (reg2.getName().equals("RAX")){
                command.add("    push "+reg1.getName());
                command.add("    push "+reg2.getName());

                command.add("    pop "+reg1.getName());
                command.add("    pop "+reg2.getName());

                command.add("    imul RAX, " + reg1.getName());
                if (!result.getName().equals("RAX")) {
                    command.add("    mov " + result.getName() + ", RAX");
                }

                if (saveRDX) command.add("    pop RDX");
                if (saveRAX) command.add("    pop RAX");

                result.setHasValue(true);
                return;
            }
            command.add("    mov RAX, " + reg1.getName());

        }

        command.add("    imul RAX, " + reg2.getName());

        if (!result.getName().equals("RAX")) {
            command.add("    mov " + result.getName() + ", RAX");
        }

        if (saveRDX) command.add("    pop RDX");
        if (saveRAX) command.add("    pop RAX");

        result.setHasValue(true);
    }


    @Override
    public void generateAdd(Register result, Register reg1, Register reg2) {
        if (!result.getName().equals(reg1.getName())) {
            command.add("    mov " + result.getName() + ", " + reg1.getName());
        }
        command.add("    add " + result.getName() + ", " + reg2.getName());
        result.setHasValue(true);
    }

    @Override
    public void generateSub(Register result, Register reg1, Register reg2) {
        if (!result.getName().equals(reg1.getName())) {
            command.add("    mov " + result.getName() + ", " + reg1.getName());
        }
        command.add("    sub " + result.getName() + ", " + reg2.getName());
        result.setHasValue(true);
    }

    @Override
    public void generateIfFalse(Register r1, Register r2, Instructions instruction) {
        if(!r1.isHasValue()) generateLoad(instruction.getArg1(), r1);

        if(instruction.getCompOp() == null){
            command.add("    cmp "+ r1.getName()+", "+"0");
            command.add("    "+"je"+" " + instruction.getResult());
            return;
        }

        if(!r2.isHasValue()) generateLoad(instruction.getArg2(), r2);

        String com = "";
        switch (instruction.getCompOp()){
            case LESS -> com = "jge";
            case LESS_EQUAL -> com = "jg";
            case EQUAL -> com = "jne";
            case NOT_EQUAL -> com = "je";
            case GREATER_EQUAL -> com = "jl";
            case MORE -> com = "jle";
        }

        command.add("    cmp "+ r1.getName()+", "+r2.getName());
        command.add("    "+com+" " + instruction.getResult());
    }

        @Override
public void generateIfTrue(Register r1, Register r2, Instructions instruction) {
    if (!r1.isHasValue()) generateLoad(instruction.getArg1(), r1);

    if (instruction.getCompOp() == null) {
        // Простое логическое значение, например: ifTrue x → jump if x != 0
        command.add("    cmp " + r1.getName() + ", 0");
        command.add("    jne " + instruction.getResult()); // если не 0 → jump
        return;
    }

    if (!r2.isHasValue()) generateLoad(instruction.getArg2(), r2);

    String jump = "";
    switch (instruction.getCompOp()) {
        case LESS -> jump = "jl";           // <
        case LESS_EQUAL -> jump = "jle";    // <=
        case EQUAL -> jump = "je";          // ==
        case NOT_EQUAL -> jump = "jne";     // !=
        case GREATER_EQUAL -> jump = "jge"; // >=
        case MORE -> jump = "jg";           // >
    }

    command.add("    cmp " + r1.getName() + ", " + r2.getName());
    command.add("    " + jump + " " + instruction.getResult());
}
    

    @Override
        // res = arg1
    public void generateAssign(Instructions instruction) {
        String result = instruction.getResult();
        String coppy = instruction.getArg1();
        
        Register temp = registerAllocator.containVar(result);

        if (temp != null) {
            temp.deleteFromReg(result);
        }

        Register r1 = registerAllocator.getReg(coppy, table,instruction);
        SymbolInfo info = table.find(result);
        if(info != null){
            info.changeValue(r1.getName());
        }
        else {
            stackManager.addTempToStack(result);
        }
        r1.addVarVirtual(result);

        if(!r1.isHasValue()){
            generateLoad(coppy, r1);
            generateStore(result, r1);
        }
    }

    @Override
    public void generateLoad(String var, Register reg) {
        String nasmReg = reg.getName();
        SymbolInfo info = table.find(var);


        if(reg.contain(var) && reg.isHasValue()){
            return;
        }

        if(info == null){
            command.add(stackManager.loadFromStackTemp(var, reg.getName()));
            reg.addVar(var);
            reg.setHasValue(true);
            return;
        }
        if(info.isConst()){
            command.add("    mov " + nasmReg + ", " + table.find(var).getValue().toString());
            reg.addVar(var);
            reg.setHasValue(true);
            return;
        }
        if(info.getType() == TypeName.STRING){
            command.add("    mov "+reg.getNameLoad(info.getSize())+", " + var);
            info.addPlace(reg.getName());
            reg.addVar(var);
            reg.setHasValue(true);
            return;
        }
        if(info.isGlobal()){
            command.add("    mov "+reg.getNameLoad(info.getSize())+", " +"["+ var+"]");
            info.addPlace(reg.getName());
            reg.addVar(var);
            reg.setHasValue(true);
            return;
        }
        command.add(stackManager.loadLocalFromStack(var ,info, reg));
        info.addPlace(reg.getName());
        reg.addVar(var);
        reg.setHasValue(true);
    }

    @Override
    public void generateReturn(Register reg, String var) {
        String r = reg.getName();
        if(!reg.isHasValue()){
            generateLoad(var ,reg);
        }
        if (!reg.getName().equals("RAX")){
            command.add("    mov RAX, " + r);
        }
        command.add("    leave");
        command.add("    ret");
    }


    @Override
    public void generatePrint(Register reg, String var) {
        command.add("    movsx "+reg.getName()+", "+reg.getNameLoad(4));
        command.add("    push "+reg.getName());
        command.add("    call print_int");
        command.add("    add rsp, 8");
    }

    @Override
    public void generateStore(String var, Register reg) {
        SymbolInfo info = table.find(var);
        if(info == null){
            command.add(stackManager.storeToStackTemp(var, reg.getName()));
            return;
        }

        if(info.isConst()) return;

        if(info.isGlobal()){
            command.add("    mov "+"["+var+"]"+", " + reg.getNameLoad(info.getSize()));
            info.addPlace("global");
            return;
        }
        command.add(stackManager.storeLocalToStack(var ,info, reg.getNameLoad(info.getSize())));
        info.addPlace("stack");
    }

    @Override
    public void generateModulo(Register result, Register reg1, Register reg2) {
        boolean saveRAX = !reg1.getName().equals("RAX") && !reg2.getName().equals("RAX") && !result.getName().equals("RAX") && !result.getName().equals("RDX");
        boolean saveRDX = !reg1.getName().equals("RDX") && !reg2.getName().equals("RDX") && !result.getName().equals("RDX");

        if (saveRAX) command.add("    push RAX");
        if (saveRDX) command.add("    push RDX");

        if (!reg1.getName().equals("RAX")) {
            if (reg2.getName().equals("RAX")) {
                command.add("    push " + reg1.getName());
                command.add("    push " + reg2.getName());

                command.add("    pop " + reg1.getName());
                command.add("    pop " + reg2.getName());

                command.add("    mov RAX, " + reg1.getName());
                command.add("    cqo");
                command.add("    idiv " + reg2.getName());

                if (!result.getName().equals("RAX")) {
                    command.add("    mov " + result.getName() + ", RAX");
                }

                if (saveRDX) command.add("    pop RDX");
                if (saveRAX) command.add("    pop RAX");

                result.setHasValue(true);
                return;
            }

            command.add("    mov RAX, " + reg1.getName());
        }

        command.add("    cqo");                         // Sign extend RAX into RDX:RAX
        command.add("    idiv " + reg2.getName());     // Divide RDX:RAX by reg2

        if (!result.getName().equals("RDX")) {
            command.add("    mov " + result.getName() + ", RDX"); // Get remainder
        }

        if (saveRDX) command.add("    pop RDX");
        if (saveRAX) command.add("    pop RAX");

        result.setHasValue(true);
    }


}
