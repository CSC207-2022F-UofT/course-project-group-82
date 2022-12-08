package com.ouieat.controllers.user;

import com.ouieat.controllers.Controller;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.UpdatedUser;
import com.ouieat.models.user.User;
import com.ouieat.models.user.UserLogin;
import com.ouieat.requests.user.AuthenticatedUserRequests;
import com.ouieat.requests.user.UnauthenticatedUserRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends Controller<UserInteractor> {

    private final UnauthenticatedUserRequests unauthenticatedUserRequests;
    private final AuthenticatedUserRequests authenticatedUserRequests;

    @Autowired
    public UserController(
        UserInteractor interactor,
        UnauthenticatedUserRequests unauthenticatedUserRequests,
        AuthenticatedUserRequests authenticatedUserRequests
    ) {
        super(interactor);
        this.unauthenticatedUserRequests = unauthenticatedUserRequests;
        this.authenticatedUserRequests = authenticatedUserRequests;
    }

    // Register a user with the given credentials
    @PostMapping(
        value = "/register",
        consumes = "application/json",
        produces = "application/json"
    )
    public String createUser(@RequestBody User newUser) {
        return unauthenticatedUserRequests
            .handle(interactor, newUser, unauthenticatedUserRequests.createUser)
            .getJsonString();
    }

    // Login a user with the given credentials
    @PostMapping(
        value = "/login",
        consumes = "application/json",
        produces = "application/json"
    )
    public String loginUser(@RequestBody UserLogin userLogin) {
        return unauthenticatedUserRequests
            .handle(
                interactor,
                userLogin,
                unauthenticatedUserRequests.loginUser
            )
            .getJsonString();
    }

    // Return a list of users with the given username
    @GetMapping(value = "/getUsersByUsername", produces = "application/json")
    public String getUsersByUsername(@RequestParam String username) {
        return unauthenticatedUserRequests
            .handle(
                interactor,
                username,
                unauthenticatedUserRequests.getUsersByUsername
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
        return authenticatedUserRequests
            .handle(
                interactor,
                updatedUser.getUserId(),
                updatedUser,
                authenticatedUserRequests.updateUserDetails
            )
            .getJsonString();
    }

    // Return the dashboard for a given user by id
    @GetMapping(value = "/dashboard", produces = "application/json")
    public String getDashboard(@RequestParam String userID) {
        return authenticatedUserRequests
            .handle(interactor, userID, authenticatedUserRequests.getDashboard)
            .getJsonString();
    }

    // Return a list of user previews (friends) for a given user by id
    @GetMapping(value = "/getFriends", produces = "application/json")
    public String getFriends(@RequestParam String userID) {
        return authenticatedUserRequests
            .handle(interactor, userID, authenticatedUserRequests.getFriends)
            .getJsonString();
    }

    // Remove a friend from a given user by id
    @GetMapping(value = "/removeFriend", produces = "application/json")
    public String removeFriend(
        @RequestParam String userID,
        @RequestParam String friendID
    ) {
        return authenticatedUserRequests
            .handle(
                interactor,
                userID,
                friendID,
                authenticatedUserRequests.removeFriend
            )
            .getJsonString();
    }

    public UnauthenticatedUserRequests getUnauthenticatedUserRequests() {
        return unauthenticatedUserRequests;
    }

    public AuthenticatedUserRequests getAuthenticatedUserRequests() {
        return authenticatedUserRequests;
    }
}
