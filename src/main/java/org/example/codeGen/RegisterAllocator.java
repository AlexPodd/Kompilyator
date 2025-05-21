package org.example.codeGen;

import org.example.IR.Instructions;
import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public class RegisterAllocator {
    private final ArrayList<Register> registers;
    private CodeGen generator;
    private ArrayList<Register> cantChange;

    public RegisterAllocator(ArrayList<Register> registers, ArrayList<Register> cantChange){
        this.registers = registers;
        this.cantChange = cantChange;
    }

    public void setGenerator(CodeGen generator) {
        this.generator = generator;
    }

    public Register getRegNotResult(String var){
        for(Register register: cantChange){
            if(register.contain(var)){
                return register;
            }
        }
        return null;
    }
    public Register getReg(String var, SymbolTable table, Instructions instruction){
        if(containVar(var) != null){
            return containVar(var);
        }

        if(isEmpty() != null){
            return isEmpty();
        }

        if(regCanBeUsed(table) != null){
            return regCanBeUsed(table);
        }

        //условие 2 пересчитываем x, поэтому можем забить
        if(valueReUse(var, instruction) != null){
            return  valueReUse(var, instruction);
        }

        return spill();
    }


    public Register containVar(String var){
        for(Register register: registers){
            if(register.contain(var)){
                return register;
            }
        }
        return null;
    }

    private Register isEmpty(){
        for(Register register: registers){
            if(register.isEmpty()){
                return register;
            }
        }
        return null;
    }

    private Register regCanBeUsed(SymbolTable table){
        for(Register register: registers){
            boolean canUse = true;

            for(String regVar: register.getContains()){
                SymbolInfo info = table.find(regVar);
                if(info == null){
                    continue;
                }
                if (info.getPlaces().size() == 1){
                    canUse = false;
                }
            }
            if(canUse && !register.isUsed()){
                register.clear();
                return register;
            }
        }
        return null;
    }

    private Register valueReUse(String var, Instructions instruction){
        for(Register register: registers){
            if(register.contain(var) && register.getContains().size() == 1) {
                if (var.equals(instruction.getResult()) && !var.equals(instruction.getArg1()) && !var.equals(instruction.getArg2())) {
                    register.clear();
                    return register;
                }
            }
        }
        return null;
    }

    private Register spill(){
        Register best = null;
        int min = Integer.MAX_VALUE;
        for(Register register: registers){
            if(min> register.getContains().size()){
                min = register.getContains().size();
                best = register;
            }
        }
        for(String res: best.getContains()){
            generator.generateStore(res, best);
        }
        best.clear();
        return best;
    }

    private Register lifeAnalyses(SymbolTable table){
         for(Register register: registers){
                boolean canUse = true;
                for(String varReg: register.getContains()){
                    if(table.find(varReg).isLife()){
                        canUse = false;
                    }
                }
                if(canUse){
                    return register;
                }
            }
         return null;
    }

    public boolean isMyReg(Register register){
        return registers.contains(register);
    }

    public void clearReg(boolean isRet, Instructions instruction, SymbolTable table, StackManager manager){
        if(isRet){
            for(Register register: registers){
                register.clear();
            }
            return;
        }
        for (Register register: registers){
            for (String var : register.getContains()) {
                SymbolInfo info = table.find(var);
                if(info == null){
                    if(instruction.isMyLogical()){
                        generator.generateStore(var, register);
                    }
                    continue;
                }


                if (info.isOnlyInReg(register.getName())){
                    info.removeFrom(register.getName());
                    generator.generateStore(var, register);
                }
            }
            register.clear();
        }


        if(!instruction.isMyLogical()) {
            manager.clearTemp();
        }
    }


    public void clearRegCall(SymbolTable table){
        for (Register register: registers){
            for (String var : register.getContains()) {
                SymbolInfo info = table.find(var);
                if(info == null){
                    continue;
                }
                if (info.isOnlyInReg(register.getName())){
                    info.removeFrom(register.getName());
                    generator.generateStore(var, register);
                }
            }
            register.clear();
        }
    }

    public ArrayList<Register> getRegisters() {
        return registers;
    }
}
