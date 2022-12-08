package com.ouieat.implementation.user.methods;

import com.ouieat.OuiLogger;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.User;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.user.UserResponses;
import org.apache.logging.log4j.Level;

public interface RemoveFriend {
    static Response apply(
        UserInteractor interactor,
        User loggedInUser,
        User friendUser
    ) {
        try {
            // Make sure the friend is in the users friend list
            if (!loggedInUser.getFriendIds().contains(friendUser.getId())) {
                return ExceptionResponses.UserErrorResponse(
                    "User is not a friend"
                );
            }

            // Remove the friend from the users friend list
            loggedInUser.getFriendIds().remove(friendUser.getId());
            interactor.save(loggedInUser);

            // Remove the user from the friends friend list
            friendUser.getFriendIds().remove(loggedInUser.getId());
            interactor.save(friendUser);

            return UserResponses.RemoveFriendResponse();
        } catch (Exception e) {
            OuiLogger.log(Level.ERROR, "Unable to remove friend: " + e);
            return ExceptionResponses.UnknownExceptionResponse();
        }
    }
}
