package com.ouieat.responses.models;

import com.ouieat.models.Notification;
import com.ouieat.responses.ResponseData;
import java.util.ArrayList;

public class NotificationsResponseData extends ResponseData {

    public ArrayList<Notification> notifications;

    public NotificationsResponseData(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }
}
