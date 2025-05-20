package org.example.codeGen;

import org.example.IR.Instructions;
import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public class RegisterAllocator {
    private final ArrayList<Register> registers;
    private final CodeGenerator generator;

    public RegisterAllocator(ArrayList<Register> registers, CodeGenerator generator){
        this.registers = registers;
        this.generator = generator;
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

        return spill(instruction);
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

    private Register spill(Instructions instruction){
        Register best = null;
        int count, min = 100000;
        for(Register register: registers){
            count = 0;
            for(String res: register.getContains()){
                count++;
            }
            if(min> count){
                min = count;
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


    public void clearReg(boolean isRet, Instructions instruction, SymbolTable table, StackManager manager, ArrayList<String> command){
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

}
