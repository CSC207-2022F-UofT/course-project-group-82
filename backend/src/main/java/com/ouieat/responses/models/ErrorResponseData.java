package com.ouieat.responses.models;

import com.ouieat.responses.ResponseData;

public class ErrorResponseData extends ResponseData {

    public final String errorMessage;

    private ErrorResponseData(String message) {
        this.errorMessage = message;
    }

    public static ErrorResponseData withMessage(String message) {
        return new ErrorResponseData(message);
    }
}
