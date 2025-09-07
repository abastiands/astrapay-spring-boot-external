package com.astrapay.controller.advice;

import com.astrapay.constants.Constants;
import com.astrapay.constants.ResponseConstants;
import com.astrapay.dto.ServiceResponse;
import com.astrapay.exception.GlobalRestApiException;
import com.astrapay.utils.DayDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class GlobalRestApiExceptionHandler {
    // Exception handler bad request
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceResponse<String> exceptionHandlerBadRequest(HttpMessageNotReadableException e) {
        ServiceResponse<String> response = ServiceResponse.<String>builder()
                .data(null)
                .responseReqId(UUID.randomUUID().toString())
                .responseCode(ResponseConstants.assignServiceCode(ResponseConstants.SYSTEM_BAD_REQUEST, Constants.SERVICE_CODE_NOTE).toString())
                .responseDescription(null)
                .responseException(ResponseConstants.SYSTEM_BAD_REQUEST.getDefaultMessage())
                .dateRequest(DayDateUtil.dateNow())
                .build();

        log.error("Error response: {}", response);
        log.error(ResponseConstants.SYSTEM_BAD_REQUEST.getDefaultMessage(), e);

        return response;
    }

    // Exception handler invalid format
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceResponse<String> exceptionHandlerInvalidFormat(MethodArgumentNotValidException e) {
        String errorMessage = null;

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMessage = error.getDefaultMessage();
        }

        ServiceResponse<String> response = ServiceResponse.<String>builder()
                .data(null)
                .responseReqId(UUID.randomUUID().toString())
                .responseCode(ResponseConstants.assignServiceCode(ResponseConstants.MESSAGE_INVALID_FIELD_FORMAT, Constants.SERVICE_CODE_NOTE).toString())
                .responseDescription(null)
                .responseException(errorMessage)
                .dateRequest(DayDateUtil.dateNow())
                .build();

        log.error("Error response: {}", response);
        log.error(ResponseConstants.SYSTEM_BAD_REQUEST.getDefaultMessage(), e);

        return response;
    }

    // Exception handler not found
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceResponse<String> exceptionHandlerNotFound(NoHandlerFoundException e) {
        ServiceResponse<String> response = ServiceResponse.<String>builder()
                .data(null)
                .responseReqId(UUID.randomUUID().toString())
                .responseCode(ResponseConstants.assignServiceCode(ResponseConstants.BUSINESS_NOT_FOUND, Constants.SERVICE_CODE_NOTE).toString())
                .responseDescription(null)
                .responseException(ResponseConstants.BUSINESS_NOT_FOUND.getDefaultMessage())
                .dateRequest(DayDateUtil.dateNow())
                .build();

        log.error("Error response: {}", response);
        log.error(ResponseConstants.BUSINESS_NOT_FOUND.getDefaultMessage(), e);

        return response;
    }

    // Exception handler internal server error
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceResponse<String> exceptionHandlerInternalServerError(Exception e) {
        ServiceResponse<String> response = ServiceResponse.<String>builder()
                .data(null)
                .responseReqId(UUID.randomUUID().toString())
                .responseCode(ResponseConstants.assignServiceCode(ResponseConstants.SYSTEM_INTERNAL_SERVER_ERROR, Constants.SERVICE_CODE_NOTE).toString())
                .responseDescription(null)
                .responseException(ResponseConstants.SYSTEM_INTERNAL_SERVER_ERROR.getDefaultMessage())
                .dateRequest(DayDateUtil.dateNow())
                .build();

        log.error("Error response: {}", response);
        log.error(ResponseConstants.SYSTEM_INTERNAL_SERVER_ERROR.getDefaultMessage(), e);

        return response;
    }

    // Custom exception handler
    @ExceptionHandler(GlobalRestApiException.class)
    public ResponseEntity<ServiceResponse<String>> exceptionHandlerGlobalRestApi(GlobalRestApiException e) {
        ServiceResponse<String> response = ServiceResponse.<String>builder()
                .data(null)
                .responseReqId(UUID.randomUUID().toString())
                .responseCode(e.getResponseCodeAsString())
                .responseDescription(null)
                .responseException(responseMessageExceptionHandler(e))
                .dateRequest(DayDateUtil.dateNow())
                .build();

        ResponseEntity<ServiceResponse<String>> responseEntity = new ResponseEntity<>(response, matchHttpStatus(e.getResponseCode().getHttpCode()));

        log.error("Error response: {}", responseEntity);
        log.error("Global Exception", e);

        return responseEntity;
    }

    // Custom response message exception handler
    private String responseMessageExceptionHandler(GlobalRestApiException e) {
        if (e.getMessage() != null) {
            return e.getMessage();
        } else {
            if (e.getResponseCode().getMessage() != null) {
                return e.getResponseCode().getMessage();
            } else {
                return e.getResponseCode().getDefaultMessage();
            }
        }
    }

    // Custom http status
    private HttpStatus matchHttpStatus(String httpCode) {
        HttpStatus status;

        if (httpCode.equalsIgnoreCase("400")) {
            status = HttpStatus.BAD_REQUEST;
        } else if (httpCode.equalsIgnoreCase("404")) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return status;
    }
}
