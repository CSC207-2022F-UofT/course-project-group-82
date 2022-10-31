package com.ouieat.controllers;

import com.ouieat.responses.RootResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String any() {
        return RootResponses.serviceRunning().getJsonString();
    }
}
