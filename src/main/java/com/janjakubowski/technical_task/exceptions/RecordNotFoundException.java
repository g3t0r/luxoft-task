package com.janjakubowski.technical_task.exceptions;

public class RecordNotFoundException extends Exception {
    public RecordNotFoundException(String primaryKey) {
        super("Can't find record with primary key: " + primaryKey);
    }
}
