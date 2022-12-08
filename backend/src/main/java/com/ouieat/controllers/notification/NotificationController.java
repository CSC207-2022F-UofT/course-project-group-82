package com.ouieat.controllers.notification;

import com.ouieat.OuiLogger;
import com.ouieat.controllers.Controller;
import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.models.notification.Notification;
import com.ouieat.models.notification.NotificationCreator;
import com.ouieat.requests.notification.AuthenticatedNotificationRequest;
import com.ouieat.requests.notification.UnauthenticatedNotificationRequest;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController extends Controller<NotificationInteractor> {

    private final UnauthenticatedNotificationRequest unauthenticatedNotificationRequest;
    private final AuthenticatedNotificationRequest authenticatedNotificationRequest;

    @Autowired
    public NotificationController(
        NotificationInteractor notificationInteractor,
        UnauthenticatedNotificationRequest unauthenticatedNotificationRequest,
        AuthenticatedNotificationRequest authenticatedNotificationRequest
    ) {
        super(notificationInteractor);
        this.unauthenticatedNotificationRequest =
            unauthenticatedNotificationRequest;
        this.authenticatedNotificationRequest =
            authenticatedNotificationRequest;
    }

    @GetMapping(value = "/notifications", produces = "application/json")
    public String getNotificationsForUser(@RequestParam String userId) {
        return authenticatedNotificationRequest
            .handle(
                interactor,
                userId,
                authenticatedNotificationRequest.getNotifications
            )
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
        return authenticatedNotificationRequest
            .handle(
                interactor,
                friendRequestNotification.getSenderId(),
                friendRequestNotification,
                authenticatedNotificationRequest.createFriendRequest
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
        return authenticatedNotificationRequest
            .handle(
                interactor,
                friendRequestNotification.getRecipientId(),
                friendRequestNotification,
                true,
                authenticatedNotificationRequest.handleFriendRequest
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
        return authenticatedNotificationRequest
            .handle(
                interactor,
                friendRequestNotification.getRecipientId(),
                friendRequestNotification,
                false,
                authenticatedNotificationRequest.handleFriendRequest
            )
            .getJsonString();
    }

    public UnauthenticatedNotificationRequest getUnauthenticatedNotificationRequest() {
        return unauthenticatedNotificationRequest;
    }

    public AuthenticatedNotificationRequest getAuthenticatedNotificationRequest() {
        return authenticatedNotificationRequest;
    }
}
