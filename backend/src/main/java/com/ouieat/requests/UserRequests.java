package com.ouieat.requests;

import com.ouieat.implementation.NotificationImplementation;
import com.ouieat.implementation.UserImplementation;
import com.ouieat.models.UpdateUser;
import com.ouieat.models.User;
import com.ouieat.models.UserCredentials;
import com.ouieat.models.UserLogin;
import com.ouieat.repository.NotificationRepository;
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
                        username,
                        user.getUsername()
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
}
