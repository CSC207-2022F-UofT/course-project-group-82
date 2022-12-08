package com.ouieat.responses.notification;

import com.ouieat.models.notification.Notification;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.handler.ResponseData;
import java.util.ArrayList;

public class NotificationResponse {

    public static Response GetNotificationsResponse(
        ArrayList<Notification> notifications
    ) {
        return new Response("success", new ResponseData<>(notifications));
    }

    public static Response CreateFriendRequestResponse(
        Notification notification
    ) {
        return new Response("success", new ResponseData<>(notification));
    }

    public static Response HandleFriendRequestResponse(
        Notification notification
    ) {
        return new Response("success", new ResponseData<>(notification));
    }
}
