package com.ouieat.implementation.notification.methods;

import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.models.user.User;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.notification.NotificationResponse;

public interface GetNotifications {
    static Response apply(
        NotificationInteractor interactor,
        User loggedInUser
    ) {
        try {
            return NotificationResponse.GetNotificationsResponse(
                interactor.getNotifications(loggedInUser.getId())
            );
        } catch (Exception e) {
            return ExceptionResponses.ServerExceptionResponse();
        }
    }
}
