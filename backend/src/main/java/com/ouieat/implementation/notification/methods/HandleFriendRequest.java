package com.ouieat.implementation.notification.methods;

import com.ouieat.OuiLogger;
import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.notification.Notification;
import com.ouieat.models.user.User;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.notification.NotificationResponse;
import org.apache.logging.log4j.Level;

public interface HandleFriendRequest {
    static Response apply(
        UserInteractor userInteractor,
        NotificationInteractor interactor,
        User loggedInUser,
        Notification toDelete,
        Boolean accept
    ) {
        try {
            if (accept) {
                // Ensure that the user has not already accepted the friend request
                if (
                    loggedInUser
                        .getFriendIds()
                        .contains(toDelete.getRecipientId())
                ) {
                    return ExceptionResponses.ClientExceptionResponse();
                }

                // Add the friend to the user's friend list
                loggedInUser.getFriendIds().add(toDelete.getRecipientId());
                userInteractor.save(loggedInUser);

                // Find the sender of the friend request
                User sender = userInteractor.findById(
                    toDelete.getRecipientId()
                );

                // Add the user to the sender's friend list
                sender.getFriendIds().add(loggedInUser.getId());
                userInteractor.save(sender);
            } else {
                // Ensure that the user has not already rejected the friend request
                if (
                    loggedInUser
                        .getFriendIds()
                        .contains(toDelete.getRecipientId())
                ) {
                    return ExceptionResponses.ClientExceptionResponse();
                }
            }

            // Delete the friend request
            interactor.delete(toDelete);

            return NotificationResponse.HandleFriendRequestResponse(toDelete);
        } catch (Exception e) {
            OuiLogger.log(Level.ERROR, "Unable to handle friend request: " + e);
            return ExceptionResponses.ServerExceptionResponse();
        }
    }
}
