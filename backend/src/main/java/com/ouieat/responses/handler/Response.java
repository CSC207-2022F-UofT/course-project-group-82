package com.ouieat.responses.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouieat.OuiLogger;
import java.time.LocalDateTime;
import java.util.Optional;

import com.ouieat.responses.exception.ExceptionResponses;
import org.apache.logging.log4j.Level;

public class Response {

    public final String dateTime = LocalDateTime.now().toString();

    public String status;

    // String representation of the origin
    public String origin = StackWalker
        .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).walk((s) -> {
            Optional<StackWalker.StackFrame> stack = s
                .dropWhile(f -> f.getClassName().equals(Response.class.getName()) || f.getClassName().equals(ExceptionResponses.class.getName()))
                .findFirst();
            if (stack.isPresent()) {
                return stack.get().getClassName();
            } else {
                return "Unknown";
            }
            });

    // String representation of the destination

    public ResponseData<?> responseData;

    public Response(
        @JsonProperty("status") String status,
        @JsonProperty("data") ResponseData<?> data
    ) {
        this.status = status;
        this.responseData = data;
    }

    @JsonIgnore
    public String getJsonString() {
        ObjectMapper mapper = new ObjectMapper();
        String mappedValue = "";
        try {
            mappedValue = mapper.writeValueAsString(this);
        } catch (StackOverflowError | JsonProcessingException e) {
            OuiLogger.log(
                Level.ERROR,
                "Could not convert response object to a json string: " +
                this.origin
            );
            OuiLogger.log(Level.ERROR, e.getMessage());
        }
        return mappedValue;
    }
}
