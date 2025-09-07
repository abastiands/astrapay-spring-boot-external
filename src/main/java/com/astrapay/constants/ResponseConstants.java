package com.astrapay.constants;

import com.astrapay.dto.ResponseCode;

public final class ResponseConstants {
    private ResponseConstants() {}

    // List response group
    private static final String GROUP_SUCCESS = "Success";
    private static final String GROUP_BUSINESS = "Business";
    private static final String GROUP_SYSTEM = "System";
    private static final String GROUP_MESSAGE = "Message";

    // List response code
    public static final ResponseCode SUCCESS = ResponseCode.builder()
            .group(GROUP_SUCCESS)
            .httpCode("200")
            .serviceCode("00")
            .caseCode("00")
            .defaultMessage(GROUP_SUCCESS)
            .build();

    public static final ResponseCode SYSTEM_BAD_REQUEST = ResponseCode.builder()
            .group(GROUP_SYSTEM)
            .httpCode("400")
            .serviceCode("99")
            .caseCode("00")
            .defaultMessage("Bad Request")
            .build();

    public static final ResponseCode MESSAGE_INVALID_FIELD_FORMAT = ResponseCode.builder()
            .group(GROUP_MESSAGE)
            .httpCode("400")
            .serviceCode("99")
            .caseCode("01")
            .defaultMessage("Invalid Field Format")
            .build();

    public static final ResponseCode BUSINESS_NOT_FOUND = ResponseCode.builder()
            .group(GROUP_BUSINESS)
            .httpCode("404")
            .serviceCode("99")
            .caseCode("00")
            .defaultMessage("Not Found")
            .build();

    public static final ResponseCode SYSTEM_INTERNAL_SERVER_ERROR = ResponseCode.builder()
            .group(GROUP_SYSTEM)
            .httpCode("500")
            .serviceCode("99")
            .caseCode("00")
            .defaultMessage("Internal Server Error")
            .build();

    // Custom service code
    public static ResponseCode assignServiceCode(ResponseCode responseCode, String serviceCode) {
        responseCode.setServiceCode(serviceCode);
        return responseCode;
    }
}
