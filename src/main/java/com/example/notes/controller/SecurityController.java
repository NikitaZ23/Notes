package com.example.notes.controller;

import com.example.notes.dto.JwtRequest;
import com.example.notes.dto.JwtResponse;
import com.example.notes.dto.RefreshJwtRequest;
import com.example.notes.service.AuthSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final AuthSecurityService authSecurityService;

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody JwtRequest jwtRequest) {
        return authSecurityService.login(jwtRequest);
    }

    @PostMapping("/access")
    public Optional<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        return authSecurityService.getAccessToken(request.getRefreshToken());
    }

    @PostMapping("/refresh")
    public JwtResponse getNewAllTokens(@RequestBody RefreshJwtRequest request) {
        return authSecurityService.refresh(request.getRefreshToken());
    }
}
