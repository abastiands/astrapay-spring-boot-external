package com.astrapay.exception;

import com.astrapay.dto.ResponseCode;
import lombok.Getter;

@Getter
public class GlobalRestApiException extends RuntimeException {
    private final transient ResponseCode responseCode;

    public GlobalRestApiException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public String getResponseCodeAsString() {
        return responseCode.getHttpCode().concat(responseCode.getServiceCode()).concat(responseCode.getCaseCode());
    }
}
