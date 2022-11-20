package com.ouieat.controllers;

import com.ouieat.OuiLogger;
import com.ouieat.implementation.UserImplementation;
import com.ouieat.models.User;
import com.ouieat.models.UserCredentials;
import com.ouieat.models.UserLogin;
import com.ouieat.repository.UserRepository;
import com.ouieat.requests.UserRequests;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    public final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    @GetMapping(
        path = "/userdetails",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getUserDetails(@RequestParam String userId) {
        return UserRequests.getUserDetails(
            this.userRepository,
            UserCredentials.fromUserID(userId)
        );
    }

    @PostMapping(
        path = "/updateuserdetails",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String updateUserDetails(@RequestBody User updatedUser) {
        return UserRequests.updateUserDetails(this.userRepository, updatedUser);
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
}
