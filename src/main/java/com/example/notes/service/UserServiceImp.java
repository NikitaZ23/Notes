package com.example.notes.service;

import com.example.notes.domain.User;
import com.example.notes.dto.requests.CreateUserRequest;
import com.example.notes.dto.requests.UpdateUserRequest;
import com.example.notes.exceptions.UserNotFoundException;
import com.example.notes.repository.NoteRepository;
import com.example.notes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    public static final String USER_NOT_FOUND_MESSAGE = "User Not Found";

    private final UserRepository userRepository;

    private final NoteRepository noteRepository;

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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        return userRepository.save(new User(user.getLogin(), passwordEncoder.encode(user.getPassword()), user.getUserName(), user.getIdRole()));
    }

    @Transactional
    @Override
    public Optional<User> updateUser(@NotNull UpdateUserRequest request, UUID userId) {
        Optional<User> userOptional = userRepository.findByUuid(userId);
        if (userOptional.isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

            User user = userOptional.get();
            user.setUserName(request.getUserName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            return Optional.of(userRepository.save(user));
        } else
            return Optional.empty();
    }

    @Transactional
    @Override
    public void deleteUser(UUID userId) {
        User user = userRepository.findByUuid(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));
        noteRepository.findByUser_Id(user.getId()).forEach(note -> noteRepository.delete(note));
        userRepository.delete(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public long getCountRepository() {
        return userRepository.count();
    }
}
