package com.ouieat.implementation.user.methods;

import com.ouieat.OuiLogger;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.UpdatedUser;
import com.ouieat.models.user.User;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.user.UserResponses;
import org.apache.logging.log4j.Level;

public interface UpdateUser {
    static Response apply(
        UserInteractor interactor,
        User loggedInUser,
        UpdatedUser updatedUser
    ) {
        try {
            if (updatedUser.email != null) {
                loggedInUser.setEmail(updatedUser.email);
            }

            if (updatedUser.username != null) {
                loggedInUser.setUsername(updatedUser.username);
            }

            if (updatedUser.profilePictureLink != null) {
                loggedInUser.setPassword(updatedUser.profilePictureLink);
            }

            if (updatedUser.firstName != null) {
                loggedInUser.setFirstName(updatedUser.firstName);
            }

            if (updatedUser.lastName != null) {
                loggedInUser.setLastName(updatedUser.lastName);
            }

            interactor.save(loggedInUser);
            return UserResponses.UpdateUserResponse();
        } catch (Exception e) {
            OuiLogger.log(Level.ERROR, "Unable to update user: " + e);
            return ExceptionResponses.UnknownExceptionResponse();
        }
    }
}
