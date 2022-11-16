package com.ouieat.implementation;

import com.ouieat.OuiLogger;
import com.ouieat.models.User;
import com.ouieat.repository.UserRepository;
import com.ouieat.responses.Response;
import com.ouieat.responses.UserResponses;
import java.util.ArrayList;
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
                return UserResponses.RegistrationResponse(
                    "failure",
                    "Username already exists"
                );
            }
            preExisting.addAll(
                userRepository.findUserByEmail(newUser.getEmail())
            );
            if (preExisting.size() > 0) {
                return UserResponses.RegistrationResponse(
                    "failure",
                    "Email already exists"
                );
            }
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Could not check uniqueness of user: " + newUser.getUsername()
            );
            OuiLogger.log(Level.ERROR, e.getMessage());
            return UserResponses.RegistrationResponse(
                "failure",
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
                return UserResponses.LoginResponse(null, "failure");
            } else if (found.size() == 1) {
                OuiLogger.log(
                    Level.INFO,
                    "Successfully found matching user for username: " + username
                );
                return UserResponses.LoginResponse(
                    found.get(0).getId(),
                    "success"
                );
            } else {
                OuiLogger.log(
                    Level.INFO,
                    "No matching users found for username: " +
                    username +
                    " with given credentials"
                );
                return UserResponses.LoginResponse(null, "failure");
            }
        } catch (Exception e) {
            OuiLogger.log(Level.ERROR, "Error logging in user : " + username);
            OuiLogger.log(Level.ERROR, e.getMessage());
            return UserResponses.LoginResponse(null, "failure");
        }
    }
}
