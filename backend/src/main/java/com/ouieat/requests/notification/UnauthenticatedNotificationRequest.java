package com.ouieat.requests.notification;

import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.requests.handler.UnauthenticatedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UnauthenticatedNotificationRequest
    extends UnauthenticatedRequest<NotificationInteractor> {

    @Autowired
    public UnauthenticatedNotificationRequest(
        NotificationInteractor interactor
    ) {
        super(interactor);
    }
}
