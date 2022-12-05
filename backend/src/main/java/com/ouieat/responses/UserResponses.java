package com.ouieat.responses;

import com.ouieat.models.User;
import com.ouieat.models.UserPreview;
import com.ouieat.responses.models.*;
import java.util.ArrayList;

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

    public static Response GetUsersByUsernameResponse(
        ArrayList<UserPreview> users
    ) {
        return new Response(
            "success",
            new UsersByUsernameResponseData(users),
            "getUsersByUsername",
            "client"
        );
    }

    public static Response GetFriendsResponse(ArrayList<UserPreview> friends) {
        return new Response(
            "success",
            new FriendsResponseData(friends),
            "getFriends",
            "client"
        );
    }

    public static Response RemoveFriendResponse() {
        return new Response(
            "success",
            new NullResponseData(),
            "removeFriend",
            "client"
        );
    }
}
