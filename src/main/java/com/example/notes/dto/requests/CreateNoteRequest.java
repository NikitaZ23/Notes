package com.example.notes.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNoteRequest {
    UUID user;
    @NotBlank(message = "Header may not be empty")
    @Size(min = 2)
    String header;
    @NotBlank(message = "Text may not be empty")
    @Size(min = 1)
    String text;
}
