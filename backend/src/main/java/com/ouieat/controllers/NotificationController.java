package com.ouieat.controllers;

import com.ouieat.models.Notification;
import com.ouieat.models.UserCredentials;
import com.ouieat.repository.NotificationRepository;
import com.ouieat.repository.UserRepository;
import com.ouieat.requests.UserRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

    public final NotificationRepository notificationRepository;
    public final UserRepository userRepository;

    @Autowired
    public NotificationController(
        NotificationRepository notificationRepository,
        UserRepository userRepository
    ) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(
        path = "/notifications",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getNotificationsForUser(@RequestParam String userId) {
        return UserRequests.getNotifications(
            this.userRepository,
            this.notificationRepository,
            UserCredentials.fromUserID(userId)
        );
    }

    @PostMapping(
        path = "/createFriendRequest",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String createFriendRequest(
        @RequestBody Notification friendRequestNotification
    ) {
        return UserRequests.createFriendRequest(
            this.userRepository,
            this.notificationRepository,
            UserCredentials.fromUserID(friendRequestNotification.getSenderId()),
            friendRequestNotification
        );
    }

    @PostMapping(
        path = "/acceptFriendRequest",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String handleFriendRequest(
        @RequestBody Notification friendRequestNotification
    ) {
        return UserRequests.handleFriendRequest(
            this.userRepository,
            this.notificationRepository,
            UserCredentials.fromUserID(
                friendRequestNotification.getRecipientId()
            ),
            friendRequestNotification,
            true
        );
    }

    @PostMapping(
        path = "/declineFriendRequest",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String declineFriendRequest(
        @RequestBody Notification friendRequestNotification
    ) {
        return UserRequests.handleFriendRequest(
            this.userRepository,
            this.notificationRepository,
            UserCredentials.fromUserID(
                friendRequestNotification.getRecipientId()
            ),
            friendRequestNotification,
            false
        );
    }
}
