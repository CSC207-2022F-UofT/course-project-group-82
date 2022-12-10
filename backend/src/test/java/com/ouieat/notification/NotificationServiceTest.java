package com.ouieat.notification;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ouieat.OuiLogger;
import com.ouieat.handler.ServiceTest;
import com.ouieat.implementation.notification.NotificationImplementation;
import com.ouieat.interactor.notification.NotificationInteractor;
import com.ouieat.models.notification.Notification;
import com.ouieat.models.notification.NotificationCreator;
import com.ouieat.requests.notification.AuthenticatedNotificationRequest;
import com.ouieat.requests.notification.UnauthenticatedNotificationRequest;
import com.ouieat.responses.handler.Response;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NotificationServiceTest
    extends ServiceTest<NotificationInteractor, AuthenticatedNotificationRequest, UnauthenticatedNotificationRequest> {

    public NotificationServiceTest() {
        super(
            NotificationInteractor.class,
            AuthenticatedNotificationRequest.class,
            UnauthenticatedNotificationRequest.class,
            NotificationImplementation.class
        );
        OuiLogger.log(Level.DEBUG, "Testing Notification Service Requests");
    }

    // Route: /notifications

    @Test
    public void getNotificationsTestInvalidID() {
        Response response = authenticatedRequest.handle(
            interactor,
            "INVALID_ID",
            authenticatedRequest.getNotifications
        );
        assertThat(response.status).isEqualTo("error");
    }

    @Test
    public void getNotificationsTestSuccess() {
        Response response = authenticatedRequest.handle(
            interactor,
            getTestUserDefault().getId(),
            authenticatedRequest.getNotifications
        );
        assertThat(response.status).isEqualTo("success");
    }

    // Route: /createFriendRequest

    @Test
    public void createFriendRequestTestNullNotification() {
        Response response = authenticatedRequest.handle(
            interactor,
            getTestUserDefault().getId(),
            null,
            authenticatedRequest.createFriendRequest
        );
        assertThat(response.status).isEqualTo("error");
    }

    @Test
    public void createFriendRequestTestSuccess() {
        NotificationCreator creator = new NotificationCreator(
            "test",
            "ZZZZZZZZZ",
            "test",
            "YYYYYYYYYYYYY",
            "test",
            "create-friend-request"
        );
        when(interactor.save(any(Notification.class)))
            .thenAnswer(i -> i.getArguments()[0]);
        when(interactor.getNotifications(any(String.class)))
            .thenAnswer(i -> new ArrayList<Notification>());
        Response response = authenticatedRequest.handle(
            interactor,
            getTestUserDefault().getId(),
            creator,
            authenticatedRequest.createFriendRequest
        );
        OuiLogger.log(Level.DEBUG, response.getJsonString());
        assertThat(response.status).isEqualTo("success");
    }

    // Route: /acceptFriendRequest

    @Test
    public void acceptFriendRequestTestNullNotification() {
        Notification creator = new Notification(
            "test",
            "test",
            "test",
            "test",
            "test",
            "create-friend-request"
        );
        Response response = authenticatedRequest.handle(
            interactor,
            getTestUserDefault().getId(),
            creator,
            true,
            authenticatedRequest.handleFriendRequest
        );
        assertThat(response.status).isEqualTo("error");
    }

    @Test
    public void acceptFriendRequestTestSuccess() {
        NotificationCreator creator = new NotificationCreator(
            "test",
            "ZZZZZZZZZ",
            "test",
            "YYYYYYYYYY",
            "test",
            "create-friend-request"
        );
        when(interactor.save(any(Notification.class)))
            .thenAnswer(i -> i.getArguments()[0]);
        Response response = authenticatedRequest.handle(
            interactor,
            getTestUserDefault().getId(),
            creator,
            authenticatedRequest.createFriendRequest
        );
        assertThat(response.status).isEqualTo("success");
        when(interactor.getNotifications(any(String.class)))
            .thenAnswer(i ->
                new ArrayList<Notification>(
                    List.of(
                        new Notification(
                            creator.getSenderName(),
                            creator.getSenderId(),
                            creator.getSenderProfilePictureLink(),
                            creator.getRecipientId(),
                            creator.getRecipientName(),
                            creator.getType()
                        )
                    )
                )
            );
        when(userInteractor.findById(creator.getRecipientId()))
            .thenReturn(getTestUserSeeded(Math.random()));
        Notification notification = (Notification) response.responseData.data;
        response =
            authenticatedRequest.handle(
                interactor,
                getTestUserDefault().getId(),
                notification,
                true,
                authenticatedRequest.handleFriendRequest
            );
        assertThat(response.status).isEqualTo("success");
    }
}
