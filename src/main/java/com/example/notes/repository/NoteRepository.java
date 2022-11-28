package com.example.notes.repository;

import com.example.notes.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepository extends RevisionRepository<Note, Integer, Integer>, JpaRepository<Note, Integer> {
    Optional<Note> findByUuid(UUID uuid);

    Iterable<Note> findByUser_Id(int id);
}
