package com.astrapay.mapper;

import com.astrapay.dto.NoteRequest;
import com.astrapay.entity.Note;

public class NoteMapper {
    public static Note createNoteMapper(NoteRequest request) {
        return Note.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .date(request.getDate())
                .build();
    }
}
