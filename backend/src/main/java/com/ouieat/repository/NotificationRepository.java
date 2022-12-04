package com.ouieat.repository;

import com.ouieat.models.Notification;
import java.util.ArrayList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NotificationRepository
    extends MongoRepository<Notification, String> {
    @Query("{recipientId: '?0'}")
    ArrayList<Notification> findNotificationByRecipientId(String id);
}
