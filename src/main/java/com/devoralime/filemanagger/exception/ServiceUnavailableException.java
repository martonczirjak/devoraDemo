package com.devoralime.filemanagger.exception;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException() {
    }

    public ServiceUnavailableException(String message) {
        super(message);
    }
}
