package com.ouieat.implementation;

import com.ouieat.OuiLogger;
import com.ouieat.models.Notification;
import com.ouieat.repository.NotificationRepository;
import com.ouieat.responses.ExceptionResponses;
import com.ouieat.responses.NotificationResponses;
import com.ouieat.responses.Response;
import java.util.ArrayList;
import org.apache.logging.log4j.Level;

public class NotificationImplementation {

    public static Response getNotificationsForUser(
        String userId,
        NotificationRepository notificationRepository
    ) {
        try {
            ArrayList<Notification> notifications = notificationRepository.findNotificationByRecipientId(
                userId
            );
            return NotificationResponses.GetUserNotificationsResponse(
                notifications
            );
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Could not get notifications for user: " + userId
            );
            return ExceptionResponses.UnknownExceptionResponse();
        }
    }
}
