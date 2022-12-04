package com.ouieat.responses;

import com.ouieat.models.Notification;
import com.ouieat.responses.models.NotificationsResponseData;
import java.util.ArrayList;

public class NotificationResponses {

    public static Response GetUserNotificationsResponse(
        ArrayList<Notification> notifications
    ) {
        return new Response(
            "success",
            new NotificationsResponseData(notifications),
            "getUserNotifications",
            "client-get"
        );
    }
}
