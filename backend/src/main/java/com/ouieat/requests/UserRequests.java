package com.ouieat.requests;

import com.ouieat.implementation.UserImplementation;
import com.ouieat.models.RestaurantRecommendation;
import com.ouieat.models.User;
import com.ouieat.models.UserLogin;
import com.ouieat.repository.RecommendationPostingRepository;
import com.ouieat.repository.UserRepository;
import com.ouieat.responses.ExceptionResponses;
import com.ouieat.responses.Response;
import com.ouieat.responses.UserResponses;

public class UserRequests {

    public static String doRegister(
        UserRepository userRepository,
        User newUser
    ) {
        // Validate that newUser has all required properties, and they are not empty
        if (newUser == null) return ExceptionResponses
            .MissingRequestParametersResponse()
            .getJsonString();

        // Ensure no values are empty when being sent
        if (
            newUser.getUsername().length() == 0 ||
            newUser.getPassword().length() == 0 ||
            newUser.getFirstName().length() == 0 ||
            newUser.getLastName().length() == 0 ||
            newUser.getEmail().length() == 0
        ) return ExceptionResponses
            .MissingRequestParametersResponse()
            .getJsonString();

        Response response = UserImplementation.saveNewUser(
            userRepository,
            newUser
        );
        return response.getJsonString();
    }

    public static String doLogin(
        UserRepository userRepository,
        UserLogin userLogin
    ) {
        if (userLogin == null) return ExceptionResponses
            .MissingRequestParametersResponse()
            .getJsonString();

        if (
            userLogin.getPassword().length() == 0 ||
            userLogin.getUsername().length() == 0
        ) return ExceptionResponses
            .MissingRequestParametersResponse()
            .getJsonString();

        Response response = UserImplementation.loginUser(
            userRepository,
            userLogin.getUsername(),
            userLogin.getPassword()
        );
        return response.getJsonString();
    }
}
