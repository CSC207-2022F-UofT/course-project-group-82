package com.ouieat.interactor.notification;

import com.ouieat.interactor.handler.Interactor;
import com.ouieat.models.notification.Notification;
import com.ouieat.repository.notification.NotificationRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationInteractor
    extends Interactor<Notification, NotificationRepository> {

    @Autowired
    public NotificationInteractor(
        NotificationRepository notificationRepository
    ) {
        super(notificationRepository);
    }

    // Get Notifications for a recipientID
    public ArrayList<Notification> getNotifications(String recipientID) {
        return repository.findNotificationByRecipientId(recipientID);
    }
}
