package com.astrapay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCode {
    private String group;
    private String httpCode;
    private String serviceCode;
    private String caseCode;
    private String defaultMessage;
    private String message;

    @Override
    public String toString() {
        return httpCode.concat(serviceCode).concat(caseCode);
    }
}
