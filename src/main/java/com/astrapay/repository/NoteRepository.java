package com.astrapay.repository;

import com.astrapay.entity.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class NoteRepository {
    private final List<Note> noteList = new ArrayList<>();
    private int currentId = 0;

    public List<Note> findAll() {
        return noteList.stream()
                .sorted(Comparator.comparing(Note::getId))
                .collect(Collectors.toList());
    }

    public Optional<Note> findById(Integer id) {
        return noteList.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Note save(Note note) {
        if (note.getId() == null) {
            note.setId(++currentId);
        } else {
            noteList.removeIf(existingItem -> existingItem.getId().equals(note.getId()));
        }
        noteList.add(note);
        return note;
    }

    public void delete(Integer id) {
        noteList.removeIf(item -> item.getId().equals(id));
    }
}
