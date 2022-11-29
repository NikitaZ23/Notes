package com.example.notes.service;

import com.example.notes.common.Role;
import com.example.notes.domain.Note;
import com.example.notes.domain.User;
import com.example.notes.dto.requests.CreateUserRequest;
import com.example.notes.dto.requests.UpdateUserRequest;
import com.example.notes.exceptions.UserNotFoundException;
import com.example.notes.repository.NoteRepository;
import com.example.notes.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    UserRepository repository;

    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    UserServiceImp service;

    @Test
    @DisplayName("Проверка создания пользователя")
    public void createUserTest() {
        User user = new User("user", "user", "user", Role.User);

        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        User serviceUser = service.createUser(new CreateUserRequest("user", "user", "user", Role.User));

        assertThat(serviceUser).isEqualTo(user);
    }

    @Test
    @DisplayName("Проверка обновления репозитория")
    public void updateUserTest() {
        User user = new User("user", "user", "user", Role.User);
        User user2 = new User("user", "user2", "user2", Role.User);

        Mockito.when(repository.findByUuid(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(repository.save(Mockito.any())).thenReturn(user2);

        Optional<User> updateUser = service.updateUser(new UpdateUserRequest("user2", "user2"), UUID.randomUUID());

        assertThat(updateUser).isEqualTo(Optional.of(user2));
    }

    @Test
    @DisplayName("Проверка получения пользователя")
    public void findUserTest() {
        User user = new User("user", "user", "user", Role.User);

        Mockito.when(repository.findByUuid(Mockito.any())).thenReturn(Optional.of(user));

        Optional<User> serviceUser = service.findByUuid(UUID.randomUUID());

        assertThat(Optional.of(user)).isEqualTo(serviceUser);
    }

    @Test
    @DisplayName("Проверка получения логина пользователя")
    public void findLoginUserTest() {
        User user = new User("user", "user", "user", Role.User);

        Mockito.when(repository.findByLogin(Mockito.any())).thenReturn(Optional.of(user));

        Optional<User> serviceUser = service.findByLogin("user");

        assertThat(Optional.of(user)).isEqualTo(serviceUser);
    }

    @Test
    @DisplayName("Проверка получения всех пользователей")
    public void findAllTest() {
        User user = new User("user", "user", "user", Role.User);
        User user2 = new User("user2", "user2", "user2", Role.User);

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(user, user2));

        Iterable<User> users = service.findAll();

        assertThat(Arrays.asList(user, user2)).isEqualTo(users);
    }

    @Test
    @DisplayName("Проверка получения пустого репозитория")
    public void findAllEmptyTest() {
        Iterable<User> profiles = service.findAll();

        assertThat(profiles).isEmpty();
    }

    @Test
    @DisplayName("Проверка получения удаления пользователя")
    public void deleteProfileTest() {
        User user = new User("user", "user", "user", Role.User);

        Note note = new Note("hi", "everyone", user);
        Note note2 = new Note("hi2", "everyone", user);

        Mockito.when(repository.findByUuid(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(noteRepository.findByUser_Id(Mockito.anyInt())).thenReturn(Arrays.asList(note, note2));

        service.deleteUser(UUID.randomUUID());

        assertThat(repository.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Проверка вызова ошибки при поиске по uuid пользователя")
    public void findByUuidTestWithException() {
        Mockito.when(repository.findByUuid(Mockito.any())).thenThrow(UserNotFoundException.class);

        Throwable throwable = catchThrowable(() -> service.findByUuid(UUID.randomUUID()));

        assertThat(throwable).isInstanceOf(UserNotFoundException.class);
    }
}
