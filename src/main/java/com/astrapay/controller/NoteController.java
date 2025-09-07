package com.astrapay.controller;

import com.astrapay.dto.NoteRequest;
import com.astrapay.entity.Note;
import com.astrapay.service.NoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
@Tag(name = "Note", description = "Service Note")
public class NoteController {
    private final NoteService noteService;

    NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getAllNote() {
        return noteService.getAllNote();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable String id) {
        return noteService.getNoteById(Integer.parseInt(id));
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Note createItem(@RequestBody NoteRequest request) {
        return noteService.addNote(request);
    }

    @PutMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Note updateItem(@PathVariable String id, @RequestBody NoteRequest request) {
        return noteService.updateNote(Integer.parseInt(id), request);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteItem(@PathVariable String id) {
        noteService.deleteNote(Integer.parseInt(id));
    }
}
