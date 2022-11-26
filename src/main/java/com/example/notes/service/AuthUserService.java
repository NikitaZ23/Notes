package com.example.notes.service;

import com.example.notes.domain.RoleAuth;
import com.example.notes.domain.UserAuth;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final List<UserAuth> users;

    public AuthUserService() {
        this.users = List.of(
                new UserAuth("Tom", "1234", "Tom", Collections.singleton(RoleAuth.USER)),
                new UserAuth("Jerry", "5678", "Jerry", Collections.singleton(RoleAuth.ADMIN)));
    }

    public Optional<UserAuth> getByLogin(@NonNull String login) {
        return users.stream().filter(user -> login.equals(user.getLogin())).findFirst();
    }
}
