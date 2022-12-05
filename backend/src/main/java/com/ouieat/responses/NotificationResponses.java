package com.ouieat.responses;

import com.ouieat.models.Notification;
import com.ouieat.responses.models.NotificationsResponseData;
import com.ouieat.responses.models.NullResponseData;
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

    public static Response FriendRequestSentResponse() {
        return new Response(
            "success",
            new NullResponseData(),
            "createFriendRequest",
            "client-get"
        );
    }

    public static Response FriendRequestAcceptedResponse() {
        return new Response(
            "success",
            new NullResponseData(),
            "acceptFriendRequest",
            "client-get"
        );
    }

    public static Response FriendRequestDeclinedResponse() {
        return new Response(
            "success",
            new NullResponseData(),
            "declineFriendRequest",
            "client-get"
        );
    }
}
