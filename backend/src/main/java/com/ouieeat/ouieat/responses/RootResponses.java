package com.ouieeat.ouieat.responses;

import java.time.LocalDateTime;

public class RootResponses {

  public static Response serviceRunning() {
    String message =
      (
        "Service is active: " +
        LocalDateTime.now() +
        " for " +
        System.currentTimeMillis() +
        "ms"
      );
    return new Response("success", message, "RootController", "client");
  }
}
