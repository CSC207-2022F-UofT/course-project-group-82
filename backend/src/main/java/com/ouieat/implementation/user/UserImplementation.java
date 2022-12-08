package com.ouieat.implementation.user;

import com.ouieat.implementation.user.methods.*;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.UpdatedUser;
import com.ouieat.models.user.User;
import com.ouieat.models.user.UserLogin;
import com.ouieat.responses.handler.Response;

public class UserImplementation {

    // Register a new user
    public static Response createUser(UserInteractor interactor, User newUser) {
        return CreateUser.apply(interactor, newUser);
    }

    // Returns a list of users with the given username
    public static Response getUsersByUsername(
        UserInteractor interactor,
        String username
    ) {
        return GetUsersByUsername.apply(interactor, username);
    }

    // Login a user with UserLogin object
    public static Response loginUser(
        UserInteractor interactor,
        UserLogin login
    ) {
        return LoginUser.apply(interactor, login);
    }

    // Returns the dashboard for a logged in user
    public static Response getDashboard(UserInteractor interactor, User user) {
        return GetDashboard.apply(interactor, user);
    }

    // Returns the friends of a given user
    public static Response getFriends(UserInteractor interactor, User user) {
        return GetFriends.apply(interactor, user);
    }

    // Removes a friend from a users friend list
    public static Response removeFriend(
        UserInteractor interactor,
        User loggedInUser,
        User friendUser
    ) {
        return RemoveFriend.apply(interactor, loggedInUser, friendUser);
    }

    // Update the user with the given updated user
    public static Response updateUser(
        UserInteractor interactor,
        User loggedInUser,
        UpdatedUser updatedUser
    ) {
        return UpdateUser.apply(interactor, loggedInUser, updatedUser);
    }
}
