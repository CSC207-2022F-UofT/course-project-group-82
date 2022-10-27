package com.ouieat.responses;

public class ExceptionResponses {

  public static Response MissingRequestParametersResponse() {
    return new Response(
      "error",
      "Missing required parameters from call",
      "ControllerExceptionHandler",
      "client"
    );
  }

  public static Response ClientExceptionResponse() {
    return new Response(
      "error",
      "An unknown client error occurred",
      "ControllerExceptionHandler",
      "client"
    );
  }

  public static Response RequestMethodNotSupportedExceptionResponse() {
    return new Response(
      "error",
      "Request method is not supported for this route",
      "ControllerExceptionHandler",
      "client"
    );
  }

  public static Response ServerExceptionResponse() {
    return new Response(
      "error",
      "An unknown server error occurred",
      "ControllerExceptionHandler",
      "client"
    );
  }

  public static Response UnknownExceptionResponse() {
    return new Response(
      "error",
      "An unknown exception has occurred",
      "ControllerExceptionHandler",
      "client"
    );
  }
}
