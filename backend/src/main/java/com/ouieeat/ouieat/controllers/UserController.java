package com.ouieeat.ouieat.controllers;

import com.ouieeat.ouieat.implementation.UserImplementation;
import com.ouieeat.ouieat.models.User;
import com.ouieeat.ouieat.models.UserLogin;
import com.ouieeat.ouieat.repository.UserRepository;
import com.ouieeat.ouieat.requests.UserRequests;
import com.ouieeat.ouieat.responses.Response;
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
