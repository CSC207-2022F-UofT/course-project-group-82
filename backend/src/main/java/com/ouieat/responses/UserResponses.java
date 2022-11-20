package com.ouieat.responses;

import com.ouieat.models.User;
import com.ouieat.responses.models.DashboardResponseData;
import com.ouieat.responses.models.LoginResponseData;
import com.ouieat.responses.models.NullResponseData;
import com.ouieat.responses.models.UserDetailResponseData;

public class UserResponses {

    public static Response RegistrationResponse(
        String status,
        String username
    ) {
        return new Response(
            status,
            new NullResponseData(),
            "saveNewUser",
            "client-register"
        );
    }

    public static Response LoginResponse(String id) {
        return new Response(
            "success",
            new LoginResponseData(id),
            "loginUser",
            "client-login"
        );
    }

    public static Response DashboardResponse(User user) {
        return new Response(
            "success",
            new DashboardResponseData(user),
            "dashboard",
            "client"
        );
    }

    public static Response UserDetailsResponse(User user) {
        return new Response(
            "success",
            new UserDetailResponseData(user),
            "userdetails",
            "client"
        );
    }

    public static Response UpdateUserDetailsResponse(User user) {
        return new Response(
            "success",
            new UserDetailResponseData(user),
            "updateuserdetails",
            "client"
        );
    }
}
