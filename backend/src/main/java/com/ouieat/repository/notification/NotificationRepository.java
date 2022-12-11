package com.ouieat.repository.notification;

import com.ouieat.models.notification.Notification;
import com.ouieat.repository.handler.Repository;
import java.util.ArrayList;
import org.springframework.data.mongodb.repository.Query;

public interface NotificationRepository extends Repository<Notification> {
    @Query("{recipientId: '?0'}")
    ArrayList<Notification> findNotificationByRecipientId(String id);
}
