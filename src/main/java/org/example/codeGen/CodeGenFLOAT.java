package org.example.codeGen;

import org.antlr.v4.parse.ANTLRParser.blockEntry_return;
import org.example.IR.Instructions;
import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;
import org.example.semantic.TypeName;

import java.util.ArrayList;

public class CodeGenFLOAT extends AbstractCodeGen {

private final Register xmm0;
    public CodeGenFLOAT(ArrayList<String> command, SymbolTable globalTable, RegisterAllocator registerAllocator, StackManager stackManager, Register xmm0) {
        super(command, globalTable, registerAllocator, stackManager);
        this.xmm0 = xmm0;
    }

    private String valueToText(Object val) {
        double num = Double.parseDouble((String) val);
        if (num < 0) {
            return "_minus" + Math.abs(num);
        } else {
            return "_"+num;
        }
    }
    private Register castOperation(Register reg1){
        Register actualR1 = reg1;
        if (!reg1.isFloat()) {
            Register castR1 = registerAllocator.getEmptyXMMReg();
            command.add("    cvtsi2sd " + castR1.getName() + ", " + reg1.getName());
            actualR1 = castR1;
            castR1.setHasValue(true);
        }
        return actualR1;
    }

    @Override
    public void generateDiv(Register result, Register reg1, Register reg2) {
        Register actualR1 = castOperation(reg1);
        Register actualR2 = castOperation(reg2);

        if (!result.getName().equals(actualR1.getName())) {
            command.add("    movsd " + result.getName() + ", " + actualR1.getName());
        }

        command.add("    divsd " + result.getName() + ", " + actualR2.getName());
        result.setHasValue(true);
    }

    @Override
    public void generateMul(Register result, Register reg1, Register reg2) {
        Register actualR1 = castOperation(reg1);
        Register actualR2 = castOperation(reg2);

        if (!result.getName().equals(actualR1.getName())) {
            command.add("    movsd " + result.getName() + ", " + actualR1.getName());
        }

        command.add("    mulsd " + result.getName() + ", " + actualR2.getName());
        result.setHasValue(true);
    }

    @Override
    public void generateAdd(Register result, Register reg1, Register reg2) {
        Register actualR1 = castOperation(reg1);
        Register actualR2 = castOperation(reg2);

        if (!result.getName().equals(actualR1.getName())) {
            command.add("    movsd " + result.getName() + ", " + actualR1.getName());
        }

        command.add("    addsd " + result.getName() + ", " + actualR2.getName());
        result.setHasValue(true);
    }

    @Override
    public void generateSub(Register result, Register reg1, Register reg2) {
        Register actualR1 = castOperation(reg1);
        Register actualR2 = castOperation(reg2);

        if (!result.getName().equals(actualR1.getName())) {
            command.add("    movsd " + result.getName() + ", " + actualR1.getName());
        }

        command.add("    subsd " + result.getName() + ", " + actualR2.getName());
        result.setHasValue(true);
    }

    @Override
    public void generateIfFalse(Register r1, Register r2, Instructions instruction) {


        if(!r1.isHasValue()) generateLoad(instruction.getArg1(), r1);
        Register actualR1 = castOperation(r1);



        if(instruction.getCompOp() == null) {
                command.add("    xorpd xmm15, xmm15");  // xmm15 = 0.0
                command.add("    ucomisd " + actualR1.getName() + ", xmm15");
                command.add("    jne " + instruction.getResult());
                return;
        }


        if(!r2.isHasValue()) generateLoad(instruction.getArg2(), r2);
        Register actualR2 = castOperation(r2);
        // Обработка операций сравнения
        String com = "";
            // Для вещественных чисел используем ucomisd + условные переходы
            command.add("    ucomisd " + actualR1.getName() + ", " + actualR2.getName());
            switch (instruction.getCompOp()) {
                case LESS -> com = "jnb";    // not below (CF=0)
                case LESS_EQUAL -> com = "ja";   // not above (CF=0 or ZF=1)
                case EQUAL -> com = "jne";   // not equal (ZF=0)
                case NOT_EQUAL -> com = "je";    // equal (ZF=1)
                case GREATER_EQUAL -> com = "jb";    // below (CF=1)
                case MORE -> com = "jbe";   // below or equal (CF=1 or ZF=1)
            }


            
        command.add("    " + com + " " + instruction.getResult());
    }


    @Override
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
            r1.addVarVirtual(result);


            if(!r1.isHasValue()){
                generateLoad(coppy, r1);
            }
    }


    @Override
    public void generateReturn(Register reg, String var) {
        String r = reg.getName();
        if(!reg.isHasValue()){
            generateLoad(var ,reg);
        }
        if (!reg.getName().equals(xmm0.getName())){
            command.add("    movsd xmm0, " + r);
        }
        command.add("    leave");
        command.add("    ret");
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
            command.add("    movsd "+"["+var+"]"+", " + reg.getNameLoad(info.getSize()));
            info.addPlace("global");
            return;
        }
        command.add(stackManager.storeLocalXMMStack(var ,info, reg.getNameLoad(info.getSize())));
        info.addPlace("stack");
    }
    @Override
    public void generateLoad(String var, Register reg) {
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
            if (info.getType().equals(TypeName.FLOAT)) {
                command.add("    movsd " + reg.getName() +", ["+ valueToText(var)+"]");
            } else if (info.getType().equals(TypeName.INTEGER)) {
                command.add("    push RAX");
                command.add("    mov rax, " + info.getValue());
                command.add("    cvtsi2sd " + reg.getName() + ", rax");
                command.add("    pop RAX");
            }

            reg.addVar(var);
            reg.setHasValue(true);
            return;
        }
        if(info.isGlobal()){
            command.add("    movq "+reg.getName()+", " +"["+ var+"]");
            info.addPlace(reg.getName());
            reg.addVar(var);
            reg.setHasValue(true);
            return;
        }
        command.add(stackManager.loadLocalXMMFromStack(var ,info, reg));
        info.addPlace(reg.getName());
        reg.addVar(var);
        reg.setHasValue(true);
    }





    @Override
    public void generatePrint(Register reg, String var) {
        command.add("    mov rdi, fmtGlobal");
        
        boolean cont = xmm0.contain(var);
        Register temp = null;
        if(!cont){
            temp = registerAllocator.getEmptyXMMReg();
            command.add("    movsd "+temp.getName()+ ", xmm0");
            command.add("    movsd xmm0, "+var);
        }

        int padding = stackManager.alignStackCall();
        
        command.add("    sub rsp, "+padding);
        
        command.add("    mov eax, 1");
        command.add("    call printf");
      
        command.add("    add rsp, "+padding);
      
        
        if(!cont){
            command.add("    movsd xmm0, "+temp.getName());
        }
    } 
    @Override
    public void generateModulo(Register result, Register reg1, Register reg2) {

    }
}
