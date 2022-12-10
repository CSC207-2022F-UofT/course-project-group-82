package com.ouieat.requests.user;

import com.ouieat.implementation.user.UserImplementation;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.User;
import com.ouieat.models.user.UserLogin;
import com.ouieat.requests.handler.FunctionalInterfaces;
import com.ouieat.requests.handler.UnauthenticatedRequest;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnauthenticatedUserRequests
    extends UnauthenticatedRequest<UserInteractor, UserImplementation> {

    public Function<User, Response> createUser = (User newUser) -> {
        // Ensure the new user object is not null
        if (newUser == null) {
            return ExceptionResponses.InvalidUserCredentialsResponse();
        }

        // Ensure the new user object has a valid email
        if (newUser.getEmail() == null || newUser.getEmail().length() == 0) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }

        // Ensure the new user object has a valid password
        if (
            newUser.getPassword() == null || newUser.getPassword().length() == 0
        ) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }

        // Ensure the new user object has a valid first name
        if (
            newUser.getFirstName() == null ||
            newUser.getFirstName().length() == 0
        ) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }

        // Ensure the new user object has a valid last name
        if (
            newUser.getLastName() == null || newUser.getLastName().length() == 0
        ) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }

        // Ensure the new user object has a valid username
        if (
            newUser.getUsername() == null || newUser.getUsername().length() == 0
        ) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }
        return implementation.createUser(newUser);
    };

    public Function<String, Response> getUsersByUsername = (String username) -> {
        if (username == null || username.length() == 0) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }
        return implementation.getUsersByUsername(username);
    };

    public Function<UserLogin, com.ouieat.responses.handler.Response> loginUser = (UserLogin userLogin) -> {
        if (userLogin == null) {
            return com.ouieat.responses.exception.ExceptionResponses.InvalidUserCredentialsResponse();
        }
        if (
            userLogin.getUsername() == null ||
            userLogin.getUsername().length() == 0
        ) {
            return com.ouieat.responses.exception.ExceptionResponses.MissingRequestParametersResponse();
        }
        if (
            userLogin.getPassword() == null ||
            userLogin.getPassword().length() == 0
        ) {
            return com.ouieat.responses.exception.ExceptionResponses.MissingRequestParametersResponse();
        }
        return implementation.loginUser(userLogin);
    };

    @Autowired
    public UnauthenticatedUserRequests(
        UserInteractor userInteractor,
        UserInteractor interactor,
        UserImplementation implementation
    ) {
        super(userInteractor, interactor, implementation);
    }
}
