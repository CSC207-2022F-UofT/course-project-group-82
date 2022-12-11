package com.ouieat.implementation.user.methods;

import com.ouieat.OuiLogger;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.User;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.user.UserResponses;
import java.util.ArrayList;
import org.apache.logging.log4j.Level;

public interface CreateUser {
    static Response apply(UserInteractor interactor, User newUser) {
        class CheckForDuplicateUsers {

            boolean duplicatesFound;

            CheckForDuplicateUsers(UserInteractor userInteractor, User user) {
                this.duplicatesFound = checkForDuplicates(userInteractor, user);
            }

            public boolean checkForDuplicates(
                UserInteractor interactor,
                User newUser
            ) {
                ArrayList<User> users = interactor.findUserByUsername(
                    newUser.getUsername()
                );
                if (users.size() > 0) {
                    return true;
                }
                users = interactor.findUserByEmail(newUser.getEmail());
                return users.size() > 0;
            }
        }

        try {
            // Make sure the same user does not already exist
            CheckForDuplicateUsers checkForDuplicateUsers = new CheckForDuplicateUsers(
                interactor,
                newUser
            );
            if (checkForDuplicateUsers.duplicatesFound) {
                return ExceptionResponses.UserErrorResponse(
                    "User already exists"
                );
            }
            // Save the user after confirming it does not already exist
            User createdUser = interactor.save(newUser);
            if (createdUser == null) {
                return ExceptionResponses.UserErrorResponse(
                    "Unable to create user"
                );
            }

            // Send back the created users id
            OuiLogger.log(
                Level.INFO,
                "Created user with id: " + createdUser.getId()
            );
            return UserResponses.RegistrationResponse(createdUser.getId());
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Unable to create user: " + e.getMessage()
            );
            return ExceptionResponses.UnknownExceptionResponse();
        }
    }
}
