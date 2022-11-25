package com.example.notes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundRestException extends ResponseStatusException {
    public UserNotFoundRestException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
