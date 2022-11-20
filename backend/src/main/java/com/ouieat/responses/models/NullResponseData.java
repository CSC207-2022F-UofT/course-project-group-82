package com.ouieat.responses.models;

import com.ouieat.responses.ResponseData;

public class NullResponseData extends ResponseData {

    @Override
    public String getJsonString() {
        return "null";
    }
}
