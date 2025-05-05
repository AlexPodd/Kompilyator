package org.example.error;

public class MyError {

    protected final String message;
    protected final int line;
    protected final int startCharPosition;
    protected final int endCharPosition;

    public MyError(String message, int line, int startCharPosition, int endCharPosition) {
        this.message = message;
        this.line = line;
        this.startCharPosition = startCharPosition;
        this.endCharPosition = endCharPosition;
    }

    public String getMessage() {
        return message;
    }

    public int getLine() {
        return line;
    }

    public int getStartCharPosition() {
        return startCharPosition;
    }

    public int getEndCharPosition() {
        return endCharPosition;
    }
}
