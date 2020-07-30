package com.janjakubowski.technical_task.exceptions;

public class IncorrectDateFormatException extends RuntimeException {
    public IncorrectDateFormatException(String message) {
        super(message);
    }
}
