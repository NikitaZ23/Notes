package com.example.notes.service;

import com.example.notes.domain.User;
import com.example.notes.dto.requests.CreateUserRequest;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public interface UserServiceImp {
    Iterable<User> findAll();
    User createUser(@NotNull CreateUserRequest user);
    Optional<User> update(@NotNull CreateUserRequest request, UUID userId);
    void deleteUser(UUID userId);
    Optional<User> findByUuid(UUID userId);
}
