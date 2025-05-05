package org.example.IR;

import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;

import java.util.*;

public class Optimizator {
    private ArrayList<Block> blocks;
    private ArrayList<Instructions> params;
    private Instructions callInst = null;


    public Optimizator() {
    }

    public void blockConstruct(ArrayList<Instructions> inst) {
        blocks = new ArrayList<>();
        params = new ArrayList<>();
        ArrayList<Instructions> temp = new ArrayList<>();

        temp.add(inst.get(0));

        Operator prevOp;
        for(int i = 1; i < inst.size(); i++){
            prevOp = inst.get(i-1).getOp();
            if(prevOp.equals(Operator.RETURN)){
                Block block = new Block(new ArrayList<>(temp));
                block.addLabel("END");
                blocks.add(block);
                temp.clear();
                temp.add(inst.get(i));
                continue;
            }

            if(prevOp.equals(Operator.CALL)){
                Block block = new Block(new ArrayList<>(temp));
                block.addLabel(inst.get(i-1).getResult());
                blocks.add(block);
                temp.clear();
                temp.add(inst.get(i));


                Collections.reverse(params);
                params.add(callInst);
                Block block1 = new Block(new ArrayList<>(params));
                block1.addLabel("NEXT");
                blocks.add(block1);
                callInst = null;
                params.clear();
                continue;

            }

            if(prevOp.equals(Operator.GOTO)){
                Block block = new Block(new ArrayList<>(temp));
                block.addLabel(inst.get(i-1).getResult());
                blocks.add(block);
                temp.clear();
                temp.add(inst.get(i));
                continue;
            }

            if(prevOp.equals(Operator.IFTRUE) ||  prevOp.equals(Operator.IFFALSE)){
                Block block = new Block(new ArrayList<>(temp));
                block.addLabel("NEXT");
                block.addLabel(inst.get(i-1).getResult());
                blocks.add(block);
                temp.clear();
                temp.add(inst.get(i));
                continue;
            }

            if(inst.get(i).getOp().equals(Operator.PARAM)){
                params.add(inst.get(i));
                continue;
            }

            if(inst.get(i).getOp().equals(Operator.CALL)){
                callInst = inst.get(i);
                continue;
            }


            if(inst.get(i).getLabel() != null){
                Block block = new Block(new ArrayList<>(temp));
                block.addLabel("NEXT");
                blocks.add(block);
                temp.clear();
                temp.add(inst.get(i));
                continue;
            }

            temp.add(inst.get(i));
        }

        if(!temp.isEmpty()){
            blocks.add(new Block(new ArrayList<>(temp)));
            temp.clear();
        }

        for (int i = 0; i < blocks.size()-1; i++) {
            addNextBlock(blocks.get(i));

            if(blocks.get(i).getLabels().contains("NEXT")){
                blocks.get(i).addNextBlock(blocks.get(i+1));
            }
        }


        /*for (int i = 0; i < blocks.size(); i++) {
            System.out.println("Block "+i);
            for(Instructions instructions: blocks.get(i).getInstructions()){
                System.out.println(instructions);
            }
            for(Block lab: blocks.get(i).getNextBlocks()){
                System.out.print(lab.getMynum()+" ");
            }
            System.out.println();
        }*/

        lifeAnalyses();

    }

    private void lifeAnalyses(){
        for (int i = blocks.size()-1; i >= 0; i--) {
            blocks.get(i).lifeAnalyses();

        }

        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).createDag();
        }
    }


    private void addNextBlock(Block block){
        for(Block bl: blocks){
            if(block.getLabels().contains(bl.getMyLabel())){
                block.addNextBlock(bl);
            }
        }
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}


