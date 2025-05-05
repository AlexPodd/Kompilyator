package org.example.error;

public class LexerError extends MyError{
    public LexerError(String message, int line, int startCharPosition, int endCharPosition) {
        super(message, line, startCharPosition, endCharPosition);
    }
}
