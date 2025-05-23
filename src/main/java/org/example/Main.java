package org.example;


import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.IR.MyLangIRVisitor;
import org.example.IR.Optimizator;
import org.example.codeGen.CodeGenerator;
import org.example.error.SemanticError;
import org.example.semantic.SemanticVisitor;
import org.example.semantic.SymbolTable;
import org.example.semantic.Types.TypeFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        // Укажи путь к файлу с кодом здесь
        String filePath = "src/main/java/org/example/testFloat.txt";
        String fileName = "src/main/java/org/example/nasm/hello.asm";
        String inputCode = readFile(filePath);

        CharStream input = CharStreams.fromString(inputCode);
        MyLangLexer lexer = new MyLangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MyLangParser1 parser = new MyLangParser1(tokens);
        TypeFactory factory = new TypeFactory();
        SymbolTable globalSymbolTable = new SymbolTable(null, "global", 0, true);
        SemanticVisitor visitor = new SemanticVisitor(factory, globalSymbolTable);

        // Token token;
        //while ((token = lexer.nextToken()).getType() != Token.EOF) {
        //    System.out.println("Token: " + token.getText() + " Type: " + lexer.getVocabulary().getSymbolicName(token.getType()));
        //}

        // Парсим код
        ParseTree tree = parser.program();


        visitor.visit(tree);


               MyLangIRVisitor irVisitor = new MyLangIRVisitor(globalSymbolTable);
               irVisitor.visit(tree);
               irVisitor.setLabel();
               irVisitor.instructionsToText();
        Optimizator optimizator = new Optimizator();
        optimizator.blockConstruct(irVisitor.getInstructions());
        CodeGenerator codeGenerator = new CodeGenerator(optimizator.getBlocks(), globalSymbolTable);
        
        codeGenerator.printCommand();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String command : codeGenerator.getCommandList()) {
                writer.write(command);
                writer.newLine();
            }
            System.out.println("Файл успешно сохранён: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }


        for(SemanticError error: visitor.getErrors()){
            System.out.println(error.getLine()+" "+error.getMessage());
        }
        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("Обнаружены синтаксические ошибки!");
        } else {
            System.out.println("Парсинг успешен!");
        }
    }

    private static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}