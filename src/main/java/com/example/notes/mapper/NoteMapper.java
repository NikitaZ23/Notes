package com.example.notes.mapper;

import com.example.notes.domain.Note;
import com.example.notes.dto.NoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    @Mapping(source = "user.userName", target = "userName")
    NoteDto map(Note note);

    Iterable<NoteDto> map(Iterable<Note> notes);
}
