package com.example.notes.dto;

import com.example.notes.common.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDto {
    int id;
    UUID uuid;
    String login;
    String password;
    String userName;
    Role idRole;
    LocalDateTime created;
    LocalDateTime modified;
}
