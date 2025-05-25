package org.example.IR;

import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Block {
    private final ArrayList<Instructions> instructions;

    private final ArrayList<Block> nextBlocks;

    private final ArrayList<String> labels;

    private final ArrayList<String> myLabel;

    private static int num = 0;
    private int myNum = 0;
    private Block optimized;

    public Block(ArrayList<Instructions> instructions) {
        this.instructions = instructions;
       myLabel = instructions.get(0).getLabels();
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

    public ArrayList<String> getMyLabels() {
        return myLabel;
    }

    public ArrayList<Instructions> getInstructions() {
        return instructions;
    }

    private Map<String, LiveInfo> tempVars = new HashMap<>();

    LiveInfo getLiveInfo(String name, SymbolTable table, Map<String, LiveInfo> temps) {
        SymbolInfo symbol = table.find(name);
        if (symbol != null) {
            return new LiveInfo(symbol.isLife(), symbol.getNextUse());
        } else {
            return temps.computeIfAbsent(name, k -> new LiveInfo());
        }
    }
    public void createDag() {
        DAG dag = new DAG(this);
        optimized = dag.generateNewBlock();
        System.out.println("---OPTIMIZED---");
        for(Instructions instruction: optimized.instructions){
            System.out.println(instruction);
        }
        System.out.println("---END---");
    }

    public void lifeAnalyses() {
        for (int i = instructions.size() - 1; i >= 0; i--) {
            Instructions temp = instructions.get(i);
            SymbolTable table = temp.getMyTable();
            LiveInfo arg1, arg2, result = null;
            SymbolInfo arg1Info, arg2Info, resultInfo;

            arg1Info = table.find(temp.getArg1());
            arg2Info = table.find(temp.getArg2());
            resultInfo = table.find(temp.getResult());

            arg1 = getLiveInfo(temp.getArg1(), table, tempVars);
            arg2 =  getLiveInfo(temp.getArg2(), table, tempVars);
            result =  getLiveInfo(temp.getResult(), table, tempVars);

            if (temp.getOp().equals(Operator.RETURN) || temp.getOp().equals(Operator.PRINT) || temp.getOp().equals(Operator.PARAM)){
                temp.setLifeResult(true);

                result.setNextUse(i);
                result.setLive(true);
                if(resultInfo != null){
                    resultInfo.addUses(i);
                    resultInfo.setLife(true);
                }
                continue;
            }

            if (arg1Info != null) {
                temp.setLifeArg1(arg1Info.isLife());
                temp.setNextUseArg1(arg1Info.getNextUse());
                arg1Info.setLife(true);
                arg1Info.addUses(i);
            }else {
                temp.setLifeArg1(arg1.isLive);
                temp.setNextUseArg1(arg1.nextUse);
                arg1.setLive(true);
                arg1.setNextUse(i);
            }

            if (arg2Info != null) {
                temp.setLifeArg2(arg2Info.isLife());
                temp.setNextUseArg2(arg2Info.getNextUse());
                arg2Info.setLife(true);
                arg2Info.addUses(i);
            } else {
                temp.setLifeArg2(arg2.isLive);
                temp.setNextUseArg2(arg2.nextUse);
                arg2.setLive(true);
                arg2.setNextUse(i);
            }

            if (resultInfo != null) {
                temp.setLifeResult(resultInfo.isLife());
                temp.setNextUseResult(resultInfo.getNextUse());

                resultInfo.setLife(true);
                resultInfo.addUses(-1);

            }else {
                temp.setLifeResult(result.isLive);
                temp.setNextUseResult(result.nextUse);
                result.setLive(false);
                result.setNextUse(-1);
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

class LiveInfo{
    boolean isLive;
    int nextUse;

    public LiveInfo(boolean isLive, int nextUse) {
        this.isLive = isLive;
        this.nextUse = nextUse;
    }
    public LiveInfo() {
        isLive = false;
        nextUse = -1;
    }
    public boolean isLive() {
        return isLive;
    }

    public int getNextUse() {
        return nextUse;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void setNextUse(int nextUse) {
        this.nextUse = nextUse;
    }
}