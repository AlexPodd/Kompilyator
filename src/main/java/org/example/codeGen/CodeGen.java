package org.example.codeGen;

import org.example.IR.Instructions;

public interface CodeGen {
    void generateDiv(Register result, Register reg1, Register reg2);
    void generateMul(Register result, Register reg1, Register reg2);
    void generateAdd(Register result, Register reg1, Register reg2);

    void generateSub(Register result, Register reg1, Register reg2);

    void generateIfFalse(Register r1, Register r2, Instructions instruction);

    void generateAssign(Instructions instruction);
    void generateLoad(String var, Register reg);

    void generateReturn(Register reg, String var);

    void generatePrint(Register reg, String var);

    void generateStore(String var, Register reg);

    void generateModulo(Register result, Register reg1, Register reg2);
    void generateIfTrue(Register r1, Register r2, Instructions instruction);

}
