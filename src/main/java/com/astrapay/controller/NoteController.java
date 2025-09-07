package com.astrapay.controller;

import com.astrapay.constants.Constants;
import com.astrapay.constants.ResponseConstants;
import com.astrapay.dto.NoteRequest;
import com.astrapay.dto.NoteResponse;
import com.astrapay.dto.ServiceResponse;
import com.astrapay.exception.GlobalRestApiException;
import com.astrapay.helpers.NoteHelper;
import com.astrapay.mapper.NoteMapper;
import com.astrapay.service.NoteService;
import com.astrapay.utils.DayDateUtil;
import com.astrapay.validator.NoteValidation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/note")
@Tag(name = "Note", description = "Service Note")
public class NoteController {
    private final NoteService noteService;

    NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ServiceResponse<List<NoteResponse>> getAllNote() {
        List<NoteResponse> noteResponseList = NoteHelper.generateAllNotes(noteService.getAllNote());
//
        if (noteResponseList.isEmpty()) {
            throw new GlobalRestApiException(ResponseConstants.assignServiceCode(ResponseConstants.BUSINESS_NOT_FOUND, Constants.SERVICE_CODE_NOTE), Constants.NOTE_NOT_FOUND);
        }

        return ServiceResponse.<List<NoteResponse>>builder()
                .data(noteResponseList)
                .responseReqId(UUID.randomUUID().toString())
                .responseCode(ResponseConstants.assignServiceCode(ResponseConstants.SUCCESS, Constants.SERVICE_CODE_NOTE).toString())
                .responseException(null)
                .dateRequest(DayDateUtil.dateNow())
                .build();
    }

    @GetMapping("/{id}")
    public ServiceResponse<NoteResponse> getNoteById(@Valid @PathVariable String id) {
        NoteResponse noteResponse = NoteMapper.createNoteResponseMapper(noteService.getNoteById(Integer.parseInt(id)));

        NoteValidation.noteResponseNull(noteResponse);

        return ServiceResponse.<NoteResponse>builder()
                .data(noteResponse)
                .responseReqId(UUID.randomUUID().toString())
                .responseCode(ResponseConstants.assignServiceCode(ResponseConstants.SUCCESS, Constants.SERVICE_CODE_NOTE).toString())
                .responseException(null)
                .dateRequest(DayDateUtil.dateNow())
                .build();
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ServiceResponse<NoteResponse> createItem(@Valid @RequestBody NoteRequest request) {
        NoteResponse noteResponse = NoteMapper.createNoteResponseMapper(noteService.addNote(request));

        NoteValidation.noteResponseNull(noteResponse);

        return ServiceResponse.<NoteResponse>builder()
                .data(noteResponse)
                .responseReqId(UUID.randomUUID().toString())
                .responseCode(ResponseConstants.assignServiceCode(ResponseConstants.SUCCESS, Constants.SERVICE_CODE_NOTE).toString())
                .responseException(null)
                .dateRequest(DayDateUtil.dateNow())
                .build();
    }

    @PutMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ServiceResponse<NoteResponse> updateItem(@Valid @PathVariable String id, @RequestBody NoteRequest request) {
        NoteResponse noteResponse = NoteMapper.createNoteResponseMapper(noteService.updateNote(Integer.parseInt(id), request));

        NoteValidation.noteResponseNull(noteResponse);

        return ServiceResponse.<NoteResponse>builder()
                .data(noteResponse)
                .responseReqId(UUID.randomUUID().toString())
                .responseCode(ResponseConstants.assignServiceCode(ResponseConstants.SUCCESS, Constants.SERVICE_CODE_NOTE).toString())
                .responseException(null)
                .dateRequest(DayDateUtil.dateNow())
                .build();
    }

    @DeleteMapping(path = "/{id}")
    public ServiceResponse<String> deleteItem(@Valid @PathVariable String id) {
        noteService.deleteNote(Integer.parseInt(id));

        return ServiceResponse.<String>builder()
                .data(Constants.SUCCESS_DELETE_DATA)
                .responseReqId(UUID.randomUUID().toString())
                .responseCode(ResponseConstants.assignServiceCode(ResponseConstants.SUCCESS, Constants.SERVICE_CODE_NOTE).toString())
                .responseException(null)
                .dateRequest(DayDateUtil.dateNow())
                .build();
    }
}
