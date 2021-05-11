package org.tah.service;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg) {
        super(msg);
    }

    public ValidationException(Throwable throwing) {
        super(throwing);
    }

    public ValidationException(String msg, Throwable throwing) {
        super(msg, throwing);
    }
}
