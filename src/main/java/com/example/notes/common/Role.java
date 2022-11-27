package com.example.notes.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Role {
    User(Set.of(Permission.DEVEPELOPERS_READ)),
    Admin(Set.of(Permission.DEVEPELOPERS_WRITE, Permission.DEVEPELOPERS_READ));

    private final Set<Permission> permissionSet;

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
