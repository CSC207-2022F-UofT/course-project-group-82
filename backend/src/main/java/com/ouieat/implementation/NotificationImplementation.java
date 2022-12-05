package com.ouieat.implementation;

import com.ouieat.OuiLogger;
import com.ouieat.models.Notification;
import com.ouieat.models.User;
import com.ouieat.repository.NotificationRepository;
import com.ouieat.repository.UserRepository;
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

    public static Response createFriendRequest(
        User senderUser,
        UserRepository userRepository,
        NotificationRepository notificationRepository,
        Notification friendRequestNotification
    ) {
        try {
            if (
                senderUser
                    .getFriendIds()
                    .contains(friendRequestNotification.getRecipientId())
            ) {
                OuiLogger.log(
                    Level.ERROR,
                    "User already friends with recipient error: " +
                    senderUser.getUsername()
                );
                return ExceptionResponses.ClientExceptionResponse();
            }
            ArrayList<Notification> notifications = notificationRepository.findNotificationByRecipientId(
                friendRequestNotification.getRecipientId()
            );
            for (Notification notification : notifications) {
                if (
                    notification.getSenderId().equals(senderUser.getId()) &&
                    notification.getType().equals("create-friend-request")
                ) {
                    OuiLogger.log(
                        Level.ERROR,
                        "User already sent friend request error: " +
                        senderUser.getUsername()
                    );
                    return ExceptionResponses.ClientExceptionResponse();
                }
            }
            notificationRepository.save(friendRequestNotification);
            return NotificationResponses.FriendRequestSentResponse();
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Could not create friend request for user: " +
                senderUser.getUsername()
            );
            return ExceptionResponses.UnknownExceptionResponse();
        }
    }

    public static Response handleFriendRequest(
        User recipientUser,
        UserRepository userRepository,
        NotificationRepository notificationRepository,
        Notification friendRequestNotification,
        boolean accept
    ) {
        try {
            if (accept) {
                recipientUser
                    .getFriendIds()
                    .add(friendRequestNotification.getSenderId());
                ArrayList<User> senderUser = userRepository.findUserById(
                    friendRequestNotification.getSenderId()
                );
                if (senderUser.size() == 0) {
                    OuiLogger.log(
                        Level.ERROR,
                        "Could not find sender user for friend request: " +
                        friendRequestNotification.getSenderId()
                    );
                    return ExceptionResponses.UnknownExceptionResponse();
                }
                senderUser.get(0).getFriendIds().add(recipientUser.getId());
                userRepository.save(recipientUser);
                userRepository.save(senderUser.get(0));
                notificationRepository.delete(friendRequestNotification);
                return NotificationResponses.FriendRequestAcceptedResponse();
            } else {
                notificationRepository.delete(friendRequestNotification);
                return NotificationResponses.FriendRequestDeclinedResponse();
            }
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Could not handle friend request for user: " +
                recipientUser.getUsername()
            );
            return ExceptionResponses.UnknownExceptionResponse();
        }
    }
}
