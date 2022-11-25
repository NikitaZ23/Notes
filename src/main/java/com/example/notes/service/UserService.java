package com.example.notes.service;

import com.example.notes.domain.User;
import com.example.notes.dto.requests.CreateUserRequest;
import com.example.notes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserServiceImp{
    public static final String USER_NOT_FOUND_MESSAGE = "User Not Found";

    private final UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public User createUser(@NotNull CreateUserRequest user) {
        return null;
    }

    @Override
    public Optional<User> update(@NotNull CreateUserRequest request, UUID userId) {
        return Optional.empty();
    }

    @Override
    public void deleteUser(UUID userId) {

    }

    @Override
    public Optional<User> findByUuid(UUID userId) {
        return Optional.empty();
    }
}
