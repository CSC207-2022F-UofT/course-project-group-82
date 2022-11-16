package com.ouieat.responses;

public class UserResponses {

    public static Response RegistrationResponse(
        String status,
        String username
    ) {
        return new Response(status, username, "saveNewUser", "client-register");
    }

    public static Response LoginResponse(String id, String status) {
        return new Response(status, id, "loginUser", "client-login");
    }
}
