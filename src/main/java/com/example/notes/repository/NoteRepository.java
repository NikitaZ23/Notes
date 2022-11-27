package com.example.notes.repository;

import com.example.notes.domain.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {
    Optional<Note> findByUuid(UUID uuid);

    Iterable<Note> findByUser_Id(int id);
}
