package org.example.error;

public class SemanticError extends MyError{

    public SemanticError(String message, int line, int startCharPosition, int endCharPosition) {
        super(message, line, startCharPosition, endCharPosition);
    }
}
