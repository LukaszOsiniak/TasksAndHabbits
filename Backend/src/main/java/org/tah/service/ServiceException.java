package org.tah.service;

public class ServiceException extends RuntimeException {
    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(Throwable throwing) {
        super(throwing);
    }

    public ServiceException(String msg, Throwable throwing) {
        super(msg, throwing);
    }
}
