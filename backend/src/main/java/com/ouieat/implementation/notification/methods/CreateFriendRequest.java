package com.ouieat.implementation.notification.methods;

import com.ouieat.OuiLogger;
import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.models.notification.Notification;
import com.ouieat.models.notification.NotificationCreator;
import com.ouieat.models.user.User;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.notification.NotificationResponse;
import org.apache.logging.log4j.Level;

public interface CreateFriendRequest {
    static Response apply(
        NotificationInteractor interactor,
        User loggedInUser,
        NotificationCreator toCreate
    ) {
        try {
            // Check if they are already friends
            if (
                loggedInUser.getFriendIds().contains(toCreate.getRecipientId())
            ) {
                return ExceptionResponses.UserErrorResponse("Already friends");
            }

            // Check if they have already sent a friend request
            if (
                interactor
                    .getNotifications(loggedInUser.getId())
                    .stream()
                    .anyMatch(notification ->
                        notification
                            .getRecipientId()
                            .equals(toCreate.getRecipientId())
                    )
            ) {
                return ExceptionResponses.UserErrorResponse("Sender already sent a friend request");
            }
            if (
                interactor
                    .getNotifications(toCreate.getRecipientId())
                    .stream()
                    .anyMatch(notification ->
                        notification.getSenderId().equals(loggedInUser.getId())
                    )
            ) {
                return ExceptionResponses.UserErrorResponse("Recipient already sent a friend request");
            }

            Notification notification = new Notification(
                toCreate.getSenderName(),
                toCreate.getSenderId(),
                toCreate.getSenderProfilePictureLink(),
                toCreate.getRecipientId(),
                toCreate.getRecipientName(),
                "create-friend-request"
            );
            interactor.save(notification);
            return NotificationResponse.CreateFriendRequestResponse(
                notification
            );
        } catch (Exception e) {
            OuiLogger.log(Level.ERROR, "Unable to create friend request: " + e);
            return ExceptionResponses.ServerExceptionResponse();
        }
    }
}
