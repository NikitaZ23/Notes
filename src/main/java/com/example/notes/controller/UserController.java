package com.example.notes.controller;

import com.example.notes.dto.UserDto;
import com.example.notes.mapper.UserMapper;
import com.example.notes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    public static final String USER_NOT_FOUND = "User not found";

    private final UserService userService;

    private final UserMapper mapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<UserDto> getAll() {
        return mapper.map(userService.findAll());
    }
//
//    @GetMapping("/{profileId}")
//    @ResponseStatus(code = HttpStatus.OK)
//    public ProfileDto findProfile(@PathVariable("profileId") final UUID uuid) {
//        final var profile = profileService.findProfile(uuid)
//                .orElseThrow(() -> new ProfileNotFoundRestExceptions(PROFILE_NOT_FOUND));
//        return mapper.map(profile);
//    }
//
//    @DeleteMapping("/{profileId}")
//    @ResponseStatus(code = HttpStatus.NO_CONTENT)
//    public void deleteProfile(@PathVariable("profileId") final UUID uuid) {
//        try {
//            profileService.deleteProfile(uuid);
//        } catch (ProfileNotFoundExceptions exceptions) {
//            throw new ProfileNotFoundRestExceptions(exceptions.getMessage());
//        }
//    }
//
//    @PostMapping
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public ProfileDto createProfile(@Valid @RequestBody final CreateProfileRequests request) {
//        return mapper.map(profileService.createProfile(request));
//    }
//
//    @PutMapping("/{profileId}")
//    @ResponseStatus(code = HttpStatus.OK)
//    public ProfileDto updateProfile(@Valid @RequestBody final CreateProfileRequests request,
//                                    @PathVariable("profileId") final UUID uuid) {
//        final var profile = profileService.updateProfile(request, uuid)
//                .orElseThrow(() -> new ProfileNotFoundRestExceptions(PROFILE_NOT_FOUND));
//
//        return mapper.map(profile);
//    }
}
