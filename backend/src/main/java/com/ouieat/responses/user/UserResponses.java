package com.ouieat.responses.user;

import com.ouieat.models.user.User;
import com.ouieat.models.user.UserPreview;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.handler.ResponseData;
import java.util.ArrayList;

public class UserResponses {

    public static Response LoginResponse(String userID) {
        return new Response("success", new ResponseData<>(userID));
    }

    public static Response RegistrationResponse(String userID) {
        return new Response("success", new ResponseData<>(userID));
    }

    public static Response GetUsersByUsernameResponse(
        ArrayList<UserPreview> filteredUsers
    ) {
        return new Response("success", new ResponseData<>(filteredUsers));
    }

    public static Response GetDashboardResponse(User user) {
        return new Response(
            "success",
            new ResponseData<>(new UserPreview(user))
        );
    }

    public static Response GetFriendsResponse(ArrayList<UserPreview> friends) {
        return new Response("success", new ResponseData<>(friends));
    }

    public static Response RemoveFriendResponse() {
        return new Response("success", new ResponseData<>("removed friend"));
    }

    public static Response UpdateUserResponse() {
        return new Response("success", new ResponseData<>("Updated user"));
    }
}
