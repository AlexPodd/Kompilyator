package org.example.codeGen;

import org.example.IR.*;
import org.example.semantic.*;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;


public class CodeGenTest {

    @Test
    public void testSimpleAddition() {
        // Создаем фиктивную таблицу символов
        SymbolTable table = new SymbolTable(null, "global", 0, true);
        table.declaration("a", new SymbolInfo(TypeName.INTEGER, "a"));
        table.declaration("b", new SymbolInfo(TypeName.INTEGER, "b"));
        table.declaration("c", new SymbolInfo(TypeName.INTEGER, "c"));
        table.declaration("d", new SymbolInfo(TypeName.INTEGER, "d"));
        table.declaration("e", new SymbolInfo(TypeName.INTEGER, "e"));
        table.declaration("f", new SymbolInfo(TypeName.INTEGER, "f"));
        table.declareConstant("2", TypeName.INTEGER);
        table.declareConstant("5", TypeName.INTEGER);

        // Инструкция: c = a + b
        Instructions add = new Instructions(Operator.PLUS, "a", "b", "c", table);

        Instructions add1 = new Instructions(Operator.PLUS, "2", "5", "a", table);

        Instructions add2 = new Instructions(Operator.PLUS, "2", "5", "c", table);

        Instructions add3 = new Instructions(Operator.PLUS, "c", "a", "d", table);

        Instructions add4 = new Instructions(Operator.PLUS, "2", "b", "e", table);

        Instructions add5 = new Instructions(Operator.PLUS, "e", "d", "f", table);

        ArrayList<Instructions> instructions = new ArrayList<>();
        instructions.add(add);
        instructions.add(add1);
        instructions.add(add2);
        instructions.add(add3);
        instructions.add(add4);
        instructions.add(add5);
        Block block = new Block(new ArrayList<>(instructions));
        block.setOptimized(block);
        Block block1 = new Block(new ArrayList<>(instructions));
        block1.setOptimized(block1);

        // Генератор
        CodeGenerator codeGen = new CodeGenerator(List.of(block,block1), table);

        codeGen.printCommand();
        // Здесь ты можешь либо распечатать результат,
        // либо добавить метод вроде getCommands() и проверить, что там есть ожидаемые строки.
        // Например:
        // assertTrue(codeGen.getCommands().contains("add RAX, RBX"));
    }
}
