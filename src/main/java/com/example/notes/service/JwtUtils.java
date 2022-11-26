package com.example.notes.service;

import com.example.notes.domain.JwtAuthentication;
import com.example.notes.domain.RoleAuth;
import io.jsonwebtoken.Claims;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setFirstName(claims.get("userName", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<RoleAuth> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        return roles.stream().map(RoleAuth::valueOf).collect(Collectors.toSet());
    }
}
