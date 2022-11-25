package com.example.notes.service;

import com.example.notes.domain.User;
import com.example.notes.dto.requests.CreateUserRequest;
import com.example.notes.dto.requests.EditUserRequest;
import com.example.notes.exceptions.UserNotFoundException;
import com.example.notes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserServiceImp {
    public static final String USER_NOT_FOUND_MESSAGE = "User Not Found";

    private final UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUuid(UUID userId) {
        return userRepository.findByUuid(userId);
    }

    @Override
    public User createUser(@NotNull CreateUserRequest user) {
        return userRepository.save(new User(user.getLogin(), user.getPassword(), user.getUserName(), user.getIdRole()));
    }

    @Transactional
    @Override
    public Optional<User> update(@NotNull EditUserRequest request, UUID userId) {
        Optional<User> userOptional = userRepository.findByUuid(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUserName(request.getUserName());
            user.setPassword(request.getPassword());
            user.setIdRole(request.getIdRole());
            return Optional.of(userRepository.save(user));
        } else
            return Optional.empty();
    }

    @Transactional
    @Override
    public void deleteUser(UUID userId) {
        User user = userRepository.findByUuid(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));
        userRepository.delete(user);
    }
}
