package com.ouieat.implementation.user;

import com.ouieat.implementation.handler.Implementation;
import com.ouieat.implementation.user.methods.*;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.UpdatedUser;
import com.ouieat.models.user.User;
import com.ouieat.models.user.UserLogin;
import com.ouieat.responses.handler.Response;
import org.springframework.stereotype.Service;

@Service
public class UserImplementation extends Implementation<UserInteractor> {

    public UserImplementation(
        UserInteractor userInteractor,
        UserInteractor interactor
    ) {
        super(userInteractor, interactor);
    }

    // Register a new user
    public Response createUser(User newUser) {
        return CreateUser.apply(this.interactor, newUser);
    }

    // Returns a list of users with the given username
    public Response getUsersByUsername(String username) {
        return GetUsersByUsername.apply(this.interactor, username);
    }

    // Login a user with UserLogin object
    public Response loginUser(UserLogin login) {
        return LoginUser.apply(this.interactor, login);
    }

    // Returns the dashboard for a logged in user
    public Response getDashboard(User user) {
        return GetDashboard.apply(this.interactor, user);
    }

    // Returns the friends of a given user
    public Response getFriends(User user) {
        return GetFriends.apply(this.interactor, user);
    }

    // Removes a friend from a users friend list
    public Response removeFriend(User loggedInUser, User friendUser) {
        return RemoveFriend.apply(this.interactor, loggedInUser, friendUser);
    }

    // Update the user with the given updated user
    public Response updateUser(User loggedInUser, UpdatedUser updatedUser) {
        return UpdateUser.apply(this.interactor, loggedInUser, updatedUser);
    }
}
