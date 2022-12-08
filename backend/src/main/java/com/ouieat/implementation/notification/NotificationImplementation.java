package com.ouieat.implementation.notification;

import com.ouieat.implementation.notification.methods.CreateFriendRequest;
import com.ouieat.implementation.notification.methods.GetNotifications;
import com.ouieat.implementation.notification.methods.HandleFriendRequest;
import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.models.notification.Notification;
import com.ouieat.models.notification.NotificationCreator;
import com.ouieat.models.user.User;
import com.ouieat.responses.handler.Response;
import org.springframework.stereotype.Service;

@Service
public class NotificationImplementation {

    public static Response getNotifications(
        NotificationInteractor interactor,
        User loggedInUser
    ) {
        return GetNotifications.apply(interactor, loggedInUser);
    }

    public static Response createFriendRequest(
        NotificationInteractor interactor,
        User loggedInUser,
        NotificationCreator toCreate
    ) {
        return CreateFriendRequest.apply(interactor, loggedInUser, toCreate);
    }

    public static Response handleFriendRequest(
        NotificationInteractor interactor,
        User loggedInUser,
        Notification toUpdate,
        Boolean accept
    ) {
        return HandleFriendRequest.apply(
            interactor,
            loggedInUser,
            toUpdate,
            accept
        );
    }
}
