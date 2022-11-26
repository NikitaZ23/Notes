package com.example.notes.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class JwtRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
