package com.ouieat.requests;

import com.ouieat.implementation.NotificationImplementation;
import com.ouieat.implementation.RecommendationImplementation;
import com.ouieat.implementation.UserImplementation;
import com.ouieat.models.*;
import com.ouieat.repository.NotificationRepository;
import com.ouieat.repository.RecommendationRepository;
import com.ouieat.repository.UserRepository;
import com.ouieat.responses.ExceptionResponses;
import com.ouieat.responses.Response;
import com.ouieat.responses.UserResponses;
import java.util.Optional;

interface UserActionHandler {
    Response onUserValidated(User user);
}

public class UserRequests {

    private static String doUserAction(
        UserRepository userRepository,
        UserCredentials cred,
        UserActionHandler handler
    ) {
        Optional<User> user = userRepository.findById(cred.getUserID());

        if (user.isPresent()) {
            return handler.onUserValidated(user.get()).getJsonString();
        }

        return ExceptionResponses
            .InvalidUserCredentialsResponse()
            .getJsonString();
    }

    public static String doRegister(
        UserRepository userRepository,
        User newUser
    ) {
        // Validate that newUser has all required properties, and they are not empty
        if (newUser == null) return ExceptionResponses
            .MissingRequestParametersResponse()
            .getJsonString();

        // Ensure no values are empty when being sent
        if (
            newUser.getUsername().length() == 0 ||
            newUser.getPassword().length() == 0 ||
            newUser.getFirstName().length() == 0 ||
            newUser.getLastName().length() == 0 ||
            newUser.getEmail().length() == 0
        ) return ExceptionResponses
            .MissingRequestParametersResponse()
            .getJsonString();

        Response response = UserImplementation.saveNewUser(
            userRepository,
            newUser
        );
        return response.getJsonString();
    }

    public static String doLogin(
        UserRepository userRepository,
        UserLogin userLogin
    ) {
        if (userLogin == null) return ExceptionResponses
            .MissingRequestParametersResponse()
            .getJsonString();

        if (
            userLogin.getPassword().length() == 0 ||
            userLogin.getUsername().length() == 0
        ) return ExceptionResponses
            .MissingRequestParametersResponse()
            .getJsonString();

        Response response = UserImplementation.loginUser(
            userRepository,
            userLogin.getUsername(),
            userLogin.getPassword()
        );
        return response.getJsonString();
    }

    public static String getUsersByUsername(
        UserRepository userRepository,
        NotificationRepository notificationRepository,
        UserCredentials userCredentials,
        String username
    ) {
        return UserRequests.doUserAction(
            userRepository,
            userCredentials,
            new UserActionHandler() {
                @Override
                public Response onUserValidated(User user) {
                    return UserImplementation.getUsersByUsername(
                        userRepository,
                        notificationRepository,
                        username,
                        user.getId()
                    );
                }
            }
        );
    }

    public static String getDashboard(
        UserRepository userRepository,
        UserCredentials credentials
    ) {
        return UserRequests.doUserAction(
            userRepository,
            credentials,
            new UserActionHandler() {
                @Override
                public Response onUserValidated(User user) {
                    return UserResponses.DashboardResponse(user);
                }
            }
        );
    }

    public static String getNotifications(
        UserRepository userRepository,
        NotificationRepository notificationRepository,
        UserCredentials credentials
    ) {
        return UserRequests.doUserAction(
            userRepository,
            credentials,
            new UserActionHandler() {
                @Override
                public Response onUserValidated(User user) {
                    return NotificationImplementation.getNotificationsForUser(
                        user.getId(),
                        notificationRepository
                    );
                }
            }
        );
    }

    public static String createFriendRequest(
        UserRepository userRepository,
        NotificationRepository notificationRepository,
        UserCredentials credentials,
        Notification friendRequestNotification
    ) {
        return UserRequests.doUserAction(
            userRepository,
            credentials,
            new UserActionHandler() {
                @Override
                public Response onUserValidated(User user) {
                    return NotificationImplementation.createFriendRequest(
                        user,
                        userRepository,
                        notificationRepository,
                        friendRequestNotification
                    );
                }
            }
        );
    }

    public static String handleFriendRequest(
        UserRepository userRepository,
        NotificationRepository notificationRepository,
        UserCredentials credentials,
        Notification friendRequestNotification,
        boolean accept
    ) {
        return UserRequests.doUserAction(
            userRepository,
            credentials,
            new UserActionHandler() {
                @Override
                public Response onUserValidated(User user) {
                    return NotificationImplementation.handleFriendRequest(
                        user,
                        userRepository,
                        notificationRepository,
                        friendRequestNotification,
                        accept
                    );
                }
            }
        );
    }

    public static String getUserDetails(
        UserRepository userRepository,
        UserCredentials credentials
    ) {
        return UserRequests.doUserAction(
            userRepository,
            credentials,
            new UserActionHandler() {
                @Override
                public Response onUserValidated(User user) {
                    return UserResponses.UserDetailsResponse(user);
                }
            }
        );
    }

    public static String updateUserDetails(
        UserRepository userRepository,
        UserCredentials userCredentials,
        UpdateUser updateUser
    ) {
        return UserRequests.doUserAction(
            userRepository,
            userCredentials,
            new UserActionHandler() {
                @Override
                public Response onUserValidated(User user) {
                    return UserImplementation.updateUserDetails(
                        userRepository,
                        user,
                        updateUser
                    );
                }
            }
        );
    }

    public static String getFriends(
        UserRepository userRepository,
        UserCredentials userCredentials
    ) {
        return UserRequests.doUserAction(
            userRepository,
            userCredentials,
            new UserActionHandler() {
                @Override
                public Response onUserValidated(User user) {
                    return UserImplementation.getFriends(userRepository, user);
                }
            }
        );
    }

    public static String removeFriendFromUsers(
        UserRepository userRepository,
        UserCredentials userCredentials,
        String friendID
    ) {
        return UserRequests.doUserAction(
            userRepository,
            userCredentials,
            new UserActionHandler() {
                @Override
                public Response onUserValidated(User user) {
                    return UserImplementation.removeFriend(
                        userRepository,
                        user,
                        friendID
                    );
                }
            }
        );
    }

    public static String getFriendRecommendations(
        UserRepository userRepository,
        RecommendationRepository recommendationRepository,
        UserCredentials userCredentials
    ) {
        return UserRequests.doUserAction(
            userRepository,
            userCredentials,
            new UserActionHandler() {
                @Override
                public Response onUserValidated(User user) {
                    return RecommendationImplementation.getRestaurantRecommendationsFromFriends(
                        recommendationRepository,
                        user
                    );
                }
            }
        );
    }
}
