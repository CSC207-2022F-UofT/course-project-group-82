package com.ouieat.controllers;

import com.ouieat.implementation.UserImplementation;
import com.ouieat.models.User;
import com.ouieat.models.UserLogin;
import com.ouieat.repository.UserRepository;
import com.ouieat.requests.UserRequests;
import com.ouieat.responses.Response;
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
    return UserRequests.doRegister(userRepository, newUser);
  }

  @PostMapping(
    path = "/login",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public String login(@RequestBody UserLogin userLogin) {
    Response response = UserImplementation.loginUser(
      userRepository,
      userLogin.getUsername(),
      userLogin.getPassword()
    );
    return response.getJsonString();
  }
}
