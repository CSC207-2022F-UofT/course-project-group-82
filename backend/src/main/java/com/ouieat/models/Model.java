package com.ouieat.models;

import org.springframework.data.annotation.Id;

public abstract class Model {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
