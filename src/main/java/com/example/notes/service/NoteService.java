package com.example.notes.service;

import com.example.notes.domain.Note;
import com.example.notes.dto.requests.CreateNoteRequest;
import com.example.notes.dto.requests.UpdateNoteRequest;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public interface NoteService {
    Iterable<Note> findAll();

    Note createNote(@NotNull CreateNoteRequest note);

    Optional<Note> updateNote(@NotNull UpdateNoteRequest request, UUID uuid);

    void deleteNote(UUID uuid);

    Iterable<Note> findByUser_Id(int uuid);

    Optional<Note> findByUuid(UUID uuid);
}
