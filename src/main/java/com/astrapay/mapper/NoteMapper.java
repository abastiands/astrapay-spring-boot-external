package com.astrapay.mapper;

import com.astrapay.dto.NoteRequest;
import com.astrapay.dto.NoteResponse;
import com.astrapay.entity.Note;

public class NoteMapper {
    public static Note createNoteMapper(NoteRequest request) {
        return Note.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .date(request.getDate())
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
