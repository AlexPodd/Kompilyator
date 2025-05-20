package org.example.IR;

import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;

public class Block {
    private final ArrayList<Instructions> instructions;

    private final ArrayList<Block> nextBlocks;

    private final ArrayList<String> labels;

    private final String myLabel;

    private static int num = 0;
    private int myNum = 0;
    private Block optimized;

    public Block(ArrayList<Instructions> instructions) {
        this.instructions = instructions;
       myLabel = instructions.get(0).getLabel();
        labels = new ArrayList<>();
        nextBlocks = new ArrayList<>();
        myNum = num;
        myNum++;
    }
    public Block(Block block, ArrayList<Instructions> instructions){
        this.myLabel = block.myLabel;
        this.labels = block.labels;
        this.nextBlocks = block.nextBlocks;
        this.instructions = instructions;
        this.myNum = block.myNum;
    }

    public Block getOptimized() {
        return optimized;
    }

    public void addNextBlock(Block nextBlock) {
        nextBlocks.add(nextBlock);
    }

    public void addLabel(String label) {
        labels.add(label);
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public ArrayList<Block> getNextBlocks() {
        return nextBlocks;
    }

    public String getMyLabel() {
        return myLabel;
    }

    public ArrayList<Instructions> getInstructions() {
        return instructions;
    }

    public void createDag() {
        DAG dag = new DAG(this);
        optimized = dag.generateNewBlock();
    }

    public void lifeAnalyses() {
        for (int i = instructions.size() - 1; i >= 0; i--) {
            Instructions temp = instructions.get(i);
            SymbolTable table = temp.getMyTable();
            SymbolInfo arg1, arg2, result = null;

            arg1 = table.find(temp.getArg1());
            arg2 = table.find(temp.getArg2());
            result = table.find(temp.getResult());

            if (temp.getOp().equals(Operator.RETURN) || temp.getOp().equals(Operator.PRINT)){
                temp.setLifeResult(true);

                if(result != null){
                    result.addUses(i);
                    result.setLife(true);
                }
                continue;
            }

            if (arg1 != null) {
                temp.setLifeArg1(arg1.isLife());
                temp.setNextUseArg1(arg1.getNextUse());
                arg1.setLife(true);
                arg1.addUses(i);
            }
            if (arg2 != null) {
                temp.setLifeArg2(arg2.isLife());
                temp.setNextUseArg2(arg2.getNextUse());
                arg2.setLife(true);
                arg2.addUses(i);
            }
            if (result != null) {
                temp.setLifeResult(result.isLife());
                temp.setNextUseResult(result.getNextUse());

                result.setLife(false);
                result.addUses(-1);
            }
        }

    }

    public ArrayList<String> getLiveVariables() {
        ArrayList<String> liveVars = new ArrayList<>();
        for (Instructions instruction : instructions) {
            if (instruction.isLifeResult()) {
                liveVars.add(instruction.getResult());
            }
        }
        return liveVars;
    }

    public void setOptimized(Block optimized) {
        this.optimized = optimized;
    }
}
