package com.ouieat.responses;

import com.ouieat.responses.models.MessageResponseData;
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
        return new Response(
            "success",
            new MessageResponseData(message),
            "RootController",
            "client"
        );
    }
}
