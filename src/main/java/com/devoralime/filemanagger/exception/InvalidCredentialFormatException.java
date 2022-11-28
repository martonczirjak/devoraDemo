package com.devoralime.filemanagger.exception;

public class InvalidCredentialFormatException extends RuntimeException {
    public InvalidCredentialFormatException() {
    }

    public InvalidCredentialFormatException(String message) {
        super(message);
    }
}
