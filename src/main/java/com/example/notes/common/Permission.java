package com.example.notes.common;

public enum Permission {
    DEVEPELOPERS_READ("developers:read"),
    DEVEPELOPERS_WRITE("developers:write");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
