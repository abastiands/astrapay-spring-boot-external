package com.astrapay.utils;

import com.astrapay.constants.Constants;

public class RestApiUtil {
    private RestApiUtil() {}

    public static String getServiceCode(String url) {
        if (url.equals("/api/v1/note")) {
            return Constants.SERVICE_CODE_NOTE;
        } else {
            return "99";
        }
    }
}
