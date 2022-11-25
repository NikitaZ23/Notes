package com.example.notes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SuchUserExistsRestException extends ResponseStatusException {
    public SuchUserExistsRestException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
