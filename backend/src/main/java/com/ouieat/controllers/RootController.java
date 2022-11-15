package com.ouieat.controllers;

import com.ouieat.responses.RootResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String any() {
        return RootResponses.serviceRunning().getJsonString();
    }
}
