package com.example.notes.repository;

import com.example.notes.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUuid(UUID uuid);

    Optional<User> findByLogin(String login);
}
