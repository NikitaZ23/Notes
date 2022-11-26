package com.example.notes.service;

import com.example.notes.domain.JwtAuthentication;
import com.example.notes.domain.UserAuth;
import com.example.notes.dto.JwtRequest;
import com.example.notes.dto.JwtResponse;
import com.example.notes.exceptions.AuthException;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthSecurityService {

    private final AuthUserService userService;
    private final JwtProvider jwtProvider;
    private final RefreshService refreshService;

    public JwtResponse login(@NonNull JwtRequest jwtRequest) {
        UserAuth user = userService.getByLogin(jwtRequest.getLogin())
                .orElseThrow(() -> new AuthException(HttpStatus.UNAUTHORIZED));
        if (user.getPassword().equals(jwtRequest.getPassword())) {
            String accessToken = jwtProvider.generateAccessToken(user);
            String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshService.save(user.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException(HttpStatus.FORBIDDEN);
        }
    }

    public Optional<JwtResponse> getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String savedRefreshToken = refreshService.load(login);
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                UserAuth user = userService.getByLogin(login)
                        .orElseThrow(() -> new AuthException(HttpStatus.UNAUTHORIZED));
                String newAccessToken = jwtProvider.generateAccessToken(user);
                return Optional.of(new JwtResponse(newAccessToken, null));
            }
        }
        return Optional.ofNullable(new JwtResponse(null, null));
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String savedRefreshToken = refreshService.load(login);
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                UserAuth user = userService.getByLogin(login)
                        .orElseThrow(() -> new AuthException(HttpStatus.UNAUTHORIZED));
                String accessToken = jwtProvider.generateAccessToken(user);
                String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshService.save(user.getLogin(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException(HttpStatus.FORBIDDEN);
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
