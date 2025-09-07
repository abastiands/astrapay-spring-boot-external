package com.astrapay.service.impl;

import com.astrapay.constants.Constants;
import com.astrapay.constants.ResponseConstants;
import com.astrapay.dto.NoteRequest;
import com.astrapay.entity.Note;
import com.astrapay.exception.GlobalRestApiException;
import com.astrapay.mapper.NoteMapper;
import com.astrapay.repository.NoteRepository;
import com.astrapay.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getAllNote() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Integer id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new GlobalRestApiException(ResponseConstants.assignServiceCode(ResponseConstants.BUSINESS_NOT_FOUND, Constants.SERVICE_CODE_NOTE), ResponseConstants.BUSINESS_NOT_FOUND.getDefaultMessage()));
    }

    @Override
    public Note addNote(NoteRequest request) {
        return noteRepository.save(NoteMapper.createNoteMapper(request));
    }

    @Override
    public Note updateNote(Integer id, NoteRequest request) {
        Note existingNote = noteRepository.findById(id)
                        .orElseThrow();

        existingNote.setTitle(request.getTitle());
        existingNote.setDescription(request.getDescription());
        existingNote.setDate(request.getDate());

        return noteRepository.save(existingNote);
    }

    @Override
    public void deleteNote(Integer id) {
        noteRepository.delete(id);
    }
}
