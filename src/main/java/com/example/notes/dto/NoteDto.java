package com.example.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class NoteDto {
    int id;
    UUID uuid;
    String userName;
    String header;
    String text;
    LocalDateTime created;
    LocalDateTime modified;
}
