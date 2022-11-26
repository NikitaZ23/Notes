package com.example.notes.controller;

import com.example.notes.dto.UserDto;
import com.example.notes.dto.requests.CreateUserRequest;
import com.example.notes.dto.requests.EditUserRequest;
import com.example.notes.exceptions.SuchUserExistsRestException;
import com.example.notes.exceptions.UserNotFoundException;
import com.example.notes.exceptions.UserNotFoundRestException;
import com.example.notes.mapper.UserMapper;
import com.example.notes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    public static final String USER_NOT_FOUND = "User not found";
    public static final String SUCH_USER_EXISTS = "Such User Exists";

    private final UserService userService;

    private final UserMapper mapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<UserDto> getAll() {
        Iterable<UserDto> map = mapper.map(userService.findAll());
        map.forEach(userDto -> System.out.println(userDto.toString()));
        return map;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto findUser(@PathVariable("userId") final UUID uuid) {
        final var user = userService.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundRestException(USER_NOT_FOUND));
        return mapper.map(user);
    }

    @GetMapping("/login/{login}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto findUserLogin(@PathVariable("login") final String login) {
        final var user = userService.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundRestException(USER_NOT_FOUND));
        return mapper.map(user);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody final CreateUserRequest request) {
        try {
            final var user = userService.createUser(request);

            return mapper.map(user);
        } catch (DataIntegrityViolationException e) {
            throw new SuchUserExistsRestException(SUCH_USER_EXISTS);
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") final UUID uuid) {
        try {
            userService.deleteUser(uuid);
        } catch (UserNotFoundException exceptions) {
            throw new UserNotFoundRestException(exceptions.getMessage());
        }
    }


    @PutMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto updateUser(@Valid @RequestBody final EditUserRequest request,
                              @PathVariable("userId") final UUID uuid) {
        final var user = userService.update(request, uuid)
                .orElseThrow(() -> new UserNotFoundRestException(USER_NOT_FOUND));

        return mapper.map(user);
    }
}
