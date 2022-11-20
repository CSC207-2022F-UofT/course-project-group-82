package com.ouieat.responses.models;

import com.ouieat.responses.ResponseData;

public class MessageResponseData extends ResponseData {

    public final String message;

    public MessageResponseData(String message) {
        this.message = message;
    }
}
