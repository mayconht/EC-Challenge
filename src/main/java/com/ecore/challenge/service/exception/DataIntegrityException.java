package com.ecore.challenge.service.exception;

public class DataIntegrityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataIntegrityException(final String message) {
        super(message);
    }

}
