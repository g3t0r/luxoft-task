package com.janjakubowski.technical_task.exceptions;

public class FileDoesNotMatchRequirementsException extends RuntimeException {
    public FileDoesNotMatchRequirementsException(String cause) {
        super("File does not match requirements: " + cause);
    }
}
