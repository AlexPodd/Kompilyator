    package org.example.codeGen;
    import org.example.IR.Block;
    import org.example.IR.Instructions;
    import org.example.IR.Operator;
    import org.example.semantic.SymbolInfo;
    import org.example.semantic.SymbolTable;
    import org.example.semantic.TypeName;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    public class CodeGenerator {
        private SymbolTable table;
        private final SymbolTable globalTable;
        private final ArrayList<String> command;

        private final Register rdx = new Register("RDX", "EAX", "AX", "AL");
        private final Register rcx = new Register("RCX", "ECX", "CX", "CL");
        private final Register rbx = new Register("RBX", "EBX", "BX", "BL");
        private final Register rax = new Register("RAX", "EAX", "AX", "AL");
        private final RegisterAllocator registerAllocator = new RegisterAllocator(new ArrayList<>(List.of(rcx, rbx, rax, rdx)), this);
        private final StackManager stackManager = new StackManager(this);

        public CodeGenerator(List<Block> blocks, SymbolTable globalTable) {
            command = new ArrayList<>();
            this.globalTable = globalTable;
            generateData();
            generateInfo();
            generateCode(blocks);
            generateEnd();
        }

        private void generateInfo(){
            command.add("global _start");
            command.add("section .text");
        }
        private void generateData(){
            command.add("section .data");
            for(String var: globalTable.allDeclarated()){
                SymbolInfo info = globalTable.find(var);
                if(info.isConst()){
                    continue;
                }
                switch (info.getType()){
                    case STRING -> {
                    }
                    case BOOLEAN -> {
                        command.add(var+" db "+info.getValue());
                    }
                    case INTEGER -> {
                        command.add(var+" dd "+info.getValue());
                    }
                    case FLOAT -> {

                    }
                    case ARRAY -> {

                    }
                }
            }
        }

        private void generateEnd(){
            command.add("    mov rax, 60");
            command.add("    syscall");
        }
        private void generateCode(List<Block> blocks){
            System.out.println();
            for(Block block: blocks) {
                Block optimized = block.getOptimized();
                for(Instructions instructions: optimized.getInstructions()){
                    System.out.println(instructions);
                }
            }
            System.out.println();


            for(Block block: blocks) {
                if (block.getMyLabel() != null){
                    command.add(block.getMyLabel()+":");

                    SymbolInfo func = globalTable.find(block.getMyLabel());
                    if(func != null){
                        stackManager.setStackTop(0);
                        generatePrologueFunc();
                    }
                    if(block.getMyLabel().equals("_start")){
                        stackManager.setStackTop(0);
                        generatePrologueFunc();
                    }

                }

                Block optimized = block.getOptimized();
                optimized.lifeAnalyses();
                Register r1 = null,r2 = null,r3 = null;
                Instructions instructionsLast = null;
                for(Instructions instruction: optimized.getInstructions()){
                    table = instruction.getMyTable();
                    stackManager.updateStackTop(table.getOffset());

                        if(!instruction.getOp().equals(Operator.IFFALSE) && !instruction.getOp().equals(Operator.GOTO) && !instruction.getOp().equals(Operator.ASSIGN) && !instruction.getOp().equals(Operator.CALL) ) {
                            r1=choseReg(instruction.getArg1(),false, instruction);
                            r2=choseReg(instruction.getArg2(),false, instruction);
                            r3=choseReg(instruction.getResult(),true, instruction);
                            if(r1 != null){
                                r1.setUsed(false);
                            }
                            if(r2 != null){
                                r2.setUsed(false);
                            }
                            if(r3 != null){
                                r3.setUsed(false);
                            }
                        }

                    generate(r1,r2,r3, instruction);
                      instructionsLast = instruction;
                }

                if(instructionsLast != null){
                    if(instructionsLast.getOp().equals(Operator.RETURN)){
                        registerAllocator.clearReg(true, instructionsLast, table, stackManager, command);
                    }
                    else {
                        registerAllocator.clearReg(false, instructionsLast, table, stackManager, command);
                    }
                }


            }
        }


        private Register choseReg(String arg, boolean isResult, Instructions instruction){
            Register register = null;
            if(arg != null){
                if(isResult){
                    register = registerAllocator.getReg(arg, table,instruction);
                    register.addVar(arg);
                }
                else {
                    register = registerAllocator.getReg(arg, table,instruction);
                    register.setUsed(true);
                    generateLoad(arg, register);
                }
            }
            return register;
        }

        private void generatePrologueFunc(){
            command.add("    push rbp");
            command.add("    mov rbp, rsp");
        }

        private void generate(Register r1, Register r2, Register r3, Instructions instruction){
            switch (instruction.getOp()){
                case DIVIDE -> {
                    generateDiv(r3, r1, r2);
                }
                case MULTIPLY -> {
                    generateMul(r3, r1, r2);
                }
                case PLUS -> {
                    generateAdd(r3, r1, r2);
                }
                case MINUS -> {
                    generateSub(r3, r1, r2);
                }
                case RETURN -> {
                    generateReturn(r3, instruction.getResult());
                }
                case IFFALSE -> {
                    generateIfFalse(instruction);
                }
                //название функции (1) количество параметров
                case CALL -> {
                    generateCall(instruction);
                }
                case GOTO -> {
                    generateGoto(instruction);
                }
                case ASSIGN -> {
                    generateAssign(instruction);
                }
                case PARAM -> {
                    generateParam(r3, instruction.getResult());
                }

            }
        }


        public void printCommand(){
            for(String s: command){
                System.out.println(s);
            }
        }


        void generateStore(String var, Register reg, Instructions instruction) {
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
            command.add(stackManager.storeToStack(info, reg.getNameLoad(info.getSize())));
            info.addPlace("stack");
        }
        private void generateStoreGlobal(String var, Register reg){

        }

        private void generateStoreStack(String var, Register reg){

        }

        void generateLoad(String var, Register reg) {
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
            if(info.isGlobal()){
                command.add("    mov "+reg.getNameLoad(info.getSize())+", " +"["+ var+"]");
                info.addPlace(reg.getName());
                reg.addVar(var);
                reg.setHasValue(true);
                return;
            }
            command.add(stackManager.loadFromStack(info, reg));
            info.addPlace(reg.getName());
            reg.addVar(var);
            reg.setHasValue(true);
        }



        void generateAdd(Register result, Register reg1, Register reg2) {
            if (!result.getName().equals(reg1.getName())) {
                command.add("    mov " + result.getName() + ", " + reg1.getName());
            }
            command.add("    add " + result.getName() + ", " + reg2.getName());
            result.setHasValue(true);
        }
        void generateSub(Register result, Register reg1, Register reg2) {
            if (!result.getName().equals(reg1.getName())) {
                command.add("    mov " + result.getName() + ", " + reg1.getName());
            }
            command.add("    sub " + result.getName() + ", " + reg2.getName());
            result.setHasValue(true);
        }


        //при умножении сохраняем значения регистров RDX и RAX
        void generateMul(Register result, Register reg1, Register reg2) {
            if(!result.getName().equals("RAX")){
                command.add("    push " + result.getName());
            }
            command.add("    push " + "RDX");


            command.add("    mov " + "RAX, "+reg1.getName());
            command.add("    imul " + "RAX" + ", " + reg2.getName());

            if(!result.getName().equals("RAX")){
                command.add("    mov " + result.getName()+","+"RAX");
                command.add("    pop " + "RDX");
                command.add("    pop " + "RAX");
            }
            else {
                command.add("    pop " + "RDX");
            }

            result.setHasValue(true);
        }

        void generateDiv(Register result, Register reg1, Register reg2) {
            if (!result.getName().equals("RAX")) {
                command.add("    push " + result.getName());
            }
            command.add("    push RDX");

            command.add("    mov RAX, " + reg1.getName());
            command.add("    cqo");
            command.add("    idiv " + reg2.getName());

            if (!result.getName().equals("RAX")) {
                command.add("    mov " + result.getName() + ", RAX");
                command.add("    pop RDX");
                command.add("    pop RAX");
            } else {
                command.add("    pop RDX");
            }



            result.setHasValue(true);
        }






        void generateIfFalse(Instructions instruction) {
            Register r1, r2;
            if(instruction.getCompOp() == null){
                r1 = registerAllocator.getReg(instruction.getArg1(),  table,instruction);
                r1.addVar(instruction.getArg1());
                generateLoad(instruction.getArg1(), r1);
                command.add("    cmp "+ r1.getName()+", "+"0");
                command.add("    "+"jne"+" " + instruction.getResult());
                return;
            }


            r1 = registerAllocator.getReg(instruction.getArg1(), table,instruction);
            r1.addVar(instruction.getArg1());
            generateLoad(instruction.getArg1(), r1);

            r2 = registerAllocator.getReg(instruction.getArg2(),table ,instruction);
            r2.addVar(instruction.getArg2());
            generateLoad(instruction.getArg2(), r2);

            String com = "";
            switch (instruction.getCompOp()){
                case LESS -> com = "jg";
                case LESS_EQUAL -> com = "jge";
                case EQUAL -> com = "jne";
                case NOT_EQUAL -> com = "je";
                case GREATER_EQUAL -> com = "jle";
                case MORE -> com = "jl";
            }

            command.add("    cmp "+ r1.getName()+", "+r2.getName());
            command.add("    "+com+" " + instruction.getResult());
        }
        void generateGoto(Instructions instruction) {
            registerAllocator.clearReg(false, instruction, table, stackManager, command);
            command.add("    jmp " + instruction.getResult());
        }


        void generateReturn(Register reg, String var) {
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

        void generateCall(Instructions instruction) {
            int numParams = Integer.parseInt(instruction.getArg2());
            int bytesToPush = numParams * 8;


            command.add("    call " + instruction.getArg1());

            if (numParams > 0) {
                command.add("    add rsp, " + bytesToPush);
            }
        }

        // res = arg1
        void generateAssign(Instructions instruction) {
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


        private void generateParam(Register res, String var){
            if(!res.isHasValue()){
                generateLoad(var, res);
            }
            command.add("    push "+res.getName());
        }

        public void addCommand(String commanda){
            command.add(commanda);
        }
        private void generatePrint(){
            
        }
    }
