package com.example.notes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoteNotFoundRestException extends ResponseStatusException {
    public NoteNotFoundRestException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
