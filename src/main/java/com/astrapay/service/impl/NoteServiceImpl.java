package com.astrapay.service.impl;

import com.astrapay.constants.Constants;
import com.astrapay.constants.ResponseConstants;
import com.astrapay.dto.NoteRequest;
import com.astrapay.entity.Note;
import com.astrapay.exception.GlobalRestApiException;
import com.astrapay.mapper.NoteMapper;
import com.astrapay.repository.NoteRepository;
import com.astrapay.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getAllNote() {
        log.info("Getting all note");

        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Integer id) {
        log.info("Getting note by id {}", id);

        return noteRepository.findById(id)
                .orElseThrow(() -> new GlobalRestApiException(ResponseConstants.assignServiceCode(ResponseConstants.BUSINESS_NOT_FOUND, Constants.SERVICE_CODE_NOTE), Constants.NOTE_NOT_FOUND));
    }

    @Override
    public Note addNote(NoteRequest request) {
        log.info("add note with request {}", request);

        return noteRepository.save(NoteMapper.createNoteMapper(request));
    }

    @Override
    public Note updateNote(Integer id, NoteRequest request) {
        log.info("updateNote id {} with request {}", id, request);

        Note existingNote = noteRepository.findById(id)
                        .orElseThrow();

        existingNote.setTitle(request.getTitle().trim());
        existingNote.setDescription(request.getDescription().trim());
        existingNote.setDate(request.getDate().trim());

        return noteRepository.save(existingNote);
    }

    @Override
    public void deleteNote(Integer id) {
        log.info("Delete note with id {}", id);

        noteRepository.findById(id)
                .orElseThrow(() -> new GlobalRestApiException(ResponseConstants.assignServiceCode(ResponseConstants.BUSINESS_NOT_FOUND, Constants.SERVICE_CODE_NOTE), Constants.NOTE_NOT_FOUND));

        noteRepository.delete(id);
    }
}
