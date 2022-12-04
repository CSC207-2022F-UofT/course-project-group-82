package com.ouieat.controllers;

import com.ouieat.OuiLogger;
import com.ouieat.implementation.UserImplementation;
import com.ouieat.models.UpdateUser;
import com.ouieat.models.User;
import com.ouieat.models.UserCredentials;
import com.ouieat.models.UserLogin;
import com.ouieat.repository.NotificationRepository;
import com.ouieat.repository.UserRepository;
import com.ouieat.requests.UserRequests;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    public final UserRepository userRepository;
    public final NotificationRepository notificationRepository;

    @Autowired
    public UserController(
        UserRepository userRepository,
        NotificationRepository notificationRepository
    ) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    @PostMapping(
        path = "/register",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String registerPost(@RequestBody User newUser) {
        return UserRequests.doRegister(this.userRepository, newUser);
    }

    @PostMapping(
        path = "/login",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String login(@RequestBody UserLogin userLogin) {
        return UserRequests.doLogin(this.userRepository, userLogin);
    }

    @PostMapping(
        path = "/updateUserDetails",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String updateUserDetails(@RequestBody UpdateUser updatedUser) {
        return UserRequests.updateUserDetails(
            this.userRepository,
            UserCredentials.fromUserID(updatedUser.getUserId()),
            updatedUser
        );
    }

    @GetMapping(
        path = "/dashboard",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getDashboard(@RequestParam String userID) {
        return UserRequests.getDashboard(
            this.userRepository,
            UserCredentials.fromUserID(userID)
        );
    }

    @GetMapping(
        path = "/getUsersByUsername",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getUsersByUsername(
        @RequestParam String username,
        @RequestParam String userId
    ) {
        return UserRequests.getUsersByUsername(
            this.userRepository,
            this.notificationRepository,
            UserCredentials.fromUserID(userId),
            username
        );
    }
}
