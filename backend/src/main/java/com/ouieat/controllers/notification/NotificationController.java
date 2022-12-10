package com.ouieat.controllers.notification;

import com.ouieat.OuiLogger;
import com.ouieat.controllers.handler.Controller;
import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.notification.Notification;
import com.ouieat.models.notification.NotificationCreator;
import com.ouieat.requests.notification.AuthenticatedNotificationRequest;
import com.ouieat.requests.notification.UnauthenticatedNotificationRequest;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController
    extends Controller<NotificationInteractor, AuthenticatedNotificationRequest, UnauthenticatedNotificationRequest> {

    @Autowired
    public NotificationController(
        UserInteractor userInteractor,
        NotificationInteractor notificationInteractor,
        UnauthenticatedNotificationRequest unauthenticatedNotificationRequest,
        AuthenticatedNotificationRequest authenticatedNotificationRequest
    ) {
        super(
            userInteractor,
            notificationInteractor,
            authenticatedNotificationRequest,
            unauthenticatedNotificationRequest
        );
    }

    @GetMapping(value = "/notifications", produces = "application/json")
    public String getNotificationsForUser(@RequestParam String userId) {
        return authenticatedRequest
            .handle(interactor, userId, authenticatedRequest.getNotifications)
            .getJsonString();
    }

    @PostMapping(
        value = "/createFriendRequest",
        consumes = "application/json",
        produces = "application/json"
    )
    public String createFriendRequest(
        @RequestBody NotificationCreator friendRequestNotification
    ) {
        OuiLogger.log(
            Level.INFO,
            "Creating friend request notification: " +
            friendRequestNotification.getSenderId()
        );
        return authenticatedRequest
            .handle(
                interactor,
                friendRequestNotification.getSenderId(),
                friendRequestNotification,
                authenticatedRequest.createFriendRequest
            )
            .getJsonString();
    }

    @PostMapping(
        value = "/acceptFriendRequest",
        consumes = "application/json",
        produces = "application/json"
    )
    public String handleFriendRequest(
        @RequestBody Notification friendRequestNotification
    ) {
        return authenticatedRequest
            .handle(
                interactor,
                friendRequestNotification.getRecipientId(),
                friendRequestNotification,
                true,
                authenticatedRequest.handleFriendRequest
            )
            .getJsonString();
    }

    @PostMapping(
        value = "/declineFriendRequest",
        consumes = "application/json",
        produces = "application/json"
    )
    public String declineFriendRequest(
        @RequestBody Notification friendRequestNotification
    ) {
        return authenticatedRequest
            .handle(
                interactor,
                friendRequestNotification.getRecipientId(),
                friendRequestNotification,
                false,
                authenticatedRequest.handleFriendRequest
            )
            .getJsonString();
    }
}
