package com.example.notes.service;

import com.example.notes.common.Role;
import com.example.notes.domain.Note;
import com.example.notes.domain.User;
import com.example.notes.dto.requests.CreateNoteRequest;
import com.example.notes.dto.requests.UpdateNoteRequest;
import com.example.notes.exceptions.NoteNotFoundException;
import com.example.notes.repository.NoteRepository;
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
public class NoteServiceTests {
    @Mock
    UserServiceImp userService;

    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteServiceImp service;

    @Test
    @DisplayName("Проверка создания записи")
    public void createNoteTest() {
        User user = new User("user", "user", "user", Role.User);
        Note note = new Note("hi", "everyone", user);

        Mockito.when(noteRepository.save(Mockito.any())).thenReturn(note);
        Mockito.when(userService.findByUuid(Mockito.any())).thenReturn(Optional.of(user));

        Note serviceNote = service.createNote(new CreateNoteRequest(UUID.randomUUID(), "hi", "everyone"));

        assertThat(serviceNote).isEqualTo(note);
    }

    @Test
    @DisplayName("Проверка обновления репозитория")
    public void updateNoteTest() {
        User user = new User("user", "user", "user", Role.User);
        Note note = new Note("hi", "everyone", user);
        Note note2 = new Note("hi2", "everyone2", user);

        Mockito.when(noteRepository.findByUuid(Mockito.any())).thenReturn(Optional.of(note));
        Mockito.when(noteRepository.save(Mockito.any())).thenReturn(note2);

        Optional<Note> updateNote = service.updateNote(new UpdateNoteRequest("hi2", "everyone2"), UUID.randomUUID());

        assertThat(updateNote).isEqualTo(Optional.of(note2));
    }

    @Test
    @DisplayName("Проверка получения записи")
    public void findNoteTest() {
        User user = new User("user", "user", "user", Role.User);
        Note note = new Note("hi", "everyone", user);

        Mockito.when(noteRepository.findByUuid(Mockito.any())).thenReturn(Optional.of(note));

        Optional<Note> serviceNote = service.findByUuid(UUID.randomUUID());

        assertThat(Optional.of(note)).isEqualTo(serviceNote);
    }

    @Test
    @DisplayName("Проверка получения всех записей")
    public void findAllTest() {
        User user = new User("user", "user", "user", Role.User);
        Note note = new Note("hi", "everyone", user);
        Note note2 = new Note("hi2", "everyone2", user);

        Mockito.when(noteRepository.findAll()).thenReturn(Arrays.asList(note, note2));

        Iterable<Note> notes = service.findAll();

        assertThat(Arrays.asList(note, note2)).isEqualTo(notes);
    }

    @Test
    @DisplayName("Проверка получения записей по user_id")
    public void findByUser_IdTest() {
        User user = new User("user", "user", "user", Role.User);
        Note note = new Note("hi", "everyone", user);
        Note note2 = new Note("hi2", "everyone2", user);

        Mockito.when(noteRepository.findByUser_Id(Mockito.anyInt())).thenReturn(Arrays.asList(note, note2));

        Iterable<Note> notes = service.findByUser_Id(user.getId());

        assertThat(Arrays.asList(note, note2)).isEqualTo(notes);
    }

    @Test
    @DisplayName("Проверка получения пустого репозитория")
    public void findAllEmptyTest() {
        Iterable<Note> notes = service.findAll();

        assertThat(notes).isEmpty();
    }

    @Test
    @DisplayName("Проверка получения удаления записи")
    public void deleteNoteTest() {
        User user = new User("user", "user", "user", Role.User);
        Note note = new Note("hi", "everyone", user);

        Mockito.when(noteRepository.findByUuid(Mockito.any())).thenReturn(Optional.of(note));

        service.deleteNote(UUID.randomUUID());

        assertThat(noteRepository.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Проверка вызова ошибки при поиске по uuid записи")
    public void findByUuidTestWithException() {
        Mockito.when(noteRepository.findByUuid(Mockito.any())).thenThrow(NoteNotFoundException.class);

        Throwable throwable = catchThrowable(() -> service.findByUuid(UUID.randomUUID()));

        assertThat(throwable).isInstanceOf(NoteNotFoundException.class);
    }
}