package com.alticci.sequence.error;

public class NegativeIndexException extends RuntimeException {
    public NegativeIndexException(String message) {
        super(message);
    }
}
