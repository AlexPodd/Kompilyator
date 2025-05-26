    package org.example.codeGen;
    import org.example.IR.Block;
    import org.example.IR.Instructions;
    import org.example.IR.Operator;
    import org.example.semantic.SymbolInfo;
    import org.example.semantic.SymbolTable;
    import org.example.semantic.TypeName;

    import javax.swing.text.html.parser.Parser;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    public class CodeGenerator {
        private SymbolTable table;
        private final SymbolTable globalTable;
        private final ArrayList<String> command;

        private final Register rdx = new Register("RDX", "EDX", "AX", "AL");
        private final Register rcx = new Register("RCX", "ECX", "CX", "CL");
        private final Register rbx = new Register("RBX", "EBX", "BX", "BL");
        private final Register rax = new Register("RAX", "EAX", "AX", "AL");

        private final Register rsi = new Register("RSI", "ESI", "SI", "SIL");

        private final Register rdi = new Register("RDI", "EDI", "DI", "DIL");

        private final Register r8 = new Register("R8", "R8D", "R8W", "R8B");
        private final Register r9 = new Register("R9", "R9D", "R9W", "R9B");
        private final Register r10= new Register("R10", "R10D", "R10W", "R10B");
        private final Register r11 = new Register("R11", "R11D", "R11W", "R11B");


        private final Register xmm0 = new Register("xmm0");
        private final Register xmm1 = new Register("xmm1");
        private final Register xmm2 = new Register("xmm2");
        private final Register xmm3 = new Register("xmm3");
        private final Register xmm4 = new Register("xmm4");
        private final Register xmm5 = new Register("xmm5");
        private final Register xmm6 = new Register("xmm6");
        private final Register xmm7 = new Register("xmm7");
       private final StackManager stackManager = new StackManager(this);

        private final CodeGenFLOAT codeGenFLOAT;
        private final CodeGenINT codeGenINT;


        private final List<Register> argRegs = List.of(rdi, rsi, rdx, rcx, r8, r9);
        private final List<Register> argRegsFloat = List.of(xmm0, xmm1, xmm2, xmm3, xmm4, xmm5, xmm6, xmm7);


        private ArrayList<Register> callerSaved = new ArrayList<>(List.of(rax, rcx, rdx, rsi, rdi, r8, r9, r10, r11, xmm0, xmm1, xmm2, xmm3, xmm4, xmm5, xmm6, xmm7));

        private ArrayList<Register> calleeSaved = new ArrayList<>(List.of(rbx));

        private final RegisterAllocator registerAllocator = new RegisterAllocator(new ArrayList<>(List.of(rax, rcx, rbx, rdx)), new ArrayList<>(List.of(rdi, rsi, r8, r9)));
        private final RegisterAllocator registerAllocatorXMM = new RegisterAllocator(new ArrayList<>(List.of(xmm0, xmm1, xmm2, xmm3,xmm4,xmm5,xmm6,xmm7)), new ArrayList<>(List.of(xmm0, xmm1, xmm2, xmm3,xmm4,xmm5,xmm6,xmm7)));

        private final List<String> argQueue = new ArrayList<>();

        public CodeGenerator(List<Block> blocks, SymbolTable globalTable) {
            command = new ArrayList<>();
            this.globalTable = globalTable;

            codeGenINT = new CodeGenINT(command, globalTable, registerAllocator, stackManager, rax);
            codeGenFLOAT = new CodeGenFLOAT(command, globalTable, registerAllocatorXMM, stackManager, xmm0);
            registerAllocator.setGenerator(codeGenINT);
            registerAllocatorXMM.setGenerator(codeGenFLOAT);
            generateData();
            generateBss();
            generateInfo();
            generateMacro();
            generateCode(blocks);
            generateEnd();
        }

        private void generateInfo(){
            command.add("global _start");
            command.add("section .text");
        }
        private void generateBss(){
            command.add("section .bss");
            command.add("num_buf resb 32");
            for(String var: globalTable.allDeclarated()){
                SymbolInfo info = globalTable.find(var);
                if (info.getValue() == null){
                    switch (info.getType()){
                        case STRING, FLOAT -> {
                            command.add(var+" resq 1");
                        }
                        case BOOLEAN -> {
                            command.add(var+" resb 1");
                        }
                        case INTEGER -> {
                            command.add(var+" resd 1");
                        }
                        case ARRAY -> {

                        }
                    }
                }
            }
        }
        private void generateData(){
            command.add("section .data");
            command.add("fmtGlobal db \"Результат: %f\", 10, 0");
            command.add("extern printf");
            for(String var: globalTable.allDeclarated()){
                SymbolInfo info = globalTable.find(var);
                if (info.getValue() == null){
                    continue;
                }
                if(info.isConst()){
                    switch (info.getType()) {
                        case STRING:
                            command.add(var.replace("\"", "")+" db "+info.getValue()+", 10");
                        break;
                        case FLOAT:
                            command.add(var+" dq "+info.getValue());    
                        break;
                        default:
                            break;
                    }
                    
                    
                    continue;
                }
                switch (info.getType()){
                    case STRING -> {
                        command.add(var+" db "+info.getValue()+", 10");
                    }
                    case BOOLEAN -> {
                        command.add(var+" db "+info.getValue());
                    }
                    case INTEGER -> {
                        command.add(var+" dd "+info.getValue());
                    }
                    case FLOAT -> {
                        command.add(var+" dq "+info.getValue());
                    }
                    case ARRAY -> {

                    }
                }
            }
        }

        private void generateMacro(){
            command.add("print_int:");
            command.add("    push rbp");
            command.add("    mov rbp, rsp");
            command.add("    push rbx");
            command.add("    push rcx");
            command.add("    push rdx");
            command.add("    push rsi");
            command.add("    push rdi");
            command.add("    push rax");
            command.add("    sub rsp, 40");
            command.add("    mov rax, [rbp+16]");
            command.add("    mov rdi, num_buf");
            command.add("    call int_to_str");
            command.add("    mov rsi, rdi");
            command.add("    mov rdx, rax");
            command.add("    mov rax, 1");
            command.add("    mov rdi, 1");
            command.add("    syscall");
            command.add("    add rsp, 40");
            command.add("    pop rax");
            command.add("    pop rdi");
            command.add("    pop rsi");
            command.add("    pop rdx");
            command.add("    pop rcx");
            command.add("    pop rbx");
            command.add("    mov rsp, rbp");
            command.add("    pop rbp");
            command.add("    ret");
            command.add("int_to_str:");
            command.add("    push rbx");
            command.add("    push rcx");
            command.add("    push rdx");
            command.add("    push rsi");
            command.add("    mov rcx, 0");
            command.add("    mov rsi, rdi");
            command.add("    cmp rax, 0");
            command.add("    jge .convert");
            command.add("    mov byte [rsi], '-'");
            command.add("    inc rsi");
            command.add("    neg rax");
            command.add(".convert:");
            command.add("    mov rbx, 10");
            command.add("    .store_loop:");
            command.add("    xor rdx, rdx");
            command.add("    div rbx");
            command.add("    push rdx");
            command.add("    inc rcx");
            command.add("    test rax, rax");
            command.add("    jnz .store_loop");
            command.add(".write_digits:");
            command.add("    pop rdx");
            command.add("    add dl, '0'");
            command.add("    mov [rsi], dl");
            command.add("    inc rsi");
            command.add("    loop .write_digits");
            command.add("    mov byte [rsi], 10");
            command.add("    inc rsi");
            command.add("    mov rax, rsi");
            command.add("    sub rax, rdi");
            command.add("    pop rsi");
            command.add("    pop rdx");
            command.add("    pop rcx");
            command.add("    pop rbx");
            command.add("    ret");
        }

        private void generateEnd(){
            command.add("    mov rax, 60");
            command.add("    syscall");
        }


        private void generateLabel(ArrayList<String> labels){
            if(labels.isEmpty()) return;

            for(String label: labels){
                command.add(label+":");
            }


            SymbolInfo func = globalTable.find(labels.get(0));
            if(func != null){

                table = func.getSymbolTable();
                generatePrologueFunc(func);
                stackManager.initFunc(func.getSymbolTable().getOffset(), func.getSymbolTable());
            }
            if(labels.get(0).equals("_start")){

                table = globalTable;
                generatePrologueFunc(null);
                stackManager.initFunc(0, null);
            }
        }

        private void generateCode(List<Block> blocks){
         
            System.out.println();
            CodeGen gen = null;
            for(Block block: blocks) {

                generateLabel(block.getMyLabels());
                block.getOptimized().lifeAnalyses();

                Register r1 = null,r2 = null,r3 = null;
                
                for(Instructions instruction: block.getOptimized().getInstructions()){
                    //   command.add(instruction.toString());
                    if(table != instruction.getMyTable()){
                        table = instruction.getMyTable();
                        stackManager.newBlockLocalVariable(table.getOffset(), table);
                    }
                    codeGenFLOAT.setTable(table);
                    codeGenINT.setTable(table);
                    switch (instruction.getTypeResult()){
                        case FLOAT -> {
                            gen = codeGenFLOAT;

                        }
                        default -> {
                            gen = codeGenINT;
                        }
                    }

                    switch (instruction.getOp()){
                        case ASSIGN -> {
                            gen.generateAssign(instruction);
                        }
                        case GOTO -> {
                            generateGoto(instruction);
                        }
                        case CALL -> {
                            generateCall(instruction);
                        }
                        case PARAM -> {
                            generateParam(instruction);
                        }
                        case IFFALSE -> {
                            RegisterAllocator allocator;
                            if(instruction.getTypeArg1().equals(TypeName.FLOAT)){
                                gen = codeGenFLOAT;
                                codeGenFLOAT.setTable(table);
                                allocator = registerAllocatorXMM;
                            }
                            else {
                                gen = codeGenINT;
                                codeGenINT.setTable(table);
                                allocator = registerAllocator;
                            }
                            r1=choseReg(instruction.getArg1(),false, instruction, allocator, gen);
                            r2=choseReg(instruction.getArg2(),false, instruction, allocator ,gen);
                            if(r1 != null){
                                r1.setUsed(false);
                            }
                            if(r2 != null){
                                r2.setUsed(false);
                            }
                            gen.generateIfFalse(r1, r2, instruction);
                        }
                        case IFTRUE -> {
                            RegisterAllocator allocator;
                            if(instruction.getTypeArg1().equals(TypeName.FLOAT)){
                                gen = codeGenFLOAT;
                                codeGenFLOAT.setTable(table);
                                allocator = registerAllocatorXMM;
                            }
                            else {
                                gen = codeGenINT;
                                codeGenINT.setTable(table);
                                allocator = registerAllocator;
                            }
                            r1=choseReg(instruction.getArg1(),false, instruction, allocator, gen);
                            r2=choseReg(instruction.getArg2(),false, instruction, allocator ,gen);
                            if(r1 != null){
                                r1.setUsed(false);
                            }
                            if(r2 != null){
                                r2.setUsed(false);
                            }
                            gen.generateIfTrue(r1, r2, instruction);
                        }
                        default -> {
                            r1=choseRegg(instruction.getArg1(),false, instruction, gen, instruction.getTypeArg1());
                            r2=choseRegg(instruction.getArg2(),false, instruction, gen, instruction.getTypeArg2());
                            r3=choseRegg(instruction.getResult(),true, instruction, gen, instruction.getTypeResult());
                            if(r1 != null){
                                r1.setUsed(false);
                            }
                            if(r2 != null){
                                r2.setUsed(false);
                            }
                            if(r3 != null){
                                r3.setUsed(false);
                            }
                            switch (instruction.getOp()){
                                case PLUS ->{
                                    gen.generateAdd(r3,r1,r2);
                                }
                                case MINUS -> {
                                    gen.generateSub(r3, r1, r2);
                                }
                                case DIVIDE -> {
                                    gen.generateDiv(r3, r1, r2);
                                }
                                case MULTIPLY -> {
                                    gen.generateMul(r3, r1, r2);
                                }
                                case RETURN -> {
                                    gen.generateReturn(r3, instruction.getResult());
                                }

                                case PRINT -> {
                                    if(instruction.getTypeResult().equals(TypeName.STRING)){
                                        generatePrintForString(instruction.getResult());
                                    }
                                    else {
                                        gen.generatePrint(r3, instruction.getResult());
                                    }
                                }
                                case MODULO -> {
                                    gen.generateModulo(r3, r1, r2);
                                }
                            }

                        }
                    }
                }
                if (block.getOptimized().getInstructions().isEmpty()){
                    continue;
                }
                Instructions instructionsLast = block.getOptimized().getInstructions().get(block.getOptimized().getInstructions().size()-1);
                registerAllocatorXMM.clearReg(instructionsLast.getOp().equals(Operator.RETURN), instructionsLast, table, stackManager);
                registerAllocator.clearReg(instructionsLast.getOp().equals(Operator.RETURN), instructionsLast, table, stackManager);
            }
        }
        private void generatePrintForString(String result){
            saveRegister();


            SymbolInfo info = table.getGlobal().find(result);
            if(info == null){
               String var = "_"+ result.replace(" ", "_");
               info = table.getGlobal().find(var);
               var = var.replace("\"", "");

                command.add(     "mov rax, 1");
                command.add(     "mov rdi, 1");
                command.add(     "mov rsi, "+var);
                command.add(     "mov rdx, "+var.length());
                command.add(     "syscall");

                saveRegisterReturn();
                return;
            }

            if(!table.isGlobal()){
                int offset = stackManager.getLocalOffset(result);
                command.add(     "mov rax, 1");
                command.add(     "mov rdi, 1");
                command.add(     "mov rsi, "+"[rbp" + offset + "]");
                command.add(     "mov rdx, "+11);
                command.add(     "syscall");

                saveRegisterReturn();
                return;
            }
            command.add(     "mov rax, 1");
            command.add(     "mov rdi, 1");
            command.add(     "mov rsi, "+result);
            command.add(     "mov rdx, "+(info.getValue().toString().length()-1));
            command.add(     "syscall");

            saveRegisterReturn();

        }
        private void saveRegister(){

            command.add("push rax");
            command.add("push rcx");
            command.add("push rdx");
            command.add("push rsi");
            command.add("push rdi");
            command.add("push r8");
            command.add("push r9");
            command.add("push r10");
            command.add("push r11");
        }
        private void saveRegisterReturn(){
            command.add("pop r11");
            command.add("pop r10");
            command.add("pop r9");
            command.add("pop r8");
            command.add("pop rdi");
            command.add("pop rsi");
            command.add("pop rdx");
            command.add("pop rcx");
            command.add("pop rax");
        }
        public Register choseRegg(String arg, boolean isResult, Instructions instruction, CodeGen generator, TypeName type){
            RegisterAllocator allocator = null;
            CodeGen generator1 = null;
            switch (type){
                case FLOAT -> {
                    allocator = registerAllocatorXMM;
                    generator1 = codeGenFLOAT;
                }
                default -> {
                    allocator =registerAllocator;
                    generator1 = codeGenINT;
                }
            }
            return choseReg(arg, isResult, instruction, allocator, generator1);
        }
        private Register choseReg(String arg, boolean isResult, Instructions instruction, RegisterAllocator registerAllocator, CodeGen generator){
            Register register = null;
            if(arg != null){
                if(isResult){
                    register = registerAllocator.getReg(arg, table,instruction);
                    register.addVar(arg);
                   SymbolInfo changed = table.find(arg);
                    if(changed != null){
                        changed.changeValue(register.getName());
                    }
                }
                else {
                    register = registerAllocator.getRegNotResult(arg);
                    if(register == null){
                        register = registerAllocator.getReg(arg, table,instruction);
                    }
                    register.setUsed(true);
                    if(!register.isHasValue()){
                        generator.generateLoad(arg, register);
                    }
                }
            }
            return register;
        }

        private void generatePrologueFunc(SymbolInfo funcInfo){
            command.add("    push rbp");
            command.add("    mov rbp, rsp");

            int intRegCount = 0;
            int floatRegCount = 0;
            int stackOffset = 16;
            if(funcInfo != null){
                for (String param : funcInfo.getParametrs()) {
                    SymbolInfo parametr = funcInfo.getSymbolTable().find(param);
                    if (parametr.getType().equals(TypeName.FLOAT)) {
                        if (floatRegCount < 6) {
                            // аргумент в XMM-регистре
                            Register register = argRegsFloat.get(floatRegCount);
                            register.setHasValue(true);
                            register.addVar(param);
                            parametr.changeValue(register.getName());
                            floatRegCount++;
                        } else {
                            parametr.changeValue("stack");
                            stackOffset += 8;
                        }
                    } else {
                        if (intRegCount < 6) {
                            Register register = argRegs.get(intRegCount);
                            register.setHasValue(true);
                            register.addVar(param);
                            parametr.changeValue(register.getName());
                            intRegCount++;
                        } else {
                            parametr.changeValue("stack");
                            stackOffset += 8;
                        }
                    }
                }
            }

        }

        public void printCommand(){
            for(String s: command){
                System.out.println(s);
            }

        }



        void generateGoto(Instructions instruction) {
            registerAllocator.clearReg(false, instruction, table, stackManager);
            command.add("    jmp " + instruction.getResult());
        }



        //переписать, говно))

        private void generateParam(Instructions instruction){
            argQueue.add(instruction.getResult());
        }

        public void generateCall(Instructions instruction) {
            clearRegCall(table);

            int intIndex = 0, floatIndex = 0;
            int stackArgCount = 0; // сколько аргументов в стек положили

            // Сначала загрузим аргументы
            for (int i = 0; i < argQueue.size(); i++) {
                String arg = argQueue.get(i);
                SymbolInfo info = table.find(arg);

                    if (info == null) {
                        try {
                            info = globalTable.find(valueToText(arg));
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }


                if (info == null){
                    for (Register r : registerAllocator.getRegisters()) {
                        if (r.contain(arg)) {
                            if (intIndex < argRegs.size()) {
                                Register target = argRegs.get(intIndex++);
                                if (!target.equals(r)) {
                                    command.add("    mov " + target.getName() + ", " + r.getName());
                                }
                            } else {
                                command.add("    push " + r.getName());
                                stackArgCount++;
                            }
                            break;
                        }
                    }

                    // 2. Проверяем во float-регистрах
                        for (Register r : registerAllocatorXMM.getRegisters()) {
                            if (r.contain(arg)) {
                                if (floatIndex < argRegsFloat.size()) {
                                    Register target = argRegsFloat.get(floatIndex++);
                                    if (!target.equals(r)) {
                                        command.add("    movaps " + target.getName() + ", " + r.getName());
                                    }
                                } else {
                                    command.add("    sub rsp, 8");
                                    command.add("    movq [rsp], " + r.getName());
                                    stackArgCount++;
                                }
                                break;
                            }
                        }

                    continue;
                }

                boolean isFloat = info.getType() == TypeName.FLOAT;

                // Проверяем, есть ли свободный регистр
                boolean hasReg = false;
                Register reg = null;

                if (isFloat && floatIndex < argRegsFloat.size()) {
                    reg = argRegsFloat.get(floatIndex++);
                    hasReg = true;
                } else if (!isFloat && intIndex < argRegs.size()) {
                    reg = argRegs.get(intIndex++);
                    hasReg = true;
                }

                if (hasReg) {
                    // Загружаем в регистр
                    if (isFloat) {
                        codeGenFLOAT.generateLoad(arg, reg);
                    } else {
                        codeGenINT.generateLoad(arg, reg);
                    }
                } else {
                    // Не хватает регистров — кладём в стек
                    // Для простоты считаем, что стек выравнен, и просто делаем push
                    if (isFloat) {
                        // Загрузить float в xmm0, потом положить в стек
                        Register tempXMM = registerAllocatorXMM.getReg(arg ,table, instruction);
                        codeGenFLOAT.generateLoad(arg, tempXMM);
                        command.add("    sub rsp, 8");  // резервируем место в стеке
                        command.add("    movq [rsp], " + tempXMM.getName());
                    } else {
                        // Загрузить int/boolean в temp reg, потом положить в стек
                        Register tempReg = registerAllocator.getReg(arg ,table, instruction);
                        codeGenINT.generateLoad(arg, tempReg);
                        command.add("    push " + tempReg.getName());
                    }
                    stackArgCount++;
                }
            }

            argQueue.clear(); // очистить после подготовки аргументов


            int needToAlign = stackManager.alignStackCall();
            command.add(    "sub rsp, "+needToAlign);
            // Вызов
            command.add("    call " + instruction.getArg1());

            for (Register reg : callerSaved) {
                for(String var: reg.getContains()){
                    SymbolInfo info = table.find(var);
                    if(info == null){
                        continue;
                    }
                    info.removeFrom(reg.getName());
                }
                reg.clear();
            }
            // После вызова — очистить стек (если что-то положили туда)
            if (stackArgCount > 0) {
                command.add("    add rsp, " + (stackArgCount * 8));
            }
            command.add("    add rsp, " + needToAlign);
            // Если есть возвращаемое значение, сохранить в temp переменную
            TypeName typeReturn = globalTable.findReturnType(instruction.getArg1());
            if (!typeReturn.equals(TypeName.NULL)) {
                switch (typeReturn) {
                    case FLOAT -> {
                        xmm0.clear();
                        xmm0.addVar(instruction.getResult());
                        xmm0.setHasValue(true);
                    }
                    default -> {
                        rax.clear();
                        rax.addVar(instruction.getResult());
                        rax.setHasValue(true);
                    }
                }
            }
        }

        public Register findInRegParamINT(String var){
            for(Register register: argRegs){
                if(register.contain(var)){
                    return register;
                }
            }
            return null;
        }
        public Register findInRegParamFLOAT(String var){
                for(Register register: argRegsFloat){
                if(register.contain(var)){
                    return register;
                }
            }
            return null;
        }
        public RegisterAllocator getRegisterAllocatorXMM() {
            return registerAllocatorXMM;
        }

        public void clearRegCall(SymbolTable table){
            boolean noClean = false;
            for (Register register: callerSaved){
                for (String var : register.getContains()) {
                    SymbolInfo info = table.find(var);



                    if(info == null){
                        if(argQueue.contains(var)){
                            noClean = true;
                        }
                        continue;
                    }


                    if (info.isOnlyInReg(register.getName())){
                        info.removeFrom(register.getName());
                        if(registerAllocatorXMM.isMyReg(register)){
                            codeGenFLOAT.generateStore(var, register);
                        }else {
                            codeGenINT.generateStore(var, register);
                        }
                    }
                }
                if(!noClean){
                    register.clear();
                }
            }
        }

        public void addCommand(String commanda){
            command.add(commanda);
        }

        private String valueToText(Object val) {
            double num = Double.parseDouble((String) val);
            if (num < 0) {
                return "_minus" + Math.abs(num);
            } else {
                return "_"+num;
            }
        }

        public ArrayList<String> getCommandList(){
            return command;
        }
    }
