package com.astrapay.service;

import com.astrapay.dto.NoteRequest;
import com.astrapay.entity.Note;

import java.util.List;

public interface NoteService {
    List<Note> getAllNote();

    Note getNoteById(Integer id);

    Note addNote(NoteRequest request);

    Note updateNote(Integer id, NoteRequest request);

    void deleteNote(Integer id);
}
