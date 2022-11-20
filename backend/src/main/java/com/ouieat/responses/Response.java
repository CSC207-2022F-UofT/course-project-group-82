package com.ouieat.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouieat.OuiLogger;
import java.time.LocalDateTime;
import org.apache.logging.log4j.Level;

public class Response {

    // DateTime for generated response
    public String dateTime = LocalDateTime.now().toString();

    // String representation of current status
    public String status;

    // Any object data that is being passed to the response
    @JsonIgnore
    private final ResponseData responseData;

    // String representation of the origin
    public String origin;

    // String representation of the destination
    public String destination;

    @JsonIgnore
    public Response(
        String status,
        ResponseData data,
        String origin,
        String destination
    ) {
        this.status = status;
        this.responseData = data;
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * Return this.responseData as a JSON string.
     */
    private String serializeResponseData(ResponseData data) {
        if (data.getJsonString() != null) {
            return data.getJsonString();
        }

        ObjectMapper mapper = new ObjectMapper();
        String mappedValue = null;

        try {
            mappedValue = mapper.writeValueAsString(data);
        } catch (StackOverflowError | JsonProcessingException e) {
            OuiLogger.log(
                Level.ERROR,
                "Could not convert response data object to a JSON string"
            );
            OuiLogger.log(Level.ERROR, e.getMessage());
        }

        return mappedValue;
    }

    // Get the JSON string representing this Response object
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

        // HACK: insert "data" property manually by stripping trailing "}" and re-adding it.
        assert mappedValue.endsWith("}");
        mappedValue = mappedValue.substring(0, mappedValue.length() - 1);
        mappedValue +=
            ",\"data\":" + this.serializeResponseData(this.responseData) + "}";

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
            responseData.toString()
        );
    }
}
