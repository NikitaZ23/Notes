package com.example.notes.repository;

import com.example.notes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUuid(UUID uuid);

    Optional<User> findByLogin(String login);
}
