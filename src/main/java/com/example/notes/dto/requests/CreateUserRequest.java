package com.example.notes.dto.requests;

import com.example.notes.common.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "Login may not be empty")
    @Size(min = 4, max = 32)
    String login;
    @NotBlank(message = "Password may not be empty")
    @Size(min = 5, max = 32)
    String password;
    @NotBlank(message = "Name may not be empty")
    @Size(min = 2, max = 32)
    String userName;
    Role idRole;
}
