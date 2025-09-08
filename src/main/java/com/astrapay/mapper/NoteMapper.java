package com.astrapay.mapper;

import com.astrapay.dto.NoteRequest;
import com.astrapay.dto.NoteResponse;
import com.astrapay.entity.Note;

public class NoteMapper {
    public static Note createNoteMapper(NoteRequest request) {
        return Note.builder()
                .title(request.getTitle().trim())
                .description(request.getDescription().trim())
                .date(request.getDate().trim())
                .build();
    }

    public static NoteResponse createNoteResponseMapper(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .description(note.getDescription())
                .date(note.getDate())
                .build();
    }
}
