package com.ouieat.implementation.user.methods;

import com.ouieat.OuiLogger;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.User;
import com.ouieat.models.user.UserLogin;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import java.util.ArrayList;
import org.apache.logging.log4j.Level;

public interface LoginUser {
    static Response apply(UserInteractor interactor, UserLogin login) {
        try {
            ArrayList<User> user = interactor.findUserByUsernameAndPassword(
                login.getUsername(),
                login.getPassword()
            );
            switch (user.size()) {
                case 0:
                    return ExceptionResponses.UserErrorResponse(
                        "Invalid username or password"
                    );
                case 1:
                    return com.ouieat.responses.user.UserResponses.LoginResponse(
                        user.get(0).getId()
                    );
                default:
                    OuiLogger.log(
                        Level.ERROR,
                        "Multiple users with the same username and password"
                    );
                    return ExceptionResponses.UserErrorResponse(
                        "Multiple users with same username and password"
                    );
            }
        } catch (Exception e) {
            OuiLogger.log(Level.ERROR, "Unable to login user: " + e);
            return ExceptionResponses.UnknownExceptionResponse();
        }
    }
}
