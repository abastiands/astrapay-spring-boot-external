package com.astrapay.validator;

import com.astrapay.constants.Constants;
import com.astrapay.constants.ResponseConstants;
import com.astrapay.dto.NoteResponse;
import com.astrapay.exception.GlobalRestApiException;
import org.springframework.stereotype.Component;

@Component
public class NoteValidation {
    public static void noteResponseNull(NoteResponse noteResponse) {
        if (noteResponse == null) {
            throw new GlobalRestApiException(ResponseConstants.assignServiceCode(ResponseConstants.BUSINESS_NOT_FOUND, Constants.SERVICE_CODE_NOTE), Constants.NOTE_NOT_FOUND);
        }
    }
}
