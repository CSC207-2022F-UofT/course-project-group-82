package com.ouieat.controllers;

import com.ouieat.OuiLogger;
import com.ouieat.responses.ExceptionResponses;
import org.apache.logging.log4j.Level;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler({ MissingServletRequestParameterException.class })
  public String missingParametersException() {
    OuiLogger.log(Level.WARN, "Missing required parameters during query");
    return ExceptionResponses
      .MissingRequestParametersResponse()
      .getJsonString();
  }

  @ExceptionHandler({ HttpClientErrorException.class })
  public String clientException() {
    OuiLogger.log(Level.ERROR, "Unknown Client Error during query");
    return ExceptionResponses.ClientExceptionResponse().getJsonString();
  }

  @ExceptionHandler({ HttpServerErrorException.class })
  public String serverException() {
    OuiLogger.log(Level.ERROR, "Unknown Server Error during query");
    return ExceptionResponses.ServerExceptionResponse().getJsonString();
  }
}
