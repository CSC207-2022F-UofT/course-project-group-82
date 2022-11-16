package com.ouieat.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouieat.OuiLogger;
import java.util.Arrays;
import org.apache.logging.log4j.Level;

public abstract class ResponseData {

    @JsonIgnore
    public String getJsonString() {
        return null;
    }
}
