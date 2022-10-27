package com.ouieeat.ouieat.controllers;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

  @GetMapping("/")
  public String any() {
    return (
      "Service is active: " +
      LocalDateTime.now() +
      " for " +
      System.currentTimeMillis() +
      "ms"
    );
  }
}
