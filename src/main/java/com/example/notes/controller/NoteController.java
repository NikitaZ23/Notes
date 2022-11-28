package com.example.notes.controller;

import com.example.notes.domain.Note;
import com.example.notes.dto.NoteDto;
import com.example.notes.dto.requests.CreateNoteRequest;
import com.example.notes.dto.requests.UpdateNoteRequest;
import com.example.notes.exceptions.NoteNotFoundException;
import com.example.notes.exceptions.NoteNotFoundRestException;
import com.example.notes.mapper.NoteMapper;
import com.example.notes.service.NoteServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/users/notes/")
public class NoteController {
    NoteServiceImp noteService;

    public static final String NOTE_NOT_FOUND_MESSAGE = "Note Not Found";

    private final NoteMapper mapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('developers:read')")
    public Iterable<NoteDto> getAll() {
        return mapper.map(noteService.findAll());
    }

    @GetMapping("/{noteId}")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('developers:read')")
    public Iterable<NoteDto> findNote(@PathVariable("noteId") final int id) {
        return mapper.map(noteService.findByUser_Id(id));
    }

    @GetMapping("note/{noteId}")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('developers:read')")
    public NoteDto findNote(@PathVariable("noteId") final UUID uuid) {
        Note note = noteService.findByUuid(uuid).orElseThrow(() -> new NoteNotFoundRestException(NOTE_NOT_FOUND_MESSAGE));
        return mapper.map(note);
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('developers:read')")
    public NoteDto createNote(@Valid @RequestBody final CreateNoteRequest request) {
        return mapper.map(noteService.createNote(request));
    }

    @PutMapping("/{noteId}")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('developers:read')")
    public NoteDto updateNote(@Valid @RequestBody final UpdateNoteRequest request,
                              @PathVariable("noteId") final UUID uuid) {

        Note note = noteService.updateNote(request, uuid).orElseThrow(() -> new NoteNotFoundRestException(NOTE_NOT_FOUND_MESSAGE));
        return mapper.map(note);
    }

    @DeleteMapping("/{noteId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('developers:read')")
    public void createNote(@PathVariable("noteId") final UUID uuid) {
        try {
            noteService.deleteNote(uuid);
        } catch (NoteNotFoundException e) {
            throw new NoteNotFoundRestException(e.getMessage());
        }
    }
}
