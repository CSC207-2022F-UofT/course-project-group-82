package com.ouieeat.ouieat.requests;

import com.ouieeat.ouieat.implementation.UserImplementation;
import com.ouieeat.ouieat.models.User;
import com.ouieeat.ouieat.repository.UserRepository;
import com.ouieeat.ouieat.responses.Response;

public class UserRequests {

  public static String doRegister(UserRepository userRepository, User newUser) {
    Response response = UserImplementation.saveNewUser(userRepository, newUser);
    return response.getJsonString();
  }
}
