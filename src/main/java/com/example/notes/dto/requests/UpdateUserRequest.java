package com.example.notes.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @NotBlank(message = "Password may not be empty")
    @Size(min = 5, max = 32)
    String password;
    @NotBlank(message = "Name may not be empty")
    @Size(min = 2, max = 32)
    String userName;
}
