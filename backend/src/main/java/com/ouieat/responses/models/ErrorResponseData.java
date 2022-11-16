package com.ouieat.responses.models;

import com.ouieat.responses.ResponseData;

public class ErrorResponseData extends ResponseData {

    public final String message;

    private ErrorResponseData(String message) {
        this.message = message;
    }

    public static ErrorResponseData withMessage(String message) {
        return new ErrorResponseData(message);
    }
}
