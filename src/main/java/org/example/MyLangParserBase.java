package org.example;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.example.error.ParserError;


import java.util.ArrayList;

public class MyLangParserBase extends Parser {

    private ArrayList<ParserError> errors;

    public MyLangParserBase(TokenStream input) {
        super(input);
        errors = new ArrayList<>();
    }

    @Override
    public ANTLRErrorStrategy getErrorHandler() {

        return super.getErrorHandler();
    }

    public void notifyErrorListeners(Token offendingToken, String msg, RecognitionException e) {
        int line = offendingToken.getLine();
        int charPosition = offendingToken.getCharPositionInLine();
        int stopCharPosition = charPosition + offendingToken.getText().length();
        errors.add(new ParserError("Ошибка парсера: " + msg, line, charPosition, stopCharPosition));
        super.notifyErrorListeners(offendingToken, msg, e);
    }

    public ArrayList<ParserError> getErrors() {
        return errors;
    }

    public void clearError(){
        errors.clear();
    }

    @Override
    public String[] getTokenNames() {
        return new String[0];
    }

    @Override
    public String[] getRuleNames() {
        return new String[0];
    }

    @Override
    public String getGrammarFileName() {
        return null;
    }

    @Override
    public ATN getATN() {
        return null;
    }
}
