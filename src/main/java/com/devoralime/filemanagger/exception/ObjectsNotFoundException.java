package com.devoralime.filemanagger.exception;

public class ObjectsNotFoundException extends RuntimeException {
    public ObjectsNotFoundException() {
    }

    public ObjectsNotFoundException(String message) {
        super(message);
    }
}
