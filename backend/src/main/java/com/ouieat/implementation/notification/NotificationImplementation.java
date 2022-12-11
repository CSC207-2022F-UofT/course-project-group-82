package com.ouieat.implementation.notification;

import com.ouieat.implementation.handler.Implementation;
import com.ouieat.implementation.notification.methods.CreateFriendRequest;
import com.ouieat.implementation.notification.methods.GetNotifications;
import com.ouieat.implementation.notification.methods.HandleFriendRequest;
import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.notification.Notification;
import com.ouieat.models.notification.NotificationCreator;
import com.ouieat.models.user.User;
import com.ouieat.responses.handler.Response;
import org.springframework.stereotype.Service;

@Service
public class NotificationImplementation
    extends Implementation<NotificationInteractor> {

    public NotificationImplementation(
        UserInteractor userInteractor,
        NotificationInteractor interactor
    ) {
        super(userInteractor, interactor);
    }

    public Response getNotifications(User loggedInUser) {
        return GetNotifications.apply(interactor, loggedInUser);
    }

    public Response createFriendRequest(
        User loggedInUser,
        NotificationCreator toCreate
    ) {
        return CreateFriendRequest.apply(interactor, loggedInUser, toCreate);
    }

    public Response handleFriendRequest(
        User loggedInUser,
        Notification toUpdate,
        Boolean accept
    ) {
        return HandleFriendRequest.apply(
            userInteractor,
            interactor,
            loggedInUser,
            toUpdate,
            accept
        );
    }
}
