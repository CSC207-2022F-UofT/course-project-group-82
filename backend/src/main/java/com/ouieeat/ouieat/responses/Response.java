package com.ouieeat.ouieat.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouieeat.ouieat.OuiLogger;
import java.time.LocalDateTime;
import org.apache.logging.log4j.Level;

public class Response {

  // DateTime for generated response
  public String dateTime = LocalDateTime.now().toString();

  // String representation of current status
  public String status;

  // Any object data that is being passed to the response
  public String data;

  // String representation of the origin
  public String origin;

  // String representation of the destination
  public String destination;

  @JsonIgnore
  public Response(
    String status,
    String data,
    String origin,
    String destination
  ) {
    this.status = status;
    this.data = data;
    this.origin = origin;
    this.destination = destination;
  }

  // Get the JSON string representing this Response object
  @JsonIgnore
  public String getJsonString() {
    ObjectMapper mapper = new ObjectMapper();
    String mappedValue = null;
    try {
      mappedValue = mapper.writeValueAsString(this);
    } catch (StackOverflowError | JsonProcessingException e) {
      OuiLogger.log(
        Level.ERROR,
        "Could not convert response object to a json string: " + this.origin
      );
      OuiLogger.log(Level.ERROR, e.getMessage());
    }
    return mappedValue;
  }

  @Override
  @JsonIgnore
  public String toString() {
    return (
      dateTime +
      " " +
      status +
      " --- " +
      "| " +
      origin +
      " -> " +
      destination +
      " | " +
      "Data: " +
      data.toString()
    );
  }
}
