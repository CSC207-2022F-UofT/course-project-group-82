package com.ouieat.controllers;

import com.ouieat.models.UserCredentials;
import com.ouieat.repository.NotificationRepository;
import com.ouieat.repository.UserRepository;
import com.ouieat.requests.UserRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
