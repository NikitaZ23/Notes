package com.example.notes.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permission {
    DEVEPELOPERS_READ("developers:read"),
    DEVEPELOPERS_WRITE("developers:write");

    private final String permission;
}
