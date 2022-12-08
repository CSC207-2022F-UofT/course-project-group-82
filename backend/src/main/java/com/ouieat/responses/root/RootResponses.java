package com.ouieat.responses.root;

import com.ouieat.responses.handler.Response;
import com.ouieat.responses.handler.ResponseData;
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
        return new Response("success", new ResponseData<>(message));
    }
}
