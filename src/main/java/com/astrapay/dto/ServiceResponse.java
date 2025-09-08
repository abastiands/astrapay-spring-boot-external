package com.astrapay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceResponse<T> {

    private T data;

    private String responseReqId;
    private String responseCode;
    private String responseDescription;
    private String responseException;
    private String dateRequest;
}
