package com.ouieat.responses.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseData<T> {

    public final T data;

    public ResponseData(@JsonProperty("data") T data) {
        this.data = data;
    }
}
