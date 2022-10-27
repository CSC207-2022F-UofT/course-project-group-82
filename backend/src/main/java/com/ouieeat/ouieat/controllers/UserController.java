package com.ouieeat.ouieat.controllers;

import com.ouieeat.ouieat.implementation.UserImplementation;
import com.ouieeat.ouieat.models.User;
import com.ouieeat.ouieat.repository.UserRepository;
import com.ouieeat.ouieat.requests.UserRequests;
import com.ouieeat.ouieat.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  public final UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/register")
  public String register(
    @RequestParam(value = "firstName") String firstName,
    @RequestParam(value = "lastName") String lastName,
    @RequestParam(value = "profilePictureLink") String profilePictureLink,
    @RequestParam(value = "username") String username,
    @RequestParam(value = "password") String password,
    @RequestParam(value = "email") String email
  ) {
    User newUser = new User(
      firstName,
      lastName,
      profilePictureLink,
      username,
      password,
      email
    );
    return UserRequests.doRegister(userRepository, newUser);
  }

  @GetMapping("/login")
  public String login(
    @RequestParam(value = "username") String username,
    @RequestParam(value = "password") String password
  ) {
    Response response = UserImplementation.loginUser(
      userRepository,
      username,
      password
    );
    return response.getJsonString();
  }
}
