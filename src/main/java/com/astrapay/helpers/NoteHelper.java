package com.astrapay.helpers;

import com.astrapay.dto.NoteResponse;
import com.astrapay.entity.Note;
import com.astrapay.mapper.NoteMapper;

import java.util.ArrayList;
import java.util.List;

public class NoteHelper {
    public static List<NoteResponse> generateAllNotes(List<Note> noteList){
        List<NoteResponse> noteResponseList = new ArrayList<>();

        for(Note note : noteList){
            NoteResponse noteResponse = NoteMapper.createNoteResponseMapper(note);

            noteResponseList.add(noteResponse);
        }
        return noteResponseList;
    }
}
