package com.ouieat.responses.exception;

import com.ouieat.responses.handler.Response;
import com.ouieat.responses.handler.ResponseData;

public class ExceptionResponses {

    public static Response MissingRequestParametersResponse() {
        return new Response(
            "error",
            new ResponseData<>("Missing request parameters")
        );
    }

    public static Response InvalidUserCredentialsResponse() {
        return new Response(
            "error",
            new ResponseData<>("Invalid user credentials")
        );
    }

    public static Response ClientExceptionResponse() {
        return new Response(
            "error",
            new ResponseData<>("An unknown client error occurred")
        );
    }

    public static Response RequestMethodNotSupportedExceptionResponse() {
        return new Response(
            "error",
            new ResponseData<>("Request method is not supported for this route")
        );
    }

    public static Response ServerExceptionResponse() {
        return new Response(
            "error",
            new ResponseData<>("An unknown server error occurred")
        );
    }

    public static Response UnknownExceptionResponse() {
        return new Response(
            "error",
            new ResponseData<>("An unknown exception has occurred")
        );
    }

    public static Response UserErrorResponse(String message) {
        return new Response("error", new ResponseData<>(message));
    }
}
