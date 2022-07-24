package com.challenge.marketplace.exception;

public class IllegalArithmeticOperationException extends RuntimeException {
    public IllegalArithmeticOperationException(String errorMessage) {
        super(errorMessage);
    }
}
