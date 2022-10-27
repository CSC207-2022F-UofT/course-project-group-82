package com.ouieeat.ouieat.controllers;

import com.ouieeat.ouieat.responses.RootResponses;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

  @GetMapping("/")
  public String any() {
    return RootResponses.serviceRunning().getJsonString();
  }
}
