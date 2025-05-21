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



        Instructions instruction;

        for(int i = 1; i < inst.size(); i++){
            instruction = inst.get(i-1);
            Operator op = instruction.getOp();



            switch (op){
                case PARAM -> {
                   /* if (!temp.isEmpty()){
                        Block block = new Block(new ArrayList<>(temp));
                        block.addLabel("NEXT");
                        blocks.add(block);
                        temp.clear();
                    }*/
                    if (instruction.getLabel() != null){
                        Block block = new Block(new ArrayList<>(temp));
                        block.addLabel("NEXT");
                        blocks.add(block);
                        temp.clear();
                    }
                    params.add(instruction);
                }
                case CALL -> {
                    if(params.get(0).getLabel() != null){
                        params.get(params.size()-1).setLabel(params.get(0).getLabel());
                    }
                    //Collections.reverse(params);
                    params.add(instruction);

                    if(instruction.getResult().equals(inst.get(i).getArg1())){
                        params.add(inst.get(i));
                        i++;
                    }
             //       Block callBlock = new Block(new ArrayList<>(params));
           //         callBlock.addLabel("NEXT");

                    temp.addAll(params);
            //        blocks.add(callBlock);
                    params.clear();

                }
                case GOTO -> {
                    Block block = new Block(new ArrayList<>(temp));
                    block.addLabel(instruction.getResult());
                    blocks.add(block);
                    temp.clear();
                    temp.add(instruction);
                }
                case IFFALSE, IFTRUE -> {
                    if(temp.isEmpty()){
                        temp.add(instruction);
                    }else {
                        Block block = new Block(new ArrayList<>(temp));
                        block.addLabel("NEXT");
                        block.addLabel(instruction.getResult());
                        blocks.add(block);
                        temp.clear();
                        temp.add(instruction);
                    }
                }
                default -> {
                    if(instruction.getLabel() != null){
                        if(temp.isEmpty()){
                            temp.add(instruction);
                        }else {
                            Block block = new Block(new ArrayList<>(temp));
                            block.addLabel("NEXT");
                            blocks.add(block);
                            temp.clear();
                            temp.add(instruction);
                        }
                    }
                    else {
                        temp.add(instruction);
                    }

                }
            }

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


