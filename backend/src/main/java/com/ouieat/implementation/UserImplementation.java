package com.ouieat.implementation;

import com.ouieat.OuiLogger;
import com.ouieat.models.*;
import com.ouieat.repository.NotificationRepository;
import com.ouieat.repository.UserRepository;
import com.ouieat.responses.ExceptionResponses;
import com.ouieat.responses.Response;
import com.ouieat.responses.UserResponses;
import com.ouieat.responses.models.ErrorResponseData;
import java.util.ArrayList;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;

@Service
public class UserImplementation {

    /*
     * Take in a UserRepository object userRepository and a User object newUser.
     * Saves the newUser to the User model using the userRepository
     */
    public static Response saveNewUser(
        UserRepository userRepository,
        User newUser
    ) {
        /* Before we try and save the user:
         * Ensure the username doesn't already exist
         * Ensure the email doesn't already exist
         */
        try {
            ArrayList<User> preExisting = userRepository.findUserByUsername(
                newUser.getUsername()
            );
            if (preExisting.size() > 0) {
                return ExceptionResponses.UserErrorResponse(
                    "Username already exists"
                );
            }
            preExisting.addAll(
                userRepository.findUserByEmail(newUser.getEmail())
            );
            if (preExisting.size() > 0) {
                return ExceptionResponses.UserErrorResponse(
                    "Email is already in use"
                );
            }
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Could not check uniqueness of user: " + newUser.getUsername()
            );
            OuiLogger.log(Level.ERROR, e.getMessage());
            return ExceptionResponses.UserErrorResponse(
                "Error validating information"
            );
        }

        /*
         * Now we can register the user successfully
         * If anything goes wrong here, return a failure response
         */
        try {
            userRepository.save(newUser);
            OuiLogger.log(
                Level.INFO,
                "Successfully saved the new user: " + newUser.getUsername()
            );
            return UserResponses.RegistrationResponse(
                "success",
                newUser.getUsername()
            );
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Failed to save the new user: " + newUser.getUsername()
            );
            OuiLogger.log(Level.ERROR, e.getMessage());
            return UserResponses.RegistrationResponse(
                "failure",
                "Error while saving the user"
            );
        }
    }

    /*
     * Take in a UserRepository object userRepository and a String username and String password
     * Try and find a user with those credentials
     * return the found user's id
     * or return null if a user is not found
     */
    public static Response loginUser(
        UserRepository userRepository,
        String username,
        String password
    ) {
        try {
            ArrayList<User> found = userRepository.findUserByUsernameAndPassword(
                username,
                password
            );

            if (found.size() > 1) {
                OuiLogger.log(
                    Level.ERROR,
                    "More than one matched results for username: " + username
                );
                return ExceptionResponses.UserErrorResponse(
                    "More than one user with this name exists"
                );
            } else if (found.size() == 1) {
                OuiLogger.log(
                    Level.INFO,
                    "Successfully found matching user for username: " + username
                );
                return UserResponses.LoginResponse(found.get(0).getId());
            } else {
                return ExceptionResponses.UserErrorResponse(
                    "No matching users found for username: " +
                    username +
                    " with given credentials"
                );
            }
        } catch (Exception e) {
            OuiLogger.log(Level.ERROR, "Error logging in user : " + username);
            OuiLogger.log(Level.ERROR, e.getMessage());
            return ExceptionResponses.UserErrorResponse(
                "Error logging in user : " + username
            );
        }
    }

    public static Response updateUserDetails(
        UserRepository userRepository,
        User currentUser,
        UpdateUser updateUser
    ) {
        try {
            if (updateUser.email != null && updateUser.email.length() > 0) {
                currentUser.setEmail(updateUser.email);
            }
            if (
                updateUser.firstName != null &&
                updateUser.firstName.length() > 0
            ) {
                currentUser.setFirstName(updateUser.firstName);
            }
            if (
                updateUser.lastName != null && updateUser.lastName.length() > 0
            ) {
                currentUser.setLastName(updateUser.lastName);
            }
            if (
                updateUser.username != null && updateUser.username.length() > 0
            ) {
                currentUser.setUsername(updateUser.username);
            }
            if (
                updateUser.profilePictureLink != null &&
                updateUser.profilePictureLink.length() > 0
            ) {
                currentUser.setProfilePictureLink(
                    updateUser.profilePictureLink
                );
            }
            userRepository.save(currentUser);
            OuiLogger.log(
                Level.INFO,
                "Successfully updated the user: " + updateUser.getUsername()
            );
            return UserResponses.UpdateUserDetailsResponse(currentUser);
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Failed to update the user: " + updateUser.getUsername()
            );
            OuiLogger.log(Level.ERROR, e.getMessage());
            return UserResponses.RegistrationResponse(
                "failure",
                "Error while updating the user"
            );
        }
    }

    public static Response getUsersByUsername(
        UserRepository userRepository,
        NotificationRepository notificationRepository,
        String username,
        String userId
    ) {
        try {
            ArrayList<User> users = new ArrayList<>(userRepository.findAll());
            ArrayList<Notification> notifications = new ArrayList<>(
                notificationRepository.findAll()
            );
            ArrayList<UserPreview> filteredUsers = new ArrayList<>();
            for (User u : users) {
                if (
                    !u.getId().equals(userId) &&
                    u
                        .getUsername()
                        .toLowerCase()
                        .contains(username.toLowerCase()) &&
                    !u.getFriendIds().contains(userId)
                ) {
                    filteredUsers.add(new UserPreview(u));
                }
            }
            ArrayList<UserPreview> shallowClonedUsers = new ArrayList<>(
                filteredUsers
            );
            // Get rid of the users that already have friend requests sent
            for (Notification n : notifications) {
                if (n.getSenderId().equals(userId)) {
                    for (UserPreview u : shallowClonedUsers) {
                        if (u.getId().equals(n.getRecipientId())) {
                            filteredUsers.remove(u);
                        }
                    }
                }
            }

            OuiLogger.log(
                Level.INFO,
                "Successfully found users: " +
                filteredUsers.size() +
                " for username: " +
                username
            );
            return UserResponses.GetUsersByUsernameResponse(filteredUsers);
        } catch (Exception e) {
            OuiLogger.log(Level.ERROR, "Failed to get users by username");
            OuiLogger.log(Level.ERROR, e.getMessage());
            return ExceptionResponses.UserErrorResponse(
                "Error while getting users by username"
            );
        }
    }
}
