package org.example.error;

public class ParserError extends MyError{


    public ParserError(String message, int line, int startCharPosition, int endCharPosition) {
        super(message, line, startCharPosition, endCharPosition);
    }
}
