package com.ouieat.requests.notification;

import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.requests.handler.UnauthenticatedRequest;
import org.springframework.stereotype.Component;

@Component
public class UnauthenticatedNotificationRequest
    extends UnauthenticatedRequest<NotificationInteractor> {}
