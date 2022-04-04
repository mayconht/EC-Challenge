package com.ecore.challenge.service.exception;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(final String message) {
        super(message);
    }
}
