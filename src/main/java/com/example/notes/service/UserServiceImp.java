package com.example.notes.service;

import com.example.notes.domain.User;
import com.example.notes.dto.requests.CreateUserRequest;
import com.example.notes.dto.requests.EditUserRequest;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public interface UserServiceImp {
    Iterable<User> findAll();
    User createUser(@NotNull CreateUserRequest user);
    Optional<User> update(@NotNull EditUserRequest request, UUID userId);
    void deleteUser(UUID userId);
    Optional<User> findByUuid(UUID userId);

    Optional<User> findByLogin(String login);
}
