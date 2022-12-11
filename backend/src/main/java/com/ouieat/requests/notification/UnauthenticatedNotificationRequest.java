package com.ouieat.requests.notification;

import com.ouieat.implementation.notification.NotificationImplementation;
import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.requests.handler.UnauthenticatedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnauthenticatedNotificationRequest
    extends UnauthenticatedRequest<NotificationInteractor, NotificationImplementation> {

    @Autowired
    public UnauthenticatedNotificationRequest(
        UserInteractor userInteractor,
        NotificationInteractor interactor,
        NotificationImplementation implementation
    ) {
        super(userInteractor, interactor, implementation);
    }
}
