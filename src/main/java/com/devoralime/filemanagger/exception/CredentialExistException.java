package com.devoralime.filemanagger.exception;

public class CredentialExistException extends RuntimeException {
    public CredentialExistException() {
    }

    public CredentialExistException(String message) {
        super(message);
    }
}
