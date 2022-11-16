package com.ouieat.responses;

import com.ouieat.responses.models.ErrorResponseData;

public class ExceptionResponses {

    public static Response MissingRequestParametersResponse() {
        return new Response(
            "error",
            ErrorResponseData.withMessage(
                "Missing required parameters from call"
            ),
            "ControllerExceptionHandler",
            "client"
        );
    }

    public static Response InvalidUserCredentialsResponse() {
        return new Response(
            "error",
            ErrorResponseData.withMessage("Invalid user credentials"),
            "ControllerExceptionHandler",
            "client"
        );
    }

    public static Response ClientExceptionResponse() {
        return new Response(
            "error",
            ErrorResponseData.withMessage("An unknown client error occurred"),
            "ControllerExceptionHandler",
            "client"
        );
    }

    public static Response RequestMethodNotSupportedExceptionResponse() {
        return new Response(
            "error",
            ErrorResponseData.withMessage(
                "Request method is not supported for this route"
            ),
            "ControllerExceptionHandler",
            "client"
        );
    }

    public static Response ServerExceptionResponse() {
        return new Response(
            "error",
            ErrorResponseData.withMessage("An unknown server error occurred"),
            "ControllerExceptionHandler",
            "client"
        );
    }

    public static Response UnknownExceptionResponse() {
        return new Response(
            "error",
            ErrorResponseData.withMessage("An unknown exception has occurred"),
            "ControllerExceptionHandler",
            "client"
        );
    }

    public static Response UserErrorResponse(String message) {
        return new Response(
            "error",
            ErrorResponseData.withMessage(message),
            "ControllerExceptionHandler",
            "client"
        );
    }
}
