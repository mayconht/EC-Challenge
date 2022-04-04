package com.ecore.challenge.resource.exception;

import com.ecore.challenge.service.exception.DataIntegrityException;
import com.ecore.challenge.service.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(final ObjectNotFoundException e, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler({DataIntegrityException.class, DataIntegrityViolationException.class})
    public ResponseEntity<StandardError> dataIntegrity(final DataIntegrityException e, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis()));
    }

}
