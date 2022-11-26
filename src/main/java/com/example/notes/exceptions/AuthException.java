package com.example.notes.exceptions;

import org.springframework.http.HttpStatus;

public class AuthException extends RuntimeException {

    public AuthException(HttpStatus status) {
        super(String.valueOf(status));
    }
}
