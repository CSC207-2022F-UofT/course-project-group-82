package com.ouieat.requests.notification;

import com.ouieat.implementation.notification.NotificationImplementation;
import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.notification.Notification;
import com.ouieat.models.notification.NotificationCreator;
import com.ouieat.models.user.User;
import com.ouieat.requests.handler.AuthenticatedRequest;
import com.ouieat.requests.handler.FunctionalInterfaces;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedNotificationRequest
    extends AuthenticatedRequest<NotificationInteractor> {

    public FunctionalInterfaces.Function2<NotificationInteractor, User, Response> getNotifications =
        NotificationImplementation::getNotifications;

    public FunctionalInterfaces.Function3<NotificationInteractor, User, NotificationCreator, Response> createFriendRequest = (
        NotificationInteractor interactor,
        User loggedInUser,
        NotificationCreator notification
    ) -> {
        if (notification == null || notification.getRecipientId() == null) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }

        return NotificationImplementation.createFriendRequest(
            interactor,
            loggedInUser,
            notification
        );
    };

    public FunctionalInterfaces.Function4<NotificationInteractor, User, Notification, Boolean, Response> handleFriendRequest = (
        NotificationInteractor interactor,
        User loggedInUser,
        Notification notification,
        Boolean accept
    ) -> {
        if (notification.getRecipientId() == null) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }

        return NotificationImplementation.handleFriendRequest(
            interactor,
            loggedInUser,
            notification,
            accept
        );
    };

    @Autowired
    public AuthenticatedNotificationRequest(UserInteractor userInteractor){
        super(userInteractor);
    }
}
