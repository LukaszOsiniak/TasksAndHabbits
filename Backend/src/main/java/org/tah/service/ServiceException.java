package org.tah.service;

public class ServiceException extends RuntimeException {
    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable throwing) {
        super(msg, throwing);
    }
}
