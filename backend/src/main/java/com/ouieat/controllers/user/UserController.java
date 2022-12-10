package com.ouieat.controllers.user;

import com.ouieat.controllers.handler.Controller;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.UpdatedUser;
import com.ouieat.models.user.User;
import com.ouieat.models.user.UserLogin;
import com.ouieat.requests.user.AuthenticatedUserRequests;
import com.ouieat.requests.user.UnauthenticatedUserRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController
    extends Controller<UserInteractor, AuthenticatedUserRequests, UnauthenticatedUserRequests> {

    @Autowired
    public UserController(
        UserInteractor userInteractor,
        UnauthenticatedUserRequests unauthenticatedUserRequests,
        AuthenticatedUserRequests authenticatedUserRequests
    ) {
        super(
            userInteractor,
            userInteractor,
            authenticatedUserRequests,
            unauthenticatedUserRequests
        );
    }

    // Register a user with the given credentials
    @PostMapping(
        value = "/register",
        consumes = "application/json",
        produces = "application/json"
    )
    public String createUser(@RequestBody User newUser) {
        return unauthenticatedRequest
            .handle(interactor, newUser, unauthenticatedRequest.createUser)
            .getJsonString();
    }

    // Login a user with the given credentials
    @PostMapping(
        value = "/login",
        consumes = "application/json",
        produces = "application/json"
    )
    public String loginUser(@RequestBody UserLogin userLogin) {
        return unauthenticatedRequest
            .handle(interactor, userLogin, unauthenticatedRequest.loginUser)
            .getJsonString();
    }

    // Return a list of users with the given username
    @GetMapping(value = "/getUsersByUsername", produces = "application/json")
    public String getUsersByUsername(@RequestParam String username) {
        return unauthenticatedRequest
            .handle(
                interactor,
                username,
                unauthenticatedRequest.getUsersByUsername
            )
            .getJsonString();
    }

    // Update a user with an update user object
    @PostMapping(
        value = "/updateUserDetails",
        consumes = "application/json",
        produces = "application/json"
    )
    public String updateUserDetails(@RequestBody UpdatedUser updatedUser) {
        return authenticatedRequest
            .handle(
                interactor,
                updatedUser.getUserId(),
                updatedUser,
                authenticatedRequest.updateUserDetails
            )
            .getJsonString();
    }

    // Return the dashboard for a given user by id
    @GetMapping(value = "/dashboard", produces = "application/json")
    public String getDashboard(@RequestParam String userID) {
        return authenticatedRequest
            .handle(interactor, userID, authenticatedRequest.getDashboard)
            .getJsonString();
    }

    // Return a list of user previews (friends) for a given user by id
    @GetMapping(value = "/getFriends", produces = "application/json")
    public String getFriends(@RequestParam String userID) {
        return authenticatedRequest
            .handle(interactor, userID, authenticatedRequest.getFriends)
            .getJsonString();
    }

    // Remove a friend from a given user by id
    @GetMapping(value = "/removeFriend", produces = "application/json")
    public String removeFriend(
        @RequestParam String userID,
        @RequestParam String friendID
    ) {
        return authenticatedRequest
            .handle(
                interactor,
                userID,
                friendID,
                authenticatedRequest.removeFriend
            )
            .getJsonString();
    }
}
