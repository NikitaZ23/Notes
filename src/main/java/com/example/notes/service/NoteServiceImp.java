package com.example.notes.service;

import com.example.notes.domain.Note;
import com.example.notes.domain.User;
import com.example.notes.dto.requests.CreateNoteRequest;
import com.example.notes.dto.requests.UpdateNoteRequest;
import com.example.notes.exceptions.NoteNotFoundException;
import com.example.notes.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NoteServiceImp implements NoteService {

    private final NoteRepository repository;

    private final UserServiceImp userService;

    public static final String NOTE_NOT_FOUND_MESSAGE = "Note Not Found";

    public static final String USER_NOT_FOUND_MESSAGE = "User Not Found";

    @Override
    public Iterable<Note> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Note createNote(@NotNull CreateNoteRequest note) {
        User user = userService.findByUuid(note.getUser()).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));

        return repository.save(new Note(note.getHeader(), note.getText(), user));
    }

    @Override
    @Transactional
    public Optional<Note> updateNote(@NotNull UpdateNoteRequest request, UUID uuid) {
        Optional<Note> optionalNote = repository.findByUuid(uuid);

        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();

            note.setHeader(request.getHeader());
            note.setText(request.getText());

            return Optional.of(repository.save(note));
        } else
            return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteNote(UUID uuid) {
        Note note = repository.findByUuid(uuid).orElseThrow(() -> new NoteNotFoundException(NOTE_NOT_FOUND_MESSAGE));
        repository.delete(note);
    }

    @Override
    public Iterable<Note> findByUser_Id(int uuid) {
        return repository.findByUser_Id(uuid);
    }

    @Override
    public Optional<Note> findByUuid(UUID uuid) {
        return repository.findByUuid(uuid);
    }
}
